/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.io.IOException;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;


/**
 *
 * @author JellyLama
 */
public class OPCodeInterpreter implements Runnable
{

    private byte[] packet;
    private GuiNuova home;

    public OPCodeInterpreter( byte[] packet, GuiNuova home)
    {
        this.packet = packet;
        this.home = home;
    }

    @Override
    public void run()
    {
        
        byte[] sAlias;
        byte[] sMsg;
        int fA = 0;
        int iM;
        int fM = 0;
        int guard = 0;
        Byte oc;
        byte[] aOc = new byte[1];
        aOc = Arrays.copyOfRange(packet, 0, 1);
        oc = aOc[0];
        String sOc = Byte.toString(oc);
        if(sOc.length() == 1)
        {
            sOc = "0" + sOc;
        }
        System.out.println("opcode: " + sOc);
        switch( sOc )
        {
            case "01":
                System.out.println("identificato messagio user to user");

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
                System.out.println("alias: " + new String(sAlias));


                iM = fA + 1;
                fM = 0; //è avanti di 1
                guard = 0;
                
                //calcola la lunghezza del messaggio sorgente
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
                System.out.println("messaggio: " + new String(sMsg));
                home.setTextAreaMessaggi("MESSAGGIO PRIVATO:\n" + new String(sAlias) + ": " + new String(sMsg));

                break;
                
            case "05":
                System.out.println("identificato messagio user to chat");
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
                System.out.println("alias: " + new String(sAlias));
                //lunghezza in byte del messaggio sorgente
                iM = fA + 1;
                fM = 0; //è avanti di 1
                guard = 0;
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
                System.out.println("messaggio: " + new String(sMsg));
                home.setTextAreaMessaggi1(new String(sAlias) + ": " + new String(sMsg));
                break;
                
            case "11":
                System.out.println("identificato messagio server disconnection");
                Byte reason = packet[1];
                Byte.toString(reason);
                switch( Byte.toString(reason) )
                {
                    case "0":
                        JOptionPane.showMessageDialog(null, "Il server è offline\n-No reason-", "ATTENZIONE", JOptionPane.WARNING_MESSAGE);
                        break;
                    case "1":
                        JOptionPane.showMessageDialog(null, "Il server è offline\nInattività di 15 minuti del client", "ATTENZIONE", JOptionPane.WARNING_MESSAGE);
                        break;
                    case "2":
                        JOptionPane.showMessageDialog(null, "Il server è stato arrestato", "ATTENZIONE", JOptionPane.WARNING_MESSAGE);
                        break;
                }
            
                try
                {
                    home.getConnessione().disconnetti(true);
                    home.setDisconnesso();
                    home.getConnessione().setGuard(false);
                }
                catch( IOException ex )
                {
                    Logger.getLogger(OPCodeInterpreter.class.getName()).log(Level.SEVERE, null, ex);
                }
            
                break;

            case "20":
                System.out.println("identificato messagio registration ack");
                //seleziona l'id
                byte[] id = new byte[1];
                id = Arrays.copyOfRange(packet, 1, 3);
                System.out.println("id: " + Arrays.toString(id));
                home.getConnessione().setId(id);
                break;
                
            case "51":
                System.out.println("identificato messagio group users list");
                Byte type = packet[1];
                Byte listLen = packet[2];
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
                byte[] userList = Arrays.copyOfRange(packet, 3, fM);
                System.out.println(new String(userList));
                switch( type )
                {
                    case 1:
                        System.out.println("type = 1");
                        break;
                    case 2:
                        System.out.println("type = 2");
                        break;
                    default:
                        System.out.println("type = 0");
                        break;
                }
                break;
                
            case "255":
                System.out.println("identificato messagio errors");
                break;

        }

    }

}
