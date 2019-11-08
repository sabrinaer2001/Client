/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package protocollo_chat;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static java.lang.System.in;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author 18868
 */
public abstract class GUI implements ActionListener  {
    
        
        public GUI(){
        //Creating the Frame
        JFrame frame = new JFrame("Chat Frame");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);

        //Creating the MenuBar and adding components
        JMenuBar mb = new JMenuBar();
        JMenu m1 = new JMenu("FILE");
        JMenu m2 = new JMenu("Help");
        mb.add(m1);
        mb.add(m2);
        JMenuItem m11 = new JMenuItem("Open");
        JMenuItem m22 = new  JMenuItem("Save as");
        m1.add(m11);
        m1.add(m22);

        //Creating the panel at bottom and adding components
        JPanel panel = new JPanel(); // the panel is not visible in output
        JLabel id_utente = new JLabel("metti il tuo nickname");
        JTextField tf = new JTextField(20); // accepts upto 20 characters
        JButton send = new JButton("registra");
        send.addActionListener (new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tf.setText("       ");
                send.setText("invia");
                id_utente.setText(" scrivi il tuo messaggio");
                JFrame stanza = new JFrame("STANZA PRINCIPALE");
                stanza.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                stanza.setSize(800, 800);
                //Creating the panel at bottom and adding components
                JPanel panel = new JPanel(); // the panel is not visible in output
                JLabel messaggio = new JLabel("messaggio");
                JTextField tf = new JTextField(100); // accepts upto 20 characters
                JButton invia = new JButton("invia");
                panel.add(messaggio); // Components Added using Flow Layout
                panel.add(tf);
                panel.add(invia);
                
               
                JTextArea ta = new JTextArea("qui riceverai i messaggi dela chat");
                
                frame.getContentPane().add(BorderLayout.SOUTH, panel);
                frame.getContentPane().add(BorderLayout.NORTH, mb);
                frame.getContentPane().add(BorderLayout.CENTER, ta);
                frame.setVisible(true);
                
                //action event pulsante invia messaggio
                invia.addActionListener (new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                
                String instring= ta.getText();
                ta.setText("     ");
                tf.setText("     ");
               
            }
        });
               
            }
        });
        
        send.setBackground(Color.green);
        
        JButton reset = new JButton("Reset");
        panel.add(id_utente); // Components Added using Flow Layout
        panel.add(id_utente); // Components Added using Flow Layout
        panel.add(tf);
        panel.add(send);
        panel.add(reset);

        // Text Area at the Center
        JTextArea ta = new JTextArea();

        //Adding Components to the frame.
        frame.getContentPane().add(BorderLayout.SOUTH, panel);
        frame.getContentPane().add(BorderLayout.NORTH, mb);
        frame.getContentPane().add(BorderLayout.CENTER, ta);
        frame.setVisible(true);

        }

     
    }

 


 
      


        
        
    
