package Pacchetti; 

// |   1 byte    | 2 bytes |  string   | 1 byte |  string   | 1 byte |
// | opcode (18) |    id   | old alias |    0   | new alias |	 0   |

/**
 * 18
 * @author richi
 * change of alias
 */
public class ChangeOfAlias {
    private String oldAlias;
    private String newAlias;
    private byte[] senderId;

    /**
     * @return the oldAlias
     */
    public String getOldAlias() {
        return oldAlias;
    }

    /**
     * @param oldAlias the oldAlias to set
     */
    public void setOldAlias(String oldAlias) {
        this.oldAlias = oldAlias;
    }

    /**
     * @return the newAlias
     */
    public String getNewAlias() {
        return newAlias;
    }

    /**
     * @param newAlias the newAlias to set
     */
    public void setNewAlias(String newAlias) {
        this.newAlias = newAlias;
    }

    /**
     * @return the senderId
     */
    public byte[] getSenderId() {
        return senderId;
    }

    /**
     * @param senderId the senderId to set
     */
    public void setSenderId(byte[] senderId) {
        this.senderId = senderId;
    }
    
    public void getCHANGEofALIAS(){



        int i = 0;
        byte[] packet = new byte[2048];
        byte[] byteOldAlias = this.oldAlias.getBytes();
        byte[] byteNewAlias = this.newAlias.getBytes();

        //OPcode
        packet[i++] = 18;
        
        //private_id
        for (byte b : this.senderId) {
            packet[i++] = b;
        }
        
        //Old_Alias
        for(byte b : byteOldAlias)
        {
            packet[i++] = b;
        }
        
        //1 byte a 0
        packet[i++] = 0;
        
        //New_Alias
        for(byte b : byteNewAlias)
        {
            packet[i++] = b;
        }
        
        //1 byte a 0
        packet[i++] = 0;
    }

}
