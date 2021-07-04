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
            questions.add(questionObjects.get(i).getQuestion());
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
                JRadioButton eachOptionJRadioButton = new JRadioButton(eachOptions);
                optionsButtonGroup.add(eachOptionJRadioButton);
                eachQuestionOptions.add(eachOptions);
                eachOptionJRadioButton.setFont(optionFont);
                questionsAndOptionsPanel.add(eachOptionJRadioButton);
            }
            questionsAndOptionsPanel.add(Box.createRigidArea(new Dimension(0, 15)));

        }
    }
    public static void main(String[] args) {
        new Questions(null);
    }


}
