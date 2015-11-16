package info.novatec.testit.webtester.junit.runner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.mock;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;

import info.novatec.testit.webtester.api.browser.Browser;
import info.novatec.testit.webtester.browser.WebDriverBrowser;
import info.novatec.testit.webtester.junit.annotations.ConfigurationValue;
import info.novatec.testit.webtester.junit.annotations.Primary;


@RunWith(WebTesterJUnitRunner.class)
public class WebTesterJUnitRunnerConfigurationValuesTest {

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

    @Before
    public void before() {
        assertThat(customInteger, is(42));
        assertThat(customString, is("foo bar"));
    }

    @Test
    public void test1() {
        assertThat(customInteger, is(42));
        assertThat(customString, is("foo bar"));
    }

}
