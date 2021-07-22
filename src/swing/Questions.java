package swing;

import Models.QuestionsModel;
import Models.UserModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
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
    private JLabel scoreLabel;
    ArrayList<QuestionsModel> questionObjects;
    ArrayList<String> questions;
    ArrayList<ArrayList<String>> options;
    ArrayList<Integer> correctAnsIndex;
    ArrayList<String> correctOptions;
    int score = 0;

    public Questions(ArrayList<QuestionsModel> questionObjects, String name, ArrayList<Long> userData, UserModel user) {
        setSize(new Dimension(Toolkit.getDefaultToolkit().getScreenSize()));
        this.questionObjects = questionObjects;
        setContentPane(mainPanel);
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
                //Save score and matches played in user file
                long oldScore = userData.get(0);
                long oldNumberOfMatchesPlayed = userData.get(1);
                userData.set(0,oldScore+score);
                userData.set(1,oldNumberOfMatchesPlayed+1);
                try {
                    user.storeData(name,userData);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                dispose();
                try {
                    new DisplayProfile(name,user,userData);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });

        //set heading
        questionsHeading.setText("<html>"+topicSelected+"\t"+difficultySelected+"</html>");
        //get questions options and answer
        questions = new ArrayList<>();
        options = new ArrayList<>();
        correctAnsIndex = new ArrayList<>();
        correctOptions = new ArrayList<>();
        createUI();
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



    public HashMap<Integer,String> createUI(String... args){
        HashMap<Integer,String> selectedOptions = new HashMap<>();
        for (int i = 0; i < questionObjects.size(); i++) {
            String currentQuestion = questionObjects.get(i).getQuestion();
            //cleaning question
            currentQuestion = cleanString(currentQuestion);

            questions.add(currentQuestion);
            options.add(questionObjects.get(i).getOptions());
            correctAnsIndex.add(questionObjects.get(i).getCorrectAnsIndex());
            correctOptions.add(questionObjects.get(i).getCorrectAns());
        }

        questionsAndOptionsPanel.setLayout(new BoxLayout(questionsAndOptionsPanel, BoxLayout.Y_AXIS));
        Font questionsFont = new Font(Font.SANS_SERIF, Font.PLAIN, 18);
        Font optionFont = new Font(Font.SANS_SERIF,Font.PLAIN,16);

        for (int i = 0; i < questions.size(); i++) {
            JLabel questionLabel = new JLabel((i+1)+" "+questions.get(i));
            questionLabel.setFont(questionsFont);
            questionsAndOptionsPanel.add(questionLabel);
            questionsAndOptionsPanel.add(Box.createRigidArea(new Dimension(0, 3)));

            ArrayList<JRadioButton> optionJRadioButtons = new ArrayList<>();
            ButtonGroup optionsButtonGroup = new ButtonGroup();
            for (String eachOptions : options.get(i)) {
                //cleaning each options
                eachOptions = cleanString(eachOptions);

                JRadioButton eachOptionJRadioButton = new JRadioButton(eachOptions);
                eachOptionJRadioButton.setActionCommand("hi");
                optionJRadioButtons.add(eachOptionJRadioButton);

                optionsButtonGroup.add(eachOptionJRadioButton);
                eachOptionJRadioButton.setFont(optionFont);

                questionsAndOptionsPanel.add(eachOptionJRadioButton);
            }

            for(JRadioButton jr : optionJRadioButtons){
                int finalI = i;
                jr.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if(jr.isSelected()){
                            selectedOptions.put(finalI,jr.getText());
                            disableButtons(optionJRadioButtons,jr.getText(),finalI);

                            if(jr.getText().equals(correctOptions.get(finalI))){
                                jr.setBackground(Color.green);
                                score++;
                                scoreLabel.setText(Integer.toString(score));
                            }else{
                                jr.setBackground(Color.red);
                            }
                        }
                    }
                });
            }
            questionsAndOptionsPanel.add(Box.createRigidArea(new Dimension(0, 15)));

        }
        return selectedOptions;
    }

    private void disableButtons(ArrayList<JRadioButton> optionJRadioButtons, String text, int questionIndex) {
        for(JRadioButton i : optionJRadioButtons){
            if(i.getText().equals(text))
                continue;
            else{
                i.setEnabled(false);
                if(i.getText().equals(correctOptions.get(questionIndex))){
                    i.setBackground(Color.green);
                }
            }
        }
    }


}
