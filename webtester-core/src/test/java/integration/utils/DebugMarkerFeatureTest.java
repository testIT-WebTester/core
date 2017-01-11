package integration.utils;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.validator.ValidateWith;
import org.openqa.selenium.UnhandledAlertException;

import integration.AbstractWebTesterIntegrationTest;

import info.novatec.testit.webtester.api.annotations.IdentifyUsing;
import info.novatec.testit.webtester.pageobjects.Button;
import info.novatec.testit.webtester.pageobjects.PageObject;
import info.novatec.testit.webtester.pageobjects.PasswordField;
import info.novatec.testit.webtester.pageobjects.TextField;

public class DebugMarkerFeatureTest extends AbstractWebTesterIntegrationTest {

    DebugMarkerFeatureTestPage page;

    boolean markingsWere;

    @Before
    public void initPage() {
        page = getBrowser().create(integration.utils.DebugMarkerFeatureTest.DebugMarkerFeatureTestPage.class);
        markingsWere = getBrowser().getConfiguration().markingsAreActivated();
        getBrowser().getConfiguration().setMarkingsActivated(true);
    }

    @Override
    protected String getHTMLFilePath() {
        return "html/utils/debugMarkerFeature.html";
    }

    @After
    public void deactivateMarkings() {
        getBrowser().getConfiguration().setMarkingsActivated(markingsWere);
    }

    @Test(expected = UnhandledAlertException.class)
    public void demonstrateDebugMarking() {
        String username = page.usernameCredential.getText();
        String password = page.passwordCredential.getText();

        page.username.setText(username);
        page.password.setText(password);
        page.login.click();
        getBrowser().acceptAlertIfVisible();
    }


    public static class DebugMarkerFeatureTestPage extends PageObject {

        @IdentifyUsing("usernameCredential")
        TextField usernameCredential;

        @IdentifyUsing("passwordCredential")
        PasswordField passwordCredential;

        @IdentifyUsing("username")
        TextField username;

        @IdentifyUsing("password")
        PasswordField password;

        @IdentifyUsing("login")
        Button login;

    }
}
