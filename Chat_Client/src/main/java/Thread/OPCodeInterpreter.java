/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Thread;

import GUI.GuiNuova;
import GUI.Repo;
import com.google.gson.Gson;
import java.io.IOException;
import java.util.ArrayList;
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
                //home.setTextAreaMessaggi("MESSAGGIO PRIVATO:\n" + new String(sAlias) + ": " + new String(sMsg));

                if(Repo.listaM.containsKey(new String(sAlias)))
                {   
                    System.out.println("Inserito e esisteva gia: " + new String(sMsg));
                    Repo.listaM.replace(new String(sAlias),Repo.listaM.get(new String(sAlias)) + new String(sAlias) + ": " + new String(sMsg) + "\n");
                }    
                else
                {   
                    System.out.println("Inserito e non esisteva: " + new String(sMsg));
                    Repo.listaM.put(new String(sAlias), new String(sAlias) + ": " + new String(sMsg) + "\n");
                }

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
                if(!"".equals(new String(sMsg)))
                {
                    System.out.println("messaggio: " + new String(sMsg));
                    home.setTextAreaMessaggi1(new String(sAlias) + ": " + new String(sMsg));
                }
                break;

                
            case "11":
                home.getConnessione().setGuard(false);
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
                    home.setButtonConnessioneText("Disconnetti");
                    home.setConnessoDisconnesso();
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
                byte[] userList; 
                Gson gson; 
                ArrayList list;
                //Byte listLen = packet[2];
                
                switch( type )
                {
                    case 1:
                        System.out.println("type = 1");
                        //calcola la fine della lista
                        for(byte b: packet)
                        {
                            if(b == 0)
                                break;
                            else
                                fM++;

                        }
                        userList= Arrays.copyOfRange(packet, 3, fM);
                        System.out.println(Arrays.toString(userList));
                        System.out.println(new String(userList));
                        gson= new Gson();
                        list = gson.fromJson(new String(userList), ArrayList.class);
                        System.out.println("ArrayList: "+list);
                        
                        //aggiunge lo user al combobox
                        home.addUser(list.get(0).toString());
                        
                        break;
                        
                    case 2:
                        System.out.println("type = 2");
                        
                        //calcola la fine della lista
                        for(byte b: packet)
                        {
                            if(b == 0)
                                break;
                            else
                                fM++;

                        }
                        userList= Arrays.copyOfRange(packet, 3, fM);
                        System.out.println(Arrays.toString(userList));
                        System.out.println(new String(userList));
                        gson= new Gson();
                        list = gson.fromJson(new String(userList), ArrayList.class);
                        System.out.println("ArrayList: "+list);
                        
                        //rimuove lo user dal combobox
                        home.removeUser(list.get(0).toString());
                        break;
                        
                    default:
                        System.out.println("type = 0");
                        //calcola la fine della lista
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
                        userList= Arrays.copyOfRange(packet, 3, fM-1);
                        System.out.println(Arrays.toString(userList));
                        System.out.println(new String(userList));
                        gson= new Gson();
                        list = gson.fromJson(new String(userList), ArrayList.class);
                        System.out.println("ArrayList: "+list);
                        
                        for(int i = 0; list.size() > i; i++)
                        {
                            System.out.println(home.getAlias());
                            //aggiunge lo user al combobox
                            if(!list.get(i).toString().equals(home.getAlias()))
                                home.addUser(list.get(i).toString());
                        }
                        break;
                }
                break;
                
            case "255":
                System.out.println("identificato messagio error");
                Byte errorCode = packet[1];
                Byte.toString(errorCode);
                switch( Byte.toString(errorCode) )
                {
                    case "000":
                        JOptionPane.showMessageDialog(null, "Il server dice: malformed package", "ATTENZIONE", JOptionPane.WARNING_MESSAGE);
                        break;
                    case "100":
                        JOptionPane.showMessageDialog(null, "Il server dice: alias already in use", "ATTENZIONE", JOptionPane.WARNING_MESSAGE);
                        break;
                    case "101":
                        JOptionPane.showMessageDialog(null, "Il server dice: unvalid alias", "ATTENZIONE", JOptionPane.WARNING_MESSAGE);
                        break;
                    case "102":
                        JOptionPane.showMessageDialog(null, "Il server dice: invalid room name", "ATTENZIONE", JOptionPane.WARNING_MESSAGE);
                        break;
                    case "200":
                        JOptionPane.showMessageDialog(null, "Il server dice: chat denied", "ATTENZIONE", JOptionPane.WARNING_MESSAGE);
                        break;
                    case "202":
                        JOptionPane.showMessageDialog(null, "Il server dice: maximum clients reached", "ATTENZIONE", JOptionPane.WARNING_MESSAGE);
                        break;
                    case "254":
                        JOptionPane.showMessageDialog(null, "Il server dice: server exploded", "ATTENZIONE", JOptionPane.WARNING_MESSAGE);
                        break;
                    case "255":
                        JOptionPane.showMessageDialog(null, "Il server dice: unspecified exception", "ATTENZIONE", JOptionPane.WARNING_MESSAGE);
                        break;
                        
                }
                break;

        }

    }

}
