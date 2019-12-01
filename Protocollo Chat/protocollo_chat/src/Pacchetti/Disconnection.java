/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Pacchetti;

/**
 *
 * @author JellyLama
 */
public class Disconnection {
/*1 byte	2 bytes
opcode ( 11 )	id*/
    private byte[] id;

    /**
     * @param id
     */
    public Disconnection(byte[] id) {
        this.id = id;
    }

    /**
     * @return the id
     */
    public byte[] getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(byte[] id) {
        this.id = id;
    }

    public byte[] getDisconnectionPacket()
    {

        int i1 = 0;
        byte[] packet = new byte[2048];
        
        //opcode
        packet[i1++] = 11;
        
        //id
        for(byte b : this.id)
        {
            packet[i1++] = b;
        }
        
        return packet;
        
    }

}