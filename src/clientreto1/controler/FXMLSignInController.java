/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientreto1.controler;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 *
 * @author Paola
 */
public class FXMLSignInController implements Initializable {
    
    /*
    @FXML
    private Label label;
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }
    */
   
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
    
    //txt_Login.textProperty().addListener(this::handleTextChange);
    
    public void handleTextChange(){
        //Verificación de los datos vacios
        if(!txt_Login.getText().isEmpty() || 
                !txt_Password.getText().isEmpty()){
            btn_Login.setDisable(true);
        }else{
            btn_Login.setDisable(false);
        }
        //Control de los maximos caracteres permitidos
        if(txt_Login.getText().length()>20){
            String login = txt_Login.getText();
            login = login.substring(0, login.length()-1);
            txt_Login.setText(login);
        }else if(txt_Password.getText().length()>20){
            String password = txt_Password.getText();
            password = password.substring(0, password.length()-1);
            txt_Password.setText(password);
        }
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
