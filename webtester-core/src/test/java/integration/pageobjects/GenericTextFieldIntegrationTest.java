package integration.pageobjects;

import info.novatec.testit.webtester.api.annotations.IdentifyUsing;
import info.novatec.testit.webtester.api.browser.Browser;
import info.novatec.testit.webtester.pageobjects.GenericTextField;
import info.novatec.testit.webtester.pageobjects.PageObject;
import integration.AbstractWebTesterIntegrationTest;
import org.apache.commons.lang.StringUtils;
import org.junit.Before;
import org.junit.Test;

import javax.annotation.PostConstruct;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * These tests check aspects of the {@link GenericTextField generic text field} page object
 * which can only be verified by using them on a live web-site with a real
 * {@link Browser browser}. Some of them might also check if we are using
 * Selenium features correctly.
 */
public class GenericTextFieldIntegrationTest extends AbstractWebTesterIntegrationTest  {

    GenericTextFieldTestPage page;

    @Before
    public void initPage() {
        page = getBrowser().create(GenericTextFieldTestPage.class);
    }

    @Override
    protected String getHTMLFilePath() {
        return "html/pageobjects/genericTextField.html";
    }

/* getting text */

    /**
     * This test - in comparison to similar unit tests - checks if we are using
     * the correct attribute to retrieve text from a generic text field.
     */
    @Test
    public final void testThatTheUsedAttributeForTextRetrievalIsCorrect() {
        assertThat(page.withValue.getText(), is("value"));
    }

    /**
     * This test checks that we can retrieve the text of generic text fields even if
     * they are invisible to the user.
     */
    @Test
    public final void testThatTextFromInvisibleFieldsCanBeRetrieved() {
        assertThat(page.invisible.getText(), is("invisible"));
    }

    /**
     * This test checks that we can retrieve the text of generic text fields even if
     * they are disabled for the user.
     */
    @Test
    public final void testThatTextFromDisabledFieldsCanBeRetrieved() {
        assertThat(page.disabled.getText(), is("disabled"));
    }

/* clearing text */

    /**
     * This test - in comparison to similar unit tests - checks if we are really
     * using the correct method for clearing generic text fields.
     */
    @Test
    public final void testThatTextCanBeCleared() {
        GenericTextField element = page.withValue.clearText();
        assertThat(element.getText(), is(StringUtils.EMPTY));
    }

/* setting text */

    /**
     * This test - in comparison to similar unit tests - checks if we are really
     * using the correct methods in order to change the text of generic text fields.
     */
    @Test
    public final void testThatTextCanBeSet() {
        GenericTextField element = page.empty.setText("no longer empty");
        assertThat(element.getText(), is("no longer empty"));
    }

    /**
     * This test checks that we can set the text of a size limited generic text field
     * and only the appropriate amount of characters is used without an
     * exception.
     */
    @Test
    public final void testThatTextFieldLimitsAreNoProblemWhenSettingText() {
        GenericTextField element = page.maxLength.setText("1234");
        assertThat(element.getText(), is("12"));
    }

/* appending text */

    /**
     * This test - in comparison to similar unit tests - checks if we are really
     * using the correct methods in order to append the text of generic text fields.
     */
    @Test
    public final void testThatTextCanBeAppended() {
        GenericTextField element = page.withValue.appendText(" with appended text");
        assertThat(element.getText(), is("value with appended text"));
    }

    /**
     * This test checks that we can append the text of a size limited generic text field
     * and only the appropriate amount of characters is used without an
     * exception.
     */
    @Test
    public final void testThatTextFieldLimitsAreNoProblemWhenAppendingText() {
        GenericTextField element = page.withValueAndMaxLength.appendText("3456");
        assertThat(element.getText(), is("123"));
    }

/* types */

    /**
     * This test verifies that every officially supported kind of generic text field is
     * recognized.
     */
    @Test
    public final void testThatAllButtonTypesAreSupported() {

        assertThat(page.inputNone.isVisible(), is(true));
        assertThat(page.inputEmpty.isVisible(), is(true));
        assertThat(page.inputText.isVisible(), is(true));

        // not strictly generic text fields
        assertThat(page.inputPassword.isVisible(), is(true));
        assertThat(page.inputNumber.isVisible(), is(true));

    }

/* utilities */

    public static class GenericTextFieldTestPage extends PageObject {

        @IdentifyUsing("maxLength")
        GenericTextField maxLength;

        @IdentifyUsing("empty")
        GenericTextField empty;
        @IdentifyUsing("withValue")
        GenericTextField withValue;
        @IdentifyUsing("withValueAndMaxLength")
        GenericTextField withValueAndMaxLength;

        @IdentifyUsing("invisible")
        GenericTextField invisible;
        @IdentifyUsing("disabled")
        GenericTextField disabled;

        @IdentifyUsing("input_none")
        GenericTextField inputNone;
        @IdentifyUsing("input_empty")
        GenericTextField inputEmpty;
        @IdentifyUsing("input_text")
        GenericTextField inputText;
        @IdentifyUsing("input_password")
        GenericTextField inputPassword;
        @IdentifyUsing("input_number")
        GenericTextField inputNumber;

        @PostConstruct
        void checkStartingConditions() {

            assertThat(empty.isVisible(), is(true));
            assertThat(withValue.isVisible(), is(true));
            assertThat(withValueAndMaxLength.isVisible(), is(true));
            assertThat(maxLength.isVisible(), is(true));
            assertThat(invisible.isVisible(), is(false));
            assertThat(disabled.isVisible(), is(true));

            assertThat(empty.getText(), is(StringUtils.EMPTY));
            assertThat(withValue.getText(), is("value"));
            assertThat(withValueAndMaxLength.getText(), is("12"));
            assertThat(maxLength.getText(), is(StringUtils.EMPTY));
            assertThat(invisible.getText(), is("invisible"));
            assertThat(disabled.getText(), is("disabled"));

        }

    }


}
