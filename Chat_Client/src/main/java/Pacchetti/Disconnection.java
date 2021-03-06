package Pacchetti;

// |    1 byte	   | 2 bytes |
// | opcode ( 11 ) |	id   |

/**
 *
 * @author 18868-18027-17694
 */
public class Disconnection {

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
        byte[] packet = new byte[3];
        
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