package GUI;

import Pacchetti.ChangeOfAlias;
import Pacchetti.Disconnection;
import Pacchetti.Registration;
import Pacchetti.UserToChat;
import Pacchetti.UserToUser;
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
    private byte [] id;

    
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

    
    public byte[] getId()
    {
        return id;
    }
    public void setId( byte[] id )
    {
        this.id = id;
    }

    //avvia la connessione con il server
    public int Connetti() throws IOException
    {   
        try{
            connection = new Socket(serverIP,serverPort);
            output = new DataOutputStream(connection.getOutputStream());
            input = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            
        }
        catch( IOException ioEception )
        {
            //esito negativo
            return(0);
            
        }
        //esito positivo
        return(1);
    }
    
    //invia il pacchetto di registrazione
    public void InviaRegistrazione(String alias, String topic) throws IOException
    {    
        //istanzia il pacchetto di registrazione
        Registration r = new Registration(alias, topic);
        //crea il pacchetto di registrazione
        byte [] packet = r.getRegistrationPacket();
        //invia il pacchetto
        output.write(packet);
        //riceve l'ack
        /*        String strInput = this.input.readLine();
        
        System.out.println(strInput);*/
        
    }
    
    public void UsertoUser(String destinationAlias, String message, byte[] senderId) 
            throws Exception
    {
        //istanzia il pacchetto User to User o il pacchetto 01
        UserToUser utu = new UserToUser(destinationAlias, message, senderId);
        //crea il pacchetto UsertoUser
        byte[] packet = utu.getUsertoUserPacket();
        //invia il pacchetto
        output.write(packet);
    }
    
    public void UsertoChat() throws Exception{
        //istanzia il pacchetto User to Chat o il pacchetto 05
        UserToChat utc = new UserToChat();
        //crea il pacchetto UsertoChat
        byte[] packet = utc.getUsertoChatPacket();
        //invia il pacchetto
        output.write(packet);
    }
    
    public void ChangeofAlias() throws Exception{
        //istanzia il pacchetto Change of Alias o il pacchetto 18
        ChangeOfAlias coa = new ChangeOfAlias();
        //crea il pacchetto ChangeofAlias
        byte[] packet = coa.getCHANGEofALIAS();
        //invia il pacchetto
        output.write(packet);
    }
        
    public void InviaDisconnessione() throws IOException
    {    
        //istanzia il pacchetto di registrazione
        Disconnection r = new Disconnection(this.id);
        //crea il pacchetto di registrazione
        byte [] packet = r.getDisconnectionPacket();
        //invia il pacchetto
        output.write(packet);
        
    }
}
