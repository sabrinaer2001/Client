/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author JellyLama
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
        
        while( true )
        {   
            if(home.socketClosed() == true)
            {
                break;
                
            }
            else
            {
                try
                {   
                    System.out.println("Waiting to get a message...");

                    //si mette in attesa di un messaggio
                    input.read(this.packet);

                    System.out.println(Arrays.toString(packet));
                    
                    //array di byte dell'alias sorgente
                    byte[] sAlias;
                    //array di byte del messaggio sorgente
                    byte[] sMsg;

                    //lunghezza in byte dell'alias sorgente
                    int fA = 0;

                    //calcola la lunghezza dell'alias sorgente
                    for(byte b: packet)
                    {   
                        if(b == 0)
                            break;
                        else
                        {
                            fA++;

                        }
                    }

                    //filtra l'alias sorgente dal pacchetto
                    sAlias = Arrays.copyOfRange(packet, 1, fA);

                    //se l'alias e diverso dal proprio alias
                    if(!new String(sAlias).equals(home.getAlias()))
                    {
                        System.out.println(new String(sAlias));

                        //lunghezza in byte del messaggio sorgente
                        int iM = fA + 1;
                        int fM = 0; //è avanti di 1
                        int guard = 0;

                        for(byte b: packet)
                        {   
                            if(!(guard == 2))

                                if(b == 0)
                                {
                                    guard++;
                                    fM++;
                                }                           
                                else
                                {
                                    fM++;
                                }
                            else
                            {
                                break;
                            }
                        }
                        //filtra il messaggio sorgente dal pacchetto
                        sMsg = Arrays.copyOfRange(packet, iM, fM-1);

                        System.out.println(new String(sMsg));

                        home.setTextAreaMessaggi(new String(sAlias) + ": " + new String(sMsg));
                    }
                    else
                    {
                    }
                }
                catch( IOException ex )
                {
                    Logger.getLogger(Ricezione.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
        }
            
    }  

}  

