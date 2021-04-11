/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dang.quizdao;

import dang.quizdto.TblTestDTO;
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
public class TblTestDAO implements Serializable {
//    Thêm thông tin bài kiểm tra

    public void saveQuiz(int testID, int mark, String userID, String time, String subjectID) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = DBHelper.makeConnection();
            String sql = "Insert into tblTest "
                    + "(testID, mark, userID, time, dateSubmit, subjectID) "
                    + "Values (?,?,?,?,CURRENT_TIMESTAMP,?)";
            ps = con.prepareStatement(sql);
            ps.setInt(1, testID);
            ps.setInt(2, mark);
            ps.setString(3, userID);
            ps.setString(4, time);
            ps.setString(5, subjectID);
            ps.execute();
        } finally {
            if (ps != null) {
                ps.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }
//    Lấy testId lớn nhất

    public int getTestID() throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = DBHelper.makeConnection();
            String sql = "Select Max(testID)"
                    + "From tblTest";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                int testID = rs.getInt(1);
                return ++testID;
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
        return 1;
    }
//    Lấy thời gian nộp bài

    public String getDateSubmit(int testID) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = DBHelper.makeConnection();
            String sql = "Select dateSubmit "
                    + "From tblTest "
                    + "Where testID = ?";
            ps = con.prepareStatement(sql);
            ps.setInt(1, testID);
            rs = ps.executeQuery();
            if (rs.next()) {
                String dateSubmit = rs.getString(1);
                return dateSubmit;
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
//    Lấy lịch sử bài làm

    public void getHistory(String userID, int pageIndex, int pageSize) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = DBHelper.makeConnection();
            String sql = "With x as (select ROW_NUMBER() over (order by testID) as r, "
                    + "testID, mark, userID, time, dateSubmit, subjectID "
                    + "From tblTest Where userID = ?) "
                    + "Select testID, mark, userID, time, dateSubmit, subjectID "
                    + "From x Where r Between ? AND ?";
            ps = con.prepareStatement(sql);
            ps.setString(1, userID);
            ps.setInt(2, pageIndex * pageSize - (pageSize - 1));
            ps.setInt(3, pageIndex * pageSize);
            rs = ps.executeQuery();
            while (rs.next()) {
                int testID = rs.getInt(1);
                int mark = rs.getInt(2);
                String time = rs.getString(4);
                String dateSubmit = rs.getString(5);
                String subjectID = rs.getString(6);
                TblSubjectDAO subjectDAO = new TblSubjectDAO();
                String subjectName = subjectDAO.getSubjectName(subjectID);
                TblTestDTO dto = new TblTestDTO(testID, mark, userID, time, dateSubmit, subjectName);
                if (this.list == null) {
                    this.list = new ArrayList<>();
                }
                this.list.add(dto);
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
//    Đếm số lượng bài kiểm tra của 1 user

    public int countHistory(String userID) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = DBHelper.makeConnection();
            String sql = "Select count(testID)"
                    + "From tblTest "
                    + "Where userID = ?";
            ps = con.prepareStatement(sql);
            ps.setString(1, userID);
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

    private List<TblTestDTO> list;

    public List<TblTestDTO> getList() {
        return list;
    }

//    Tìm kiếm bằng môn học
    public void searchHistoryBySubject(String subjectID, String userID, int pageIndex, int pageSize) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = DBHelper.makeConnection();
            String sql = "With x as (select ROW_NUMBER() over (order by testID) as r, "
                    + "testID, mark, userID, time, dateSubmit, subjectID "
                    + "From tblTest Where subjectID = ? AND userID = ?) "
                    + "Select testID, mark, userID, time, dateSubmit, subjectID "
                    + "From x Where r Between ? AND ?";
            ps = con.prepareStatement(sql);
            ps.setString(1, subjectID);
            ps.setString(2, userID);
            ps.setInt(3, pageIndex * pageSize - (pageSize - 1));
            ps.setInt(4, pageIndex * pageSize);
            rs = ps.executeQuery();
            while (rs.next()) {
                int testID = rs.getInt(1);
                int mark = rs.getInt(2);
                String time = rs.getString(4);
                String dateSubmit = rs.getString(5);
                TblSubjectDAO subjectDAO = new TblSubjectDAO();
                String subjectName = subjectDAO.getSubjectName(subjectID);
                TblTestDTO dto = new TblTestDTO(testID, mark, userID, time, dateSubmit, subjectName);
                if (this.list == null) {
                    this.list = new ArrayList<>();
                }
                this.list.add(dto);
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
//    Đếm số lượng tìm kiếm bài thi bằng môn học

    public int countHistorySearch(String subjectID, String userID) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = DBHelper.makeConnection();
            String sql = "Select count(testID) "
                    + "From tblTest "
                    + "Where subjectID = ? AND userID = ?";
            ps = con.prepareStatement(sql);
            ps.setString(1, subjectID);
            ps.setString(2, userID);
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
}
