package fxml;

import com.jfoenix.controls.JFXTextArea;
import java.awt.Toolkit;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import static java.lang.Integer.parseInt;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import java.util.TreeMap;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Md Ataullha
 */
public class quizquestionFXMLController implements Initializable {

    @FXML
    private Label quizTitleForExam;
    @FXML
    private Label quizTimerForExam;
    @FXML
    private RadioButton option3;
    // private ToggleGroup Options;
    @FXML
    private RadioButton option1;
    @FXML
    private RadioButton option2;
    @FXML
    private RadioButton option4;
    @FXML
    private Label numberOfCertainQuestion;
    @FXML
    private Label numberofTotalQuestion;
    @FXML
    private Button quizCompleteSubmit;
    @FXML
    private Button nextQuestion;
    private Button previousQuestion;
    @FXML
    private TextArea quizQuestion;

    public String x, y, sName, SRN;

    static boolean stopclock = false;
    static int qnqbtn = 0, qpqbtn = 0;

    public static int skipno = 0, rep = 0;

    public static int correct_answer = 0, wrong_answer = 0, not_attempted = 0;
    public static String examinee_name;
    public static String examinee_reg_no;
    public static String honolulu;
    public static String curquesno, maincurquesno;
    public static int loadnum = 0;

    quizPanController qpcj = new quizPanController();

    public static TreeMap<String, String> courrectanswerfromfile = new TreeMap<>();
    public static TreeMap<String, String> answerfromstudent = new TreeMap<>();

    public static String[] debq = new String[6];
    // private ToggleGroup radioGroup;

    public static List<String[]> questionslist = new ArrayList<String[]>();

    @FXML
    private RadioButton bydefaultradio;
    private JFXTextArea option1textarea;
    private JFXTextArea option2textarea;
    private JFXTextArea option3textarea;
    private JFXTextArea option4textarea;

    public quizquestionFXMLController() {
        this.x = qpcj.getTargetquiztitle();
        this.y = qpcj.getNumberofques();
    }

    public static int min = 0, hour = 0, sec = 0, totaltime = 0, inti = 0;
    public static boolean firsttime = true;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        quizTitleForExam.setText(x);
        numberofTotalQuestion.setText(y);
        inithashmap();
        try {
            // quizQuestionlist();
            if (firsttime) {
                quizQuestion();
                //answerKiDila();
            }
        } catch (IOException ex) {
            Logger.getLogger(quizquestionFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }

        this.hideSubmitButton();
//        this.hidePrevButton();
        quizTime();
        def();
        try {
            cursnameandreg();
        } catch (IOException ex) {
            Logger.getLogger(quizquestionFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
        GT();
    }

    @FXML
    private void quizNextQuestion(ActionEvent event) throws IOException {
        answerKiDila();
        quizQuestion();
        //if(parseInt(maincurquesno)<=(lines()-1)){
        hideSubmitButton();
        // }
        if ((loadnum) == (lines())) {
            hideNextButton();
            showSubmitButton();

        }
    }

    private void quizQuestion() throws IOException {

        if (firsttime) {
            quizQuestionlist();
        }
        firsttime = false;

        String[] q = new String[6];

        q = questionslist.get(loadnum);

        quizQuestion.setText(q[1]);
        option1.setText(q[2]);//list.get(0));
        option2.setText(q[3]);//list.get(1));
        option3.setText(q[4]);//list.get(2));
        option4.setText(q[5]);//list.get(3));
        maincurquesno = q[0];
        numberOfCertainQuestion.setText((loadnum+1) + "");

        loadnum++;

    }

    public void quizQuestionlist() throws FileNotFoundException, IOException {///////////////////////////////////////////need to change//////////////////////////////////////////////////////////

        //System.out.println("Number of line"+lines());
        for (int tt = 1; tt <= parseInt(y); tt++) {

            String question = "", opt1 = null, opt2 = null, opt3 = null, opt4 = null;

            String fname;
            fname = x + "question.txt";
            BufferedReader br = null;
            br = new BufferedReader(new FileReader(fname));

            for (int i = 1; i <= skipno; i++) {
                br.readLine();
            }

            br.readLine();

//            while ((br.readLine()) != null) {
            maincurquesno = br.readLine();
            br.readLine();
            //int countjj = 0;
            for (int jj = 1; jj <= 10000; jj++) {
                question = question + br.readLine() + "\n";
            }
            br.readLine();
            opt1 = br.readLine();
            br.readLine();
            opt2 = br.readLine();
            br.readLine();
            opt3 = br.readLine();
            br.readLine();
            opt4 = br.readLine();
            br.readLine();
            br.readLine();
            br.readLine();//---------------------------->>>><<<<>>>><<<<>>>><<<>>>><<<<>>>><<<<>>>><<<<>>><<<>>><<<
            // System.out.println(br.readLine());
            // rep++;
            // curquesno = rep + "";
            skipno += 10014;

            String[] singlequestion = new String[6];

            singlequestion[0] = maincurquesno;
            singlequestion[1] = question;
            singlequestion[2] = opt1;
            singlequestion[3] = opt2;
            singlequestion[4] = opt3;
            singlequestion[5] = opt4;
            // break;
            ///continue;
            // }

            questionslist.add(singlequestion);

        }
        Collections.shuffle(questionslist);

    }

    private int lines() throws FileNotFoundException, IOException {///////////////////////////////////////////need to change//////////////////////////////////////////////////////////
        BufferedReader bread = null;
        String flname;
        int line = 0;
        flname = x + "question.txt";
        bread = new BufferedReader(new FileReader(flname));
        while (bread.readLine() != null) {
            line++;
        }
        return line / 10014;
    }

    private void hideNextButton() {
        this.nextQuestion.setVisible(false);
    }

    private void showNextButton() {
        this.nextQuestion.setVisible(true);
    }

    private void hidePrevButton() {
        this.previousQuestion.setVisible(false);
    }

    private void showPrevButton() {
        this.previousQuestion.setVisible(true);
    }

    private void hideSubmitButton() {
        this.quizCompleteSubmit.setVisible(false);
    }

    private void showSubmitButton() {
        this.quizCompleteSubmit.setVisible(true);
    }

    private void answerKiDila() {///////////////////////////////////////////need to change//////////////////////////////////////////////////////////

        String kidagaila = "";

        if (option1.isSelected()) {
            kidagaila = kidagaila + "1";

            System.out.println("selected option=" + kidagaila);
        }
        if (option2.isSelected()) {
            kidagaila = kidagaila + "2";
            System.out.println("selected option==" + kidagaila);
        }
        if (option3.isSelected()) {
            kidagaila = kidagaila + "3";
            System.out.println("selected option===" + kidagaila);
        }
        if (option4.isSelected()) {
            kidagaila = kidagaila + "4";
            System.out.println("selected option====" + kidagaila);
        }

        System.out.println("selected option" + kidagaila);
        // System.out.println("selected option"+maincurquesno);

        if (kidagaila.trim().isEmpty()) {
            answerfromstudent.put(maincurquesno + "", "0");
        } else {
            answerfromstudent.put(maincurquesno + "", kidagaila);
        }

        option1.setSelected(false);
        option2.setSelected(false);
        option3.setSelected(false);
        option4.setSelected(false);
        bydefaultradio.setSelected(true);
    }

    @FXML
    private void amarQuizSesKintuRabbyKajKoreNai(ActionEvent event) throws IOException {
        // answerKiDila();

        //quizQuestion()
        //koytaThikAche();
        //timer.cancel();
        totaltime = 0;
        //koytaThikAche();
        stopclock = true;

    }

    private void asolAnswer() throws IOException {

        BufferedReader boiread = null;
        String filenam;
        int line = 0;
        filenam = x + "answer.txt";
        boiread = new BufferedReader(new FileReader(filenam));
        String asol;
        int i = 1;
        while ((asol = boiread.readLine()) != null) {
            courrectanswerfromfile.put(i + "", asol);
            i++;
        }
    }

    private void koytaThikAche() throws IOException {
        cursnameandreg();
        //answerKiDila();
        asolAnswer();
        for (int iii = 1; iii <= parseInt(y); iii++) {
            if ((courrectanswerfromfile.get(iii + "")).trim().equals(answerfromstudent.get(iii + ""))) {
                correct_answer++;
            } else {
                if ((answerfromstudent.get(iii + "")).trim().equals("0")) {
                    not_attempted++;
                } else {
                    wrong_answer++;
                }

            }
        }

        for (int io = 1; io <= parseInt(y); io++) {
            System.out.println(courrectanswerfromfile.get(io + "") + "\n");
        }

        for (int io = 1; io <= parseInt(y); io++) {
            System.out.println(answerfromstudent.get(io + "") + "\n");
        }

        try {
            String hobe = x;
            String tfnm = hobe + "quizresult.txt";
            File afilexyz = new File(tfnm);
            FileWriter frwds = new FileWriter(afilexyz, true);
            BufferedWriter browd = new BufferedWriter(frwds);
            browd.write("STUDENT NAME : " + examinee_name + "\n");
            browd.write("STUDENT REGISTRATION NUMBER : " + examinee_reg_no + "\n");
            browd.write("CORRECT ANSWER : " + correct_answer + "\n");
            browd.write("WRONG ANSWER : " + wrong_answer + "\n");
            browd.write("NOT ATTMPTED : " + not_attempted + "\n");
            browd.close();

        } catch (IOException e) {
        }
    }

    private void quizTime() {
        //   totaltime =  30;
        Timer timer = new Timer();
        TimerTask tt = new TimerTask() {
            @Override
            public void run() {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            TimeConvert();
                        } catch (IOException ex) {
                            System.exit(0);
                        }
                        if (totaltime >= 10) {
                            quizTimerForExam.setTextFill(Color.GREENYELLOW);
                        }
                        if (totaltime < 10) {
                            quizTimerForExam.setTextFill(Color.YELLOW);
                        }
                        if (totaltime < 4) {
                            quizTimerForExam.setTextFill(Color.RED);
                            Toolkit.getDefaultToolkit().beep();
                        }
                        if (totaltime == 0 || stopclock == true) {
                            // stopclock = true;
                            answerKiDila();
                            timer.cancel();
                            try {
                                koytaThikAche();
                                System.out.println("::=============::koytathik");

                            } catch (IOException ex) {
                                Logger.getLogger(quizquestionFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            quizTimerForExam.setTextFill(Color.BLACK);
                            quizTimerForExam.setText("00:00:00");

                            try {
                                System.out.println("::=============::");
                                Parent root = FXMLLoader.load(getClass().getResource("/fxml/StudentQuizReportFXML.fxml"));
                                Stage stage = (Stage) quizTimerForExam.getScene().getWindow();
                                Scene scene = new Scene(root);
                                stage.setScene(scene);
                                //stage.setMaximized(true);
                            } catch (IOException e) {
//                System.exit(0);
                            }

                            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                        }
                    }
                });
            }
        };
        timer.schedule(tt, 0, 1000);
    }

    private void TimeConvert() throws IOException {
        min = (int) TimeUnit.SECONDS.toMinutes(totaltime);
        sec = totaltime - (60 * min);
        hour = (int) TimeUnit.MINUTES.toHours(min);
        min = min - (60 * hour);
        System.out.println(Format(hour) + ":" + Format(min) + ":" + Format(sec));
        quizTimerForExam.setText(Format(hour) + ":" + Format(min) + ":" + Format(sec));
        if (totaltime > 0) {
            totaltime--;

        } else {
            totaltime = 0;
            stopclock = true;

        }
    }

    private String Format(int f) {
        if (f < 10) {
            return "0" + f;
        }
        return f + "";
    }

    private void cursnameandreg() throws FileNotFoundException, IOException {
        BufferedReader boisread = null;
        String filenam8;
        filenam8 = "currentstudent.txt";
        boisread = new BufferedReader(new FileReader(filenam8));
        while ((examinee_name = boisread.readLine()) != null) {
            examinee_reg_no = boisread.readLine();
        }

        try {
            FileOutputStream writer = new FileOutputStream("currentstudent.txt");
            writer.write(("").getBytes());
            writer.close();

            System.out.println(examinee_name);
            System.out.println(examinee_reg_no);

            System.out.println("All WORK PROPERLY...!!");
        } catch (IOException e) {
        }
    }

    public String getStudentName() {
        return this.examinee_name;
    }

    public String getRollNum() {
        return this.examinee_reg_no;
    }

    public int getCorrectAns() {
        return this.correct_answer;
    }

    public int getWrongAns() {
        return this.wrong_answer;
    }

    public int getNoAns() {
        return this.not_attempted;
    }

    private void def() {
        option1.setSelected(false);
        option2.setSelected(false);
        option3.setSelected(false);
        option4.setSelected(false);
        bydefaultradio.setSelected(true);
    }

    private void inithashmap() {
        for (int i = 1; i <= parseInt(y); i++) {
            courrectanswerfromfile.put(i + "", 0 + "");
        }
        for (int j = 1; j <= parseInt(y); j++) {
            answerfromstudent.put(j + "", 0 + "");
        }
    }

    private void GT() {
        try {
            String fxtime = x + "QuizTimerList.txt";
            BufferedReader bri = null;
            bri = new BufferedReader(new FileReader(fxtime));
            while (bri.readLine() != null) {
                honolulu = bri.readLine();
                System.out.println("honolulu" + honolulu);
                totaltime = parseInt(honolulu) * 60;
                break;
            }
            // quizPanController fcb = new quizPanController();
            // fcb.setTIME(tutaltume);
        } catch (IOException e) {

        }

    }
//
//    @FXML
//    private void quizPreviousQuestion(ActionEvent event) {
//    }

}
