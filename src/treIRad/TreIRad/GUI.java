package treIRad.TreIRad;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

        setSize(700,700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Tre på i rad");
        setVisible(true);

    }


    public void winPopUp(Winner win){
        if (win==Winner.Player){
            JOptionPane.showMessageDialog(null,"Congratulations!");
        }
        else if (win==Winner.Ai){
            JOptionPane.showMessageDialog(null,"Shameful loss");
        }
    }




    public void deactivateButton(JButton jb){
        jb.setEnabled(false);
    }

    public void setButtonImage(JButton jb, ImageIcon icon){
        jb.setIcon(icon);
        jb.setDisabledIcon(icon);
    }


    private class Listener implements ActionListener{

        public void actionPerformed(ActionEvent e) {
            if (e.getSource()==jb1){
                control.playerMove(jb1, 1);

            }
            if (e.getSource()==jb2){
                control.playerMove(jb2,2);
            }

            if (e.getSource()==jb3){
                control.playerMove(jb3,3);

            }
            if (e.getSource()==jb4){
                control.playerMove(jb4,4);

            }
            if (e.getSource()==jb5){
                control.playerMove(jb5,5);

            }
            if (e.getSource()==jb6){
                control.playerMove(jb6,6);

            }
            if (e.getSource()==jb7){
                control.playerMove(jb7,7);

            }
            if (e.getSource()==jb8){
                control.playerMove(jb8,8);

            }
            if (e.getSource()==jb9){
                control.playerMove(jb9,9);

            }

        }
    }

}
