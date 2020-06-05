package treIRad;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

/**
 * Class that handles the graphical user interface of tre i rad
 *
 * @author Amir
 */
public class GUI extends JFrame {

    private final Control control;

    private final JButton jb1 = new JButton("1");
    private final JButton jb2 = new JButton("2");
    private final JButton jb3 = new JButton("3");
    private final JButton jb4 = new JButton("4");
    private final JButton jb5 = new JButton("5");
    private final JButton jb6 = new JButton("6");
    private final JButton jb7 = new JButton("7");
    private final JButton jb8 = new JButton("8");
    private final JButton jb9 = new JButton("9");

    private ImageIcon playerIcon;
    private ImageIcon aiIcon;
    private ImageIcon backgroundIcon;

    private final JPanel pnlNorth = new JPanel(new GridLayout(1, 3));
    private final JPanel pnlMain = new JPanel(new GridLayout(3, 3));

    private final JLabel lblScore = new JLabel("Score: ");
    private final JLabel lblRoundsLeft = new JLabel("Remaining battles: ");
    private final JLabel lblResult = new JLabel("");

    /**
     * Constructor that sets up the GUI
     *
     * @param control a control object so that the class can communicate with the control class.
     */
    protected GUI(Control control) {


        this.control = control;
        setIcons();

        setLayout(new BorderLayout());

        pnlNorth.add(lblResult);
        pnlNorth.add(lblScore);
        pnlNorth.add(lblRoundsLeft);

        pnlMain.add(jb1);
        pnlMain.add(jb2);
        pnlMain.add(jb3);
        pnlMain.add(jb4);
        pnlMain.add(jb5);
        pnlMain.add(jb6);
        pnlMain.add(jb7);
        pnlMain.add(jb8);
        pnlMain.add(jb9);

        add(pnlNorth, BorderLayout.NORTH);
        add(pnlMain, BorderLayout.CENTER);


        Listener listener = new Listener();
        jb1.addActionListener(listener);
        jb2.addActionListener(listener);
        jb3.addActionListener(listener);
        jb4.addActionListener(listener);
        jb5.addActionListener(listener);
        jb6.addActionListener(listener);
        jb7.addActionListener(listener);
        jb8.addActionListener(listener);
        jb9.addActionListener(listener);

        setSize(700, 700);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("Tre på i rad");
        setVisible(true);


    }

    /**
     * Method to show how many rounds there are remaining
     *
     * @param rounds how many rounds remaining
     */
    protected void setRoundsLeft(int rounds) {
        lblRoundsLeft.setText("Remaining battles: " + rounds);
    }

    /**
     * Method to update the GUI with the current score
     *
     * @param score value for the players score
     */
    protected void setScore(int score) {
        lblScore.setText("Score: " + score);
    }

    /**
     * Not yet implemented method. To show the results of the previous battle.
     *
     * @param result Shows if the last battle was a win/draw/loss.
     */
    protected void setLblResult(String result) {
        lblResult.setText("The last battle was a " + result);
    }

    /**
     *
     */
    private void setBackground() {
        jb1.setIcon(backgroundIcon);
        jb1.setDisabledIcon(backgroundIcon);
        jb2.setIcon(playerIcon);
        jb3.setIcon(playerIcon);
    }

    /**
     * Method for reading in and setting the background imageicon, the player and the ai imageicons.
     */
    private void setIcons() {
        try {

            Image backgroundImg = ImageIO.read(new File("src/resources/TreBackground.jpg"));
            Image playerImg = ImageIO.read(new File("src/resources/TreShip.jpg"));
            Image aiImg = ImageIO.read(new File("src/resources/TreInvader.jpg"));

            backgroundIcon = new ImageIcon(backgroundImg);
            playerIcon = new ImageIcon(playerImg);
            aiIcon = new ImageIcon(aiImg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * Returns the imageicon for player or ai, based on whos turn it currently is.
     *
     * @param turn Enum that shows if its player or ai turn
     * @return imageicon that represents the player or Ai.
     */
    private ImageIcon getImageIcon(Turn turn) {
        if (turn == Turn.Player) {
            return playerIcon;
        }
        return aiIcon;
    }

    /**
     * Method for displaying a popup window
     *
     * @param str the text that the popup shows.
     */
    protected void winPopUp(String str) {
        JOptionPane.showMessageDialog(null, str);
    }


    /**
     * Method for placing the player or ai's imageicon on a JButton on the gui. Then disables that button.
     *
     * @param i    represents a button/square on the board
     * @param turn whos turn it currently is
     */
    protected void setJb(int i, Turn turn) {
        JButton jb = new JButton();

        //int value is transformed to the matching JButton inside the switch statement
        switch (i) {
            case 1:
                jb = jb1;
                break;
            case 2:
                jb = jb2;
                break;
            case 3:
                jb = jb3;
                break;
            case 4:
                jb = jb4;
                break;
            case 5:
                jb = jb5;
                break;
            case 6:
                jb = jb6;
                break;
            case 7:
                jb = jb7;
                break;
            case 8:
                jb = jb8;
                break;
            case 9:
                jb = jb9;
                break;
        }

        jb.setIcon(getImageIcon(turn));
        jb.setDisabledIcon(getImageIcon(turn));
        jb.setEnabled(false);

    }


    /**
     * Method for resetting the board, removing all imageicons and setting all buttons enabled.
     */
    protected void resetGui() {
        jb1.setIcon(null);
        jb2.setIcon(null);
        jb3.setIcon(null);
        jb4.setIcon(null);
        jb5.setIcon(null);
        jb6.setIcon(null);
        jb7.setIcon(null);
        jb8.setIcon(null);
        jb9.setIcon(null);

        jb1.setDisabledIcon(null);
        jb2.setDisabledIcon(null);
        jb3.setDisabledIcon(null);
        jb4.setDisabledIcon(null);
        jb5.setDisabledIcon(null);
        jb6.setDisabledIcon(null);
        jb7.setDisabledIcon(null);
        jb8.setDisabledIcon(null);
        jb9.setDisabledIcon(null);

        jb1.setEnabled(true);
        jb2.setEnabled(true);
        jb3.setEnabled(true);
        jb4.setEnabled(true);
        jb5.setEnabled(true);
        jb6.setEnabled(true);
        jb7.setEnabled(true);
        jb8.setEnabled(true);
        jb9.setEnabled(true);

        setIcons();
    }


    /**
     * Actionlistener class for the GUI
     *
     * @author Amir
     */
    private class Listener implements ActionListener {

        /**
         * Method for relaying to the control class that a button has been pressed
         *
         * @param e
         */
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == jb1) {
                control.playerMove(1);

            }
            if (e.getSource() == jb2) {
                control.playerMove(2);
            }

            if (e.getSource() == jb3) {
                control.playerMove(3);

            }
            if (e.getSource() == jb4) {
                control.playerMove(4);

            }
            if (e.getSource() == jb5) {
                control.playerMove(5);

            }
            if (e.getSource() == jb6) {
                control.playerMove(6);

            }
            if (e.getSource() == jb7) {
                control.playerMove(7);

            }
            if (e.getSource() == jb8) {
                control.playerMove(8);

            }
            if (e.getSource() == jb9) {
                control.playerMove(9);

            }

        }
    }

}
