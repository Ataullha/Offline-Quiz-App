/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fxml;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.layout.Background;

/**
 * FXML Controller class
 *
 * @author Md Ataullha
 */
public class QuizReportPieChartController implements Initializable {

    @FXML
    private PieChart pieChart;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    
    
      quizquestionFXMLController qqfc = new quizquestionFXMLController();
      public int  rolls,corrects,wrongs,noanss;

    /**
     *
     */
    public QuizReportPieChartController() {
        //this.sname = qqfc.getStudentName();
     //   this.rolls = qqfc.getRollNum();
        this.corrects = qqfc.getCorrectAns();
        this.wrongs = qqfc.getWrongAns();
        this.noanss = qqfc.getNoAns();
    }

    
    
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         piechart();
    }
    
    private void piechart(){
         ObservableList<PieChart.Data> pie = FXCollections.observableArrayList(
                new PieChart.Data("WRONG ANSWER",wrongs),
                new PieChart.Data("NOT ATTEMPTED",noanss),
                new PieChart.Data("CORRECT ANSWER",corrects)
        );
        pieChart.setData(pie);
        pieChart.setTitle("VISUAL ANALYSIS");
        pieChart.setClockwise(true);
        pieChart.setAnimated(true);
       // pieChart.setBackground(Background.EMPTY);
      // pieChart.setLabelsVisible(true);
    }
    
}
