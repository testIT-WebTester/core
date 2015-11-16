package integration.pageobjects;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import org.junit.Before;
import org.junit.Test;

import integration.AbstractWebTesterIntegrationTest;

import info.novatec.testit.webtester.api.annotations.IdentifyUsing;
import info.novatec.testit.webtester.api.browser.Browser;
import info.novatec.testit.webtester.pageobjects.Button;
import info.novatec.testit.webtester.pageobjects.PageObject;


/**
 * These tests check aspects of the {@link Button button} page object which can
 * only be verified by using them on a live web-site with a real {@link Browser
 * browser}. Some of them might also check if we are using Selenium features
 * correctly.
 */
public class ButtonIntegrationTest extends AbstractWebTesterIntegrationTest {

    ButtonTestPage page;

    @Before
    public void initPage() {
        page = getBrowser().create(ButtonTestPage.class);
    }

    @Override
    protected String getHTMLFilePath() {
        return "html/pageobjects/button.html";
    }

    /* getting label / text */

    /**
     * This test - in comparison to similar unit tests - checks if we are using
     * the correct attribute to retrieve the label/text of a button.
     */
    @Test
    public final void testThatTheUsedAttributeForTextRetrievalIsCorrect() {
        assertThat(page.submitButton.getLabel(), is("Change Page"));
        assertThat(page.submitButton.getVisibleText(), is(""));
        assertThat(page.button.getLabel(), is("Button"));
        assertThat(page.button.getVisibleText(), is("Button"));
    }

    /* getting value */

    /**
     * This test checks if the correct value is retrieved.
     */
    @Test
    public final void testThatRetrievedValueIsCorrect() {
        assertThat(page.button.getValue(), is("normal_button"));
        assertThat(page.submitButton.getValue(), is("Change Page"));
    }

    /* click */

    /**
     * This test verifies the basic functionality of clicking a button. This
     * might seam a bit redundant since clicking a page object is implemented in
     * the base {@link PageObject} class but since it is the primary function of
     * a button to be clicked it should be verified non the less.
     */
    @Test
    public final void testThatButtonsCanBeClicked() {
        page.submitButton.click();
        assertThat(getBrowser().getPageTitle(), is("Target Page"));
    }

    /* types */

    /**
     * This test verifies that every officially supported kind of button is
     * recognized.
     */
    @Test
    public final void testThatAllButtonTypesAreSupported() {
        assertThat(page.button.isVisible(), is(true));
        assertThat(page.inputSubmit.isVisible(), is(true));
        assertThat(page.inputReset.isVisible(), is(true));
        assertThat(page.inputButton.isVisible(), is(true));
    }

    /* utilities */

    public static class ButtonTestPage extends PageObject {

        @IdentifyUsing("submitButton")
        Button submitButton;

        @IdentifyUsing("button")
        Button button;
        @IdentifyUsing("input_submit")
        Button inputSubmit;
        @IdentifyUsing("input_reset")
        Button inputReset;
        @IdentifyUsing("input_button")
        Button inputButton;

    }

}
