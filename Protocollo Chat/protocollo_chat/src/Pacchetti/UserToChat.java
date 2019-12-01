package Pacchetti;

// |         HEADER        |      PAYLOAD     |
// |   1 byte    | 2 bytes | string  | 1 byte |
// | opcode (05) |    id   | message |	  0   |

/**
 * 05
 * @author richi
 * Pacchetto USER to CHAT
 */
public class UserToChat {
    private String message;
    private byte[] senderId;
    
    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message the message to set
     */
    public void setMessage(String message) {
        this.message = message;
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

    public void getUsertoChatPacket() {

        int i = 0;

        byte[] byteMessage = this.message.getBytes();
        byte[] packet = new byte[byteMessage.length+4];
        
        /*HEADER*/
        
        //opcode
        packet[i++] = 05;

        // id
        for (byte b : this.senderId) {
            packet[i++] = b;
        }

        /* PAYLOAD */

        // Message
        for (byte b : byteMessage) {
            packet[i++] = b;
        }

        // 0
        packet[i++] = 0;
        
    }
    
}
