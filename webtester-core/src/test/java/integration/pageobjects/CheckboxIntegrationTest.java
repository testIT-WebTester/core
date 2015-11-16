package integration.pageobjects;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import javax.annotation.PostConstruct;

import org.junit.Before;
import org.junit.Test;

import integration.AbstractWebTesterIntegrationTest;

import info.novatec.testit.webtester.api.annotations.IdentifyUsing;
import info.novatec.testit.webtester.api.browser.Browser;
import info.novatec.testit.webtester.pageobjects.Checkbox;
import info.novatec.testit.webtester.pageobjects.PageObject;


/**
 * These tests check aspects of the {@link Checkbox checkbox} page object which
 * can only be verified by using them on a live web-site with a real
 * {@link Browser browser}. Some of them might also check if we are using
 * Selenium features correctly.
 */
public class CheckboxIntegrationTest extends AbstractWebTesterIntegrationTest {

    CheckboxTestPage page;

    @Before
    public void initPage() {
        page = getBrowser().create(CheckboxTestPage.class);
    }

    @Override
    protected String getHTMLFilePath() {
        return "html/pageobjects/checkbox.html";
    }

    /* selection state */

    /**
     * This test - in comparison to similar unit tests - checks if we are using
     * the correct method to retrieve the selection state from a checkbox.
     */
    @Test
    public final void testThatWeAreUsingTheCorrectMethodToRetrieveSelectionState() {
        assertThat(page.selected.isSelected(), is(true));
        assertThat(page.notSelected.isSelected(), is(false));
    }

    /**
     * This test checks that we can retrieve the selection state of a checkbox
     * even if they are invisible to the user.
     */
    @Test
    public final void testThatSelectionStateFromInvisibleCheckboxesCanBeRetrieved() {
        assertThat(page.invisible.isSelected(), is(true));
    }

    /**
     * This test checks that we can retrieve the selection state of a checkbox
     * even if they are disabled for the user.
     */
    @Test
    public final void testThatSelectionStateFromDisabledCheckboxesCanBeRetrieved() {
        assertThat(page.disabled.isSelected(), is(true));
    }

    /* setting selection state */

    /**
     * This test checks that we can change the selection state of a checkbox
     * from not selected to selected.
     */
    @Test
    public final void testThatSelectionStateCanBeChanged_NotSelectedTotSelected() {
        Checkbox checkbox = page.notSelected.setSelection(true);
        assertThat(checkbox.isSelected(), is(true));
    }

    /**
     * This test checks that we can change the selection state of a checkbox
     * from selected to not selected.
     */
    @Test
    public final void testThatSelectionStateCanBeChanged_SelectedToNotSelected() {
        Checkbox checkbox = page.selected.setSelection(false);
        assertThat(checkbox.isSelected(), is(false));
    }

    /* utilities */

    public static class CheckboxTestPage extends PageObject {

        @IdentifyUsing("selected")
        Checkbox selected;
        @IdentifyUsing("notSelected")
        Checkbox notSelected;

        @IdentifyUsing("invisible")
        Checkbox invisible;
        @IdentifyUsing("disabled")
        Checkbox disabled;

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
