package integration.pageobjects;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import org.junit.Before;
import org.junit.Test;

import integration.AbstractWebTesterIntegrationTest;

import info.novatec.testit.webtester.api.annotations.IdentifyUsing;
import info.novatec.testit.webtester.api.browser.Browser;
import info.novatec.testit.webtester.pageobjects.Link;
import info.novatec.testit.webtester.pageobjects.PageObject;


/**
 * These tests check aspects of the {@link Link link} page object which can only
 * be verified by using them on a live web-site with a real {@link Browser
 * browser}. Some of them might also check if we are using Selenium features
 * correctly.
 */
public class LinkIntegrationTest extends AbstractWebTesterIntegrationTest {

    LinkTestPage page;

    @Before
    public void initPage() {
        page = getBrowser().create(LinkTestPage.class);
    }

    @Override
    protected String getHTMLFilePath() {
        return "html/pageobjects/link.html";
    }

    /* click */

    /**
     * This test verifies the basic functionality of clicking a link. This might
     * seam a bit redundant since clicking a page object is implemented in the
     * base {@link PageObject} class but since it is the primary function of a
     * button to be clicked it should be verified non the less.
     */
    @Test
    public final void testThatLinksCanBeClicked() {
        page.link.click();
        assertThat(getBrowser().getPageTitle(), is("Target Page"));
    }

    /* utilities */

    public static class LinkTestPage extends PageObject {

        @IdentifyUsing("link")
        Link link;

    }

}
