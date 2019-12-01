/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Pacchetti.Registration;
import java.io.*;
import java.net.*;


/**
 *
 * @author JellyLama
 */
public class Connessione
{
    private BufferedReader input;
    private DataOutputStream output;
    private String messaggio = "";
    private String serverIP = "127.0.0.1";
    private Socket connection;
    private int serverPort = 53101;

    public BufferedReader getInput()
    {
        return input;
    }

    public void setInput( BufferedReader input )
    {
        this.input = input;
    }

    public DataOutputStream getOutput()
    {
        return output;
    }

    public void setOutput( DataOutputStream output )
    {
        this.output = output;
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
            output = new DataOutputStream(connection.getOutputStream());
            input = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            
        }
        catch( IOException ioEception )
        {
            
            return(0);
            
        }
        return(1);
    }
    public void InviaRegistrazione(String alias, String topic) throws IOException
    {
        Registration r = new Registration(alias, topic);
        byte [] packet = r.getRegistrationPacket();
        output.write(packet);
    }

    
}
