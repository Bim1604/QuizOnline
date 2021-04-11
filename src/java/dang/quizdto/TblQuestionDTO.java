/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dang.quizdto;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Admin
 */
public class TblQuestionDTO implements Serializable {

    private String id;
    private String question_content;
    private String createDate;
    private String subjectID;
    private boolean status;
    private List<TblAnswerDTO> answer;
    
    public TblQuestionDTO() {
    }

    public TblQuestionDTO(String id, String question_content, String createDate, String subjectID, boolean status, List<TblAnswerDTO> answer) {
        this.id = id;
        this.question_content = question_content;
        this.createDate = createDate;
        this.subjectID = subjectID;
        this.status = status;
        this.answer = answer;
    }

    @Override
    public String toString() {
        return "TblQuestionDTO{" + "id=" + id + ", question_content=" + question_content + ", createDate=" + createDate + ", subjectID=" + subjectID + ", status=" + status + ", answer=" + answer + '}';
    }

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the question_content
     */
    public String getQuestion_content() {
        return question_content;
    }

    /**
     * @param question_content the question_content to set
     */
    public void setQuestion_content(String question_content) {
        this.question_content = question_content;
    }

    /**
     * @return the createDate
     */
    public String getCreateDate() {
        return createDate;
    }

    /**
     * @param createDate the createDate to set
     */
    public void setCreateDate(String createDate) {
        this.createDate = createDate;
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

    /**
     * @return the status
     */
    public boolean isStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(boolean status) {
        this.status = status;
    }

    /**
     * @return the answer
     */
    public List<TblAnswerDTO> getAnswer() {
        return answer;
    }

    /**
     * @param answer the answer to set
     */
    public void setAnswer(List<TblAnswerDTO> answer) {
        this.answer = answer;
    }

    
}
