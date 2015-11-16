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
import info.novatec.testit.webtester.pageobjects.NumberField;
import info.novatec.testit.webtester.pageobjects.PageObject;


/**
 * These tests check aspects of the {@link NumberField number field} page object
 * which can only be verified by using them on a live web-site with a real
 * {@link Browser browser}. Some of them might also check if we are using
 * Selenium features correctly.
 */
public class NumberFieldIntegrationTest extends AbstractWebTesterIntegrationTest {

    NumberFieldTestPage page;

    @Before
    public void initPage() {
        page = getBrowser().create(NumberFieldTestPage.class);
    }

    @Override
    protected String getHTMLFilePath() {
        return "html/pageobjects/numberField.html";
    }

    /* get value */

    /**
     * This test verifies that numeric values can be retrieved.
     */
    @Test
    public final void testThatGettingValueReturnsCorrectNumber() {
        assertThat(page.withValue.getValue(), is(42L));
    }

    /* set value */

    /**
     * This test verifies that numeric values can be set.
     */
    @Test
    public final void testThatSettingValueWorks() {
        NumberField element = page.empty.setValue(42L);
        assertThat(element.getValue(), is(42L));
    }

    /* These tests check that the implementation of the text field parent class
     * works for number fields as well. This is done because a number fields
     * only accept numbers and therefore might behave differently. */

    @Test
    public final void testThatGettingOfTextWorksWithParentImplementation() {
        assertThat(page.withValue.getText(), is("42"));
    }

    @Test
    public final void testThatClearingOfTextWorksWithParentImplementation() {
        NumberField element = page.withValue.clearText();
        assertThat(element.getText(), is(StringUtils.EMPTY));
    }

    @Test
    public final void testThatSettingOfTextWorksWithParentImplementation() {
        NumberField element = page.empty.setText("21");
        assertThat(element.getText(), is("21"));
    }

    @Test
    public final void testThatAppendingOfTextWorksWithParentImplementation() {
        NumberField element = page.withValue.appendText("84");
        assertThat(element.getText(), is("4284"));
    }

    /* utilities */

    public static class NumberFieldTestPage extends PageObject {

        @IdentifyUsing("empty")
        NumberField empty;
        @IdentifyUsing("withValue")
        NumberField withValue;

        @PostConstruct
        void checkStartingConditions() {

            assertThat(empty.isVisible(), is(true));
            assertThat(withValue.isVisible(), is(true));

            assertThat(empty.getText(), is(StringUtils.EMPTY));
            assertThat(withValue.getText(), is("42"));

        }

    }

}
