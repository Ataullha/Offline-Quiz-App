/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fxml;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import modles.Student;
import org.controlsfx.control.Notifications;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Md Ataullha
 */
public class AdminStudentTabController implements Initializable {

    @FXML
    private VBox formContainer;
    @FXML
    private JFXTextField studentName;
    @FXML
    private JFXTextField regNo;
    @FXML
    private TableView<Student> studentTable;
    @FXML
    private TableColumn<Student, String> studentNameColumn;
    @FXML
    private TableColumn<Student, String> registrationNoColumn;
    @FXML
    private JFXButton saveButton;
    @FXML
    private Button backbutton11;

    static int loadatimeonly = 0;

    static boolean firsttime = true;

    quizPanController qp = new quizPanController();
    @FXML
    private Button load;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
//        try {
//            addprevstd();
//        } catch (IOException ex) {
//            Logger.getLogger(AdminStudentTabController.class.getName()).log(Level.SEVERE, null, ex);
//        }

        load.setDisable(true);
        try {
            if(reload()) load.setDisable(false);
        } catch (IOException ex) {
            Logger.getLogger(AdminStudentTabController.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            genTable();
        } catch (IOException ex) {
            Logger.getLogger(AdminStudentTabController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
      

        ///Student student = new Student("", "");
        /// studentTable.getItems().add(0, student);
    }

    private void genTable() throws IOException {

        studentNameColumn.setCellValueFactory(new PropertyValueFactory<>("SName"));
        registrationNoColumn.setCellValueFactory(new PropertyValueFactory<>("SRegNo"));
        studentTable.setItems(value);
    }
    ObservableList<Student> value = FXCollections.observableArrayList();

    @FXML
    private void saveStudent(ActionEvent event) throws IOException {
        
        System.out.println("quizPanController.targetquiztitle");

        String sName = studentName.getText();
        String sRegNo = regNo.getText();
        System.out.println(sName);
        System.out.println(sRegNo);
        String msg = null;
        if (sName.length() <= 0 || sRegNo.length() != 10) {
            msg = "Student Name && Registration No. Must be filled!";
        }
        if (msg != null) {
            Notifications.create()
                    .title("Insert Both Name & Reg Correctly")
                    .darkStyle()
                    .position(Pos.CENTER_LEFT)
                    .text(msg)
                    .showError();
        } else {
            if (alreadyRegister() == 1) {
                Notifications.create()
                        .title("Already Registered!")
                        .darkStyle()
                        .position(Pos.CENTER)
                        .text(msg)
                        .showInformation();
            } else {
                String fname1 = quizPanController.targetquiztitle + "student.txt";
                File files = new File(fname1);
                FileWriter fw = new FileWriter(files, true);
                try (BufferedWriter bw = new BufferedWriter(fw)) {
                    bw.write(sName + "\n");
                    bw.write(sRegNo + "\n");
                }
                  String fname233 = "student.txt";
                File files2 = new File(fname233);
                FileWriter fw3 = new FileWriter(files2, true);
                try (BufferedWriter bw = new BufferedWriter(fw3)) {
                    bw.write(sName + "\n");
                    bw.write(sRegNo + "\n");
                }
                
                String fname2 = quizPanController.targetquiztitle + "registration.txt";
                File filed = new File(fname2);
                FileWriter frw = new FileWriter(filed, true);
                try (BufferedWriter brw = new BufferedWriter(frw)) {
                    brw.write(sRegNo + "\n");
                }
                Student student = new Student(studentName.getText(), regNo.getText());
                studentTable.getItems().add(0, student);
                studentName.clear();
                regNo.clear();

                //load.setDisable(false);
                //firsttime = false;

            }

        }
    }

    @FXML
    private void previousData(ActionEvent event) throws FileNotFoundException, IOException {
        String fname1 =  quizPanController.targetquiztitle + "student.txt";
        String studentnamestring, registrationnostring;

        BufferedReader bap = null;
        try {
            bap = new BufferedReader(new FileReader(fname1));
        } catch (FileNotFoundException exc) {

        }
        while ((studentnamestring = bap.readLine()) != null && loadatimeonly == 0 ) {
            registrationnostring = bap.readLine();
            Student student = new Student(studentnamestring, registrationnostring);
            studentTable.getItems().add(0, student);
        }
        loadatimeonly = 1;
        ///  bap.close();
    }

    @FXML
    private void backbuttoadminhome11(ActionEvent event) throws IOException {
        loadatimeonly = 0;
        System.exit(loadatimeonly);
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/TEACHERHOMEPANNEL.fxml"));
        Scene scene = new Scene(root);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

    int alreadyRegister() throws IOException {

        

        String fname2 =  quizPanController.targetquiztitle + "registration.txt";
        String rgdn;

        BufferedReader bappa = null;
        try {
            bappa = new BufferedReader(new FileReader(fname2));
        } catch (FileNotFoundException exc) {

        }
        while ((rgdn = bappa.readLine()) != null) {
            if (rgdn.trim().equalsIgnoreCase(regNo.getText())) {
                return 1;
            }
        }
        return 0;
    }
    
    boolean reload() throws IOException{
        boolean nope = false;
        
         String fname1 = quizPanController.targetquiztitle + "student.txt";
                File files = new File(fname1);
                
                if(!files.exists()){
                    nope = true;
                }else{
                    files.createNewFile();
                }
                
        
        return nope;
    }
}
