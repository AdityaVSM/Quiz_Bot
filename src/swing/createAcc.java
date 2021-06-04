package swing;

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
                new DisplayProfile(userName);
            }
        });
        if(textField1.getText() != null){
            userName = textField1.getText();
        }
    }
}
