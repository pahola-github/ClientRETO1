/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientreto1.controller;

import clientreto1.logic.SigneableFactory;
import exceptions.InvalidPasswordException;
import exceptions.ServerException;
import exceptions.UserNotExistException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.User;

/**
 * Login FXML Controller class
 *
 * @author Paola
 * @version 1.2
 */
public class FXMLSignInController {

    /**
     * This is the logger that it go to save information about the desktop
     * application
     */
    private static final Logger LOGGER = Logger
            .getLogger("clientreto1.controller.FXMLSignInController");

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

    private User user;

    /**
     * Set stage for the login
     *
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
        //Create a scene associated to to the parent root
        Scene scene = new Scene(root);
        //Associate the scene with the stage
        stage.setScene(scene);
        //Set window's properties
        stage.setTitle("Login");
        stage.setResizable(false);
        //Set window's event handlers button
        btn_Login.setDisable(true);
        btn_Login.setDefaultButton(true);
        btn_Login.setOnAction(this::handleButtonAction);
        //Set window's event handlers text
        txt_Login.requestFocus();
        txt_Login.textProperty().addListener(this::handleTextChanged);
        txt_Password.textProperty().addListener(this::handleTextChanged);
        //Set window's event handlers link
        link_SignUp.setOnAction(this::handleSignUp);
        //Show the LogIn window
        stage.show();
    }

    /**
     * Set atributes to the controls that it need in the window showing event
     *
     * @param event
     */
    public void handleButtonAction(ActionEvent event) {
        if (event.getSource().equals(btn_Login)) {
            LOGGER.info("Login button actioned!");

            try {
                User user = new User();

                user.setLogin(txt_Login.getText());
                user.setPassword(txt_Password.getText());        
                SigneableFactory.getSigneableImplementation().signIn(user);              
                FXMLLoader loader = new FXMLLoader(getClass()
                        .getResource("/clientreto1/view/LogOut.fxml"));
                Parent root = (Parent) loader.load();
                FXMLLogOutController logOutController
                        = (FXMLLogOutController) loader.getController();
                Stage logOutStage = new Stage();
                logOutController.setStage(logOutStage);
                logOutController.initStage(root, user);
            } catch (IOException ex) {
                LOGGER.warning("FXMLSignInController : Exception on FXMLSignInController");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setContentText("Sorry, an error has ocurred");
                alert.showAndWait();
            } catch (UserNotExistException ex) {
                LOGGER.warning("FXMLSignInController : Login not found");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Login Error");
                alert.setContentText("User does not exist");
                alert.showAndWait();
            } catch (ServerException ex) {
                LOGGER.warning("FXMLSignInController : Server connection error");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Server Error");
                alert.setContentText("Unable to connect with server");
                alert.showAndWait();
            } catch (InvalidPasswordException ex) {
                LOGGER.warning("FXMLSignInController : Wrong Password");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Password Error");
                alert.setContentText("Password does not exist");
                alert.showAndWait();
            }/*catch(MaxConnectionException ex){
                LOGGER.warning("FXMLSignInController : Max Connection");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Connection Error");
                alert.setContentText("The server has exceeded the maximun number of connection, try again in a few minutes.");
            }*/
        }
    }

    /**
     * Verify if there are empty fields and modify button login's setDisable
     * method. If there are any field empty, disable the login button, else
     * enable it. Also verify the that the person who is writting don't write
     * more than 20 characters,check the introduction of symbols.
     *
     * @param observable
     * @param oldValue
     * @param newValue
     */
    public void handleTextChanged(ObservableValue observable,
            String oldValue, String newValue) {
        
        String errString = null;
        String errStringPass = null;
        
        if (txt_Login.getText().trim().isEmpty()
                || txt_Password.getText().trim().isEmpty()) {
            btn_Login.setDisable(true);
        } else {
            btn_Login.setDisable(false);
        }
        
        if (txt_Login.isFocused()) {
            if (txt_Login.getText().isEmpty()) {
                errString = "";
            } else if (!Pattern.matches("[a-zA-Z0-9]+", txt_Login.getText())) { 
                errString = "Username not valid \n (Only A-Z and 0-9)";
            } else if (txt_Login.getText().length() < 6
                    || txt_Login.getText().length() > 20) { 
                errString = "Username must to be between \n 6 – 20 characteres";
            }
            err_Login.setText(errString);
        }
        if (txt_Password.isFocused()) {
            if (txt_Password.getText().length() > 20) {
                errStringPass = "Use 20 characters maximum \nfor the password";
            }
            err_Password.setText(errStringPass);
        }
    }

    /**
     * Load the signUp xml and pass the control to it controller
     * @param event
     */
    public void handleSignUp(ActionEvent event) {
            //Create the loader for the signup xml
        FXMLLoader loader = new FXMLLoader(getClass()
                .getResource("/clientreto1/view/SignUp.fxml"));
            //Create the parent and load the tree
        Parent root = null;
        try {
            root = (Parent) loader.load();
                //Create the Stage
            Stage signUpStage = new Stage();
                //Load de controller
            FXMLSignUpController controller = loader.getController();
                //Set the stage
            controller.setStage(signUpStage);
                //Pass the control to the controller
            controller.initStage(root);

        } catch (IOException ex) {
            LOGGER.log(Level.SEVERE, "An error in the SignIn loader", ex);
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Sorry, an error has ocurred");
            alert.showAndWait();
        }

    }

    public void logOut(User user) {
        
        FXMLLoader loader = new FXMLLoader(getClass()
                .getResource("/clientreto1/view/LogOut.fxml"));
        
        Parent root;
        try {
            root = (Parent) loader.load();
                //Create stage
            Stage logOutStage = new Stage();
                //Load the controller
            FXMLLogOutController controller = loader.getController();
                //Set the stage and the user
            controller.setStage(logOutStage);
            controller.setUser(user);
                //Pass the communication with the next layer
                //PAss the control to the controller
            controller.initStage(root, user);
                //Hide this stage
            stage.hide();
        } catch (IOException ex) {
            LOGGER.log(Level.SEVERE, "An error in the SignIn loader", ex);
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Sorry, an error has ocurred");
            alert.showAndWait();
        }
    }
}
