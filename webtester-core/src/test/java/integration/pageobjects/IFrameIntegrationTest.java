package integration.pageobjects;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import org.junit.Before;
import org.junit.Test;

import integration.AbstractWebTesterIntegrationTest;

import info.novatec.testit.webtester.api.annotations.IdentifyUsing;
import info.novatec.testit.webtester.api.browser.Browser;
import info.novatec.testit.webtester.pageobjects.IFrame;
import info.novatec.testit.webtester.pageobjects.PageObject;


/**
 * These tests check aspects of the {@link IFrame iframe} page object which can
 * only be verified by using them on a live web-site with a real {@link Browser
 * browser}. Some of them might also check if we are using Selenium features
 * correctly.
 */
public class IFrameIntegrationTest extends AbstractWebTesterIntegrationTest {

    IFrameTestPage page;

    @Before
    public void initPage() {
        page = getBrowser().create(IFrameTestPage.class);
    }

    @Override
    protected String getHTMLFilePath() {
        return "html/pageobjects/iframe.html";
    }

    /* source path */

    /**
     * This test - in comparison to similar unit tests - checks if we are using
     * the correct attribute to retrieve the source path of the iframe.
     */
    @Test
    public final void testThatSourcePathCanBeRead() {
        String expectedSourcePath = getFormattedTestResourcePath("html/pageobjects/_frameContent.html");
        assertThat(page.iframe.getSourcePath(), is(expectedSourcePath));
    }

    /* utilities */

    public static class IFrameTestPage extends PageObject {

        @IdentifyUsing("iframe")
        IFrame iframe;

    }

}
