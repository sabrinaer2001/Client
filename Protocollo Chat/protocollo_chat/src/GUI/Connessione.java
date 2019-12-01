/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.io.*;
import java.net.*;


/**
 *
 * @author JellyLama
 */
public class Connessione
{
    private BufferedReader output;
    private PrintWriter input;
    private String messaggio = "";
    private String serverIP = "127.0.0.1";
    private Socket connection;
    private int serverPort = 53101;

    public BufferedReader getOutput()
    {
        return output;
    }

    public void setOutput( BufferedReader output )
    {
        this.output = output;
    }

    public PrintWriter getInput()
    {
        return input;
    }

    public void setInput( PrintWriter input )
    {
        this.input = input;
    }

    public String getMessaggio()
    {
        return messaggio;
    }

    public void setMessaggio( String messaggio )
    {
        this.messaggio = messaggio;
    }

    public String getServerIP()
    {
        return serverIP;
    }

    public void setServerIP( String serverIP )
    {
        this.serverIP = serverIP;
    }

    public Socket getConnection()
    {
        return connection;
    }

    public void setConnection( Socket connection )
    {
        this.connection = connection;
    }

    public int getServerPort()
    {
        return serverPort;
    }

    public void setServerPort( int serverPort )
    {
        this.serverPort = serverPort;
    }
    
    public int Connetti() throws IOException
    {   
        try{
            connection = new Socket(serverIP,serverPort); 
            
        }
        catch( IOException ioEception )
        {
            
            return(0);
            
        }
        return(1);
    }

    
}
