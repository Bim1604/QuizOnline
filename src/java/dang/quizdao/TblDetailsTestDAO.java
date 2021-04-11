/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dang.quizdao;

import dang.quizdto.TblDetailsTestDTO;
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
public class TblDetailsTestDAO implements Serializable {
//    thêm vào chi tiết bài kiểm tra

    public void saveDetailsTest(String questionID, int testID, float point, String answerStudentID) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = DBHelper.makeConnection();
            String sql = "Insert into tblDetailsTest "
                    + "(questionID, testID, point, answerStutent) "
                    + "Values (?,?,?,?)";
            ps = con.prepareStatement(sql);
            ps.setString(1, questionID);
            ps.setInt(2, testID);
            ps.setFloat(3, point);
            TblAnswerDAO answerDAO = new TblAnswerDAO();
            String answerStudent = answerDAO.getAnswerContent(answerStudentID);
            ps.setString(4, answerStudent);
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

//    Lấy chi tiết bài kiểm tra
    public void getDetailsTest(int testID) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = DBHelper.makeConnection();
            String sql = "Select questionID, point, answerStutent "
                    + "From tblDetailsTest "
                    + "Where testID = ?";
            ps = con.prepareStatement(sql);
            ps.setInt(1, testID);
            rs = ps.executeQuery();
            while (rs.next()) {
                String questionID = rs.getString(1);
                TblQuestionDAO dao = new TblQuestionDAO();
                String questionContent = dao.getQuestionContent(questionID);
                float point = rs.getFloat(2);
                String answerStudent = rs.getString(3);
                TblDetailsTestDTO dto = new TblDetailsTestDTO(testID, questionContent, point, answerStudent);
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

    private List<TblDetailsTestDTO> list;

    public List<TblDetailsTestDTO> getList() {
        return list;
    }

}
