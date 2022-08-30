/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fxml;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.FlowPane;

/**
 * FXML Controller class
 *
 * @author Md Ataullha
 */
public class QuizListFXMLController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private FlowPane mainPannel;

    private String qt = "";
    private String noq = "";
    private String tot = "";

    public String tutaltume;
    static int mark = 0;
    static int track = 0;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            BufferedReader bufread = null;
            int line = 0;
            try {

                bufread = new BufferedReader(new FileReader("QuizList.txt"));

            } catch (FileNotFoundException ex) {
            }
            String l;
            while ((l=bufread.readLine()) != null) {
                line++;
                System.out.println(l);
            }
            System.out.println("before-after"+line);
            for (int i = 1; i <= line / 3; i++) {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/quizPan.fxml"));
                BufferedReader bufred = null;
                try {

                    bufred = new BufferedReader(new FileReader("QuizList.txt"));

                } catch (FileNotFoundException ex) {
                }
                while ((bufred.readLine()) != null) {
                    for (int j = 1; j <= mark+track; j++) {
                        bufred.readLine();
                    }
                    qt = bufred.readLine();
                    noq = bufred.readLine();
                  // tot = bufread.readLine();
                 //   System.out.println(qt);
                 //   System.out.println(noq);
                  //  System.out.println(tot);
                    mark += 2;
                    track++;
                    break;
                }
                try {
                    Node node = fxmlLoader.load();
                    quizPanController qpc = fxmlLoader.getController();
                    qpc.setQT(qt);
                    qpc.setNOQ(noq);
                  //  qpc.setTIME(tot);
                    mainPannel.getChildren().add(node);
                } catch (IOException ex) {
                    Logger.getLogger(QuizListFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                }
               
        }
        }catch (IOException ex) {
            Logger.getLogger(QuizListFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
