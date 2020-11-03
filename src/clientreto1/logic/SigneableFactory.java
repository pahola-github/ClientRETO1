/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientreto1.logic;

import interfaces.Signeable;

/**
 * Return SigneableImplementation
 * @author Paola and Bryssa
 */
public class SigneableFactory {
    
    /**
     * Method to return the implementation of the interface
     * @return the implementation of the interface
     */
    public static Signeable getSigneableImplementation(){
        return new SigneableImplementation();
    }
    
}
