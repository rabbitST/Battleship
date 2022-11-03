package com.codecool.battleship.gui;



import com.codecool.battleship.dao.DAO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class HighScore extends JLayeredPane {
    JLabel highScoreLabel;
    String[][] highScore;


    HighScore(Display display) {
        setBounds(0, 0, 1600, 800);
        setBackground(Color.YELLOW);
        setVisible(false);
        setLayout(null);

        highScoreLabel = new JLabel();
        highScoreLabel.setBounds(220, 100, 600, 600);
        highScoreLabel.setFont(new Font("Lucida", Font.BOLD, 24));
        highScoreLabel.setForeground(Color.WHITE);

        try {
            highScore = new DAO().getDatabase();
        } catch (IOException e) {
            e.printStackTrace();
            return;
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
            return;
        }

        int row=0;
        StringBuilder highScoreTable=new StringBuilder();
        highScoreTable.append("<html><table><tr><td style='width:50px'>Place</td><td " +
                "style='width:180px'>Name</td><td " +
                "style='width:50px;'>Points</td></tr>");
        for (String[] field: highScore
        ) {
            row++;
            highScoreTable.append("<tr><td>");
            highScoreTable.append(row);
            highScoreTable.append("</td><td>");
            highScoreTable.append(field[0].replaceAll("([^A-z ])",""));
            highScoreTable.append("</td><td style='text-align:center;'><p>");
            highScoreTable.append(field[1]);
            highScoreTable.append("</p></td></tr>");
        }
        highScoreTable.append("</table></html>");
        highScoreLabel.setText(highScoreTable.toString());
        add(highScoreLabel, Integer.valueOf(2));
        highScoreLabel.setVisible(true);

        ImageIcon highScoreBackgroundIMG = new ImageIcon("src/main/java/com/codecool/battleship/iconsOfComponents/HSBackground.png");
        JLabel backGround = new JLabel(highScoreBackgroundIMG);
        backGround.setBounds(0, 0, 1600, 800);
        backGround.setLayout(null);
        backGround.setVisible(true);
        add(backGround, Integer.valueOf(1));

        JButton buttonMainMenu = new JButton("Main menu");
        buttonMainMenu.setBounds(260, 670, 300, 42);
        buttonMainMenu.setFont(new Font("Lucida", Font.BOLD, 24));
        buttonMainMenu.setMargin(new Insets(2, 2, 2, 2));
        buttonMainMenu.setVisible(true);
        this.add(buttonMainMenu, Integer.valueOf(2));

        buttonMainMenu.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                display.getHighScore().setVisible(false);
                display.getMainMenu().setVisible(true);
            }
        });
    }
}
