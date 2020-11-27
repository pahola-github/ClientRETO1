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
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javafx.application.Platform;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import model.User;

/**
 * This class contains the Login FXML controller.
 *
 * @author Paola, Aingeru
 */
public class FXMLSignInController {

    /**
     * This is the logger that it go to save information about the desktop application
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
     * @param stage receives a Stage.
     */
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    /**
     * Set and initialize the stage and its properties.
     *
     * @param root Receives a parent stage that is the base class for all nodes that have children in the scene graph.
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
        
        //------------- AINGERU ---------------------
        //The following line is called when there is an external request to close the window.
        stage.onCloseRequestProperty().set(this::handleCloseRequest);
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
     * @param event An event representing some type of action. In this case when the Login Button is fired.
     */
    public void handleButtonAction(ActionEvent event)  {
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
                //------------------------------AINGERU---------------------
                //The following 2 lines set the Login text as default(empty) and put the focus in it.
                txt_Login.setText("");
                txt_Login.requestFocus();
                // --------------------------------AINGERU--------------------
                //If a ServerException is caught, an alert is displayed with the text "Unable to connect with server"
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
     * Verify if there are empty fields and modify button login's setDisable method. If there are any field empty, disable the login button, else enable it. Also verify the that the person who is writting don't write more than 20 characters,check the introduction of symbols.
     *
     * @param observable  an entity that wraps a value and allows to observe the value for changes
     * @param oldValue The old value that the method is going to observe (in this case is not used)      
     * @param newValue The new value that the method is going to observe (in this case is not used)  
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
        if (txt_Login.getText().length() < 6
                || txt_Login.getText().length() > 20) {
            btn_Login.setDisable(true);
        }
        if (txt_Password.getText().length() < 6
                || txt_Password.getText().length() > 20) {
            btn_Login.setDisable(true);
        }

        if (txt_Login.isFocused()) {
            if (txt_Login.getText().isEmpty()) {
                errString = "";
            } else if (!Pattern.matches("[a-zA-Z0-9]+", txt_Login.getText())) {
                errString = "Username not valid \n (Only A-Z and 0-9)";
            } else if (txt_Login.getText().length() < 6
                    || txt_Login.getText().length() > 20) {
                errString = "Username must to be between \n 6 – 20 characteres";
                
        //----------------------- AINGERU--------------------------
        // This if controls that the maximum number of characters to write in Login text is 20.
                if (txt_Login.getText().length() > 20 ) {
                    String maxLength = txt_Login.getText().substring(0, 20);
                    txt_Login.setText(maxLength);
                }
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
     *
     * @param event An event representing some type of action. In this case when the hyperlink is pressed. 
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
    /**
     * This method displays an alert to close the current window, which asks for confirmation
     * @param event An event that indicates that a window its going to change  its status.
     */
    public void handleCloseRequest(WindowEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Close");
        alert.setHeaderText("Application will be closed.");
        alert.setContentText("Do you want to close the window?");
        alert.getButtonTypes().setAll(ButtonType.OK,ButtonType.CANCEL);
        Optional<ButtonType> result = alert.showAndWait();
        if(result.get().equals(ButtonType.OK)){
        stage.close();
        Platform.exit();
    }else{
            event.consume();
            alert.close();
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
