package info.novatec.testit.webtester.testng.listener;

import info.novatec.testit.webtester.api.browser.Browser;
import info.novatec.testit.webtester.browser.WebDriverBrowser;
import info.novatec.testit.webtester.testng.annotations.ConfigurationValue;
import info.novatec.testit.webtester.testng.annotations.Primary;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import javax.annotation.Resource;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.mock;

@Listeners(WebTesterTestNGListener.class)
public class WebTesterTestNGListenerConfigurationValueTest {

    private static WebDriver webDriver = mock(WebDriver.class);

    @Primary
    @Resource
    private static Browser staticBrowser = WebDriverBrowser.buildForWebDriver(webDriver);
    @Resource
    private Browser browser = WebDriverBrowser.buildForWebDriver(webDriver);

    @ConfigurationValue("custom.integer")
    private static Integer customInteger;
    @ConfigurationValue("custom.string")
    private String customString;

    @BeforeClass
    public static void beforeClass() {
        assertThat(customInteger, is(42));
    }

    @BeforeMethod
    public void beforeMethod() {
        assertThat(customInteger, is(42));
        assertThat(customString, is("foo bar"));
    }

    @Test
    public void test1() {
        assertThat(customInteger, is(42));
        assertThat(customString, is("foo bar"));
    }

}
