/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dang.quizdao;

import dang.quizdto.TblAnswerDTO;
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
public class TblAnswerDAO implements Serializable{
//    Lấy câu trả lời thông qua question
    public void getAnswer(String questionID) throws SQLException, NamingException{
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = DBHelper.makeConnection();
            String sql = "Select answerID, questionID, answerContent, answerCorrect "
                    + "From tblAnswer "
                    + "Where questionID = ?";
            ps = con.prepareStatement(sql);
            ps.setString(1, questionID);
            rs = ps.executeQuery();
            while (rs.next()){
                String answerID = rs.getString(1);
                String answerContent = rs.getString(3);
                boolean answerCorrect = rs.getBoolean(4);
                TblAnswerDTO dto = new TblAnswerDTO(answerID, answerID, answerContent, answerCorrect);
                if (this.listAnswer == null){
                    this.listAnswer = new ArrayList<>();
                }
                this.listAnswer.add(dto);
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
    
    private List<TblAnswerDTO> listAnswer;

    public List<TblAnswerDTO> getListAnswer() {
        return listAnswer;
    }
    
    //    chấm điểm
    public float getEachQuestionMark(String answerID) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = DBHelper.makeConnection();
            String sql = "Select answerCorrect "
                    + "From tblAnswer "
                    + "Where answerID = ?";
            ps = con.prepareStatement(sql);
            ps.setString(1, answerID);
            rs = ps.executeQuery();
            int point = 0;
            if (rs.next()) {
                if (rs.getBoolean(1) == true) {
                    point++;
                }
                return point;
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
//     Lấy AnswerContent
    public String getAnswerContent(String answerID) throws SQLException, NamingException{
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = DBHelper.makeConnection();
            String sql = "Select answerContent "
                    + "From tblAnswer "
                    + "Where answerID = ?";
            ps = con.prepareStatement(sql);
            ps.setString(1, answerID);
            rs = ps.executeQuery();
            if (rs.next()){
                return rs.getString(1);
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
}
