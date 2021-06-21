package swing;

import Models.QuestionsModel;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Questions extends JFrame{
    private JPanel mainPanel;
    ArrayList<QuestionsModel> questionObjects;

    public Questions(ArrayList<QuestionsModel> questionObjects){
        this.questionObjects = questionObjects;

        setSize(700,500);
        setContentPane(mainPanel);

        System.out.println(questionObjects);
//        TextArea ta = new TextArea();
//
//        for(QuestionsModel objects : questionObjects){
//            ta.append(String.valueOf(objects));
//        }

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

}
