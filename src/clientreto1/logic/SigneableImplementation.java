/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientreto1.logic;

import exceptions.EmailExistException;
import exceptions.ServerException;
import exceptions.UserExistException;
import exceptions.InvalidPasswordException;
import interfaces.Signeable;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import message.Message;
import model.User;

/**
 * Signeable implementation for getting the message from a bd.
 * @author Paola and Bryssa
 */
public class SigneableImplementation implements Signeable {
    
    private final String HOST = "127.0.0.1";
    private final Integer PORT = 60000;
    
    private ObjectInputStream in;
    private ObjectOutputStream out;
    private Socket sc;
    
    private Message msg;
    
    public SigneableImplementation (){
        
    }

    @Override
    public User signIn(User user) throws InvalidPasswordException, ServerException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public User signUp(User user) throws UserExistException, EmailExistException, ServerException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
