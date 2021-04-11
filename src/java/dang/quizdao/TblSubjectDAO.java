/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dang.quizdao;

import dang.quizdto.TblSubjectDTO;
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
public class TblSubjectDAO implements Serializable{
//    Lấy tất cả môn học
    public void getAllSubject() throws SQLException, NamingException{
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try {
            con = DBHelper.makeConnection();
            String sql = "Select subjectID, subjectName "
                    + "From tblSubject";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()){
                String subID = rs.getString(1);
                String subName = rs.getString(2);
                if (this.listSub == null){
                    this.listSub = new ArrayList<>();
                }
                TblSubjectDTO dto = new TblSubjectDTO(subID, subName);
                this.listSub.add(dto);
            }
        } finally {
            if (rs != null){
                rs.close();
            }
            if (ps != null){
                ps.close();
            }
            if (con != null){
                con.close();
            }
        }
    }
    private List<TblSubjectDTO> listSub;

    public List<TblSubjectDTO> getListSub() {
        return listSub;
    }
//    Lấy tên môn học
    public String getSubjectName(String subjectID) throws SQLException, NamingException{
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try {
            con = DBHelper.makeConnection();
            String sql = "Select subjectName "
                    + "From tblSubject "
                    + "Where subjectID = ?";
            ps = con.prepareStatement(sql);
            ps.setString(1, subjectID);
            rs = ps.executeQuery();
            if (rs.next()){
                String subjectName = rs.getString(1);
                return subjectName;
            }
        } finally {
            if (rs != null){
                rs.close();
            }
            if (ps != null){
                ps.close();
            }
            if (con != null){
                con.close();
            }
        }
        return null;
    }
}
