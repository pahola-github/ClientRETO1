/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientreto1.controller;

import clientreto1.logic.SigneableFactory;
import exceptions.EmailExistException;
import exceptions.MaxConnectionException;
import exceptions.ServerException;
import exceptions.UserExistException;
import interfaces.Signeable;
import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javafx.beans.InvalidationListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import model.User;
import model.UserPrivilege;
import model.UserStatus;

/**
 * Class to control the actions of the SignUp window
 *
 * @author Bryssa
 */
public class FXMLSignUpController {

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
    @FXML
    private ProgressIndicator pri_progression;

    private Stage stage;

    //Local data check
    private boolean usernameOK = false;
    private boolean emailOK = false;
    private boolean nameOK = false;
    private boolean passOK = false;
    private boolean verifyOK = false;

    /**
     * Set the stage from the main
     *
     * @param stage
     */
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    /**
     * Init method to set the stage, the action handlers and some properties
     *
     * @param root
     */
    public void initStage(Parent root) {
        Scene scene = new Scene(root);
        //stage = new Stage();
        stage.setScene(scene);

        //Minor properties
        stage.setTitle("SignUp new User");
        stage.setResizable(false);
        stage.initModality(Modality.APPLICATION_MODAL);

        //Sign Up button
        btn_SignUp.setDisable(true);
        btn_SignUp.setDefaultButton(true);
        btn_SignUp.setOnAction(this::handleButtonAction);

        //Back button
        btn_Back.setOnAction(this::handleButtonAction);
        
        //Red cross button
        stage.onCloseRequestProperty().set(this::handleCloseRequest);
        

        //Text Fields
        txt_Username.textProperty().addListener(this::handleTextChange);
        stage.show();
        txt_Email.textProperty().addListener(this::handleTextChange);
        txt_Fullname.textProperty().addListener(this::handleTextChange);
        txt_Password.textProperty().addListener(this::handleTextChange);
        txt_VerifyPass.textProperty().addListener(this::handleTextChange);
        txt_Username.requestFocus();
    }

    /**
     * Method that handles the action of the buttons
     *
     * @param event of a button actioned
     */
    @FXML
    private void handleButtonAction(ActionEvent event) {
        if (event.getSource().equals(btn_SignUp)) { //Button SignUp
            LOGGER.info("SignUp button actioned!");

            try{//Try to connect with the server, if not, catch the exceptions and shows and error
                User user = new User();
                user.setLogin(txt_Username.getText());
                user.setEmail(txt_Email.getText());
                user.setFullName(txt_Fullname.getText());
                user.setPassword(txt_Password.getText());
                user.setPrivilege(UserPrivilege.USER);
                user.setStatus(UserStatus.ENABLED);
                user.setLastAccess(Date.valueOf(LocalDate.now()));
                user.setLastPasswordChange(Date.valueOf(LocalDate.now()));
                
                LOGGER.info("User created...Sending to server...");
                Signeable client = SigneableFactory.getSigneableImplementation();
                user = client.signUp(user);
                popUpInfo("User created successfully.");
                LOGGER.info("User Created");
                
                
            }/*catch(MaxConnectionException e){
                popUpError("Server is busy right now, "
                         + "\n try again later.");
            }*/catch(UserExistException e){
                popUpError("User already exist.");
                err_Username.setText("User already exist");
                txt_Username.requestFocus();
            }catch(EmailExistException e){
                popUpError("Email already exist.");
                err_Email.setText("Email already exist");
                txt_Email.requestFocus();
            }catch(ServerException e){
                 popUpError("An error ocurred trying to sign up, "
                         + "\n try again later.");
            }/*catch(IOException e){
                popUpError("An error ocurred trying to sign up, "
                         + "\n try again later.");
            }*/catch(Exception e){
                popUpError("An error ocurred trying to sign up, "
                         + "\n try again later.");
            }
        }
        if (event.getSource().equals(btn_Back)) {//Button BACK
            LOGGER.info("Back button actioned!");
            closeAlert();
        }
    }

    /**
     * Event handler, when the user click on the red cross button of the window
     * @param event of the button
     */
    private void handleCloseRequest(WindowEvent event) {
        LOGGER.info("Red cross button actioned!");
        closeAlert();
    }
    
    /**
     * Method to check that the LOCAL data is right
     *
     * @param observable
     * @param oldValue
     * @param newValue
     */
    private void handleTextChange(ObservableValue observable, String oldValue, String newValue) {
        String errString = null;
        btn_SignUp.setDisable(true);

        //Username local check
        if (txt_Username.isFocused()) { //If is focused
            usernameOK = false;
            if (txt_Username.getText().isEmpty()) {//Field is empty
                errString = "Username cannot be empty";
            } else if (!Pattern.matches("[a-zA-Z0-9]+", txt_Username.getText())) { //Syntax w/o especial characters or blankspaces
                errString = "Username not valid \n (Only A-Z and 0-9)";
            } else if (txt_Username.getText().length() < 6
                    || txt_Username.getText().length() > 20) { //Username length is between 6 and 20
                errString = "Username must to be between \n 6 – 20 characters";
            } else {//If username is OK
                errString = "";
                usernameOK = true;
            }
            err_Username.setText(errString);
        }

        //Email local check
        if (txt_Email.isFocused()) { //If is focused
            emailOK = false;
            if (txt_Email.getText().isEmpty()) { //Field is empty
                errString = "Email cannot be empty";
            } else if (!checkEmail(txt_Email.getText())) { //Syntax is correct callin  a method to verify
                errString = "E-mail syntax. not valid\n(e.g. example@email.com)";
            } else if (txt_Email.getText().length() < 6
                    || txt_Email.getText().length() > 30) { //Email length is between 6 and 20
                errString = "E-mail must to be between \n 6-30 characters";
            } else { //If email is OK
                errString = "";
                emailOK = true;
            }
            err_Email.setText(errString);
        }

        //Fullname local check
        if (txt_Fullname.isFocused()) { //If is focused
            nameOK = false;
            if (txt_Fullname.getText().isEmpty()) {//Field is empty
                errString = "Fullname cannot be empty";
            } else if (!Pattern.matches("[a-zA-Z_ ]+", txt_Fullname.getText())) { // 
                errString = "Fullname not valid\n(Only A-Z and blankspaces)";
            } else if (txt_Fullname.getText().length() < 5
                    || txt_Fullname.getText().length() > 50) { //Fullname length is between 6 and 20
                errString = "Fullname must to be between \n 5 – 50 characters";
            } else {//If fullname is OK
                errString = "";
                nameOK = true;
            }
            err_Fullname.setText(errString);
        }

        //Password local check
        if (txt_Password.isFocused()) { //If is focused
            passOK = false;
            if (txt_Password.getText().isEmpty()) { //Field is empty
                errString = "Password cannot be empty";
            } else if (txt_Password.getText().length() < 4
                    || txt_Password.getText().length() > 20) { //Password length is between 4 and 20
                errString = "Password must to be between \n 4 – 20 characters";
            } else { //If password is OK
                errString = "";
                passOK = true;
            }
            err_Password.setText(errString);
        }

        //Password verification local check
        if (txt_VerifyPass.isFocused() || txt_Password.isFocused()) {//If is focused
            verifyOK = false;
            if (txt_VerifyPass.getText().isEmpty()) {
                errString = "Password verificstion cannot be empty";
            } else if (!txt_VerifyPass.getText().equals(txt_Password.getText())) { //If password verification not matches the main password 
                errString = "Passwords do not match,\n try again";
            } else { //If password verification is OK
                errString = "";
                verifyOK = true;
            }
            err_VerifyPass.setText(errString);
        }

        int level = okLevel();//Gets the level of verification

        //Sets the progress in the indicator according to the level of verification
        pri_progression.setProgress(0.2 * level);

        if (level == 5) {//Final check, if all OK, enable SignUp button
            btn_SignUp.setDisable(false);
        }
    }

    /**
     * Method that checks if the email has "@" and "."
     *
     * @param email to check
     * @return the final result
     */
    private boolean checkEmail(String email) {
        boolean ok = false;
        boolean at = false;

        //Verify the email, searching for "@" and "."
        for (int letra = 0; email.length() > letra; letra++) {
            if (email.substring(letra, letra + 1).equalsIgnoreCase("@"))//Search for @
                at = true;
            if (at && email.substring(letra, letra + 1).equalsIgnoreCase(".")){//Search for .
                return ok=true;
            }
        }
        return ok;
    }

    private int okLevel() {
        Integer level = 0;
        if (usernameOK) 
            level++;
        if (emailOK) 
            level++;
        if (nameOK) 
            level++;
        if (passOK)
            level++;
        if (verifyOK)
            level++;
        return level;
    }
    
    
    
    /**
     * 
     * TODO
     * DEPURAR ESTO
     * juntar los tres metodos del alert mandando parametros para definir
     * el tipo y los mensajes
     * 
     * ESTOS TRES METODOS SON SOLAMENTE TEMPORALES PARA PRUEBAS
     * 
     */
    
     /**
     * Method to show an alert asking if the user is sure to return  to SignIn window
     */
    private void closeAlert (){
        LOGGER.info("Return to SignIn");
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Close confirmation");
        alert.setHeaderText("Registration will be cancelled."
                + "\nAll the data will be erased.");
        alert.setContentText("Are you sure?");
        alert.getButtonTypes().setAll(ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.YES) {//User click on YES
            stage.hide();
        } else { //User click on NO
            alert.close();
        }
    }
    
    private void popUpError(String msg){
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("ERROR");
        alert.setHeaderText("SignUp failed");
        alert.setContentText(msg);
        Button okButton = (Button) alert.getDialogPane().lookupButton(ButtonType.OK);
        okButton.setId("okbutton");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            stage.hide();
        }
    }
    
    private void popUpInfo(String msg){
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Info");
        alert.setHeaderText("Information");
        alert.setContentText(msg);
        Button okButton = (Button) alert.getDialogPane().lookupButton(ButtonType.OK);
        okButton.setId("okbutton");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            stage.hide();
        }
    }
    
}
