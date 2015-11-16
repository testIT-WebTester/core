package integration.pageobjects;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import org.junit.Before;
import org.junit.Test;

import integration.AbstractWebTesterIntegrationTest;

import info.novatec.testit.webtester.api.annotations.IdentifyUsing;
import info.novatec.testit.webtester.api.browser.Browser;
import info.novatec.testit.webtester.pageobjects.Headline;
import info.novatec.testit.webtester.pageobjects.PageObject;


/**
 * These tests check aspects of the {@link Headline headline} page object which
 * can only be verified by using them on a live web-site with a real
 * {@link Browser browser}. Some of them might also check if we are using
 * Selenium features correctly.
 */
public class HeadlineIntegrationTest extends AbstractWebTesterIntegrationTest {

    HeadlineTestPage page;

    @Before
    public void initPage() {
        page = getBrowser().create(HeadlineTestPage.class);
    }

    @Override
    protected String getHTMLFilePath() {
        return "html/pageobjects/headline.html";
    }

    /* getting visible text */

    /**
     * This test checks that we are in fact using the correct mechanisms to get
     * to the visible text of a headline element.
     */
    @Test
    public final void testThatTheVisibleTextOfHeadlinesCanBeRetrieved() {
        assertThat(page.headline1.getVisibleText(), is("Headline 1"));
    }

    /**
     * This test checks that we support all of the H1-H6 headline tags.
     */
    @Test
    public final void testThatAllHeadlineTypesAreSupported() {
        assertThat(page.headline1.isVisible(), is(true));
        assertThat(page.headline2.isVisible(), is(true));
        assertThat(page.headline3.isVisible(), is(true));
        assertThat(page.headline4.isVisible(), is(true));
        assertThat(page.headline5.isVisible(), is(true));
        assertThat(page.headline6.isVisible(), is(true));
    }

    /* utilities */

    public static class HeadlineTestPage extends PageObject {

        @IdentifyUsing("headline1")
        Headline headline1;
        @IdentifyUsing("headline2")
        Headline headline2;
        @IdentifyUsing("headline3")
        Headline headline3;
        @IdentifyUsing("headline4")
        Headline headline4;
        @IdentifyUsing("headline5")
        Headline headline5;
        @IdentifyUsing("headline6")
        Headline headline6;

    }

}
