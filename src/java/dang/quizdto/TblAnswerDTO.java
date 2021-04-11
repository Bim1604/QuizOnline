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
public class TblAnswerDTO implements Serializable{
    private String answerID;
    private String questionID;
    private String answerContent;
    private boolean answerCorrect;

    public TblAnswerDTO() {
    }

    @Override
    public String toString() {
        return "TblAnswerDTO{" + "answerID=" + answerID + ", questionID=" + questionID + ", answerContent=" + answerContent + ", answerCorrect=" + answerCorrect + '}';
    }

    public TblAnswerDTO(String answerID, String questionID, String answerContent, boolean answerCorrect) {
        this.answerID = answerID;
        this.questionID = questionID;
        this.answerContent = answerContent;
        this.answerCorrect = answerCorrect;
    }

    /**
     * @return the answerID
     */
    public String getAnswerID() {
        return answerID;
    }

    /**
     * @param answerID the answerID to set
     */
    public void setAnswerID(String answerID) {
        this.answerID = answerID;
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
     * @return the answerContent
     */
    public String getAnswerContent() {
        return answerContent;
    }

    /**
     * @param answerContent the answerContent to set
     */
    public void setAnswerContent(String answerContent) {
        this.answerContent = answerContent;
    }

    /**
     * @return the answerCorrect
     */
    public boolean isAnswerCorrect() {
        return answerCorrect;
    }

    /**
     * @param answerCorrect the answerCorrect to set
     */
    public void setAnswerCorrect(boolean answerCorrect) {
        this.answerCorrect = answerCorrect;
    }
    
    
}
