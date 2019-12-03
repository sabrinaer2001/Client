package GUI;

import Pacchetti.Registration;
import java.io.*;
import java.net.*;
import java.util.Arrays;

/**
 *
 * @author JellyLama
 */
public class Connessione
{   
    private DataInputStream input;
    private BufferedOutputStream output;
    private String messaggio = "";
    private String serverIP = "127.0.0.1";
    private Socket connection;
    private int serverPort = 53101;
    private byte [] id;

    


       
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
       
    public int getServerPort()
    {
        return serverPort;
    }
    public void setServerPort( int serverPort )
    {
        this.serverPort = serverPort;
    }
    
    public byte[] getId()
    {
        return id;
    }
    public void setId( byte[] id )
    {
        this.id = id;
    }

    /*    //avvia la connessione con il server
    public int Connetti() throws IOException
    {
    try{
    connection = new Socket(serverIP,serverPort);
    output = new BufferedOutputStream(connection.getOutputStream());
    input = new DataInputStream(connection.getInputStream());
    
    }
    catch( IOException ioEception )
    {
    //esito negativo
    return(0);
    
    }
    //esito positivo
    return(1);
    }*/
    
    //invia il pacchetto di registrazione
    public int ConnettiInviaRegistrazione(String alias, String topic) throws IOException
    {   
        try{
            connection = new Socket(serverIP,serverPort);
            /*this.connection = c;*/
            output = new BufferedOutputStream(connection.getOutputStream());
            input= new DataInputStream(connection.getInputStream());
            
            //istanzia il pacchetto di registrazione
            Registration r = new Registration(alias, topic);

            //crea il pacchetto di registrazione
            byte [] packet = r.getRegistrationPacket();

            //invia il pacchetto di registrazione
            output.write(packet);
            //svuota il buffer
            output.flush();

            //riceve l'ack
            byte[] ack = new byte[2];
            input.read(ack);

            //seleziona l'id
            this.id = Arrays.copyOfRange(ack, 1, 3);

            System.out.println(Arrays.toString(id));

            //esito positivo
            return(1);
        }
        catch( IOException ioEception )
        {
            //esito negativo
            return(0);
            
        }
    }
    /*    public void InviaDisconnessione() throws IOException
    {
    //istanzia il pacchetto di registrazione
    Disconnection r = new Disconnection(this.id);
    //crea il pacchetto di registrazione
    byte [] packet = r.getDisconnectionPacket();
    //invia il pacchetto
    output.write(packet);
    
    }*/
}
