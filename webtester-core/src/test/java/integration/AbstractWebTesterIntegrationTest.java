package integration;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import java.io.File;

import info.novatec.testit.webtester.pageobjects.PageObject;
import org.junit.Before;
import org.junit.BeforeClass;

import utils.TestBrowserFactory;

import info.novatec.testit.webtester.api.browser.Browser;


public abstract class AbstractWebTesterIntegrationTest {

    protected static final String DEFAULT = "default";
    protected static final String UNKNOWN = "unknown";
    protected static final String SELECTED = "selected";
    protected static final String DESELECTED = "deselected";
    protected static final String DISABLED = "disabled";
    protected static final String INVISIBLE = "invisible";
    protected static final String WITH_VALUE = "withValue";
    protected static final String EMPTY = "empty";
    protected static final String MAX_LENGTH = "maxLength";

    private static Browser browser;

    @BeforeClass
    public static void setUpBeforeClass() {
        if (browser == null) {
            browser = new TestBrowserFactory().createBrowser();
        }
    }

    @Before
    public void navigateToTestURL() {
        String htmlFilePath = getHTMLFilePath();
        if (htmlFilePath != null) {
            browser.open(getFormattedTestResourcePath(htmlFilePath));
        }
    }

    protected void open(String filePath) {
        browser.open(getFormattedTestResourcePath(filePath));
    }

    // ABSTRACTS

    protected String getHTMLFilePath() {
        return null;
    }

    // UTILITY

    protected void assertPageObjectCanBeInitialized(PageObject object) {
        assertThat(object.isPresent(), is( true));
    }

    protected static Browser getBrowser() {
        return browser;
    }

    protected static String getFormattedTestResourcePath(String fileName) {

        File testResourceFolder = getTestResourceFolder();
        File testResourceFile = new File(testResourceFolder, fileName);
        assertThat(testResourceFile.isFile(), is(true));

        String systemDependentPath = testResourceFile.getAbsolutePath();
        String replacedBackslashes = systemDependentPath.replaceAll("\\\\", "/");
        String replacedWhitespaces = replacedBackslashes.replaceAll(" ", "%20");

        return "file:///" + replacedWhitespaces;

    }

    private static File getTestResourceFolder() {
        File srcFolder = new File("src");
        File srcTestFolder = new File(srcFolder, "test");
        return new File(srcTestFolder, "resources");
    }

}
