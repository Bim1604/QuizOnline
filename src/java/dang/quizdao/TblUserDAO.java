/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dang.quizdao;

import dang.unities.DBHelper;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.naming.NamingException;

/**
 *
 * @author Admin
 */
public class TblUserDAO implements Serializable{
//    Kiểm tra login vào tài khoản 
    public String checkLogin(String username, String password) throws SQLException, NamingException{
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try {
            con = DBHelper.makeConnection();
            String sql = "Select name "
                    + "From tblUser "
                    + "Where email = ? AND password = ?";
            ps = con.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);
            rs = ps.executeQuery();
            if (rs.next()){
                String fullName = rs.getString(1);
                if (fullName.length() > 0){
                    return fullName;
                }
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
    
//    Lấy role 
    public boolean getRole(String email) throws SQLException, NamingException{
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try {
            con = DBHelper.makeConnection();
            String sql = "Select role "
                    + "From tblUser "
                    + "Where email = ?";
            ps = con.prepareStatement(sql);
            ps.setString(1, email);
            rs = ps.executeQuery();
            if (rs.next()){
                boolean role = rs.getBoolean(1);
                return role;
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
        return false;
    }
    
//    Tạo Tài khoản mới
    public boolean createNewAccount(String email, String name, String password, boolean role, boolean status) throws SQLException, NamingException{
        Connection con = null;
        PreparedStatement ps = null;
        
        try {
            con = DBHelper.makeConnection();
            String sql = "Insert into tblUser "
                    + "(email, password, role, status, name) "
                    + "Values (?,?,?,?,?)";
            ps = con.prepareStatement(sql);
            ps.setString(1, email);
            ps.setString(2, password);
            ps.setBoolean(3, role);
            ps.setBoolean(4, status);
            ps.setString(5, name);
            int row = ps.executeUpdate();
            if (row > 0){
                return true;
            }
        } finally {
            if (ps != null){
                ps.close();
            }
            if (con != null){
                con.close();
            }
        }
        return false;
    }
//    Kiểm tra tài khoản trùng để tạo tài khoản mới
    public boolean checkExistAccount(String username) throws SQLException, NamingException{
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = DBHelper.makeConnection();
            String sql = "Select name "
                    + "From tblUser "
                    + "Where email = ?";
            ps = con.prepareStatement(sql);
            ps.setString(1, username);
            rs = ps.executeQuery();
            if (rs.next()){
                String fullName = rs.getString(1);
                if (fullName.length() > 0){
                    return true;
                }
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
        return false;
    }
    public String checkLoginWithGoogle(String userID) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;        
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "Select name " 
                        + "From tblUser " 
                        + "Where email = ?";
                ps = con.prepareStatement(sql);
                ps.setString(1, userID);
                rs = ps.executeQuery();
                if (rs.next()) {                    
                    String name = rs.getString(1);
                    return name;
                }
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
