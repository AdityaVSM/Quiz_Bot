package swing;

import Models.UserModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DisplayProfile extends JFrame{

    private JPanel mainPanel;
    private JList list1;
    private JLabel heading;
    private JButton startNewGameButton;
    private JButton exitButton;
    private UserModel user;

    public DisplayProfile(String name, UserModel user){
        this.user = user;
        setSize(700,500);
        setContentPane(mainPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        String [] ListData={"\n","UserModel name : \t "+name , "Score: \t" +user.getScore(), "Matches played: \t"+user.getMatchesPlayed(), "\n"};
        list1.setListData(ListData);

        //data has type Object[]
        list1.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        list1.setLayoutOrientation(JList.HORIZONTAL_WRAP);
        list1.setVisibleRowCount(-1);

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        startNewGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new Selection();
            }
        });
    }


}
