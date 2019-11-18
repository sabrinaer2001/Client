/*
 * To change this license header, choose License Headers in Project Properties.
 * To change thismessaggiolate file, choose Tools |messaggiolates
 * and open themessaggiolate in the editor.
 */
package protocollo_chat;


import java.awt.BorderLayout;
import java.awt.Color;
import static java.awt.Color.black;
import java.io.*;
import java.net.*;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author 18007
 */
public class CLIENT_TCP extends javax.swing.JFrame{
    

    private ObjectOutputStream output;
    private ObjectInputStream input;
    private String messaggio="";
    private String serverIP;
    private Socket connection;
    private int port = 5555;
    
   
    public CLIENT_TCP (String a){
    
        initComponents();
        
        this.setTitle("Client");
        this.setVisible(true);
        stato.setVisible(true);
        
        serverIP = a;
    
    
    
    }
        //creazione GUI
    private void initComponents() {
        
        //componenti
        JPanel jPanel2 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jTextField1 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        chatArea = new javax.swing.JTextArea();
        jLabel2 = new javax.swing.JLabel();
        stato = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        JScrollPane jScrollPane2 = new javax.swing.JScrollPane();

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jPanel1.setLayout(null);
        
        
        //action listener, gestione eventi
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });
        jPanel1.add(jTextField1);
        jTextField1.setBounds(30, 350, 270, 30);

        jButton1.setText("Invio");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1);
        jButton1.setBounds(310, 350, 80, 30);

        chatArea.setColumns(20);
        chatArea.setRows(5);
        jScrollPane1.setViewportView(chatArea);

        jPanel1.add(jScrollPane1);
        jScrollPane1.setBounds(20, 20, 380, 280);

        jLabel2.setForeground(new java.awt.Color(0, 0, 1));
        jLabel2.setText("scrivi il messasaggio");
  
        jPanel1.add(jLabel2);
        jLabel2.setBounds(30, 330, 150, 20);

        stato.setText("...");
        jPanel1.add(stato);
        stato.setBounds(40, 310, 300, 30);

     
        jPanel1.add(jLabel1);
        jLabel1.setBounds(0, 0, 420, 410);
        jPanel1.add(jScrollPane2);
        jScrollPane2.setBounds(160, 110, 50, 50);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 421, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 414, Short.MAX_VALUE)
        );

        setSize(new java.awt.Dimension(437, 453));
        setLocationRelativeTo(null);
    }     
    
     private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {                                            
         //invia messaggio nel jtextfield e lo "resetta"
        sendMessage(jTextField1.getText());
	jTextField1.setText("");
    }                                           

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {                                         
        //invia messaggio nel jtextfield e lo "resetta"
       sendMessage(jTextField1.getText());
	jTextField1.setText("");
    }  

    
    public void startRunning()
    {
       try
       {    //tentivo di connessione al server
            stato.setText("attessa connessione ...");
            try
            {
                connection = new Socket(InetAddress.getByName(serverIP),port);
            }catch(IOException ioEception)
            {       //mancata connessione con apertura di pannello con avviso
                    JOptionPane.showMessageDialog(null,"Il server è giù","ATTENZIONE",JOptionPane.WARNING_MESSAGE);
            }//connessione avvenuta output ip server
            stato.setText("Connected to: " + connection.getInetAddress().getHostName());


            output = new ObjectOutputStream(connection.getOutputStream());
            output.flush();
            input = new ObjectInputStream(connection.getInputStream());

            whileChatting();
       }
       catch(IOException ioException)
       {
            ioException.printStackTrace();
       }
    }
    //invio messaggi durante chat
        private void whileChatting() throws IOException
          {
            jTextField1.setEditable(true);
        do{
              try
              {
                      messaggio = (String) input.readObject();
                      chatArea.append("\n"+messaggio);
              }
              catch(ClassNotFoundException classNotFoundException)
              {
              }
          }while(!messaggio.equals("Client - END"));
        }
  
    
    private void sendMessage(String message)
    {
        try
        {
            output.writeObject("Client - " + message);
            output.flush();
            chatArea.append("\nClient - "+message);
        }
        catch(IOException ioException)
        {       
            chatArea.append("\n Impossibile mandare il messaggio");
        }
    }
    
    
        // Dichiarazione variaibili                    
        private javax.swing.JTextArea chatArea;
        private javax.swing.JButton jButton1;
        private javax.swing.JLabel jLabel1;
        private javax.swing.JLabel jLabel2;
        private javax.swing.JPanel jPanel1;
        private javax.swing.JScrollPane jScrollPane1;
        private javax.swing.JTextField jTextField1;
        private javax.swing.JLabel stato;
    
    }



    


