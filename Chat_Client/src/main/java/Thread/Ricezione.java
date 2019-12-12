/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Thread;

import GUI.GuiNuova;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author 18868-18027-17694
 */
public class Ricezione implements Runnable
{  
    private DataInputStream input;
    private BufferedOutputStream output;
    private Socket socket;
    private GuiNuova home;
    byte[] packet = new byte[2048];

    public Ricezione( DataInputStream input, BufferedOutputStream output, Socket socket, GuiNuova home )
    {
        this.input = input;
        this.output = output;
        this.socket = socket;
        this.home = home;
    }

    @Override
    public void run()
    {  
        System.out.println("thread is running...");
        
        while( home.getConnessione().isGuard() )
        {   

            try
            {   
                System.out.println("Waiting to get a message...");

                //si mette in attesa di un messaggio
                input.read(this.packet);

                OPCodeInterpreter r1 =new OPCodeInterpreter(this.packet, this.home);  
                Thread t1 =new Thread(r1);  
                t1.start();
                System.out.println("Ricevuto e passato");

            }
            catch( IOException ex )
            {
                Logger.getLogger(Ricezione.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            
        }
            
    }  

}  

