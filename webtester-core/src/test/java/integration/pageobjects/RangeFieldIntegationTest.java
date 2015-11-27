package integration.pageobjects;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import info.novatec.testit.webtester.api.annotations.IdentifyUsing;
import info.novatec.testit.webtester.pageobjects.PageObject;
import info.novatec.testit.webtester.pageobjects.html5.RangeField;
import integration.AbstractWebTesterIntegrationTest;

import javax.annotation.PostConstruct;

import org.junit.Before;
import org.junit.Test;


public class RangeFieldIntegationTest extends AbstractWebTesterIntegrationTest {
    RangeFieldTestPage page;

    @Before
    public void initPage() {
        page = getBrowser().create(RangeFieldTestPage.class);
    }

    @Override
    protected String getHTMLFilePath() {
        return "html/pageobjects/html5/rangefield.html";
    }

    /* getting Range */

    /**
     * This test - in comparison to similar unit tests - checks if we are using
     * the correct attribute to retrieve range from a range field.
     */
    @Test
    public final void testThatTheUsedAttributeForRangeRetrievalIsCorrect() {
        assertThat(page.withValue.getRange(), is(5));
    }

    /**
     * This test checks that we can retrieve the range of range fields even if
     * they are invisible to the user.
     */
    @Test
    public final void testThatRangeFromInvisibleFieldsCanBeRetrieved() {
        assertThat(page.invisible.getRange(), is(7));
    }

    /**
     * This test checks that we can retrieve the range of range fields even if
     * they are disabled for the user.
     */
    @Test
    public final void testThatRangeFromDisabledFieldsCanBeRetrieved() {
        assertThat(page.disabled.getRange(), is(8));
    }

    /* getting Min value */

    /**
     * This test - in comparison to similar unit tests - checks if we are using
     * the correct attribute to retrieve range from a range field.
     */
    @Test
    public final void testThatTheUsedAttributeMinIsRetrievedCorrect() {
        assertThat(page.changeRange.getMinRange(), is(1));
    }

    /* getting Max value */

    /**
     * This test - in comparison to similar unit tests - checks if we are using
     * the correct attribute to retrieve range from a range field.
     */
    @Test
    public final void testThatTheUsedAttributeMaxIsRetrievedCorrect() {
        assertThat(page.changeRange.getMaxRange(), is(10));
    }

    /* clearing Range */

    /**
     * This test - in comparison to similar unit tests - checks if we are really
     * using the correct method for clearing range fields.
     */
    @Test
    public final void testThatRangeCanBeCleared() {
        RangeField element = page.clearRange.reset();
        assertThat(element.getRange(), is(0));
    }

    /* setting Range */

    /**
     * This test - in comparison to similar unit tests - checks if we are really
     * using the correct methods in order to change the range of range fields.
     */
    @Test
    public final void testThatRangeCanBeSet() {
        RangeField element = page.changeRange.setRange(7);
        assertThat(element.getRange(), is(7));
    }

    /* types */

    /**
     * This test verifies that every officially supported kind of range field is
     * recognized.
     */
    @Test
    public final void testThatAllButtonTypesAreSupported() {
        assertThat(page.inputRange.isVisible(), is(true));
    }

    /* utilities */

    public static class RangeFieldTestPage extends PageObject {
        @IdentifyUsing("empty")
        RangeField empty;
        @IdentifyUsing("changeRange")
        RangeField changeRange;
        @IdentifyUsing("withValue")
        RangeField withValue;
        @IdentifyUsing("clearRange")
        RangeField clearRange;
        @IdentifyUsing("inputRange")
        RangeField inputRange;

        @IdentifyUsing("invisible")
        RangeField invisible;
        @IdentifyUsing("disabled")
        RangeField disabled;

        @PostConstruct
        void checkStartingConditions() {

            assertThat(empty.isVisible(), is(true));
            assertThat(changeRange.isVisible(), is(true));
            assertThat(withValue.isVisible(), is(true));
            assertThat(invisible.isVisible(), is(false));
            assertThat(disabled.isVisible(), is(true));
        }

    }
}
