/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientreto1.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import model.User;

/**
 * 
 * @author Paola
 */
public class FXMLLogOutController {
   
    @FXML
    private Button btn_LogOut;
    
    
    private Stage stage;
    /**
     * Set stage for the login 
     * @param stage 
     */
    public void setStage(Stage stage) {
        this.stage = stage;
    }
    /**
     * 
     * @param root 
     */
    public void initStage(Parent root){
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("LogOut");
        stage.setResizable(false);
        //btn_LogOut.setOnAction(this::pushLogOut);
        stage.show();
    }
    
     private void pushLogOut(ActionEvent event){
         
         
         
     }

    
     
}
