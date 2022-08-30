/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fxml;

import com.jfoenix.controls.JFXTextArea;
import java.io.BufferedWriter;
import java.io.File;
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
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.stage.Stage;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author MAS
 */
public class AddQuizFXMLController implements Initializable {

    @FXML
    private Button AddNextQues;
    @FXML
    private Button submitQuestion;
    @FXML
    private Label numberOfCurrentQuestion;
    @FXML
    private RadioButton option1radio;
    @FXML
    private RadioButton option2radio;
    @FXML
    private RadioButton option3radio;
    @FXML
    private RadioButton option4radio;
    @FXML
    private JFXTextArea option4;
    @FXML
    private JFXTextArea option3;
    @FXML
    private JFXTextArea option2;
    @FXML
    private JFXTextArea option1;
    @FXML
    private JFXTextArea question;

    static int count = 1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        hideSub();

    }

    @FXML
    private void setNextQuestion(ActionEvent event) throws IOException {
        if (question.getText().trim().isEmpty() || option4.getText().trim().isEmpty() || option1.getText().trim().isEmpty() || option2.getText().trim().isEmpty() || option3.getText().trim().isEmpty() || OptionisSelected().isEmpty()) {
            if (question.getText().trim().isEmpty()) {
                Notifications notification = Notifications.create();
                notification.text("Add your question for the next setps");
                notification.title("ENTER A VALID WORD FOR YOUR QUESTION(BLANK QUESTION IS NOT ALLOWED)");
                notification.darkStyle();
                notification.position(Pos.TOP_RIGHT);
                notification.showError();
                notification.hideAfter(javafx.util.Duration.millis(800));
            } else if (option4.getText().trim().isEmpty() || option1.getText().trim().isEmpty() || option2.getText().trim().isEmpty() || option3.getText().trim().isEmpty()) {
                Notifications notification = Notifications.create();
                notification.text("Add All Four Options of This Questions If Have");
                notification.title("QUIZ ALL OPTIONS [OPTION A,OPTION B,OPTION C,OPTION D]");
                notification.darkStyle();
                notification.position(Pos.TOP_RIGHT);
                notification.showError();
                notification.hideAfter(javafx.util.Duration.millis(800));
            } else {
                Notifications notification = Notifications.create();
                notification.text("Add A Valid Option For Quiz Answer If Have");
                notification.title("QUIZ CORRECT OPTION");
                notification.darkStyle();
                notification.position(Pos.TOP_RIGHT);
                notification.showWarning();
                notification.hideAfter(javafx.util.Duration.millis(800));
            }
        } else {

            String quiztitlename = fxml.SETQUIZTITLEController.QT;

            try {
                File myObj1 = new File(quiztitlename + "registration.txt");
                if (myObj1.createNewFile()) {
                    System.out.println("File created: " + myObj1.getName());
                } else {
                    System.out.println("File already exists.");
                }
            } catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
            try {
                File myObj = new File(quiztitlename + "student.txt");
                if (myObj.createNewFile()) {
                    System.out.println("File created: " + myObj.getName());
                } else {
                    System.out.println("File already exists.");
                }
            } catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }

            File file = new File(quiztitlename + "question.txt");
            FileWriter fr = new FileWriter(file, true);
            try (BufferedWriter br = new BufferedWriter(fr)) {
                br.write("####\n");
                br.write(count + "\n");
                br.write("###\n");
                br.write(question.getText() + "\n");
                int exist = 10000 - countLines(question.getText());
                for (int i = 1; i <= exist; i++) {
                    br.write(" " + "\n");
                }
                br.write("###\n");
                br.write(option1.getText() + "\n");
                br.write("###\n");
                br.write(option2.getText() + "\n");
                br.write("###\n");
                br.write(option3.getText() + "\n");
                br.write("###\n");
                br.write(option4.getText() + "\n");
                br.write("###\n");
                br.write(OptionisSelected() + "\n");
                br.write("####\n");
            }

            ButtonF();

            count++;

            File afile = new File(quiztitlename + "answer.txt");
            FileWriter frw = new FileWriter(afile, true);
            try (BufferedWriter brw = new BufferedWriter(frw)) {
                brw.write(OptionisSelected() + "\n");
            }

            question.clear();
            option1.clear();
            option2.clear();
            option3.clear();
            option4.clear();

            option1radio.setSelected(false);
            option2radio.setSelected(false);
            option3radio.setSelected(false);
            option4radio.setSelected(false);

            numberOfCurrentQuestion.setText(count + "");

        }
    }

    @FXML
    private void completeQuestionSetting(ActionEvent event) throws IOException {
        if (question.getText().trim().isEmpty() || option4.getText().trim().isEmpty() || option1.getText().trim().isEmpty() || option2.getText().trim().isEmpty() || option3.getText().trim().isEmpty() || OptionisSelected().isEmpty()) {
            if (question.getText().trim().isEmpty()) {
                Notifications notification = Notifications.create();
                notification.text("Add your question for the next setps");
                notification.title("ENTER A VALID WORD FOR YOUR QUESTION(BLANK QUESTION IS NOT ALLOWED)");
                notification.darkStyle();
                notification.position(Pos.TOP_RIGHT);
                notification.showError();
                notification.hideAfter(javafx.util.Duration.millis(800));
            } else if (option4.getText().trim().isEmpty() || option1.getText().trim().isEmpty() || option2.getText().trim().isEmpty() || option3.getText().trim().isEmpty()) {
                Notifications notification = Notifications.create();
                notification.text("Add All Four Options of This Questions If Have");
                notification.title("QUIZ ALL OPTIONS [OPTION A,OPTION B,OPTION C,OPTION D]");
                notification.darkStyle();
                notification.position(Pos.TOP_RIGHT);
                notification.showError();
                notification.hideAfter(javafx.util.Duration.millis(800));
            } else {
                Notifications notification = Notifications.create();
                notification.text("Add A Valid Option For Quiz Answer If Have");
                notification.title("QUIZ CORRECT OPTION");
                notification.darkStyle();
                notification.position(Pos.TOP_RIGHT);
                notification.showWarning();
                notification.hideAfter(javafx.util.Duration.millis(800));
            }
        } else {
            String quiztitlename = fxml.SETQUIZTITLEController.QT;
            File file = new File(quiztitlename + "question.txt");
            FileWriter fr = new FileWriter(file, true);
            try (BufferedWriter br = new BufferedWriter(fr)) {
                br.write("####\n");
                br.write(count + "\n");
                br.write("###\n");
                br.write(question.getText() + "\n");
                int exist = 10000 - countLines(question.getText());
                for (int i = 1; i <= exist; i++) {
                    br.write(" " + "\n");
                }
                //System.out.println(countLines(question.getText()));//<!---->
                br.write("###\n");
                br.write(option1.getText() + "\n");
                br.write("###\n");
                br.write(option2.getText() + "\n");
                br.write("###\n");
                br.write(option3.getText() + "\n");
                br.write("###\n");
                br.write(option4.getText() + "\n");
                br.write("###\n");
                br.write(OptionisSelected() + "\n");
                br.write("####\n");
            }

            count++;

            File afile = new File(quiztitlename + "answer.txt");
            FileWriter frw = new FileWriter(afile, true);
            try (BufferedWriter brw = new BufferedWriter(frw)) {
                brw.write(OptionisSelected() + "\n");
            }

            question.clear();
            option1.clear();
            option2.clear();
            option3.clear();
            option4.clear();

            option1radio.setSelected(false);
            option2radio.setSelected(false);
            option3radio.setSelected(false);
            option4radio.setSelected(false);

            Notifications notification = Notifications.create();
            notification.text("YOUR QUIZ IS SAVED");
            notification.title("QUIZ SAVED!!");
            notification.position(Pos.CENTER);
            notification.showInformation();

            Stage stage = (Stage) submitQuestion.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/SETQUIZTITLE.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        }
    }

    public String OptionisSelected() {
        String s = "";
        if (option1radio.isSelected()) {
            s = s + '1';
        }
        if (option2radio.isSelected()) {
            s = s + '2';
        }
        if (option3radio.isSelected()) {
            s = s + '3';
        }
        if (option4radio.isSelected()) {
            s = s + '4';
        }

        return s.trim();
    }

    private void ButtonF() {
        if (Integer.parseInt(SETQUIZTITLEController.TQ) <= (count + 1)) {
            this.submitQuestion.setVisible(true);
            this.AddNextQues.setVisible(false);
        } else {
            this.submitQuestion.setVisible(false);
            this.AddNextQues.setVisible(true);
        }

    }

    private void hideSub() {
        this.submitQuestion.setVisible(false);
    }

    private static int countLines(String str) {
        String[] lines = str.split("\r\n|\r|\n");
        return lines.length;
    }

}
