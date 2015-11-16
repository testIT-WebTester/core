package integration.pageobjects;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import javax.annotation.PostConstruct;

import org.apache.commons.lang.StringUtils;
import org.junit.Before;
import org.junit.Test;

import integration.AbstractWebTesterIntegrationTest;

import info.novatec.testit.webtester.api.annotations.IdentifyUsing;
import info.novatec.testit.webtester.api.browser.Browser;
import info.novatec.testit.webtester.pageobjects.PageObject;
import info.novatec.testit.webtester.pageobjects.PasswordField;


/**
 * These tests check aspects of the {@link PasswordField password field} page
 * object which can only be verified by using them on a live web-site with a
 * real {@link Browser browser}. Some of them might also check if we are using
 * Selenium features correctly.
 */
public class PasswordFieldIntegrationTest extends AbstractWebTesterIntegrationTest {

    PasswordFieldTestPage page;

    @Before
    public void initPage() {
        page = getBrowser().create(PasswordFieldTestPage.class);
    }

    @Override
    protected String getHTMLFilePath() {
        return "html/pageobjects/passwordField.html";
    }

    /* These tests check that the implementation of the text field parent class
     * works for password fields as well. This is done because a password field
     * obfuscates the input and therefore might behave differently. */

    @Test
    public final void testThatGettingOfTextWorksWithParentImplementation() {
        assertThat(page.withValue.getText(), is("value"));
    }

    @Test
    public final void testThatClearingOfTextWorksWithParentImplementation() {
        PasswordField element = page.withValue.clearText();
        assertThat(element.getText(), is(StringUtils.EMPTY));
    }

    @Test
    public final void testThatSettingOfTextWorksWithParentImplementation() {
        PasswordField element = page.empty.setText("no longer empty");
        assertThat(element.getText(), is("no longer empty"));
    }

    @Test
    public final void testThatAppendingOfTextWorksWithParentImplementation() {
        PasswordField element = page.withValue.appendText(" with appended text");
        assertThat(element.getText(), is("value with appended text"));
    }

    /* utilities */

    public static class PasswordFieldTestPage extends PageObject {

        @IdentifyUsing("empty")
        PasswordField empty;
        @IdentifyUsing("withValue")
        PasswordField withValue;

        @PostConstruct
        void checkStartingConditions() {

            assertThat(empty.isVisible(), is(true));
            assertThat(withValue.isVisible(), is(true));

            assertThat(empty.getText(), is(StringUtils.EMPTY));
            assertThat(withValue.getText(), is("value"));

        }

    }

}
