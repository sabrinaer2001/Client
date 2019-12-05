/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.util.Arrays;


/**
 *
 * @author JellyLama
 */
public class OPCodeInterpreter implements Runnable
{

    private byte[] packet;
    private GuiNuova home;

    public OPCodeInterpreter( byte[] packet, GuiNuova home )
    {
        this.packet = packet;
        this.home = home;
    }
    enum OpCode
    {
        // Messages
        MsgUserToUser           (01),
        MsgUserToGroup          (05),

        // User status
        Registration            (10),
        Disconnection           (11),
        AliasChange             (18),

        // control messages
        RegAck                  (20),
        GroupUsersListRrq       (50),
        GroupUsersList          (51),

        // 
        Error                   (255);
    }

// 01
// 05
// 11
// 10
// 20
// 51
// 255
// 18
    @Override
    public void run()
    {
        
        byte[] sAlias;
        byte[] sMsg;
        int fA = 0;
        int iM;
        int fM;
        int guard;
            Byte oc;
            Byte utu = new Byte("01");
            byte[] aOc = new byte[1];
            aOc = Arrays.copyOfRange(packet, 0, 1);
            oc = aOc[0];
            
            switch( oc )
            {
                case utu:
                    System.out.println("identificato messagio user to user");
                    //array di byte dell'alias sorgente
                    
                    //array di byte del messaggio sorgente

                    //lunghezza in byte dell'alias sorgente

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

                    System.out.println(new String(sAlias));

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
                    
                    home.setTextAreaMessaggi("MESSAGGIO PRIVATO:\n" + new String(sAlias) + ": " + new String(sMsg));
                    
                    break;
                case 05:
                    System.out.println(Arrays.toString(packet));
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
                    System.out.println(new String(sAlias));
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
                    System.out.println(new String(sMsg));
                    home.setTextAreaMessaggi(new String(sAlias) + ": " + new String(sMsg));
                    break;
                case "10":
                    break;
                case "11":
                    break;
                case "18":
                    break;
                case "20":
                    break;
                case "51":
                    break;
                case "255":
                    break;

            
        }

    }

}
