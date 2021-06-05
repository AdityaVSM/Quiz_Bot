package swing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Enumeration;

public class Selection extends JFrame {
    private JPanel mainPanel;
    private JLabel heading;
    private JPanel topPanel;
    private JPanel bottomPanel;
    private JPanel bottomLeftPanel;
    private JPanel bottomRightPanel;
    private JPanel leftRadioButtonsPanel;
    private JPanel rightRadioButtonPanel;
    private JButton startButton;
    private JButton exitButton;


    //difficulty
    String difficultySelected;
    ButtonGroup difficultyGroup;
    JRadioButton jRadioButtonA = new JRadioButton("Easy");
    JRadioButton jRadioButtonB = new JRadioButton("Medium");
    JRadioButton jRadioButtonC = new JRadioButton("Hard");

    //topic
    String topicSelected;
    ButtonGroup topicGroup;
    JRadioButton jRadioButton1 = new JRadioButton("GK");
    JRadioButton jRadioButton2 = new JRadioButton("Science");
    JRadioButton jRadioButton3 = new JRadioButton("Tech");
    JRadioButton jRadioButton4 = new JRadioButton("Sports");


    public Selection(){
        setSize(700,500);
        setContentPane(mainPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        setTopic();
        setDifficulty();
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new Questions();
            }
        });
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }

    public void disableButtons(JRadioButton[] arr, String text){
        for(JRadioButton i : arr){
            if(i.getText().equals(text))
                continue;
            i.setEnabled(false);
        }
    }
    public String getSelectedButtonText(ButtonGroup buttonGroup) {
        for (Enumeration<AbstractButton> buttons = buttonGroup.getElements(); buttons.hasMoreElements();) {
            AbstractButton button = buttons.nextElement();

            if (button.isSelected()) {
                return button.getText();
            }
        }
        return null;
    }

    public void setTopic(){
        topicGroup = new ButtonGroup();
        topicGroup.add(jRadioButton1);
        topicGroup.add(jRadioButton2);
        topicGroup.add(jRadioButton3);
        topicGroup.add(jRadioButton4);

        JRadioButton[] topicArrGroup = new JRadioButton[]{jRadioButton1,jRadioButton2,jRadioButton3,jRadioButton4};
        jRadioButton1.setFont(new Font("Courier",Font.PLAIN,20));
        jRadioButton2.setFont(new Font("Courier",Font.PLAIN,20));
        jRadioButton3.setFont(new Font("Courier",Font.PLAIN,20));
        jRadioButton4.setFont(new Font("Courier",Font.PLAIN,20));


        leftRadioButtonsPanel.setLayout(new BoxLayout(leftRadioButtonsPanel, BoxLayout.Y_AXIS));
        leftRadioButtonsPanel.add(jRadioButton1);
        leftRadioButtonsPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        leftRadioButtonsPanel.add(jRadioButton2);
        leftRadioButtonsPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        leftRadioButtonsPanel.add(jRadioButton3);
        leftRadioButtonsPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        leftRadioButtonsPanel.add(jRadioButton4);


        for( JRadioButton i :topicArrGroup){
            i.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(i.isSelected()) {
                        topicSelected = getSelectedButtonText(topicGroup);
                        disableButtons(topicArrGroup,topicSelected);
                    }
                    System.out.println(topicSelected);
                }
            });
        }
    }


    public void setDifficulty(){
        difficultyGroup = new ButtonGroup();
        difficultyGroup.add(jRadioButtonA);
        difficultyGroup.add(jRadioButtonB);
        difficultyGroup.add(jRadioButtonC);

        JRadioButton[] difficultyArrGroup = new JRadioButton[]{jRadioButtonA,jRadioButtonB,jRadioButtonC};
        jRadioButtonA.setFont(new Font("Courier",Font.PLAIN,20));
        jRadioButtonB.setFont(new Font("Courier",Font.PLAIN,20));
        jRadioButtonC.setFont(new Font("Courier",Font.PLAIN,20));

        rightRadioButtonPanel.setLayout(new BoxLayout(rightRadioButtonPanel, BoxLayout.Y_AXIS));
        rightRadioButtonPanel.add(jRadioButtonA);
        rightRadioButtonPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        rightRadioButtonPanel.add(jRadioButtonB);
        rightRadioButtonPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        rightRadioButtonPanel.add(jRadioButtonC);

        for( JRadioButton i :difficultyArrGroup){
            i.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(i.isSelected()) {
                        difficultySelected = getSelectedButtonText(difficultyGroup);
                        disableButtons(difficultyArrGroup,difficultySelected);
                    }
                    System.out.println(difficultySelected);
                }
            });
        }
    }
}
