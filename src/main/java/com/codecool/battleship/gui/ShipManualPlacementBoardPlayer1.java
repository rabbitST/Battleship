package com.codecool.battleship.gui;

import com.codecool.battleship.Board;
import com.codecool.battleship.util.SquareStatus;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;

import static com.codecool.battleship.util.Constant.BOARD_COLOR;

public class ShipManualPlacementBoardPlayer1 extends JLayeredPane {

    private final int DIMENSION = 10;
    Display display;
    private JLayeredPane placingContainer;
    private JLabel placingBoard, placingPositionContainer, playerID;
    private JTextField placingPositionInput;
    private JRadioButton orientationHorizontal, orientationVertical;
    private JButton generateFleet, startGame;

    public ShipManualPlacementBoardPlayer1(Display display) {

        this.setBounds(0, 0, 1600, 800);
        this.setVisible(false);
        this.setLayout(null);

        placingBoard = new JLabel();
        placingBoard.setBounds(640, 50, 660, 660);
        placingBoard.setVisible(true);
        placingBoard.setLayout(null);


        JLabel backGround = new JLabel(new ImageIcon("src/main/java/com/codecool/battleship/iconsOfComponents/shipsBckgd.png"));
        backGround.setBounds(0, 0, 1600, 800);
        add(backGround, Integer.valueOf(0));
        placingPositionContainer = new JLabel();
        placingPositionContainer.setBounds(240, 50, 400, 660);
        placingPositionContainer.setFont(new Font("Lucida", Font.BOLD, 26));
        placingPositionContainer.setLayout(null);
        placingPositionContainer.setBackground(Color.cyan);
        placingPositionContainer.setForeground(Color.WHITE);
        placingPositionContainer.setOpaque(true);
        placingPositionContainer.setVisible(true);

        playerID = new JLabel("Player 1. Ship-position:");
        playerID.setBounds(5, 10, 300, 30);
        playerID.setFont(new Font("Lucida", Font.BOLD, 26));
        playerID.setLayout(null);
        playerID.setBackground(Color.cyan);
        playerID.setForeground(Color.WHITE);

        placingPositionInput = new JTextField(10);
        placingPositionInput.setBounds(300, 5, 100, 50);
        placingPositionInput.setFont(new Font("Arial", Font.BOLD, 40));
        placingPositionInput.setHorizontalAlignment(JTextField.CENTER);
        placingPositionInput.setBackground(Color.WHITE);
        placingPositionInput.setOpaque(true);
        placingPositionInput.setVisible(true);
        int fontSize = 24;
        int width = 100;
        orientationHorizontal = new JRadioButton("Horizontal");
        orientationHorizontal.setBounds(0, 55, width, 30);
        orientationHorizontal.setFont(new Font("Lucida", Font.BOLD, fontSize));
        orientationHorizontal.setOpaque(false);
        orientationHorizontal.setForeground(Color.WHITE);

        orientationVertical = new JRadioButton("Vertical");
        orientationVertical.setBounds(100, 55, width, 30);
        orientationVertical.setFont(new Font("Lucida", Font.BOLD, fontSize));
        orientationVertical.setOpaque(false);
        orientationVertical.setForeground(Color.WHITE);

        generateFleet = new JButton("Generate My Fleet!");
        generateFleet.setBounds(5, 100, 200, 30);
        generateFleet.setFont(new Font("Lucida", Font.BOLD, 20));
        generateFleet.setMargin(new Insets(2, 2, 2, 2));

        startGame = new JButton("Let's play!");
        startGame.setBounds(245, 100, 150, 30);
        startGame.setFont(new Font("Lucida", Font.BOLD, 20));
        startGame.setMargin(new Insets(2, 2, 2, 2));

        placingPositionContainer.add(placingPositionInput, Integer.valueOf(2));
        placingPositionContainer.add(playerID, Integer.valueOf(2));
        placingPositionContainer.add(orientationHorizontal, Integer.valueOf(2));
        placingPositionContainer.add(orientationVertical, Integer.valueOf(2));
        placingPositionContainer.add(placingPositionInput, Integer.valueOf(2));
        placingPositionContainer.add(generateFleet, Integer.valueOf(2));
        placingPositionContainer.add(startGame, Integer.valueOf(2));
        add(placingPositionContainer, Integer.valueOf(1));

        add(placingBoard, Integer.valueOf(1));

        JButton backMainMenu = new JButton("Main menu");
        backMainMenu.setBounds(540, 300, 150, 30);
        backMainMenu.setFont(new Font("Lucida", Font.BOLD, 20));
        backMainMenu.setMargin(new Insets(2, 2, 2, 2));

        boolean isShipsPlaced = true;
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                placingPositionInput.requestFocus();
            }
        });
        Action action = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                put ships on board
//                makeMove(fieldMove.getX());
//                fieldMove.setText("");
//                moveInputPanel();

                if (isShipsPlaced) {
                    display.getShipPlacementBoardPlayer1().setVisible(false);
                    display.getGameBoard().setVisible(true);
                    display.getFieldMove().requestFocus();
                }
            }
        };
        placingPositionInput.addActionListener(action);

    }

    public void drawFleetOnTable(Board board) {
        Border whiteBorder = BorderFactory.createLineBorder(Color.white, 1);
        JLabel label;
        for (int i = 0; i <= DIMENSION; i++) {
            for (int j = 0; j <= DIMENSION; j++) {
                if (i == 0 && j != 0) {
                    label = new JLabel(Character.toString(j + 64), SwingConstants.CENTER);
                    label.setFont(new Font("Arial", Font.BOLD, 40));
                } else {
                    if (j == 0 && i != 0) {
                        label = new JLabel(String.valueOf(i), SwingConstants.CENTER);
                        label.setFont(new Font("Arial", Font.BOLD, 40));

                    } else {

                        if (i > 0 && j>0 && board.getOcean()[i-1][j-1].getSquareStatus().equals(SquareStatus.SHIP)) {
                            label = new JLabel(new ImageIcon("src/main/java/com/codecool/battleship/iconsOfComponents/battleShipIcon.png"));
                        } else {
                            label = new JLabel();
                        }
                    }
                }
                label.setBounds(j * 60, i * 60, 60, 60);
                label.setBackground(BOARD_COLOR);
                label.setBorder(whiteBorder);
                label.setOpaque(true);
                label.setVisible(true);
                drawSquaresOnPlayersBoard(label, placingBoard);
            }
        }
        placingBoard.update(placingBoard.getGraphics());
    }


    public JTextField getPlacingPositionInput() {
        return placingPositionInput;
    }

    public void drawSquaresOnPlayersBoard(JLabel label, JLabel placingBoard) {
        placingBoard.add(label, Integer.valueOf(1));
    }

    public JButton getGenerateFleet() {
        return generateFleet;
    }
}
