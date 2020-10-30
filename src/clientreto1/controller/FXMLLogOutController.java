/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientreto1.controller;

import static com.sun.xml.internal.ws.spi.db.BindingContextFactory.LOGGER;
import java.util.Optional;
import java.util.logging.Level;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import model.User;

/**
 * LogOut FXML class Controller
 * @author Paola
 * @version 1.2
 */
public class FXMLLogOutController {

    @FXML
    private Button btn_LogOut;
    
    @FXML
    private Label lbl_NombreUsuario;

    private User user;

    /**
     * Return user
     * @return
     */
    public User getUser() {
        return user;
    }

    /**
     *  Set user for the LogOut
     * @param user
     */
    public void setUser(User user) {
        this.user = user;
    }

    private Stage stage;

    /**
     * Return stage
     * @return
     */
    public Stage getStage() {
        return stage;
    }

    /**
     * Set stage for the logOut
     * @param stage
     */
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    /**
     * Set and initialize the stage and its properties.
     * @param root
     */
    public void initStage(Parent root, User user) {
        //Create a scene associated to to the parent root
        Scene scene = new Scene(root);
         //Associate the scene with the stage
        stage.setScene(scene);
        //Set window's properties
        stage.setTitle("LogOut");
        stage.setResizable(false);
        stage.initModality(Modality.WINDOW_MODAL);
        //Set window's event handlers button
        btn_LogOut.setOnAction(this::pushLogOut);
        //Set window's event handlers text
        lbl_NombreUsuario.setText(user.getLogin());
        //Show the LogOut window
        stage.onCloseRequestProperty().set(this::handleCloseRequest);
        stage.show();
    }
    /**
     * A popup appears, ask if you are sure you want to log out.
     * @param event 
     */
    private void pushLogOut(ActionEvent event) {
       LOGGER.info("Return to SignIn");
       Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
       alert.setTitle("Log Out confirmation");
       alert.setHeaderText("You are going to log out.");
       alert.setContentText("Are you sure you want to logout?");
       alert.getButtonTypes().setAll(ButtonType.YES, ButtonType.NO);
       Optional<ButtonType> result = alert.showAndWait();
       if(result.get()==ButtonType.YES) {//User click on YES)
           stage.hide();
       }else{
           alert.close();
       }
    }
    /**
     * A popup appears, ask if you are sure you want to close application.
     * @param event 
     */
    private void handleCloseRequest(WindowEvent event){
        LOGGER.info("Close Application");
       Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
       alert.setTitle("Close confirmation");
       alert.setHeaderText("Application will be closed");
       alert.setContentText("Are you sure?");
       alert.getButtonTypes().setAll(ButtonType.YES, ButtonType.NO);
       Optional<ButtonType> result = alert.showAndWait();
       if(result.get()==ButtonType.YES) {//User click on YES)
           stage.close();
           Platform.exit();
       }else{
           alert.close();
       }
    }

}
