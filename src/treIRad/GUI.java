package treIRad;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class GUI extends JFrame{

    private Control control;

    private JButton jb1 = new JButton("1");
    private JButton jb2 = new JButton("2");
    private JButton jb3 = new JButton("3");
    private JButton jb4 = new JButton("4");
    private JButton jb5 = new JButton("5");
    private JButton jb6 = new JButton("6");
    private JButton jb7 = new JButton("7");
    private JButton jb8 = new JButton("8");
    private JButton jb9 = new JButton("9");

    private ImageIcon playerIcon;
    private ImageIcon aiIcon;


    public GUI (Control control){
        
        this.control=control;
        setLayout(new GridLayout(3,3));

        add(jb1);
        add(jb2);
        add(jb3);
        add(jb4);
        add(jb5);
        add(jb6);
        add(jb7);
        add(jb8);
        add(jb9);

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

        setIcons();
        setSize(700,700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Tre på i rad");
        setVisible(true);

    }

    //Sets icons for each player
    public void setIcons() {
        try {

            Image playerImg = ImageIO.read(new File("images/spaceship.png"));
            Image aiImg = ImageIO.read(new File("images/ufo.png"));

            playerIcon = new ImageIcon(playerImg);
            aiIcon = new ImageIcon(aiImg);
        }catch (IOException e){
            e.printStackTrace();
        }
    }


    //Returns current players icon
    public ImageIcon getImageIcon(Turn turn){
        if (turn==Turn.Player){
            return playerIcon;
        }
        return aiIcon;
    }

    //Pop up window that displays an inputted message
    public void winPopUp(String str){
        JOptionPane.showMessageDialog(null,str);
    }


    //Takes an int value which represesnts a button and an enum which shows whos turn it is.
    // Then sets the current players imageicon on the button and then disables that button
    public void setJb(int i, Turn turn){
        JButton jb = new JButton("");

        //int value is transformed to the matching JButton inside the switch statement
        switch (i) {
            case 1:
                jb = jb1;
                break;
            case 2:
                jb=jb2;
                break;
            case 3:
                jb=jb3;
                break;
            case 4:
                jb=jb4;
                break;
            case 5:
                jb=jb5;
                break;
            case 6:
                jb=jb6;
                break;
            case 7:
                jb=jb7;
                break;
            case 8:
                jb=jb8;
                break;
            case 9:
                jb=jb9;
                break;
        }

        jb.setIcon(getImageIcon(turn));
        jb.setDisabledIcon(getImageIcon(turn));
        jb.setEnabled(false);

    }

    public void resetGui(){
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


    private class Listener implements ActionListener{

        public void actionPerformed(ActionEvent e) {
            if (e.getSource()==jb1){
                control.playerMove( 1);

            }
            if (e.getSource()==jb2){
                control.playerMove(2);
            }

            if (e.getSource()==jb3){
                control.playerMove(3);

            }
            if (e.getSource()==jb4){
                control.playerMove(4);

            }
            if (e.getSource()==jb5){
                control.playerMove(5);

            }
            if (e.getSource()==jb6){
                control.playerMove(6);

            }
            if (e.getSource()==jb7){
                control.playerMove(7);

            }
            if (e.getSource()==jb8){
                control.playerMove(8);

            }
            if (e.getSource()==jb9){
                control.playerMove(9);

            }

        }
    }

}
