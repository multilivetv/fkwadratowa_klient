package com.multilive;

import org.json.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.URL;
import java.lang.String;
import java.lang.*;




public class MainForm {

    private JPanel jpanel;
    private JPanel panel1;
    private JButton generate;
  //  private Image img;
    public JTextField input;
    private JLabel output;

    public MainForm() {
        generate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                output.setText(input.getText());
                JSONAPI.expressionstring = input.getText();
                try {

                    JOptionPane.showMessageDialog(
                            null, JSONAPI.SHOWRESOLV());
                    //expression = jt.getText();

                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                if (JSONAPI.status == 200) {
                    Parabola.start();
                } else if (JSONAPI.status == 400){

                }
                //JLabel label = new JLabel(new ImageIcon("C:\\image\\cerber.jpg"));
            }
        });
    }
    public int math(int x){
        int resolv;
        resolv = x*x;
        return resolv;

    }


    public static void main(String [] args) throws Exception {
        JFrame frame = new JFrame("Generator Wykresu Funkcji Kwadratowej");
        frame.setContentPane(new MainForm().jpanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
