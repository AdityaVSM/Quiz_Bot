package swing;

import Models.QuestionsModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Questions extends JFrame{
    private JPanel mainPanel;
    private JList questionList;
    private JButton quitButton;
    private JButton submitButton;
    private JPanel bottomPane;
    private JScrollPane questionsScrollPane;
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
            listModel.addElement((questions.get(i)+"\n\n").toString());
            ArrayList<String> eachQuestionOptions = options.get(i);
            for (int j=0; j<eachQuestionOptions.size(); j++){
                listModel.addElement(eachQuestionOptions.get(j)+"\n");
            }
        }

        //populate question List
        questionList.setFont(new java.awt.Font("sans-serif", 0, 18));
        questionList.setModel(listModel);



    }

    public static void main(String[] args) {
        new Questions(null);
    }

}
