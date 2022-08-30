/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fxml;

import static fxml.Singup.Email;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author Md Ataullha
 */
public class SETQUIZTITLEController implements Initializable {
    
    @FXML
    private TextField quizTitle;
    @FXML
    private Button startQuiz;
    @FXML
    private TextField numberOfQuestion;
    @FXML
    private TextField totalTimeForQuiz;
    
    public static String QT = "";
    public static String TQ = "";
    public static String TT = "";
    @FXML
    private Button backbutton1;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
    @FXML
    private void saveTitleAndStartQuiz(ActionEvent event) throws IOException {
        if ((alreadyRegisterEmail()==1)||quizTitle.getText().trim().isEmpty() || numberOfQuestion.getText().trim().isEmpty() || totalTimeForQuiz.getText().trim().isEmpty()) {
        if(alreadyRegisterEmail()==1){
             Notifications notification = Notifications.create();
            notification.text("QUIZ ALREADY EXIST");
            notification.title("QUIZ EXIST");
            notification.darkStyle();
            notification.position(Pos.CENTER);
            notification.showError();
            notification.hideAfter(javafx.util.Duration.millis(80));
            
        }else{
                Notifications notification = Notifications.create();
            notification.text("FILL ALL THE VALUE");
            notification.title("FILL ALL");
            notification.darkStyle();
            notification.position(Pos.CENTER);
            notification.showError();
            notification.hideAfter(javafx.util.Duration.millis(80));
        }
        } else {
            QT = quizTitle.getText();
            TQ = numberOfQuestion.getText();
            
            System.out.println(QT);
            System.out.println(TQ);
            //System.out.println(TT);
            
            try {
                String hoyna = "QuizList";
                String tfn = hoyna + ".txt";
                File afilez = new File(tfn);
                FileWriter frwd = new FileWriter(afilez, true);
                BufferedWriter brwd = new BufferedWriter(frwd);
                brwd.write("#" + "\n");
                brwd.write(QT + "\n");
                brwd.write(TQ + "\n");
                brwd.close();
                
            } catch (IOException e) {
            }
            try{
                TT = totalTimeForQuiz.getText();
                 String hoyna = QT+"QuizTimerList";
                String tfkn = hoyna + ".txt";
                File afilekz = new File(tfkn);
                FileWriter frkwd = new FileWriter(afilekz, true);
                BufferedWriter brkwd = new BufferedWriter(frkwd);
                brkwd.write("#" + "\n");
                brkwd.write(TT + "\n");
                brkwd.close();
                
                
            }catch(IOException e){
               
            }
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/AddQuizFXML.fxml"));
            Scene scene = new Scene(root);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(scene);
            window.show();
        }
    }

    @FXML
    private void backbuttoadminhome(ActionEvent event) throws IOException {
          Parent root = FXMLLoader.load(getClass().getResource("/fxml/TEACHERHOMEPANNEL.fxml"));
        Scene scene = new Scene(root);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }
    
    int alreadyRegisterEmail() throws IOException {

        String fname2 = "QuizList.txt";
        String quti;

        BufferedReader bappa = null;
        try {
            bappa = new BufferedReader(new FileReader(fname2));
        } catch (FileNotFoundException exc) {

        }
        while ((bappa.readLine()) != null) {
            quti = bappa.readLine();
            bappa.readLine();

            if (quti.trim().equalsIgnoreCase(quizTitle.getText())) {
                return 1;
            }
        }
        return 0;
    }

}
