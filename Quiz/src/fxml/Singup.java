package fxml;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.controlsfx.control.Notifications;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Md Ataullha
 */
public class Singup {

    public static String Email;
    public static String Password;
    @FXML
    private TextField adminRegistrationEmail;
    @FXML
    private PasswordField adminRegistrationPassword;

    @FXML
    private void submitRegistration(ActionEvent event) throws IOException {

        Pattern P = Pattern.compile("[a-z0-9]+([-+._][a-z0-9]+){0,2}@.*?(\\.(a(?:[cdefgilmnoqrstuwxz]|ero|(?:rp|si)a)|b(?:[abdefghijmnorstvwyz]iz)|c(?:[acdfghiklmnoruvxyz]|at|o(?:m|op))|d[ejkmoz]|e(?:[ceghrstu]|du)|f[ijkmor]|g(?:[abdefghilmnpqrstuwy]|ov)|h[kmnrtu]|i(?:[delmnoqrst]|n(?:fo|t))|j(?:[emop]|obs)|k[eghimnprwyz]|l[abcikrstuvy]|m(?:[acdeghklmnopqrstuvwxyz]|il|obi|useum)|n(?:[acefgilopruz]|ame|et)|o(?:m|rg)|p(?:[aefghklmnrstwy]|ro)|qa|r[eosuw]|s[abcdeghijklmnortuvyz]|t(?:[cdfghjklmnoprtvwz]|(?:rav)?el)|u[agkmsyz]|v[aceginu]|w[fs]|y[etu]|z[amw])\\b){1,2}");
        Email = adminRegistrationEmail.getText();
        Password = adminRegistrationPassword.getText();

        if (!P.matcher(Email).matches() || Password.length() < 1 || (alreadyRegisterEmail() == 1)) {

            if (!P.matcher(Email).matches() || Password.length() < 1) {
                Notifications notification = Notifications.create();
                notification.text("Enter a valid gamil & Password");
                notification.title("IS This Valid?");
                notification.position(Pos.CENTER);
                notification.showError();
                notification.hideAfter(javafx.util.Duration.millis(2000));
            } else {
                Notifications notification = Notifications.create();
                notification.text("Already Registered!");
                notification.title("Singin Please!");
                notification.position(Pos.CENTER);
                notification.showError();
                notification.hideAfter(javafx.util.Duration.millis(2000));

            }
        } else {

            try {
                JavaMailUtil.sendMail(Email);
            } catch (Exception ex) {
                Logger.getLogger(Singup.class.getName()).log(Level.SEVERE, null, ex);
            }

            Notifications notification = Notifications.create();
            notification.text("Registration Completed");
            notification.title("COMPLETED");
            notification.darkStyle();
            notification.position(Pos.CENTER);
            notification.showInformation();
            notification.hideAfter(javafx.util.Duration.millis(2000));
//            Parent root = FXMLLoader.load(getClass().getResource("/fxml/TEACHERLOGIN.fxml"));
//            Scene scene = new Scene(root);
//            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
//            window.setScene(scene);
//            window.show();

            Parent root = FXMLLoader.load(getClass().getResource("/fxml/OTPFXML.fxml"));
            Scene scene = new Scene(root);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(scene);
            window.show();

        }
        System.out.println(Email + "eitai email");
        System.out.println(Password + "eitai password");
        System.out.println("Kaj korteche na...!!");

    }

    int alreadyRegisterEmail() throws IOException {

        try {
            File myObj2 = new File("QuizList.txt");
            if (myObj2.createNewFile()) {
                System.out.println("File created: " + myObj2.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        try {
            File myObj = new File("AdminLoginInfo.txt");
            if (myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        String fname2 = "AdminLoginInfo.txt";
        String email;

        BufferedReader bappa = null;
        try {
            bappa = new BufferedReader(new FileReader(fname2));
        } catch (FileNotFoundException exc) {

        }
        
        while ((bappa.readLine()) != null) {
            email = bappa.readLine();
            bappa.readLine();

            if (email.trim().equalsIgnoreCase(Email)) {
                return 1;
            }
        }
        return 0;
    }

    @FXML
    private void cancel(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/TEACHERLOGIN.fxml"));
        Scene scene = new Scene(root);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }
}
