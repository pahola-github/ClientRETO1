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
import java.util.logging.Logger;
import message.Message;
import message.MessageType;
import model.User;

/**
 * Signeable implementation for getting the message from a bd.
 *
 * @author Paola and Bryssa
 */
public class SigneableImplementation implements Signeable {
    
    private static final Logger LOGGER = Logger
            .getLogger("clientreto1.logic");

    //Reading of the IP of the server in the properties file
    private static final String IP = ResourceBundle.getBundle(
            "clientreto1.logic.ServerProperties").getString("IP");

   //Reading of the PORT of the server in the properties file
    private static final int PORT = Integer.parseInt(ResourceBundle.getBundle(
            "clientreto1.logic.ServerProperties").getString("PORT"));
    
    
    private Socket sc;
    private Message msg;
    private Message reply;
    private ObjectInputStream in;
    private ObjectOutputStream out;

    /**
     * 
     * @param user
     * @return
     * @throws UserNotExistException
     * @throws ServerException
     * @throws InvalidPasswordException
     */
    @Override
    public User signIn(User user) throws UserNotExistException, ServerException, InvalidPasswordException {

        try {


            sc = new Socket(IP, PORT);
          

            out = new ObjectOutputStream(sc.getOutputStream());
            in = new ObjectInputStream(sc.getInputStream());

            msg = new Message(user, MessageType.SIGNIN);

            out.writeObject(msg);

            reply = (Message) in.readObject();

        } catch (IOException ex) {

        } catch (ClassNotFoundException ex) {

        } finally {

            try {

                sc.close();

            } catch (IOException ex) {

            }

        }

        return user;
    }

    /**
     *
     * @param user
     * @return
     * @throws UserExistException
     * @throws EmailExistException
     * @throws ServerException
     */
    @Override
    public User signUp(User user) throws UserExistException, EmailExistException, ServerException {
        try {

            sc = new Socket(IP, PORT);

            out = new ObjectOutputStream(sc.getOutputStream());

            msg = new Message(user, MessageType.SIGNUP);

            out.writeObject(msg);

            reply = (Message) in.readObject();

        } catch (IOException ex) {

        } catch (ClassNotFoundException ex) {

        } finally {

            try {

                sc.close();

            } catch (IOException ex) {

            }

        }

        return null;

    }

}
