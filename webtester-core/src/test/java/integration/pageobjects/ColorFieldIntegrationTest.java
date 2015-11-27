package integration.pageobjects;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import info.novatec.testit.webtester.api.annotations.IdentifyUsing;
import info.novatec.testit.webtester.api.browser.Browser;
import info.novatec.testit.webtester.pageobjects.PageObject;
import info.novatec.testit.webtester.pageobjects.html5.ColorField;
import integration.AbstractWebTesterIntegrationTest;

import javax.annotation.PostConstruct;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.support.Color;


/**
 * These tests check aspects of the {@link ColorField color field} page object
 * which can only be verified by using them on a live web-site with a real
 * {@link Browser browser}. Some of them might also check if we are using
 * Selenium features correctly.
 */
public class ColorFieldIntegrationTest extends AbstractWebTesterIntegrationTest {

    ColorFieldTestPage page;

    @Before
    public void initPage() {
        page = getBrowser().create(ColorFieldTestPage.class);
    }

    @Override
    protected String getHTMLFilePath() {
        return "html/pageobjects/html5/colorfield.html";
    }

    /* getting Color */

    /**
     * This test - in comparison to similar unit tests - checks if we are using
     * the correct attribute to retrieve color from a color field.
     */
    @Test
    public final void testThatTheUsedAttributeForColorRetrievalIsCorrect() {
        assertThat(page.withValue.getColor(), is(Color.fromString("#efefef")));
    }

    /**
     * This test - in comparison to similar unit tests - checks if we are using
     * the correct attribute to retrieve color from a color field with the
     * alternative get method 'getColorAsString'.
     */
    @Test
    public final void testThatTheUsedAttributeForColorRetrievalIsCorrectWithGetMethodAsString() {
        assertThat(page.withValue.getColorAsString(), is("#efefef"));
    }

    /**
     * This test checks that we can retrieve the color of color fields even if
     * they are invisible to the user.
     */
    @Test
    public final void testThatColorFromInvisibleFieldsCanBeRetrieved() {
        assertThat(page.invisible.getColor(), is(Color.fromString("#00EE00")));
    }

    /**
     * This test checks that we can retrieve the color of color fields even if
     * they are disabled for the user.
     */
    @Test
    public final void testThatColorFromDisabledFieldsCanBeRetrieved() {
        assertThat(page.disabled.getColor(), is(Color.fromString("#00ff00")));
    }

    /* clearing Color */

    /**
     * This test - in comparison to similar unit tests - checks if we are really
     * using the correct method for clearing color fields.
     */
    @Test
    public final void testThatColorCanBeCleared() {
        ColorField element = page.clearColor.reset();
        assertThat(element.getColor(), is(Color.fromString("#000000")));
    }

    /* setting Color */

    /**
     * This test - in comparison to similar unit tests - checks if we are really
     * using the correct methods in order to change the color of color fields.
     */
    @Test
    public final void testThatColorCanBeSet() {
        ColorField element = page.changeColor.setColor(Color.fromString("#0000ff"));
        assertThat(element.getColor(), is(Color.fromString("#0000ff")));
    }

    /* types */

    /**
     * This test verifies that every officially supported kind of color field is
     * recognized.
     */
    @Test
    public final void testThatAllButtonTypesAreSupported() {
        assertThat(page.inputColor.isVisible(), is(true));
    }

    /* utilities */

    public static class ColorFieldTestPage extends PageObject {
        @IdentifyUsing("empty")
        ColorField empty;
        @IdentifyUsing("withValue")
        ColorField withValue;

        @IdentifyUsing("invisible")
        ColorField invisible;
        @IdentifyUsing("disabled")
        ColorField disabled;

        @IdentifyUsing("inputColor")
        ColorField inputColor;

        @IdentifyUsing("defaultColor_red")
        ColorField defaultColor_red;

        @IdentifyUsing("changeColor")
        ColorField changeColor;

        @IdentifyUsing("clearColor")
        ColorField clearColor;

        @PostConstruct
        void checkStartingConditions() {

            assertThat(empty.isVisible(), is(true));
            assertThat(withValue.isVisible(), is(true));
            assertThat(invisible.isVisible(), is(false));
            assertThat(disabled.isVisible(), is(true));

            assertThat(empty.getColorAsString(), is("#000000"));
            assertThat(defaultColor_red.getColorAsString(), is("#ff0000"));

        }

    }

}
