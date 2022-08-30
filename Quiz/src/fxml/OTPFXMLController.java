/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fxml;

import static fxml.Singup.Email;
import static fxml.Singup.Password;
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
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author MAS
 */
public class OTPFXMLController implements Initializable {

    @FXML
    private TextField otpNum;

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
    private void otpverify(ActionEvent event) throws IOException {
        
        
        String otpmatchstr = otpNum.getText();
        String strr = JavaMailUtil.str;

        
        if(otpmatchstr.trim().equals(strr)){
              Notifications notification = Notifications.create();
            notification.text("Verification Completed");
            notification.title("COMPLETED");
            notification.darkStyle();
            notification.position(Pos.CENTER);
            notification.showInformation();
            notification.hideAfter(javafx.util.Duration.millis(1000));
            
            try {
                String fname = "AdminLoginInfo.txt";
                File file = new File(fname);
                FileWriter fr = new FileWriter(file, true);
                try (BufferedWriter br = new BufferedWriter(fr)) {
                    br.write("#" + "\n");
                    br.write(Email + "\n");
                    br.write(Password + "\n");
                }
            } catch (IOException e) {

            }
            
            
             Parent root = FXMLLoader.load(getClass().getResource("/fxml/TEACHERLOGIN.fxml"));
            Scene scene = new Scene(root);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(scene);
            window.show();
        }else{
             Notifications notification = Notifications.create();
            notification.text("Verification Failed");
            notification.title("Enter a Valid OTP");
            notification.darkStyle();
            notification.position(Pos.CENTER);
            notification.showInformation();
            notification.hideAfter(javafx.util.Duration.millis(1000));
            
        }
        
        
        
            
            
        
    }
    
}
