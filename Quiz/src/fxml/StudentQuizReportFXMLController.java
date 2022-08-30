/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fxml;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Md Ataullha
 */
public class StudentQuizReportFXMLController implements Initializable {
    
    @FXML
    private Label correctAnswer;
    @FXML
    private Label wrongAnswer;
    @FXML
    private Label notAttemoted;
    @FXML
    private Label totalQuestion;
    @FXML
    private VBox pieChartVBox;
    @FXML
    private HBox barChartVBox;
    @FXML
    private Label comment;
    @FXML
    private Label StdRegNo;

    /**
     * Initializes the controller class.
     *
     */
    public String sname = "";
    /**
     *
     */
    public String roll;
    public int correct;
    public int wrong;
    public int noans;
    public int comments;
    
    quizquestionFXMLController qqfc = new quizquestionFXMLController();
    //private Label SRN;
    
    public StudentQuizReportFXMLController() {
        //this.roll = null;
        this.sname = qqfc.getStudentName();
        this.roll = qqfc.getRollNum();
        this.correct = qqfc.getCorrectAns();
        this.wrong = qqfc.getWrongAns();
        this.noans = qqfc.getNoAns();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        info();
        
        FXMLLoader fxmlLoader1 = new FXMLLoader(getClass().getResource("/fxml/QuizReportBarChart.fxml"));
        try {
            Node node1 = fxmlLoader1.load();
            barChartVBox.getChildren().add(node1);
        } catch (IOException ex) {
            Logger.getLogger(quizquestionFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        FXMLLoader fxmlLoader2 = new FXMLLoader(getClass().getResource("/fxml/QuizReportPieChart.fxml"));
        try {
            Node node2 = fxmlLoader2.load();
            pieChartVBox.getChildren().add(node2);
        } catch (IOException ex) {
            Logger.getLogger(quizquestionFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

//    private void newStudentLoginLoad(ActionEvent event) throws IOException {
//        Parent root = FXMLLoader.load(getClass().getResource("/Login.fxml"));
//        Stage stage = new Stage();
//        Scene scene = new Scene(root);
//        stage.setScene(scene);
//        stage.show();
//    }
    private void info() {
        
        correctAnswer.setText(correct + "");
        wrongAnswer.setText(wrong + "");
        notAttemoted.setText(noans + "");
        StdRegNo.setText(roll);
        totalQuestion.setText(correct+wrong+noans+"");
       // System.out.println(roll);
        if (correct > 0 && wrong == 0 && noans == 0) {
            comment.setText("BEST");
        }
        if (correct == 0) {
            comment.setText("GOOD");
        } else {
            comment.setText("BETTER");
        }
    }

//    @FXML
//    private void completeButton(ActionEvent event) throws IOException {
//          Parent root = FXMLLoader.load(getClass().getResource("/fxml/STUDENTLOGIN.fxml"));
//        Scene scene = new Scene(root);
//        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
//        window.setScene(scene);
//        window.show();
//    }

    @FXML
    private void completeButton(ActionEvent event) throws IOException {
        //    @FXML
//    private void completeButton(ActionEvent event) throws IOException {
System.exit(0);
//        Parent root = FXMLLoader.load(getClass().getResource("/fxml/STUDENTLOGIN.fxml"));
//        Scene scene = new Scene(root);
//        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
//        window.setScene(scene);
//        window.show();
//        window.close();
//        
       //    }
        
    }
    
}
