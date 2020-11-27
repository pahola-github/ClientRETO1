/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientreto1.controller;

import clientreto1.FXApplication;
import javafx.scene.input.KeyCode;

import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.testfx.framework.junit.ApplicationTest;
import javafx.stage.Stage;
import org.junit.FixMethodOrder;
import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.base.NodeMatchers.isDisabled;
import static org.testfx.matcher.base.NodeMatchers.isEnabled;
import static org.testfx.matcher.base.NodeMatchers.isFocused;
import static org.testfx.matcher.base.NodeMatchers.isVisible;
import static org.testfx.matcher.control.TextInputControlMatchers.hasText;

/**
 * This class contains all the tests of the SignIn Controller
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
    public void test01_Initialization() {

        verifyThat("#btn_Login", isDisabled());
        verifyThat("#txt_Login", isEnabled());
        verifyThat("#txt_Password", isEnabled());
        verifyThat("#link_SignUp", isEnabled());

    }

    @Test
    public void test02_LoginAndPasswordAreFilled() {
        verifyThat("#txt_Login", hasText(""));
        verifyThat("#txt_Password", hasText(""));
        verifyThat("#btn_Login", isDisabled());
    }

    @Test
    public void test03_UserExists() {

        //Si existe el usuario y la password, le da acceso a la ventana main.
        clickOn("#txt_Login");
        write("aingeru");
        clickOn("#txt_Password");
        write("abcd*1234");
        clickOn("#btn_Login");
        verifyThat("#logoutPane", isVisible());
        clickOn("#btn_LogOut");
        clickOn("Sí");
        }

    @Test
    public void test04_UserNotExists() {

        //Si  el usuario o la password no existen, le da acceso a la ventana main.
        clickOn("#txt_Login");
        write("aingeruuu");
        clickOn("#txt_Password");
        write("abcd*1234");
        clickOn("#btn_Login");
        verifyThat("User does not exist",isVisible());
        clickOn("Aceptar");
        
        }
     
    
    @Test
    public void test05_PasswordIsIncorrect() {
        clickOn("#txt_Login");
        write("aingeru");
        clickOn("#txt_Password");
        write("asdadsa");
        clickOn("#btn_Login");
        verifyThat("Password does not exist", isVisible());
        clickOn("Aceptar");
        
    }
    @Test
    public void test06_LoginOrPasswordFieldsDataIsIncorrect() {
        //Error usar un minimo de 6 caracteres
        clickOn("#txt_Login");
        write("as");
        verifyThat("#err_Login", isVisible());
        eraseText(2);
        //Error usar un máximo de 20 caracteres en user
        clickOn("#txt_Login");
        write("aaaaaaaaaaaaaaaaaaaaa");
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
     @Test
    public void test07_HyperLinkCanBePulsed() {

         clickOn("#link_SignUp");
         verifyThat("#signupPane", isVisible());
         clickOn("#btn_Back");
         clickOn("Sí");
        
        }
    
    //THIS TEST ONLY RUNS SUCCESFULLY WHERE THE SERVER IS DOWN
    /* 
    @Test
    public void test08_DatabaseErrorIsVisible() {

        clickOn("#txt_Login");
        write("aingeru");
        clickOn("#txt_Password");
        write("abcd*1234");
        clickOn("#btn_Login");
        verifyThat("Unable to connect with server", isVisible());
        clickOn("Aceptar");

    }*/

    @Test
    public void test09_CantWriteMoreThan20CharactersInLoginField() {

        clickOn("#txt_Login");
        write("aaaaaaaaaaaaaaaaaaaaab");
        eraseText(20);
        verifyThat("#txt_Login", hasText(""));

    }

    @Test
    public void test10_IfUserIsInvalidClearLoginTextField() {

        clickOn("#txt_Login");
        write("aaaaaaaaa");
        clickOn("#txt_Password");
        write("abcd*1234");
        clickOn("#btn_Login");
        verifyThat("User does not exist", isVisible());
        clickOn("Aceptar");
        verifyThat("#txt_Login", isFocused());
        verifyThat("#txt_Login", hasText(""));

    }
    
    @Test
    public void test11_PasswordIsIntroducedAndUserHasOneCharacterLoginButtonIsDisabled() {

        
        clickOn("#txt_Password");
        write("abcd*1234");
        clickOn("#txt_Login");
        write("a");
        verifyThat("#btn_Login", isDisabled());

    }
    @Test
    public void test12_CloseRequest() {

        this.push(KeyCode.ALT, KeyCode.F4);
        verifyThat("Do you want to close the window?", isVisible());
        clickOn("Cancelar");
        
    }

}