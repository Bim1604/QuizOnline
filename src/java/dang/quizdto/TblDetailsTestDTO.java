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
public class TblDetailsTestDTO implements Serializable{
    private int testID;
    private String questionID;
    private float point;
    private String answerStudent;

    public TblDetailsTestDTO() {
    }

    public TblDetailsTestDTO(int testID, String questionID, float point, String answerStudent) {
        this.testID = testID;
        this.questionID = questionID;
        this.point = point;
        this.answerStudent = answerStudent;
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
     * @return the questionID
     */
    public String getQuestionID() {
        return questionID;
    }

    /**
     * @param questionID the questionID to set
     */
    public void setQuestionID(String questionID) {
        this.questionID = questionID;
    }

    /**
     * @return the point
     */
    public float getPoint() {
        return point;
    }

    /**
     * @param point the point to set
     */
    public void setPoint(float point) {
        this.point = point;
    }

    /**
     * @return the answerStudent
     */
    public String getAnswerStudent() {
        return answerStudent;
    }

    /**
     * @param answerStudent the answerStudent to set
     */
    public void setAnswerStudent(String answerStudent) {
        this.answerStudent = answerStudent;
    }

    
}
