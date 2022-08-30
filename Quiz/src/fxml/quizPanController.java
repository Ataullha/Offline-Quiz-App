/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fxml;

import static fxml.TEACHERHOMEPANNELController.getresultbuttonpressed;
import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
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
import javafx.scene.control.Label;
import javafx.stage.Stage;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author Md Ataullha
 */
public class quizPanController implements Initializable {

    @FXML
    private Label QT;
    @FXML
    private Label NOQ;
    @FXML
    private Button SQ;

    public static String targetquiztitle;

    private static String numberofques;
    @FXML
    private Label TIME;
    @FXML
    private Button DEL;
    @FXML
    private Button AS;
    @FXML
    private Button GS;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        targetquiztitle = QT.getText();

        SQ.setDisable(true);
        SQ.setVisible(false);

        AS.setDisable(true);
        AS.setVisible(false);

        GS.setDisable(true);
        GS.setVisible(false);

        if ((!getresultbuttonpressed) && !(TEACHERHOMEPANNELController.addstudentbuttonpressed)) {
            SQ.setDisable(false);
            SQ.setVisible(true);
        }
        if (getresultbuttonpressed) {
            GS.setDisable(false);
            GS.setVisible(true);
        }
        if (TEACHERHOMEPANNELController.addstudentbuttonpressed) {
            AS.setDisable(false);
            AS.setVisible(true);
        }
    }

    @FXML
    private void startQuiz(ActionEvent event) throws IOException {
        
        targetquiztitle = QT.getText();
        numberofques = NOQ.getText();
        
        System.out.println(targetquiztitle);
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/QuizQuestionFXML.fxml"));
        Scene scene = new Scene(root);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
        // window.setMinWidth(705);
        // window.setMinHeight(543);
    }

    public void setQT(String value) {
        this.QT.setText(value);
    }

    public void setNOQ(String value) {
        this.NOQ.setText(value);
    }

    public String getTargetquiztitle() {
        return quizPanController.targetquiztitle;
    }

    public String getNumberofques() {
        return quizPanController.numberofques;
    }

    /**
     *
     * @param Value = string get from quizListFXMLController
     */
    public void setTIME(String Value) {
        this.TIME.setText(Value);
    }

    @FXML
    private void deleteAQuiz(ActionEvent event) throws FileNotFoundException, IOException {

        targetquiztitle = QT.getText();
        numberofques = NOQ.getText();

        String qls = "";

        String f1n = QT.getText() + "answer.txt";
        String f2n = QT.getText() + "highest.txt";
        String f3n = QT.getText() + "lowest.txt";
        String f4n = QT.getText() + "question.txt";
        String f5n = QT.getText() + "quizresult.txt";
        String f6n = QT.getText() + "QuizTimerList.txt";

        File f1 = new File(f1n);

        File f2 = new File(f2n);

        File f3 = new File(f3n);

        File f4 = new File(f4n);

        File f5 = new File(f5n);

        File f6 = new File(f6n);

        f1.deleteOnExit();
        f2.deleteOnExit();
        f3.deleteOnExit();
        f4.deleteOnExit();
        f5.deleteOnExit();
        f6.deleteOnExit();

        Notifications notification = Notifications.create();
        notification.text("Quiz is Deleted");
        notification.title("Delted !");
        notification.darkStyle();
        notification.position(Pos.CENTER);
        notification.showError();
        notification.hideAfter(javafx.util.Duration.millis(800));

        BufferedReader br = null;

        // boolean a = false;
        br = new BufferedReader(new FileReader("QuizList.txt"));
        while ((br.readLine()) != null) {

            String qtff = br.readLine();
            String qtqnff = br.readLine();

            if (qtff.trim().equals(QT.getText().trim())) {

            } else {
                qls = qls + "#" + "\n" + qtff + "\n" + qtqnff + "\n";
            }

        }

        FileOutputStream writer = new FileOutputStream("QuizList.txt");
        writer.write(("").getBytes());
        writer.close();

        File file = new File("QuizList.txt");
        FileWriter fr = new FileWriter(file, true);
        try (BufferedWriter brw = new BufferedWriter(fr)) {
            brw.write(qls);
            brw.close();
        }

        System.exit(0);

    }

    @FXML
    private void AddStudentForEachQuiz(ActionEvent event) throws IOException {

        targetquiztitle = QT.getText();
        numberofques = NOQ.getText();

        Parent root = FXMLLoader.load(getClass().getResource("/fxml/AdminStudentTab.fxml"));
        Scene scene = new Scene(root);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();

    }

    @FXML
    private void getResultInAExcelFile(ActionEvent event) throws IOException {

        targetquiztitle = QT.getText();
        numberofques = NOQ.getText();
        
        BufferedReader bf = null;

        // boolean a = false;
        String b, d, f, h;
        String s="";
        String header = "STUDENT REGISTRATION NO ,  CORRECT ANSWER , WRONG ANSWER ,  NOT ATTEMPTED";
        bf = new BufferedReader(new FileReader(targetquiztitle+"quizresult.txt"));
        while ((bf.readLine()) != null) {

            String a = bf.readLine();
            b = a.substring(30, a.length());

            String c = bf.readLine();
            d = c.substring(17, c.length());
            
            String e = bf.readLine();
            f = e.substring(15, e.length());
            
            String g = bf.readLine();
            h = g.substring(15, g.length());
            
            s = s+b+","+d+","+f+","+h+"\n";
            
            //System.out.println(s);
                
            }
        s = header+"\n"+s;
        
          
        File file = new File(targetquiztitle+"result.csv");
        FileWriter fr = new FileWriter(file, true);
        try (BufferedWriter brw = new BufferedWriter(fr)) {
            brw.write(s+"\n");
            brw.close();
        }
        
        Desktop desktop = Desktop.getDesktop();  
        
        desktop.open(file);
        
        System.exit(0);
        
        

        }
    }
