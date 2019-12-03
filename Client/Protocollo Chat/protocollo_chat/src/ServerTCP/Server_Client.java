/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ServerTCP;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author 5ei
 */
public class Server_Client {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        // The name of the file to open.
    String fileName = "input.txt";
    // This will reference one line at a time
    String line = null;
    String holder=null;
    String clientWord;
    int bytNumber;

    try {
      //create welcoming socket at port 5555
      ServerSocket welcomeSocket = new ServerSocket(5555);
      //wait, on welcoming socket for contact by client
      Socket connectionSocket = welcomeSocket.accept();
      //create input stream, attached to socket
      BufferedReader inFromClient = 
      new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
      //create output stream, attached to socket
      DataOutputStream outToClient =
      new DataOutputStream(connectionSocket.getOutputStream());
      //read in line from socket
      clientWord = inFromClient.readLine();

      if(clientWord.equals("query")) {
        try {
          // FileReader reads text files in the default encoding.
          FileReader fileReader = new FileReader(fileName);

          // Always wrap FileReader in BufferedReader.
          BufferedReader buffer = new BufferedReader(fileReader);

          while((line = buffer.readLine()) != null) {
            System.out.println(line);
            bytNumber = line.getBytes().length;
            holder=Integer.toString(bytNumber);
            //pr.println(bytNumber);//only printing first line not going until eof
            outToClient.writeBytes(holder);
            // line = buffer.readLine();
            // pr.flush();
          }   

          // Always close files.
          buffer.close();         
        }
        catch(FileNotFoundException ex) {
          System.out.println("Unable to open file '" + fileName + "'");
        }
        catch(IOException ex) {
          System.out.println ("Error reading file '" + fileName + "'");
          // Or we could just do this: 
          // ex.printStackTrace();
        }
      }// end if statement
    }
    catch(IOException ex) {
    }
  }    
}
