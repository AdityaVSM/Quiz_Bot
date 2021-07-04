package swing;

import javax.imageio.ImageIO;
import javax.swing.*;
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
    private JButton exitButton;
    private BufferedImage mainQuizImage;



    public Home() {
        setSize(1500,800);
        setContentPane(mainPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

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
        imageLabel = new JLabel(new ImageIcon("images/quizImage.jpg"));
    }
}
