/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientreto1.controller;

import java.util.logging.Logger;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author Bryssa
 */
public class SignUpController {
    
    private static final Logger LOGGER = Logger
            .getLogger("clientreto1.controller");

    @FXML
    private Button btn_SignUp;
    @FXML
    private Button btn_Back;
    @FXML
    private TextField txt_Username;
    @FXML
    private TextField txt_Email;
    @FXML
    private TextField txt_Fullname;
    @FXML
    private TextField txt_Password;
    @FXML
    private TextField txt_VerifyPass;
    @FXML
    private Label err_Username;
    @FXML
    private Label err_Email;
    @FXML
    private Label err_Fullname;
    @FXML
    private Label err_Password;
    @FXML
    private Label err_VerifyPass;
    
    private Stage stage;
    
    /**
     * 
     * @param stage 
     */
    public void setStage(Stage stage) {
        this.stage = stage;
    }
    
    
    /**
     * 
     * @param root 
     */
    public void initStage(Parent root) { 
        Scene scene = new Scene(root);
        stage = new Stage();
        stage.setScene(scene);
        
        //Minor properties
        stage.setTitle("SignUp new User");
        stage.setResizable(false);
        stage.initModality(Modality.APPLICATION_MODAL);
        
        
        btn_SignUp.setDisable(true);
        btn_SignUp.setDefaultButton(true);
        btn_SignUp.setOnAction(this::handleButtonAction);
       
        txt_Username.requestFocus();
        txt_Username.textProperty().addListener(this::handleTextChange);
        
        txt_Email.textProperty().addListener(this::handleTextChange);
        
        stage.show();
    }
    
    
    /**
     * 
     * @param event 
     */
    @FXML
    private void handleButtonAction(ActionEvent event) {
        if(event.getSource().equals(btn_SignUp)){
            LOGGER.info("SignUp button actioned!");
            //TODO
        }
        if(event.getSource().equals(btn_Back)){
            LOGGER.info("Back button actioned");
            //TODO
        }
    }
    
    
    
    /**
     * 
     * @param observable
     * @param oldValue
     * @param newValue 
     */
    private void handleTextChange
        (ObservableValue observable, String oldValue, String newValue) {
        String errString = null;
        boolean usernameOK = false;
        boolean emailOK = false;
        boolean nameOK = false;
        boolean passOK =  false;
        boolean verifyOK = false; //I need it?
        btn_SignUp.setDisable(true);
        
        
        
        
        //Username local check
        if(txt_Username.isFocused()){
            if(hasNumbers(txt_Username.getText())){ //Sintax w/o numbers or especial characters
                errString = "Username not valid \n (Only A-Z and 0-9)";
            }else if(txt_Username.getText().length()<6 
                    || txt_Username.getText().length()>20){ //Username length is between 6 and 20
                errString = "Username must to be between \n 6 – 20 characteres";
            }else{//Username OK
                errString = "";
                usernameOK = true;
            }
            err_Username.setText(errString);
        }
        
        //Email local check
        if(txt_Email.isFocused()){
            LOGGER.info("prueba de observable");
        }
        
        
        //TODO seguir poniendo los checks locales con el .isFocused
        //Para que no compruebe todos los campos con cualquier cambio
        
        
        
        //Final check, if all OK, enable SignUp button
        if(usernameOK){
            btn_SignUp.setDisable(false);
        }
        
        
        
        
    }
        
    /**
     * Metod to check if a String contains numbers
     * @param value The string to check
     * @return a boolean with the final result
     */
    private boolean hasNumbers(String value){
        boolean hasNumbers = false;
        //A large and ugly If that checks if the string contains numbers
        if(value.contains("1") || value.contains("2")
                ||value.contains("3") || value.contains("4")
                ||value.contains("5") || value.contains("6")
                ||value.contains("7") || value.contains("8")
                ||value.contains("9") || value.contains("0")){
            hasNumbers= true;
        }
        return hasNumbers;
    }

    
}
