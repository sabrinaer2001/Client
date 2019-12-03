package Pacchetti;

// |                        HEADER	         |          PAYLOAD          |
// |   1 byte    |  2 bytes  |      string       | 1 byte | string  | 1 byte |
// | opcode (01) | sender id | destination alias |    0   | message |	 0   |

/**
 * 01
 * @author richi
 * Packet USER to USER 
 */
public class UserToUser{
    private String destinationAlias;
    private String message;
    private byte[] senderId; 

    /**
     * @param destinationAlias
     * @param message
     * @param senderId
     */
    public UserToUser(String destinationAlias, String message, byte[] senderId) {
        this.destinationAlias = destinationAlias;
        this.message = message;
        this.senderId = senderId;
    }

    public String getDestinationAlias() {
        return destinationAlias;
    }

    public void setDestinationAlias(String destinationAlias) {
        this.destinationAlias = destinationAlias;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String Message) {
        this.message = Message;
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
    public void setSender_id(byte[] senderId) {
        this.senderId = senderId;
    }

    public byte[] getUsertoUserPacket() {

        int i = 0;

        byte[] byteDestinationAlias = this.destinationAlias.getBytes();
        byte[] byteMessage = this.message.getBytes();
        byte[] packet = new byte[5+byteDestinationAlias.length+byteMessage.length];
        
        /*HEADER*/
        
        //OPcode
        packet[i++] = 01;

        // ID
        for (byte b : this.senderId) {
            packet[i++] = b;
        }

        // Destination_Alias
        for (byte b : byteDestinationAlias) {
            packet[i++] = b;

        }

        // 1 byte a 0
        packet[i++] = 0;

        /* PAYLOAD */

        // messagge
        for (byte b : byteMessage) {
            packet[i++] = b;

        }

        // 1 byte 0
        packet[i++] = 0;
        
        return packet;
    }

}
