/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fxml;

import java.io.BufferedReader;
import java.io.FileReader;
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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author Md Ataullha
 */
public class TEACHERLOGINController implements Initializable {

    @FXML
    private TextField adminEmail;
    @FXML
    private PasswordField adminPassword;
    @FXML
    private Button adminLoginButton;
    
    static boolean markx=false;

    /**
     * Initializes the controller class.
     */
    @FXML
    private void loginAdmin(ActionEvent event) throws IOException {
        
        String email = adminEmail.getText();
        String password = adminPassword.getText();
        BufferedReader br= null;
        br = new BufferedReader(new FileReader("AdminLoginInfo.txt"));
        
        while(br.readLine()!=null){
            String mail=br.readLine();
            String Pass=br.readLine();        
        if (email.trim().equalsIgnoreCase(mail) && password.trim().equalsIgnoreCase(Pass)) {

            try {
                Notifications notification = Notifications.create();
                notification.text("Now you are ready");
                notification.title("LOGIN SUCCESSED!");
                notification.darkStyle();
                notification.position(Pos.CENTER);
                notification.showConfirm();
                notification.hideAfter(javafx.util.Duration.millis(2000));
                Parent root = FXMLLoader.load(getClass().getResource("/fxml/TEACHERHOMEPANNEL.fxml"));
                Stage stage = (Stage) adminEmail.getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setScene(scene);
               // stage.setMaximized(true);
            } catch (IOException e) {
                System.exit(0);
            }

            System.out.println("Login Success");
            markx=true;
            break;
        }
        
        }
       if((markx==false)) {
            Notifications notification = Notifications.create();
            notification.text("Pleae Register or Enter Correct Email and Pasword");
            notification.title("LOGIN FAILED!");
            notification.darkStyle();
            notification.position(Pos.CENTER);
            notification.showError();
            notification.hideAfter(javafx.util.Duration.millis(2000));
            System.out.println("Login Failed");
        
    }
        
    }

    // public static String stdName;
    // public static String sRegNum;
    @FXML
    private void adminRegister(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/RegistrationFXML.fxml"));
        Scene scene = new Scene(root);
        Notifications notification = Notifications.create();
        notification.text("Enter Your Correct Email & Password");
        notification.title("Registration!");
        notification.darkStyle();
        notification.position(Pos.CENTER);
        notification.showConfirm();
        notification.hideAfter(javafx.util.Duration.millis(2000));

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
