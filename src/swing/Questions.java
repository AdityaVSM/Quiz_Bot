package swing;

import Models.QuestionsModel;
import swing.Selection;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static swing.Selection.difficultySelected;
import static swing.Selection.topicSelected;

public class Questions extends JFrame{
    private JPanel mainPanel;
    private JList questionList = new JList();
    private JButton quitButton;
    private JButton submitButton;
    private JPanel bottomPane;
    private JScrollPane questionsScrollPane;
    private JLabel questionsHeading;
    private JPanel questionsAndOptionsPanel;
    ArrayList<QuestionsModel> questionObjects;

    public Questions(ArrayList<QuestionsModel> questionObjects) {
        this.questionObjects = questionObjects;

        setSize(1000, 1000);
        setContentPane(mainPanel);

//        System.out.println(questionObjects);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        //set heading
        questionsHeading.setText(topicSelected + "                  " + difficultySelected);

        //get questions options and answer
        ArrayList<String> questions = new ArrayList<>();
        ArrayList<ArrayList<String>> options = new ArrayList<>();
        ArrayList<Integer> correctAnsIndex = new ArrayList<>();

        for (int i = 0; i < questionObjects.size(); i++) {
            String currentQuestion = questionObjects.get(i).getQuestion();
            //cleaning question
            currentQuestion = cleanString(currentQuestion);

            questions.add(currentQuestion);
            options.add(questionObjects.get(i).getOptions());
            correctAnsIndex.add(questionObjects.get(i).getCorrectAnsIndex());
        }


        questionsAndOptionsPanel.setLayout(new BoxLayout(questionsAndOptionsPanel, BoxLayout.Y_AXIS));
        Font questionsFont = new Font(Font.SANS_SERIF, Font.PLAIN, 18);
        Font optionFont = new Font(Font.SANS_SERIF,Font.PLAIN,16);

        for (int i = 0; i < questions.size(); i++) {
            JLabel questionLabel = new JLabel((i+1)+" "+questions.get(i));
            questionLabel.setFont(questionsFont);
            questionsAndOptionsPanel.add(questionLabel);
            questionsAndOptionsPanel.add(Box.createRigidArea(new Dimension(0, 3)));

            ButtonGroup optionsButtonGroup = new ButtonGroup();
            ArrayList<String> eachQuestionOptions = new ArrayList<>();
            for (String eachOptions : options.get(i)) {
                //cleaning each options
                eachOptions = cleanString(eachOptions);
                JRadioButton eachOptionJRadioButton = new JRadioButton(eachOptions);
                optionsButtonGroup.add(eachOptionJRadioButton);
                eachQuestionOptions.add(eachOptions);
                eachOptionJRadioButton.setFont(optionFont);
                questionsAndOptionsPanel.add(eachOptionJRadioButton);
            }
            questionsAndOptionsPanel.add(Box.createRigidArea(new Dimension(0, 15)));

        }
    }
    public String cleanString(String rawString){
        Map<String, String> map = new HashMap<>();
        map.put("&quot;", "\"");
        map.put("&amp;", "&");
        map.put("&#039;", "\'");
        map.put("&Iacute;", "Í");
        map.put("&ldquo;", "\"");
        map.put("&rdquo;", "\"");

        Pattern p = Pattern.compile("&quot;|&amp;|&#039;|&Iacute;|&ldquo;|&rdquo;");
        Matcher m = p.matcher(rawString);

        StringBuffer sb = new StringBuffer();
        while (m.find()){
            m.appendReplacement(sb, map.get(m.group()));
        }
        m.appendTail(sb);

        return sb.toString();
    }


    public static void main(String[] args) {
        new Questions(null);
    }


}
