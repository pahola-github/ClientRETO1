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
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
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
    private PasswordField txt_Password;
    @FXML
    private PasswordField txt_VerifyPass;
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
    
    //Local data check
    private boolean usernameOK = false;
    private boolean emailOK = false;
    private boolean nameOK = false;
    private boolean passOK =  false;
    private boolean verifyOK = false;
    
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
        
        txt_Fullname.textProperty().addListener(this::handleTextChange);
        
        txt_Password.textProperty().addListener(this::handleTextChange);
        
        txt_VerifyPass.textProperty().addListener(this::handleTextChange);
        
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
        btn_SignUp.setDisable(true);
        
        
        
        
        //Username local check
        if(txt_Username.isFocused()){
            usernameOK = false;
            
            
            if(txt_Username.getText().isEmpty()){
                errString = "Username cannot be empty";
            }else  if(!Pattern.matches("[a-zA-Z0-9]+",txt_Username.getText())){ //Sintax w/o especial characters or blankspaces
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
            if(txt_Email.getText().isEmpty()){
                errString = "Email cannot be empty";
            }
            //TODO else if
        }
        
        //Fullname local check
        if(txt_Fullname.isFocused()){
            nameOK = false;
            
            if(txt_Fullname.getText().isEmpty()){
                errString = "Fullname cannot be empty";
            }else if(!Pattern.matches("[a-zA-Z_ ]+", txt_Fullname.getText())){
                errString = "Fullname not valid\n(Only A-Z and blankspaces)";
            }else if(txt_Fullname.getText().length()<5
                    || txt_Fullname.getText().length()>50){
                errString = "Fullname must to be between \n 5 – 50 characteres";
            }else{
                errString = "";
                nameOK = true;
            }
            err_Fullname.setText(errString);
        }
        
        //Password local check
        if(txt_Password.isFocused()){
            passOK = false;
            if(txt_Password.getText().isEmpty()){
                errString = "Password cannot be empty";
            }else if(txt_Password.getText().length()<4
                    || txt_Password.getText().length()>20){
                errString = "Password must to be between \n 4 – 20 characteres";
            }else{
                errString = "";
                passOK = true;
            }
            err_Password.setText(errString);
        }
        
        
        //Password verification local check
        if(txt_VerifyPass.isFocused()){
            verifyOK = false;
            if(!txt_VerifyPass.getText().equals(txt_Password.getText())){
                errString = "Passwords do not match,\n try again";
            }else{
                errString = "";
                verifyOK = true;
            }
            err_VerifyPass.setText(errString);
        }
        

        
        
        //Final check, if all OK, enable SignUp button
        if(usernameOK && emailOK && nameOK && passOK && verifyOK){
            btn_SignUp.setDisable(false);
        }
        
        
        
        
    }
        

    
}
