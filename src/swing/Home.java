package swing;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class Home extends JFrame {
    private JPanel mainPanel;
    private JPanel imagePanel;
    private JButton signInButton;
    private JLabel imageLabel;
    private JLabel introLabel;
    private JButton exitButton;
    private BufferedImage mainQuizImage;



    public Home() {
        setSize(1000,600);
        setContentPane(mainPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        introLabel.setText("<html><ul><li>Welcome to our Quiz!</li><br><li>You will find numerous random <br> questions on different topics you choose</li><br><li>If you have already played this game <br> your score will be added to your<br>score in previous game</li><br><li>Random questions are selected <br>from an online API so you need not to <br> worry about repetitive questions</li></ul></html>");
        signInButton.setBorderPainted(false);
        signInButton.setBackground(new Color(105,110,175));// inside the brackets your rgb color value like 255,255,255
        signInButton.setForeground(Color.YELLOW);
        signInButton.setFocusPainted(false);

        signInButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new createAcc();
            }
        });

    }

    public static void main(String[] args) {
        new Home();
    }

    private void createUIComponents() {
        imageLabel = new JLabel(new ImageIcon("images/download.jfif"));
    }
}
