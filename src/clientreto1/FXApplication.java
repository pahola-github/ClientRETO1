/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientreto1;

import clientreto1.controller.FXMLLogOutController;
import clientreto1.controller.FXMLSignInController;
import java.util.logging.Logger;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;
import model.User;

/**
 * THIS "MAIN" CLASS IS ONLY FOR TEST SIGNUP CONTROLLER
 * @author Bryssa and Paola
 */
public class FXApplication extends Application {
    
    private static final Logger LOGGER = Logger.getLogger("clientreto1");
            
    
    @Override
    public void start(Stage stage) throws Exception {
        LOGGER.info("Load of the view and the controller");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/clientreto1/view/SignIn.fxml"));
        Parent root = (Parent)loader.load();
        FXMLSignInController controller = loader.getController();
        
        
       
        LOGGER.info("Load complete");
        controller.setStage(stage);
        controller.initStage(root); 
        
    }

    /**
     * Launch main
     * @param args 
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
