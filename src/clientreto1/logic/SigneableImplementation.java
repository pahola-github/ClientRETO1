/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientreto1.logic;

import exceptions.EmailExistException;
import exceptions.ServerException;
import exceptions.UserExistException;
import exceptions.UserOrPassNotExistException;
import interfaces.Signeable;
import model.User;

/**
 *
 * @author Paola and Bryssa
 */
public class SigneableImplementation implements Signeable {
    
    public SigneableImplementation (){
        
    }

    @Override
    public User signIn(User user) throws UserOrPassNotExistException, ServerException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public User signUp(User user) throws UserExistException, EmailExistException, ServerException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
