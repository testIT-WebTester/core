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
import info.novatec.testit.webtester.pageobjects.TextArea;


/**
 * These tests check aspects of the {@link TextArea text are} page object which
 * can only be verified by using them on a live web-site with a real
 * {@link Browser browser}. Some of them might also check if we are using
 * Selenium features correctly.
 */
public class TextAreaIntegrationTest extends AbstractWebTesterIntegrationTest {

    TextAreaTestPage page;

    @Before
    public void initPage() {
        page = getBrowser().create(TextAreaTestPage.class);
    }

    @Override
    protected String getHTMLFilePath() {
        return "html/pageobjects/textArea.html";
    }

    /* getting rows */

    /**
     * This test - in comparison to similar unit tests - checks if we are using
     * the correct attribute to retrieve rows from a text area.
     */
    @Test
    public final void testThatTheUsedAttributeForRowRetrievalIsCorrect() {
        assertThat(page.twentyOneRowsFourtyTwoColumns.getRowCount(), is(21));
    }

    /**
     * This test - in comparison to similar unit tests - checks that the default
     * of -1 from our code is not actually returned because Selenium and or the
     * Browser used for testing is actually returning its own default of 20.
     */
    @Test
    public final void testThatTheDefaultRowCountOfSeleniumOrTheBrowserIsReturnedIfNoLimitsAreSet() {
        assertThat(page.noRowsNoColumns.getRowCount(), is(2));
    }

    /**
     * This test checks that we can retrieve the rows of text areas even if they
     * are invisible to the user.
     */
    @Test
    public final void testThatRowsFromInvisibleFieldsCanBeRetrieved() {
        assertThat(page.invisible.getRowCount(), is(2));
    }

    /**
     * This test checks that we can retrieve the rows of text areas even if they
     * are disabled for the user.
     */
    @Test
    public final void testThatRowsFromDisabledFieldsCanBeRetrieved() {
        assertThat(page.disabled.getRowCount(), is(2));
    }

    /* getting columns */

    /**
     * This test - in comparison to similar unit tests - checks if we are using
     * the correct attribute to retrieve columns from a text area.
     */
    @Test
    public final void testThatTheUsedAttributeForColumnRetrievalIsCorrect() {
        assertThat(page.twentyOneRowsFourtyTwoColumns.getColumnCount(), is(42));
    }

    /**
     * This test - in comparison to similar unit tests - checks that the default
     * of -1 from our code is not actually returned because Selenium and or the
     * Browser used for testing is actually returning its own default of 20.
     */
    @Test
    public final void testThatTheDefaultColumnCountOfSeleniumOrTheBrowserIsReturnedIfNoLimitsAreSet() {
        assertThat(page.noRowsNoColumns.getColumnCount(), is(20));
    }

    /**
     * This test checks that we can retrieve the columns of text areas even if
     * they are invisible to the user.
     */
    @Test
    public final void testThatColumnsFromInvisibleFieldsCanBeRetrieved() {
        assertThat(page.invisible.getColumnCount(), is(20));
    }

    /**
     * This test checks that we can retrieve the columns of text areas even if
     * they are disabled for the user.
     */
    @Test
    public final void testThatColumnsFromDisabledFieldsCanBeRetrieved() {
        assertThat(page.disabled.getColumnCount(), is(20));
    }

    /* These tests check that the implementation of the text field parent class
     * works for text areas as well. This is done because a text area is not an
     * input field and therefore might behave differently. */

    @Test
    public final void testThatGettingOfTextWorksWithParentImplementation() {
        assertThat(page.withValue.getText(), is("value"));
    }

    @Test
    public final void testThatClearingOfTextWorksWithParentImplementation() {
        TextArea element = page.withValue.clearText();
        assertThat(element.getText(), is(StringUtils.EMPTY));
    }

    @Test
    public final void testThatSettingOfTextWorksWithParentImplementation() {
        TextArea element = page.empty.setText("no longer empty");
        assertThat(element.getText(), is("no longer empty"));
    }

    @Test
    public final void testThatAppendingOfTextWorksWithParentImplementation() {
        TextArea element = page.withValue.appendText(" with appended text");
        assertThat(element.getText(), is("value with appended text"));
    }

    /* utilities */

    public static class TextAreaTestPage extends PageObject {

        @IdentifyUsing("noRowsNoColumns")
        TextArea noRowsNoColumns;
        @IdentifyUsing("twentyOneRowsFourtyTwoColumns")
        TextArea twentyOneRowsFourtyTwoColumns;

        @IdentifyUsing("empty")
        TextArea empty;
        @IdentifyUsing("withValue")
        TextArea withValue;

        @IdentifyUsing("invisible")
        TextArea invisible;
        @IdentifyUsing("disabled")
        TextArea disabled;

        @PostConstruct
        void checkStartingConditions() {

            assertThat(noRowsNoColumns.isVisible(), is(true));
            assertThat(twentyOneRowsFourtyTwoColumns.isVisible(), is(true));
            assertThat(empty.isVisible(), is(true));
            assertThat(withValue.isVisible(), is(true));
            assertThat(invisible.isVisible(), is(false));
            assertThat(disabled.isVisible(), is(true));

            assertThat(empty.getText(), is(StringUtils.EMPTY));
            assertThat(withValue.getText(), is("value"));
            assertThat(invisible.getText(), is("invisible"));
            assertThat(disabled.getText(), is("disabled"));

        }

    }

}
