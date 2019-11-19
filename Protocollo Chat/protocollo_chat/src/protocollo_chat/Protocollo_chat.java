/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package protocollo_chat;

import com.sun.security.ntlm.Client;
import java.awt.event.ActionEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 *
 * @author 18868
 */
public class Protocollo_chat {

         
         public static void main(String[] args) 
    {
        CLIENT_TCP client;
        client = new CLIENT_TCP("127.0.0.1");
        client.startRunning();
    }
           
           
                }
   

        
   
