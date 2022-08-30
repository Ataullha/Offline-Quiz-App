/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modles;

/**
 *
 * @author Md Ataullha
 */
public class Quiz {
    private Integer QuizId;
    private String title;

    public Quiz(Integer QuizId, String title) {
        this.QuizId = QuizId;
        this.title = title;
    }
    

    public Integer getQuizId() {
        return QuizId;
    }

    public String getTitle() {
        return title;
    }

    public void setQuizId(Integer QuizId) {
        this.QuizId = QuizId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "Quiz{" + "QuizId=" + QuizId + ", title=" + title + '}';
    }
    
}
