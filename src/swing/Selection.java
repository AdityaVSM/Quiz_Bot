package swing;

import javax.swing.*;
import java.awt.*;

public class Selection extends JFrame {
    private JPanel mainPanel;
    private JLabel heading;
    private JPanel topPanel;
    private JPanel bottomPanel;
    private JPanel bottomLeftPanel;
    private JPanel bottomRightPanel;
    private JPanel leftRadioButtonsPanel;
    private JPanel rightRadioButtonPanel;

    //difficulty
    JRadioButton jRadioButtonA = new JRadioButton("Easy");
    JRadioButton jRadioButtonB = new JRadioButton("Medium");
    JRadioButton jRadioButtonC = new JRadioButton("Hard");

    //topic
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
    }

    public void setTopic(){
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

        //try using Button group and disable buttons once any button is clicked

    }

    public void setDifficulty(){
        jRadioButtonA.setFont(new Font("Courier",Font.PLAIN,20));
        jRadioButtonB.setFont(new Font("Courier",Font.PLAIN,20));
        jRadioButtonC.setFont(new Font("Courier",Font.PLAIN,20));

        rightRadioButtonPanel.setLayout(new BoxLayout(rightRadioButtonPanel, BoxLayout.Y_AXIS));
        rightRadioButtonPanel.add(jRadioButtonA);
        rightRadioButtonPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        rightRadioButtonPanel.add(jRadioButtonB);
        rightRadioButtonPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        rightRadioButtonPanel.add(jRadioButtonC);
    }

}
