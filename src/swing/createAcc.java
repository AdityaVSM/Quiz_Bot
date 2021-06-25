package swing;

import Models.UserModel;

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
    private JLabel errorText;
    private JPanel errorTextPanel;
    public String userName;

    public createAcc(){
        errorText.setVisible(false);
        textField1.setText("");
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
                if(textField1.getText().equals("")){
                    errorText.setVisible(true);
                    int delay = 5000; //milliseconds
                    ActionListener taskPerformer = new ActionListener() {
                        public void actionPerformed(ActionEvent evt) {
                            errorText.setVisible(false);
                        }
                    };
                    new javax.swing.Timer(delay, taskPerformer).start();
                }else{
                    userName = textField1.getText();
                    UserModel user = new UserModel(userName); //If user already exists in file login else create acc
                    user.setMatchesPlayed(12);  //get data from file
                    user.setScore(450);         //get data from file
                    new DisplayProfile(userName,user);
                    dispose();
                }
            }
        });


    }
}
