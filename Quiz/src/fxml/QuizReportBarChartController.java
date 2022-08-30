/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fxml;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import static java.lang.Integer.parseInt;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

/**
 * FXML Controller class
 *
 * @author Md Ataullha
 */
public class QuizReportBarChartController implements Initializable {

    @FXML
    private BarChart<?, ?> highestlowestchart;
    @FXML
    private NumberAxis number;
    @FXML
    private CategoryAxis hightestlowest;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    public int yournum = 0, highest = 0, lowest = 0;

    /**
     *
     */
    public String hello;

    quizquestionFXMLController qqfc = new quizquestionFXMLController();

    public QuizReportBarChartController() {
        this.yournum = qqfc.getCorrectAns();
        this.hello = qqfc.x;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
            highestnum();
        } catch (IOException ex) {
            Logger.getLogger(QuizReportBarChartController.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            lowstnum();
        } catch (IOException ex) {
            Logger.getLogger(QuizReportBarChartController.class.getName()).log(Level.SEVERE, null, ex);
        }
        barchart();
    }

    private void barchart() {
        XYChart.Series set = new XYChart.Series<>();
        set.getData().add(new XYChart.Data("HIGHEST CORRECT ANSWER", highest));
        set.getData().add(new XYChart.Data("YOUR CORRECT ANSWER", qqfc.getCorrectAns()));
        set.getData().add(new XYChart.Data("LOWEST CORRECT ANSWER", lowest));
        highestlowestchart.getData().addAll(set);
        highestlowestchart.setBarGap(33);
        //highestlowestchart.getAccessibleText();

    }

    private void highestnum() throws FileNotFoundException, IOException {
        try {
            FileOutputStream writer1s = new FileOutputStream(hello+"highest.txt");
            writer1s.write("0".getBytes());
            writer1s.close();
        } catch (IOException e) {

        }
        BufferedReader bro = null;

        bro = new BufferedReader(new FileReader(hello + "highest.txt"));
        String high = bro.readLine();
        highest = parseInt(high);
        try {
            FileOutputStream writers = new FileOutputStream(hello+"highest.txt");
            writers.write(("").getBytes());
            writers.close();
        } catch (IOException e) {
        }

        File fileo = new File(hello+"highest.txt");
        FileWriter fro = new FileWriter(fileo);

        if (parseInt(high) <= yournum) {

            fro.write(yournum + "");
            highest = yournum;

        } else {
            fro.write(0 + "");

        }
        fro.close();

    }

    private void lowstnum() throws FileNotFoundException, IOException {
        
        try {
            FileOutputStream writer2s = new FileOutputStream(hello+"lowest.txt");
            writer2s.write("0".getBytes());
            writer2s.close();
        } catch (IOException e) {

        }

        BufferedReader br = null;

        br = new BufferedReader(new FileReader(hello+"lowest.txt"));
        String low = br.readLine();
        lowest = parseInt(low);
        try {
            FileOutputStream writer = new FileOutputStream(hello+"lowest.txt");
            writer.write(("").getBytes());
            writer.close();
        } catch (IOException e) {
        }

        File file = new File(hello+"lowest.txt");
        FileWriter fr = new FileWriter(file);

        if (parseInt(low) >= yournum) {

            fr.write(yournum + "");
            lowest = yournum;

        } else {
            fr.write(10000 + "");

        }
        fr.close();
    }
}
