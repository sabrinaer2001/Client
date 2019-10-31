/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package protocollo_chat;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 *
 * @author 18868
 */
public class Protocollo_chat {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        
        //creo il client
        Socket client = new Socket ("host id", port);
        //cero flussi input/output
        DataInputStream is = new DataInputStream(client.getInputStream());
        DataOutputStream os = new DataOutputStream (client.getOutputStream());
        //ricezione trasmissione I/O
        String line = is.readLine();
        System.out.println(line);
        
        
        //creo ciclo booleano
        boolean valore = true;
        while (valore){
         if opcode = 
        //gestisco gli opcode e la chiamate dei metodi
        
        
        
        if //utente disocnesso valore = false
           valore=false;     
        }
        
        //chiudo il client
        client.close();
        
        
    }
    
}
