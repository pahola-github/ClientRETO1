package clientreto1.logic;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import message.Message;

/**
 *
 * @author Garikoitz
 */
public class SigneableImplementationThread extends Thread {

    private Socket sc;
    private Message msg;
    private Message reply;
    private ObjectInputStream in;
    private ObjectOutputStream out;
    private static final Logger LOGGER = Logger.getLogger(SigneableImplementationThread.class.getName());

    public SigneableImplementationThread(Socket sc, Message msg) {
        this.sc = sc;
        this.msg = msg;
        start();
    }

    public void run() {
        
        try {
            
            out = new ObjectOutputStream(sc.getOutputStream()); // Output object.
            in = new ObjectInputStream(sc.getInputStream()); // Input object.

            out.writeObject(msg); // Send message to server.

            reply = (Message) in.readObject(); // Read the response for the server.

        } catch (IOException ex) {
            
            LOGGER.log(Level.SEVERE, "Input/Output error.", ex);
            
        } catch (ClassNotFoundException ex) {
            
            LOGGER.log(Level.SEVERE, "Class not found error.", ex);
            
        }

    }
    
    /**
     * Method for get the message.
     * @return reply message.
     */
    public Message getReply() {
        return reply;
    }

}
