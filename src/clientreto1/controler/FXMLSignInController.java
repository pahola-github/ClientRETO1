/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientreto1.controler;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Logger;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

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
    
    public void setStage(Stage stage) {
        this.stage = stage;
    }
    
    public void initStage(Parent root) { 
        
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Login");
        stage.setResizable(false);
        stage.setOnShowing(this::handleWindowShowing);
        btn_Login.setOnAction(this::handleClick);
        txt_Login.textProperty().addListener(this::handleTextChanged);
        txt_Password.textProperty().addListener(this::handleTextChanged);
        stage.show();
    }
    
    public void handleWindowShowing(WindowEvent event){
        LOG.info("Beginning LogInController::handleWindowShowing");
        //Set the mnemonic parse to the login button
        btn_Login.setMnemonicParsing(true);
        //Set the mnemonic character and the text
        btn_Login.setText("_Login");
    }
    
    
    public void handleClick(ActionEvent event){
            //logIn();
    }
    
    public void handleTextChanged(ObservableValue observable, 
            String oldValue, String newValue){
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

    private static class LOG {

        private static void info(String beginning_LogInControllerhandleWindowShow) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        public LOG() {
        }
    }
    
}
