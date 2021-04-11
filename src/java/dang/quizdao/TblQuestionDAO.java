/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dang.quizdao;

import dang.quizdto.TblAnswerDTO;
import dang.quizdto.TblQuestionDTO;
import dang.unities.DBHelper;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;

/**
 *
 * @author Admin
 */
public class TblQuestionDAO implements Serializable {

//    Tìm kiếm bằng câu hỏi và môn 
    public void searchQuestion(String searchValue, String txtSubjectID, boolean txtStatus, int pageIndex, int pageSize) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = DBHelper.makeConnection();
            String sql = "with x as (select ROW_NUMBER() over (order by id) as r, "
                    + "id, question_content, createDate, subjectID, status "
                    + "From tblQuestion "
                    + "Where  question_content like ? AND status = ? AND subjectID = ?) "
                    + "Select id, question_content, createDate, subjectID, status "
                    + "From x Where r BETWEEN ? AND ?";
            ps = con.prepareStatement(sql);
            ps.setString(1, "%" + searchValue + "%");
            ps.setBoolean(2, txtStatus);
            ps.setString(3, txtSubjectID);
            ps.setInt(4, pageIndex * pageSize - (pageSize - 1));
            ps.setInt(5, pageIndex * pageSize);
            rs = ps.executeQuery();
            while (rs.next()) {
                String id = rs.getString(1);
                String question_content = rs.getString(2);
                String createDate = rs.getString(3);
                String subjectID = rs.getString(4);
                boolean status = rs.getBoolean(5);
                TblAnswerDAO dao = new TblAnswerDAO();
                dao.getAnswer(id);
                List<TblAnswerDTO> answerContent = dao.getListAnswer();
                if (this.listQuestion == null) {
                    this.listQuestion = new ArrayList<>();
                }
                TblQuestionDTO dto = new TblQuestionDTO(id, question_content, createDate, subjectID, status, answerContent);
                this.listQuestion.add(dto);
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }
// Tìm kiếm bằng tên câu hỏi

    public void searchQuestionByName(String searchValue, boolean txtStatus, int pageIndex, int pageSize) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = DBHelper.makeConnection();
            String sql = "with x as (select ROW_NUMBER() over (order by id) as r, "
                    + "id, question_content, createDate, subjectID, status "
                    + "From tblQuestion "
                    + "Where  question_content like ? AND status = ?) "
                    + "Select id, question_content, createDate, subjectID, status "
                    + "From x Where r BETWEEN ? AND ?";
            ps = con.prepareStatement(sql);
            ps.setString(1, "%" + searchValue + "%");
            ps.setBoolean(2, txtStatus);
            ps.setInt(3, pageIndex * pageSize - (pageSize - 1));
            ps.setInt(4, pageIndex * pageSize);
            rs = ps.executeQuery();
            while (rs.next()) {
                String id = rs.getString(1);
                String question_content = rs.getString(2);
                String createDate = rs.getString(3);
                String subjectID = rs.getString(4);
                boolean status = rs.getBoolean(5);
                TblAnswerDAO dao = new TblAnswerDAO();
                dao.getAnswer(id);
                List<TblAnswerDTO> answerContent = dao.getListAnswer();
                if (this.listQuestion == null) {
                    this.listQuestion = new ArrayList<>();
                }
                TblQuestionDTO dto = new TblQuestionDTO(id, question_content, createDate, subjectID, status, answerContent);
                this.listQuestion.add(dto);
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }
//    Tìm kiếm bằng tên môn

    public void searchQuestionBySubject(String txtSubjectID, int pageIndex, int pageSize) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = DBHelper.makeConnection();
            String sql = "with x as (select ROW_NUMBER() over (order by id) as r, "
                    + "id, question_content, createDate, subjectID, status "
                    + "From tblQuestion "
                    + "Where subjectID = ?) "
                    + "Select id, question_content, createDate, subjectID, status "
                    + "From x Where r BETWEEN ? AND ?";
            ps = con.prepareStatement(sql);
            ps.setString(1, txtSubjectID);
            ps.setInt(2, pageIndex * pageSize - (pageSize - 1));
            ps.setInt(3, pageIndex * pageSize);
            rs = ps.executeQuery();
            while (rs.next()) {
                String id = rs.getString(1);
                String question_content = rs.getString(2);
                String createDate = rs.getString(3);
                String subjectID = rs.getString(4);
                boolean status = rs.getBoolean(5);
                TblAnswerDAO dao = new TblAnswerDAO();
                dao.getAnswer(id);
                List<TblAnswerDTO> answerContent = dao.getListAnswer();
                if (this.listQuestion == null) {
                    this.listQuestion = new ArrayList<>();
                }
                TblQuestionDTO dto = new TblQuestionDTO(id, question_content, createDate, subjectID, status, answerContent);
                this.listQuestion.add(dto);
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }
    private List<TblQuestionDTO> listQuestion;

    /**
     * @return the listQuestion
     */
    public List<TblQuestionDTO> getListQuestion() {
        return listQuestion;
    }
//    Tạo câu hỏi

    public boolean createQuestion(String id, String question_content, String subjectID, boolean status, String check) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = DBHelper.makeConnection();
            String sql = "Insert into tblQuestion "
                    + "(id,question_content,createDate,subjectID,status) "
                    + "Values(?,?,CURRENT_TIMESTAMP,?,?)";
            ps = con.prepareStatement(sql);
            ps.setString(1, id);
            ps.setString(2, question_content);
            ps.setString(3, subjectID);
            ps.setBoolean(4, status);
            int row = ps.executeUpdate();
            if (row > 0) {
                return true;
            }
        } finally {
            if (ps != null) {
                ps.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }
//    Tạo Câu trả lời cho câu hỏi

    public boolean createAnswer(String answerID, String questionID, String answerContent, boolean answerCorrect) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = DBHelper.makeConnection();
            String sql = "Insert into tblAnswer "
                    + "(answerID, questionID, answerContent, answerCorrect) "
                    + "Values(?,?,?,?)";
            ps = con.prepareStatement(sql);
            ps.setString(1, answerID);
            ps.setString(2, questionID);
            ps.setString(3, answerContent);
            ps.setBoolean(4, answerCorrect);
            int row = ps.executeUpdate();
            if (row > 0) {
                return true;
            }
        } finally {
            if (ps != null) {
                ps.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }

//    Lấy id lớn nhất bảng question
    public String getQuestionID() throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = DBHelper.makeConnection();
            String sql = "Select id "
                    + "From tblQuestion ";
            ps = con.prepareStatement(sql);
            int max = 0;
            rs = ps.executeQuery();
            while (rs.next()) {
                int id = Integer.parseInt(rs.getString(1));
                if (id > max) {
                    max = id;
                }
            }
            return String.valueOf(++max);
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }
//    Lấy id lớn nhất bảng answer

    public String getAnswerID() throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = DBHelper.makeConnection();
            String sql = "Select answerID "
                    + "From tblAnswer ";
            ps = con.prepareStatement(sql);
            int max = 0;
            rs = ps.executeQuery();
            while (rs.next()) {
                int id = Integer.parseInt(rs.getString(1));
                if (id > max) {
                    max = id;
                }
            }
            return String.valueOf(++max);
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }
//    Đếm số lượng record theo searchValue

    public int countByQuestionContent(String searchValue, boolean status) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = DBHelper.makeConnection();
            String sql = "Select COUNT(id) "
                    + "From tblQuestion "
                    + "Where question_content LIKE ? AND status =?";
            ps = con.prepareStatement(sql);
            ps.setString(1, "%" + searchValue + "%");
            ps.setBoolean(2, status);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return 0;
    }
//    Đếm số lượng record theo subjectID

    public int countBySubjectID(String subjectID) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = DBHelper.makeConnection();
            String sql = "Select COUNT(id) "
                    + "From tblQuestion "
                    + "Where subjectID =?";
            ps = con.prepareStatement(sql);
            ps.setString(1, subjectID);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return 0;
    }
//    Đếm số lượng record theo subjectID và questionConent 

    public int count(String subjectID, String searchValue, boolean status) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = DBHelper.makeConnection();
            String sql = "Select COUNT(id) "
                    + "From tblQuestion "
                    + "Where question_content like ? AND status = ? AND subjectID = ?";
            ps = con.prepareStatement(sql);
            ps.setString(1, "%" + searchValue + "%");
            ps.setBoolean(2, status);
            ps.setString(3, subjectID);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return 0;
    }
//    Lấy tên câu hỏi

    public String getQuestionContent(String questionID) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = DBHelper.makeConnection();
            String sql = "Select question_content "
                    + "From tblQuestion "
                    + "Where id = ?";
            ps = con.prepareStatement(sql);
            ps.setString(1, questionID);
            rs = ps.executeQuery();
            if (rs.next()) {
                String questionContent = rs.getString(1);
                return questionContent;
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return null;
    }
//    Cập nhật câu hỏi ở bảng question

    public boolean updateQuestion(String id, String question_content, String subjectID, boolean status) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = DBHelper.makeConnection();
            String sql = "Update tblQuestion "
                    + "Set question_content = ?, subjectID = ?, status = ? "
                    + "Where id = ?";
            ps = con.prepareStatement(sql);
            ps.setString(1, question_content);
            ps.setString(2, subjectID);
            ps.setBoolean(3, status);
            ps.setString(4, id);
            int row = ps.executeUpdate();
            if (row > 0) {
                return true;
            }
        } finally {
            if (ps != null) {
                ps.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }
//    Cập nhật câu trả lời ở bảng answer

    public boolean updateAnswer(String answerID, String answerContent, boolean answerCorrect) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = DBHelper.makeConnection();
            String sql = "Update tblAnswer "
                    + "Set answerContent = ?, answerCorrect = ? "
                    + "Where answerID = ?";
            ps = con.prepareStatement(sql);
            ps.setString(1, answerContent);
            ps.setBoolean(2, answerCorrect);
            ps.setString(3, answerID);
            int row = ps.executeUpdate();
            if (row > 0) {
                return true;
            }
        } finally {
            if (ps != null) {
                ps.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }
//    Xóa Câu hỏi

    public boolean deleteQuestion(String id) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = DBHelper.makeConnection();
            String sql = "Update tblQuestion "
                    + "Set status = ? "
                    + "Where id = ?";
            ps = con.prepareStatement(sql);
            ps.setBoolean(1, false);
            ps.setString(2, id);
            int row = ps.executeUpdate();
            if (row > 0) {
                return true;
            }
        } finally {
            if (ps != null) {
                ps.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }
// Đếm số lượng câu hỏi 
     public int getCountQuiz(String subjectID) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = DBHelper.makeConnection();
            String sql = "Select COUNT(id) "
                    + "From tblQuestion "
                    + "Where subjectID = ? AND status = ?";
            ps = con.prepareStatement(sql);
            ps.setString(1, subjectID);
            ps.setBoolean(2, true);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return 0;
    }
//    Lấy câu hỏi
    public void getQuestion(String subject) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = DBHelper.makeConnection();
            String sql = "Select Top 10 id, question_content, createDate, subjectID, status "
                    + " From tblQuestion "
                    + " Where subjectID = ? AND status = ? order by NEWID() ";
            ps = con.prepareStatement(sql);
            ps.setString(1, subject);
            ps.setBoolean(2, true);
            rs = ps.executeQuery();
            while (rs.next()) {
                String id = rs.getString(1);
                String question_content = rs.getString(2);
                String createDate = rs.getString(3);
                String subjectID = rs.getString(4);
                boolean status = rs.getBoolean(5);
                TblAnswerDAO dao = new TblAnswerDAO();
                dao.getAnswer(id);
                List<TblAnswerDTO> answerContent = dao.getListAnswer();
                if (this.listQuestion == null) {
                    this.listQuestion = new ArrayList<>();
                }
                TblQuestionDTO dto = new TblQuestionDTO(id, question_content, createDate, subjectID, status, answerContent);
                this.listQuestion.add(dto);
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }
}
