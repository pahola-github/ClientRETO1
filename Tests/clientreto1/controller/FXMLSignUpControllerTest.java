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
 * @author gsalg
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class FXMLSignUpControllerTest extends ApplicationTest {

    @Override
    public void start(Stage stage) throws Exception {

        new FXApplication().start(stage);
        clickOn("#link_SignUp");
    }

    @Test
    public void test1_Initialization() {

        verifyThat("#btn_SignUp", isDisabled());
        verifyThat("#btn_Back", isEnabled());
        verifyThat("#txt_Username", isEnabled());
        verifyThat("#txt_Email", isEnabled());
        verifyThat("#txt_Fullname", isEnabled());
        verifyThat("#txt_Password", isEnabled());
        verifyThat("#txt_VerifyPass", isEnabled());
        verifyThat("#txt_Username", isFocused());

    }

    @Test
    public void test2_SignUpButtonIsDisabled() {

        clickOn("#txt_Username");
        write("username");
        clickOn("#txt_Email");
        write("username@email.com");
        clickOn("#txt_Password");
        write("username");
        clickOn("#txt_VerifyPass");
        write("username");
        verifyThat("#btn_SignUp", isDisabled());

    }

    @Test
    public void test3_TextfieldsAreInformed() {

        clickOn("#txt_Fullname");
        write("User Name");
        verifyThat("#btn_SignUp", isEnabled());

    }

}
