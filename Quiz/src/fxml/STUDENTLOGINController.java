/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fxml;

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
public class STUDENTLOGINController implements Initializable {
    
    
    quizPanController qps = new quizPanController();

    @FXML
    private TextField studentName;
    @FXML
    private TextField studentRegistrationNo;
    @FXML
    private Button studentLoginButton;
    
     static String stdName;
    static String sRegNum;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void loginStudent(ActionEvent event) throws IOException {
        boolean flag = false;
        
       // System.out.println(quizPanController.targetquiztitle);
       // System.out.println(qps.getTargetquiztitle());

        stdName = studentName.getText();
        sRegNum = studentRegistrationNo.getText();
        System.out.println(stdName + "\n" + sRegNum + "\n");

        try {
            BufferedReader br = null;
            String RN = null;
            System.out.println(stdName + "\n" + sRegNum + "\n");
            br = new BufferedReader(new FileReader("student.txt"));
            while (br.readLine() != null) {
                RN = br.readLine();
                if (RN.equals(sRegNum)) {
                    System.out.println("login success");
                    System.out.println(stdName + "\n" + sRegNum + "\n");
                    flag = true;
                    break;
                }
            }

        } catch (FileNotFoundException ex) {

        }
        if (flag) {

            try {
                String filenamed = "currentstudent.txt";
                File filed = new File(filenamed);
                FileWriter filewrd = new FileWriter(filed, true);
                try (BufferedWriter bufrwrid = new BufferedWriter(filewrd)) {
                    bufrwrid.write(stdName+"\n");
                    bufrwrid.write(sRegNum+"\n");
                }
            } catch(IOException e){
                System.out.println("no bug");
            }

            try {
                
                Parent root = FXMLLoader.load(getClass().getResource("/fxml/QuizListFXML.fxml"));
                Stage stage = (Stage) studentName.getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.setMaximized(true);

            } catch (IOException e) {
                System.out.println("BUGGG...!!");
            }

            System.out.println("Login Success");
        } else {
            Notifications.create()
                    .title("You Are not Register For this Quiz")
                    .darkStyle()
                    .position(Pos.CENTER_LEFT)
                    .text("Contact With  Admin For Registration")
                    .showWarning();
        }
    }

    public String getStudentName() {
        return stdName;
    }

    public static String getStudentRegName() {
        return sRegNum;

    }
}