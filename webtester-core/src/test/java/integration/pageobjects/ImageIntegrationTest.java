package integration.pageobjects;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import org.junit.Before;
import org.junit.Test;

import integration.AbstractWebTesterIntegrationTest;

import info.novatec.testit.webtester.api.annotations.IdentifyUsing;
import info.novatec.testit.webtester.api.browser.Browser;
import info.novatec.testit.webtester.pageobjects.Image;
import info.novatec.testit.webtester.pageobjects.PageObject;


/**
 * These tests check aspects of the {@link Image image} page object which can
 * only be verified by using them on a live web-site with a real {@link Browser
 * browser}. Some of them might also check if we are using Selenium features
 * correctly.
 */
public class ImageIntegrationTest extends AbstractWebTesterIntegrationTest {

    ImageTestPage page;

    @Before
    public void initPage() {
        page = getBrowser().create(ImageTestPage.class);
    }

    @Override
    protected String getHTMLFilePath() {
        return "html/pageobjects/image.html";
    }

    /* source path */

    /**
     * This test - in comparison to similar unit tests - checks if we are using
     * the correct attribute to retrieve the image's source path for local
     * files.
     */
    @Test
    public final void testThatSourcePathCanBeRetrieved_LocalFile() {
        String expectedPath = getFormattedTestResourcePath("html/pageobjects/_image.png");
        assertThat(page.image.getSourcePath(), is(expectedPath));
    }

    /**
     * This test - in comparison to similar unit tests - checks if we are using
     * the correct attribute to retrieve the image's source path for remote
     * files.
     */
    @Test
    public final void testThatSourcePathCanBeRetrieved_RemoteFile() {
        String expectedPath = "http://www.novatec-gmbh.de/fileadmin/styles/novatec_v5.5/images/header-logo.jpg";
        assertThat(page.externalImage.getSourcePath(), is(expectedPath));
    }

    /* file name */

    /**
     * This test - in comparison to similar unit tests - checks if we are using
     * the correct attribute to retrieve the image's file name for local files.
     */
    @Test
    public final void testThatFileNameCanBeRetrieved_LocalFile() {
        assertThat(page.image.getFileName(), is("_image.png"));
    }

    /**
     * This test - in comparison to similar unit tests - checks if we are using
     * the correct attribute to retrieve the image's file name for remote files.
     */
    @Test
    public final void testThatFileNameCanBeRetrieved_RemoteFile() {
        assertThat(page.externalImage.getFileName(), is("header-logo.jpg"));
    }

    /* utilities */

    public static class ImageTestPage extends PageObject {

        @IdentifyUsing("image")
        Image image;

        @IdentifyUsing("externalImage")
        Image externalImage;

    }

}
