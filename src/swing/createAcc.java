package swing;

import Models.UserModel;
import org.json.simple.parser.ParseException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

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


        exitButton.setBorderPainted(false);
        exitButton.setBackground(new Color(153, 60, 243));// inside the brackets your rgb color value like 255,255,255
        exitButton.setForeground(Color.white);
        exitButton.setFocusPainted(false);

        continueButton.setBorderPainted(false);
        continueButton.setBackground(new Color(153, 60, 243));// inside the brackets your rgb color value like 255,255,255
        continueButton.setForeground(Color.white);
        continueButton.setFocusPainted(false);


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
                    ArrayList<Long> userData = new ArrayList<>();
                    try {
                        userData = user.getScoreAndMatchesPlayed(userName);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                    if(userData.get(1)==0){
                        try {
                            user.storeData(userName,userData);
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }
                    try {
                        new DisplayProfile(userName,user,userData);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                    dispose();
                }
            }
        });


    }
}
