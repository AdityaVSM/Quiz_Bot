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
    private JList questionList;
    private JButton quitButton;
    private JButton submitButton;
    private JPanel bottomPane;
    private JScrollPane questionsScrollPane;
    private JLabel questionsHeading;
    ArrayList<QuestionsModel> questionObjects;

    public Questions(ArrayList<QuestionsModel> questionObjects){
        this.questionObjects = questionObjects;

        setSize(700,500);
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
        questionsHeading.setText(topicSelected+"                  "+difficultySelected);

        //get questions options and answer
        ArrayList<String> questions = new ArrayList<>();
        ArrayList<ArrayList<String>> options = new ArrayList<>();
        ArrayList<Integer> correctAnsIndex = new ArrayList<>();

        for(int i=0 ;i<questionObjects.size(); i++){
            questions.add(questionObjects.get(i).getQuestion());
            options.add(questionObjects.get(i).getOptions());
            correctAnsIndex.add(questionObjects.get(i).getCorrectAnsIndex());
        }

        DefaultListModel listModel = new DefaultListModel();
        for(int i=0; i<questionObjects.size(); i++){
            JLabel questionLabel = new JLabel();
            questionLabel.setText(
                    "<html>"+
                            questions.get(i)+"<br>"+
                    "</html>"
            );
            listModel.addElement(questionLabel.getText());
            ArrayList<String> eachQuestionOptions = options.get(i);
            for (int j=0; j<eachQuestionOptions.size(); j++){
                listModel.addElement(eachQuestionOptions.get(j)+"\n");
            }
            JLabel extraSpace = new JLabel();
            extraSpace.setText(
                    "<html> <br><br> </html>"
            );
            listModel.addElement(extraSpace.getText());
        }

        //populate question List
        questionList.setFont(new java.awt.Font("sans-serif", 0, 18));
        questionList.setModel(listModel);


        questionList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }
        });
    }

    public static void main(String[] args) {
        new Questions(null);
    }

}
