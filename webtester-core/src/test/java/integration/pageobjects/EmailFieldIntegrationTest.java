package integration.pageobjects;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import info.novatec.testit.webtester.api.annotations.IdentifyUsing;
import info.novatec.testit.webtester.api.browser.Browser;
import info.novatec.testit.webtester.pageobjects.PageObject;
import info.novatec.testit.webtester.pageobjects.html5.EmailField;
import integration.AbstractWebTesterIntegrationTest;

import javax.annotation.PostConstruct;

import org.junit.Before;
import org.junit.Test;


/**
 * These tests check aspects of the {@link EmailField email field} page object
 * which can only be verified by using them on a live web-site with a real
 * {@link Browser browser}. Some of them might also check if we are using
 * Selenium features correctly.
 */
public class EmailFieldIntegrationTest extends AbstractWebTesterIntegrationTest {
    EmailFieldTestPage page;

    @Before
    public void initPage() {
        page = getBrowser().create(EmailFieldTestPage.class);
    }

    @Override
    protected String getHTMLFilePath() {
        return "html/pageobjects/html5/emailfield.html";
    }

    /* getting Email */

    /**
     * This test - in comparison to similar unit tests - checks if we are using
     * the correct attribute to retrieve color from a color field.
     */
    @Test
    public final void testThatTheUsedAttributeForEmailRetrievalIsCorrect() {
        assertThat(page.withValue.getEmail(), is("valuetest@testIt.de"));
    }

    /**
     * This test checks that we can retrieve the email of email fields even if
     * they are invisible to the user.
     */
    @Test
    public final void testThatEmailFromInvisibleFieldsCanBeRetrieved() {
        assertThat(page.invisible.getEmail(), is("invisible@testIt.de"));
    }

    /**
     * This test checks that we can retrieve the email of email fields even if
     * they are disabled for the user.
     */
    @Test
    public final void testThatEmailFromDisabledFieldsCanBeRetrieved() {
        assertThat(page.disabled.getEmail(), is("disabled@testIt.de"));
    }

    /* clearing Email */

    /**
     * This test - in comparison to similar unit tests - checks if we are really
     * using the correct method for clearing color fields.
     */
    @Test
    public final void testThatEmailCanBeCleared() {
        EmailField element = page.clearEmail.clear();
        assertThat(element.getEmail(), is(""));
    }

    /* setting Email */

    /**
     * This test - in comparison to similar unit tests - checks if we are really
     * using the correct methods in order to set the email address of an email
     * fields.
     */
    @Test
    public final void testThatEmailCanBeSet() {
        EmailField element = page.changeEmail.setEmailAdress("newTest@testIt.de");
        assertThat(element.getEmail(), is("newTest@testIt.de"));
    }

    /* types */

    /**
     * This test verifies that every officially supported kind of email field is
     * recognized.
     */
    @Test
    public final void testThatAllButtonTypesAreSupported() {
        assertThat(page.inputEmail.isVisible(), is(true));
    }

    /* utilities */

    public static class EmailFieldTestPage extends PageObject {
        @IdentifyUsing("empty")
        EmailField empty;
        @IdentifyUsing("withValue")
        EmailField withValue;

        @IdentifyUsing("changeEmail")
        EmailField changeEmail;

        @IdentifyUsing("invisible")
        EmailField invisible;
        @IdentifyUsing("disabled")
        EmailField disabled;

        @IdentifyUsing("clearEmail")
        EmailField clearEmail;

        @IdentifyUsing("inputEmail")
        EmailField inputEmail;

        @PostConstruct
        void checkStartingConditions() {

            assertThat(empty.isVisible(), is(true));
            assertThat(withValue.isVisible(), is(true));
            assertThat(changeEmail.isVisible(), is(true));
            assertThat(invisible.isVisible(), is(false));
            assertThat(disabled.isVisible(), is(true));

            assertThat(clearEmail.getEmail(), is("clearMe@testIt.de"));
            assertThat(inputEmail.getEmail(), is("emailTest@testIt.de"));

        }
    }
}
