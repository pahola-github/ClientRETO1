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
    private Label err_Username;
    
    private Stage stage;
    
    
    public void setStage(Stage stage) {
        this.stage = stage;
    }
    
    public void initStage(Parent root) { 
        Scene scene = new Scene(root);
        stage = new Stage();
        stage.setScene(scene);
        
        //Minor properties
        stage.setTitle("SignUp new User");
        stage.setResizable(false);
        stage.initModality(Modality.APPLICATION_MODAL);
        
        
        btn_SignUp.setDisable(true);
        btn_SignUp.setOnAction(this::handleButtonAction);
       
        txt_Username.textProperty().addListener(this::handleTextChange);
        
        stage.show();
    }
    
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
        LOGGER.info("You Reached the button action handler!");
    }
    
    
    
    private void handleTextChange(ObservableValue observable, String oldValue, String newValue) {
        String errString = null;
        boolean usernameOK = false;
        
        btn_SignUp.setDisable(true);
        
        
        
        
        //Username Client check
        if(txt_Username.getText().length()<6 || txt_Username.getText().length()>20){ //Username length is between 6 and 20
            errString = "Username must to be between \n 6 – 20 characteres";
        }
        else{//Username OK
            errString = "";
            usernameOK = true;
        }
        err_Username.setText(errString);
        
        
        
        
        //Final check, if all OK, enable SignUp button
        if(usernameOK){
            btn_SignUp.setDisable(false);
        }
        
        
        
        
    }

    
}
