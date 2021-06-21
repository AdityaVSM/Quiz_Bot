package swing;

import Models.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class createAcc extends JFrame{

    private JLabel heading;
    private JLabel createAccText;
    private JTextField textField1;
    private JButton continueButton;
    private JButton exitButton;
    private JPanel mainPanel;
    public String userName;

    public createAcc(){
        setSize(700,300);
        setContentPane(mainPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        continueButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                userName = textField1.getText();
                User user = new User(userName); //If user already exists in file login else create acc
//                System.out.println(user);
                user.setMatchesPlayed(12);  //get data from file
                user.setScore(450);         //get data from file
                new DisplayProfile(userName,user);
            }
        });
        if(textField1.getText() != null){
            userName = textField1.getText();
        }
    }
}
