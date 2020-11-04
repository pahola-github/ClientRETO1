package clientreto1.controller;

import clientreto1.FXApplication;

import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.testfx.framework.junit.ApplicationTest;
import javafx.stage.Stage;
import org.junit.FixMethodOrder;
import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.base.NodeMatchers.isEnabled;
import static org.testfx.matcher.base.NodeMatchers.isNotNull;
import static org.testfx.matcher.base.NodeMatchers.isVisible;
import static org.testfx.matcher.control.TextInputControlMatchers.hasText;

/**
 * This class contains all the tests of the LogOut controller
 * @author Aingeru
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class FXMLLogOutControllerTest extends ApplicationTest {

    @Override
    public void start(Stage stage) throws Exception {

        new FXApplication().start(stage);
           
    }
    
     @Test
    public void test1_Initialization() {
        clickOn("#txt_Login");
        write("aingeru");
        clickOn("#txt_Password");
        write("abcd*1234");
        clickOn("#btn_Login");  
        verifyThat("#logoutPane", isVisible());
        verifyThat("#btn_LogOut", isEnabled());
        verifyThat("#lbl_NombreUsuario",isVisible());
      
    }
      @Test
    public void test2_LogOutButtonIsFunctional() {
        clickOn("#btn_LogOut");
        clickOn("Sí");
        verifyThat("#signinPane", isVisible());
    }
}
