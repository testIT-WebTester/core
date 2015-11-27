package integration.pageobjects;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import info.novatec.testit.webtester.api.annotations.IdentifyUsing;
import info.novatec.testit.webtester.pageobjects.PageObject;
import info.novatec.testit.webtester.pageobjects.html5.UrlField;
import integration.AbstractWebTesterIntegrationTest;

import javax.annotation.PostConstruct;

import org.junit.Before;
import org.junit.Test;


public class UrlFieldIntegrationTest extends AbstractWebTesterIntegrationTest {
    UrlFieldTestPage page;

    @Before
    public void initPage() {
        page = getBrowser().create(UrlFieldTestPage.class);
    }

    @Override
    protected String getHTMLFilePath() {
        return "html/pageobjects/html5/urlfield.html";
    }

    /* getting Url */

    /**
     * This test - in comparison to similar unit tests - checks if we are using
     * the correct attribute to retrieve String from a url field.
     */
    @Test
    public final void testThatTheUsedAttributeForUrlRetrievalIsCorrect() {
        assertThat(page.withValue.getUrl(), is("http://www.google.de"));
    }

    /**
     * This test checks that we can retrieve the string even if they are
     * invisible to the user.
     */
    @Test
    public final void testThatUrlFromInvisibleFieldsCanBeRetrieved() {
        assertThat(page.invisible.getUrl(), is("http://www.invisible.de"));
    }

    /**
     * This test checks that we can retrieve the string even if they are
     * disabled for the user.
     */
    @Test
    public final void testThatUrlFromDisabledFieldsCanBeRetrieved() {
        assertThat(page.disabled.getUrl(), is("http://www.disabled.de"));
    }

    /* clearing Url */

    /**
     * This test - in comparison to similar unit tests - checks if we are really
     * using the correct method for clearing color fields.
     */
    @Test
    public final void testThatUrlCanBeCleared() {
        UrlField element = page.clearUrl.reset();
        assertThat(element.getUrl(), is(""));
    }

    /* setting Url */

    /**
     * This test - in comparison to similar unit tests - checks if we are really
     * using the correct methods in order to change the string of url fields.
     */
    @Test
    public final void testThatUrlCanBeSet() {
        UrlField element = page.changeUrl.setUrl("https://www.google.de");
        assertThat(element.getUrl(), is("https://www.google.de"));
    }

    /* types */

    /**
     * This test verifies that every officially supported kind of url field is
     * recognized.
     */
    @Test
    public final void testThatAllButtonTypesAreSupported() {
        assertThat(page.inputUrl.isVisible(), is(true));
    }

    /* utilities */

    public static class UrlFieldTestPage extends PageObject {
        @IdentifyUsing("empty")
        UrlField empty;
        @IdentifyUsing("withValue")
        UrlField withValue;
        @IdentifyUsing("changeUrl")
        UrlField changeUrl;
        @IdentifyUsing("clearUrl")
        UrlField clearUrl;
        @IdentifyUsing("inputUrl")
        UrlField inputUrl;

        @IdentifyUsing("invisible")
        UrlField invisible;
        @IdentifyUsing("disabled")
        UrlField disabled;

        @PostConstruct
        void checkStartingConditions() {

            assertThat(empty.isVisible(), is(true));
            assertThat(withValue.isVisible(), is(true));
            assertThat(changeUrl.isVisible(), is(true));
            assertThat(invisible.isVisible(), is(false));
            assertThat(disabled.isVisible(), is(true));
        }

    }
}
