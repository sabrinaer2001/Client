/*
 * To change this license header, choose License Headers in Project Properties.
 * To change thismessaggiolate file, choose Tools |messaggiolates
 * and open themessaggiolate in the editor.
 */
package protocollo_chat;


import java.io.*;
import java.net.*;
/**
 *
 * @author 18007
 */
public class CLIENT_TCP {

   public static void main(String[] args) {
    String messaggio;
    String displayBytes;
    try {
      //create input stream
      BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
      //create client socket, connect to server
      Socket clientSocket = new Socket("127.0.0.",5555);
      //create output stream attached to socket
      DataOutputStream outToServer =
      new DataOutputStream(clientSocket.getOutputStream());

    

      //create input stream attached to socket
      BufferedReader inFromServer =
      new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

     messaggio = inFromUser.readLine();

      //send line to server
      outToServer.writeBytes(messaggio);

      //read line from server
      //displayBytes = inFromServer.readLine();

      while((displayBytes = inFromServer.readLine()) != null) {
        System.out.print(displayBytes);
      }
      //clientSocket.close();
    }
    catch(Exception ex) {
    }
  }
}



    


