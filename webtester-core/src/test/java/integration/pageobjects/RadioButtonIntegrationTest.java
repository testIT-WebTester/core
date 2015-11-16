package integration.pageobjects;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import javax.annotation.PostConstruct;

import org.junit.Before;
import org.junit.Test;

import integration.AbstractWebTesterIntegrationTest;

import info.novatec.testit.webtester.api.annotations.IdentifyUsing;
import info.novatec.testit.webtester.api.browser.Browser;
import info.novatec.testit.webtester.pageobjects.PageObject;
import info.novatec.testit.webtester.pageobjects.RadioButton;


/**
 * These tests check aspects of the {@link RadioButton radio button} page object
 * which can only be verified by using them on a live web-site with a real
 * {@link Browser browser}. Some of them might also check if we are using
 * Selenium features correctly.
 */
public class RadioButtonIntegrationTest extends AbstractWebTesterIntegrationTest {

    RadioButtonTestPage page;

    @Before
    public void initPage() {
        page = getBrowser().create(RadioButtonTestPage.class);
    }

    @Override
    protected String getHTMLFilePath() {
        return "html/pageobjects/radioButton.html";
    }

    /* selection state */

    /**
     * This test - in comparison to similar unit tests - checks if we are using
     * the correct method to retrieve the selection state from a radio button.
     */
    @Test
    public final void testThatWeAreUsingTheCorrectMethodToRetrieveSelectionState() {
        assertThat(page.selected.isSelected(), is(true));
        assertThat(page.notSelected.isSelected(), is(false));
    }

    /**
     * This test checks that we can retrieve the selection state of a radio
     * button even if they are invisible to the user.
     */
    @Test
    public final void testThatSelectionStateFromInvisibleRadioButtonsCanBeRetrieved() {
        assertThat(page.invisible.isSelected(), is(true));
    }

    /**
     * This test checks that we can retrieve the selection state of a radio
     * button even if they are disabled for the user.
     */
    @Test
    public final void testThatSelectionStateFromDisabledRadioButtonsCanBeRetrieved() {
        assertThat(page.disabled.isSelected(), is(true));
    }

    /**
     * This test checks that we can change the selection state of a radio
     * button.
     */
    @Test
    public final void testThatSelectionStateCanBeChanged() {
        RadioButton radioButton = page.notSelected.select();
        assertThat(radioButton.isSelected(), is(true));
    }

    /* utilities */

    public static class RadioButtonTestPage extends PageObject {

        @IdentifyUsing("selected")
        RadioButton selected;
        @IdentifyUsing("notSelected")
        RadioButton notSelected;

        @IdentifyUsing("invisible")
        RadioButton invisible;
        @IdentifyUsing("disabled")
        RadioButton disabled;

        @PostConstruct
        void checkStartingConditions() {

            assertThat(selected.isVisible(), is(true));
            assertThat(notSelected.isVisible(), is(true));
            assertThat(invisible.isVisible(), is(false));
            assertThat(disabled.isVisible(), is(true));
            assertThat(disabled.isEnabled(), is(false));

        }

    }

}
