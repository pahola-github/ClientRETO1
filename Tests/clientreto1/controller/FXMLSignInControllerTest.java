/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientreto1.controller;

import clientreto1.FXApplication;

import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.testfx.framework.junit.ApplicationTest;
import javafx.stage.Stage;
import org.junit.FixMethodOrder;
import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.base.NodeMatchers.isDisabled;
import static org.testfx.matcher.base.NodeMatchers.isEnabled;
import static org.testfx.matcher.base.NodeMatchers.isVisible;
import static org.testfx.matcher.base.NodeMatchers.isNull;
import static org.testfx.matcher.base.NodeMatchers.isInvisible;
import static org.testfx.matcher.base.NodeMatchers.isNotFocused;
import static org.testfx.matcher.base.NodeMatchers.isFocused;
import static org.testfx.matcher.base.NodeMatchers.isNotNull;
import static org.testfx.matcher.control.TextInputControlMatchers.hasText;

/**
 *
 * @author Aingeru
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class FXMLSignInControllerTest extends ApplicationTest {

    @Override
    public void start(Stage stage) throws Exception {

        new FXApplication().start(stage);
    }

    @Test
    public void test1_Initialization() {

        verifyThat("#btn_Login", isDisabled());
        verifyThat("#txt_Login", isEnabled());
        verifyThat("#txt_Password", isEnabled());
        verifyThat("#link_SignUp", isEnabled());

    }

    @Test
    public void test2_LoginAndPasswordAreFilled() {
        verifyThat("#txt_Login", hasText(""));
        verifyThat("#txt_Password", hasText(""));
        verifyThat("#btn_Login", isDisabled());
    }

    @Test
    public void test3_LoginButtonPulsation() {
        clickOn("#txt_Login");
        write("as");
        //Error usar un minimo de 6 caracteres
        verifyThat("#err_Login", isVisible());
        eraseText(2);
        clickOn("#txt_Login");
        write("aaaaaaaaaaaaaaaaaaaaa");   
        //Error usar un máximo de 20 caracteres en user
        verifyThat("#err_Login", isVisible());
        eraseText(21);
        //Error usar caracteres invalidos
        clickOn("#txt_Login");
        write("as@");
        verifyThat("#err_Login", isVisible());
        eraseText(3);
         //Error usar un máximo de 20 caracteres en password
        clickOn("#txt_Password");
        write("aaaaaaaaaaaaaaaaaaaaa"); 
        verifyThat("#err_Password", isVisible());
        eraseText(21);

    }

}
