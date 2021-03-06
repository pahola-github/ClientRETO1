/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientreto1.logic;

import exceptions.EmailExistException;
import exceptions.InvalidPasswordException;
import exceptions.ServerException;
import exceptions.UserExistException;
import exceptions.UserNotExistException;
import interfaces.Signeable;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import message.Message;
import message.MessageType;
import model.User;

/**
 * Signeable implementation for send message to server and get the response from a database.
 *
 * @author Gari
 */
public class SigneableImplementation implements Signeable {

    private static final Logger LOGGER = Logger
            .getLogger("clientreto1.logic");
    
    //Path of the properties file
    private static final String properties = "clientreto1.resources.ServerProperties";

    //Reading of the IP of the server in the properties file
    private static final String IP = ResourceBundle
            .getBundle(properties).getString("IP");

    //Reading of the PORT of the server in the properties file
    private static final int PORT = Integer.parseInt(ResourceBundle
            .getBundle(properties).getString("PORT"));

    private Socket sc;
    private Message msg;
    private Message reply;
    private ObjectInputStream in;
    private ObjectOutputStream out;

    /**
     * SignIn method to connect with the server
     *
     * @param user The User data that the client has introduced.
     * @return The object with the user data
     * @throws UserNotExistException An exception that shows an error if the user not exists.
     * @throws ServerException An exception that shows an error if the server has an internal error.
     * @throws InvalidPasswordException An exception that shows an error if the password is incorrect.
     */
    @Override
    public User signIn(User user) throws UserNotExistException, ServerException, InvalidPasswordException {

        try {
            sc = new Socket(IP, PORT); // Make the socket with our IP and PORT we will use to comunicate with the Server.

            msg = new Message(user, MessageType.SIGNIN); // Make a message we will send with the user.

            SigneableImplementationThread thread = new SigneableImplementationThread(sc, msg);
            thread.join();

            reply = thread.getReply(); // Read the response for the server.

            switch (reply.getMessageType()) { // Select the type of the reply message.
                case USER_NOT_EXIST:
                    throw new UserNotExistException();
                case SERVER_ERROR:                  
                    throw new ServerException();
                case INVALID_PASSWORD:
                    throw new InvalidPasswordException();
                default:
                    break;
            }
        } catch (IOException ex) {

            LOGGER.log(Level.SEVERE, "Input/Output error.", ex);

        } catch (InterruptedException ex) {
          
            LOGGER.log(Level.SEVERE, "Syncronize error.", ex);
          
        } finally {
            try {
              
                if (sc == null) {
                    throw new ServerException();

                } else {
                    sc.close(); // Close the socket.
                }
            } catch (IOException ex) {

                LOGGER.log(Level.SEVERE, "Socket close error.", ex);

           }
        }

        return user; // Return the user to the controller.
    }

    /**
     * SignUp method to connecto with the server
     *
     * @param user The User data that the client has introduced.
     * @return The object with the user data.
     * @throws UserExistException An exception that shows an error if the user exists.
     * @throws EmailExistException An exception that shows an error if the email exists.
     * @throws ServerException An exception that shows an error if the server has an internal error.
     */
    @Override
    public User signUp(User user) throws UserExistException, EmailExistException, ServerException {
        try {

            sc = new Socket(IP, PORT); // Make the socket with our IP and PORT we will use to comunicate with the Server.

            msg = new Message(user, MessageType.SIGNUP); // Make a message we will send with the user.

            SigneableImplementationThread thread = new SigneableImplementationThread(sc, msg);
            thread.join();

            reply = thread.getReply(); // Read the response for the server.

            switch (reply.getMessageType()) { // Select the type of the reply message.

                case USER_EXIST:
                    throw new UserExistException();

                case SERVER_ERROR:
                    throw new ServerException();

                case EMAIL_EXIST:
                    throw new EmailExistException();

                default:
                    break;

            }

        } catch (IOException ex) {

            LOGGER.log(Level.SEVERE, "Input/Output error.", ex);

        } catch (InterruptedException ex) {
            LOGGER.log(Level.SEVERE, "Syncronize error.", ex);
        } finally {

            try {

                sc.close(); // Close the socket.

            } catch (IOException ex) {

                LOGGER.log(Level.SEVERE, "Socket close error.", ex);

            }

        }

        return user; // Return the user to the controller.
    }
    
}
