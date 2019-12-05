package GUI;

import Pacchetti.ChangeOfAlias;
import Pacchetti.Disconnection;
import Pacchetti.Registration;
import Pacchetti.UserToChat;
import Pacchetti.UserToUser;
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
    private String serverIP;
    private Socket socket;
    private int serverPort = 53101;
    private byte [] id;
    
    
    public DataInputStream getInput()
    {
        return input;
    }
    public void setInput( DataInputStream input )
    {
        this.input = input;
    }

    
    public BufferedOutputStream getOutput()
    {
        return output;
    }
    public void setOutput( BufferedOutputStream output )
    {
        this.output = output;
    }

    
    public Socket getSocket()
    {
        return socket;
    }
    public void setSocket( Socket socket )
    {
        this.socket = socket;
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
    
    //invia il pacchetto di registrazione
    public int ConnettiInviaRegistrazione(String alias, String topic) throws IOException
    {   
        try{
            socket = new Socket(serverIP,serverPort);
            output = new BufferedOutputStream(socket.getOutputStream());
            input= new DataInputStream(socket.getInputStream());
            
            //istanzia il pacchetto di registrazione
            Registration r = new Registration(alias, topic);

            //crea il pacchetto di registrazione
            byte [] packet = r.getRegistrationPacket();

            //invia il pacchetto di registrazione
            output.write(packet);
            //svuota il buffer
            output.flush();

            //riceve l'ack
            byte[] ack = new byte[4 + alias.getBytes().length];
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
    
    public void disconnetti() throws IOException
    {   
        //istanzia il pacchetto di disconnessione
        Disconnection d = new Disconnection(this.id);

        //crea il pacchetto di disconnessione
        byte [] packet = d.getDisconnectionPacket();

        //invia il pacchetto di disconnessione
        output.write(packet);
        //svuota il buffer
        output.flush();
        
        this.input.close();
        this.output.close();
        this.socket.close();
    }
    
    public void UsertoUser(String dstAlias, String message) throws Exception
    {
        //istanzia il pacchetto User to User o il pacchetto 01
        UserToUser utu = new UserToUser(dstAlias, message, this.id);
        //crea il pacchetto UsertoUser
        byte[] packet = utu.getUsertoUserPacket();
        //invia il pacchetto
        output.write(packet);
        output.flush();
    }
    
    public void UsertoChat(String m) throws Exception{
        //istanzia il pacchetto User to Chat o il pacchetto 05
        UserToChat utc = new UserToChat(m, this.id);
        //crea il pacchetto UsertoChat
        byte[] packet = utc.getUsertoChatPacket();
        //invia il pacchetto
        output.write(packet);
        output.flush();
    }
    
    public void ChangeofAlias(String oldA, String newA) throws Exception{
        //istanzia il pacchetto Change of Alias o il pacchetto 18
        ChangeOfAlias coa = new ChangeOfAlias(oldA, newA, this.id);
        //crea il pacchetto ChangeofAlias
        byte[] packet = coa.getCHANGEofALIAS();
        //invia il pacchetto
        output.write(packet);
        output.flush();
    }
}
