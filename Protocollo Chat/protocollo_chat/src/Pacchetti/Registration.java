/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pacchetti;

/**
 *
 * @author 5ei
 */
public class Registration {
    
    private String alias;
    private String topic;

    public Registration(String alias, String topic) {
        this.alias = alias;
        this.topic = topic;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }
    
    public byte[] getRegistrationPacket()
    {
        int i1 = 0;
        byte[] packet = new byte[2048];
        byte[] byteAlias = this.alias.getBytes();
        byte[] byteTopic = this.topic.getBytes();
        
        //opcode
        packet[i1++] = 10;
        
        //version
        packet[i1++] = 0;
        
        //alias
        for(byte b : byteAlias)
        {
            packet[i1++] = b;
        }
        
        //0
        packet[i1++] = 0;
        
        //topic optional
        for(byte b : byteTopic)
        {
            packet[i1++] = b;
        }
        
        //0
        packet[i1++] = 0;
        
        return packet;
        
    }
            
}
