/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientreto1.controller;


import java.util.logging.Logger;
import java.util.regex.Pattern;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


/**
 * Login FXML Controller class
 * @author Paola
 * @version 1.1
 */
public class FXMLSignInController {
    /**
     * This  is the logger that it go to save information about the desktop
    application
     */
   private static final Logger LOGGER = Logger
            .getLogger("clientreto1.controller");
    
    @FXML
    private Label err_Login;
    @FXML 
    private Label err_Password;
    @FXML
    private TextField txt_Login;
    @FXML 
    private PasswordField txt_Password;
    @FXML
    private Button btn_Login;
    @FXML
    private Hyperlink link_SignUp;
    
    private Stage stage;
    
    
    /**
     * Set stage for the login 
     * @param stage 
     */
    public void setStage(Stage stage) {
        this.stage = stage;
    }
    /**
     * Set and initialize the stage and its properties.
     * @param root 
     */
    public void initStage(Parent root) { 
        //
        Scene scene = new Scene(root);
        stage.setScene(scene);
        //
        stage.setTitle("Login");
        stage.setResizable(false);
        
       //
        btn_Login.setDisable(true);
        btn_Login.setDefaultButton(true);
        btn_Login.setOnAction(this::handleButtonAction);
        //
        txt_Login.requestFocus();
        txt_Login.textProperty().addListener(this::handleTextChanged);
        txt_Password.textProperty().addListener(this::handleTextChanged);
        stage.show();
    }
    
   
    
    /**
     * 
     * @param event 
     */
    public void handleButtonAction(ActionEvent event){
            if(event.getSource().equals(btn_Login)){
            LOGGER.info("Login button actioned!");
            //TODO
        }
    }
    /**
     * 
     * @param observable
     * @param oldValue
     * @param newValue 
     */
    public void handleTextChanged(ObservableValue observable, 
            String oldValue, String newValue){
        String errString = null;
        String errStringPass = null;
       
       //.trim() elimina espacios
        if(txt_Login.getText().trim().isEmpty() || 
                txt_Password.getText().trim().isEmpty()){
            btn_Login.setDisable(true);
        }else{
            btn_Login.setDisable(false);
        }
        
       if(txt_Login.isFocused()){
            if(txt_Login.getText().isEmpty()){
                errString = "";
            }else if(!Pattern.matches("[a-zA-Z0-9]+", txt_Login.getText())){ //Sintax w/o numbers or especial characters
                errString = "Username not valid \n (Only A-Z and 0-9)";
            }else if(txt_Login.getText().length()<6 
                    || txt_Login.getText().length()>20){ //Username length is between 6 and 20
                errString = "Username must to be between \n 6 – 20 characteres";
            }
            err_Login.setText(errString);
        }
        if(txt_Password.isFocused()){
            if(txt_Password.getText().length()>20){
                errStringPass = "Use 20 characters maximum \nfor the password";
        }
            err_Password.setText(errStringPass);
       }
    }
    
    
}
