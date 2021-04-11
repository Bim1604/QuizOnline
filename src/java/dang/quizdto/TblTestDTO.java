/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dang.quizdto;

import java.io.Serializable;

/**
 *
 * @author Admin
 */
public class TblTestDTO implements Serializable{
    private int testID;
    private float mark; 
    private String userID;
    private String time;
    private String dateSubmit;
    private String subjectID;

    public TblTestDTO() {
    }

    public TblTestDTO(int testID, float mark, String userID, String time, String dateSubmit, String subjectID) {
        this.testID = testID;
        this.mark = mark;
        this.userID = userID;
        this.time = time;
        this.dateSubmit = dateSubmit;
        this.subjectID = subjectID;
    }

    /**
     * @return the testID
     */
    public int getTestID() {
        return testID;
    }

    /**
     * @param testID the testID to set
     */
    public void setTestID(int testID) {
        this.testID = testID;
    }

    /**
     * @return the mark
     */
    public float getMark() {
        return mark;
    }

    /**
     * @param mark the mark to set
     */
    public void setMark(float mark) {
        this.mark = mark;
    }

    /**
     * @return the userID
     */
    public String getUserID() {
        return userID;
    }

    /**
     * @param userID the userID to set
     */
    public void setUserID(String userID) {
        this.userID = userID;
    }

    /**
     * @return the time
     */
    public String getTime() {
        return time;
    }

    /**
     * @param time the time to set
     */
    public void setTime(String time) {
        this.time = time;
    }

    /**
     * @return the dateSubmit
     */
    public String getDateSubmit() {
        return dateSubmit;
    }

    /**
     * @param dateSubmit the dateSubmit to set
     */
    public void setDateSubmit(String dateSubmit) {
        this.dateSubmit = dateSubmit;
    }

    /**
     * @return the subjectID
     */
    public String getSubjectID() {
        return subjectID;
    }

    /**
     * @param subjectID the subjectID to set
     */
    public void setSubjectID(String subjectID) {
        this.subjectID = subjectID;
    }

        
}
