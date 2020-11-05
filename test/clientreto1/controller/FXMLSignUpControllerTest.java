package clientreto1.controller;

import clientreto1.FXApplication;

import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.testfx.framework.junit.ApplicationTest;
import javafx.stage.Stage;
import org.junit.After;
import org.junit.FixMethodOrder;
import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.base.NodeMatchers.isDisabled;
import static org.testfx.matcher.base.NodeMatchers.isEnabled;
import static org.testfx.matcher.base.NodeMatchers.isVisible;
import static org.testfx.matcher.base.NodeMatchers.isFocused;
import static org.testfx.matcher.control.TextInputControlMatchers.hasText;

/**
 *
 * @author gsalg
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class FXMLSignUpControllerTest extends ApplicationTest {

    @Override
    public void start(Stage stage) throws Exception {

        new FXApplication().start(stage);
        clickOn("#link_SignUp");
    }

    @After
    public void closeSignUpWindow() {
        clickOn("#btn_Back");
        clickOn("Sí");
    }

    @Test
    public void testA_Initialization() {

        verifyThat("#txt_Username", hasText(""));
        verifyThat("#txt_Email", hasText(""));
        verifyThat("#txt_Fullname", hasText(""));
        verifyThat("#txt_PasswordSU", hasText(""));
        verifyThat("#txt_VerifyPass", hasText(""));

        verifyThat("#txt_Username", isFocused());
        verifyThat("#btn_Back", isEnabled());
        verifyThat("#btn_SignUp", isDisabled());

    }

    @Test
    public void testB_SignUpButtonIsDisabled() {

        clickOn("#txt_Username");
        write("username");
        verifyThat("#btn_SignUp", isDisabled());
        eraseText(8);

        clickOn("#txt_Email");
        write("username@email.com");
        verifyThat("#btn_SignUp", isDisabled());
        eraseText(18);

        clickOn("#txt_Fullname");
        write("User Name");
        verifyThat("#btn_SignUp", isDisabled());
        eraseText(9);

        clickOn("#txt_PasswordSU");
        write("username");
        verifyThat("#btn_SignUp", isDisabled());
        eraseText(8);

        clickOn("#txt_VerifyPass");
        write("username");
        verifyThat("#btn_SignUp", isDisabled());
        eraseText(8);

    }
    @Test
    public void testC_SignUpButtonIsEnabled() {

        clickOn("#txt_Username");
        write("username");

        clickOn("#txt_Email");
        write("username@email.com");

        clickOn("#txt_Fullname");
        write("User Name");
        
        clickOn("#txt_PasswordSU");
        write("username");
        
        clickOn("#txt_VerifyPass");
        write("username");

        verifyThat("#btn_SignUp", isEnabled());

    }

    @Test
    public void testD_TextFieldVerify(){
        
        clickOn("#txt_Username"); // Comprueba error cuando tengo menos de 6 caracteres.
        write("asd");
        verifyThat("#err_Username", isVisible());
        eraseText(3);
        
        clickOn("#txt_Username");
        write("aaaaaaaaaaaaaaaaaaaaaaaaaa"); // Comprueba error cuando tengo más de 20 caracteres.
        verifyThat("#err_Username", isVisible());
        eraseText(26);
        
        clickOn("#txt_Username"); // Comprueba error cuando tengo caracteres invalidos.
        write("a*#");
        verifyThat("#err_Username", isVisible());
        eraseText(3);
        
        clickOn("#txt_Email"); // Comprueba error cuando tengo más de 50 caracteres.
        write("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        verifyThat("#err_Email", isVisible());
        eraseText(58);
        
        clickOn("#txt_Email"); // Comprueba error cuando tengo menos de 6 caracteres y no utilizo la sintaxis correcta.
        write("asd");
        verifyThat("#err_Email", isVisible());
        eraseText(3);
        
        clickOn("#txt_Fullname"); // Comprueba error cuando tengo menos de 6 caracteres. 
        write("asd");
        verifyThat("#err_Fullname", isVisible());
        eraseText(3);
        
        clickOn("#txt_Fullname"); // Comprueba error cuando tengo más de 50 caracteres.
        write("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        verifyThat("#err_Fullname", isVisible());
        eraseText(58);
        
        clickOn("#txt_PasswordSU"); // Comprueba error cuando tengo menos de 4 caracteres.
        write("asd");
        verifyThat("#err_Password", isVisible());
        eraseText(3);
        
        clickOn("#txt_PasswordSU");
        write("aaaaaaaaaaaaaaaaaaaaaaaaaa"); // Comprueba error cuando tengo más de 20 caracteres.
        verifyThat("#err_Password", isVisible());
        eraseText(26);
        
    }
    
    @Test
    public void testE_VerifyPassword(){
        
        clickOn("#txt_PasswordSU");
        write("abcd*1234"); // Comprueba error cuando tengo más de 20 caracteres.
        
        clickOn("#txt_VerifyPass");
        write("abcd*1111"); // Comprueba error cuando tengo más de 20 caracteres.
        verifyThat("#err_VerifyPass", isVisible());
        
    }
    
    @Test
    public void testF_UserExistErrorVerify(){
        
        clickOn("#txt_Username");
        write("garikoitz"); 
        
        clickOn("#txt_PasswordSU");
        write("abcd*1234");
        
        clickOn("#txt_Email");
        write("gari@gmail.com");     

        clickOn("#txt_Fullname");
        write("Garikoitz Salgado");       
             
        clickOn("#txt_VerifyPass");
        write("abcd*1234");
        
        clickOn("#btn_SignUp");
        verifyThat("User already exist.", isVisible());
        clickOn("Aceptar");
        
    }
    @Test
    public void testG_EmailExistErrorVerify() {

        clickOn("#txt_Username");
        write("username");
        
        clickOn("#txt_PasswordSU");
        write("abcd*1234");
        
        clickOn("#txt_Email");
        write("gari@gmail.com");

        clickOn("#txt_Fullname");
        write("Garikoitz Salgado");
               
        clickOn("#txt_VerifyPass");
        write("abcd*1234");

        clickOn("#btn_SignUp");
        verifyThat("Email already exist.", isVisible());
        clickOn("Aceptar");

    }

    @Test
    public void testH_UserCreatedVerify(){
        
        clickOn("#txt_Username");
        write("signUpTest"); 
        
        clickOn("#txt_Email");
        write("signUpTest@gmail.com");     

        clickOn("#txt_Fullname");
        write("SignUp Test");       
        
        clickOn("#txt_PasswordSU");
        write("abcd*1234");
        
        clickOn("#txt_VerifyPass");
        write("abcd*1234");
        
        clickOn("#btn_SignUp");
        verifyThat("User created successfully", isVisible());
        clickOn("Aceptar");
        
    }
}
