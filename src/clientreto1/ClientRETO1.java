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

/**
 * THIS "MAIN" CLASS IS ONLY FOR TEST SIGNUP CONTROLLER
 * @author Bryssa
 */
public class ClientRETO1 extends Application {
    
    private static final Logger LOGGER = Logger.getLogger("clientreto1");
            
    
    @Override
    public void start(Stage stage) throws Exception {
        LOGGER.info("Load of the view and the controller");
        //Probar ventanas: Cambio ("/clientreto1/[ventana].fxml"))
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/clientreto1/view/SignIn.fxml"));
        Parent root = (Parent)loader.load();
        //Probar ventanas:FXML[nombre]Controller
        FXMLSignInController controller = loader.getController();
        
        LOGGER.info("Load complete");
        controller.setStage(stage);
        controller.initStage(root);
    }


    public static void main(String[] args) {
        launch(args);
    }
    
}
