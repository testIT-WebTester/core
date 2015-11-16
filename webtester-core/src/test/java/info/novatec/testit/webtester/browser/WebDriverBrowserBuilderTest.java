package info.novatec.testit.webtester.browser;

import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.sameInstance;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.openqa.selenium.WebDriver;

import info.novatec.testit.webtester.api.browser.Browser;
import info.novatec.testit.webtester.api.config.Configuration;
import info.novatec.testit.webtester.api.pageobjects.PageObjectFactory;
import info.novatec.testit.webtester.config.BaseConfiguration;
import info.novatec.testit.webtester.internal.pageobjects.DefaultPageObjectFactory;


@RunWith(MockitoJUnitRunner.class)
public class WebDriverBrowserBuilderTest {

    @Mock
    WebDriver webDriver;

    @Mock
    PageObjectFactory factory;
    @Mock
    Configuration configuration;

    @Test
    public void testThatAllBrowserServicesAreSetWithDefaults() {
        Browser browser = new WebDriverBrowserBuilder(webDriver).build();
        assertThatDefaultConfigurationWasSet(browser);
        assertThatDefaultPageObjectFactoryWasSet(browser);
    }

    @Test
    public void testThatConfigurationToBeUsedCanBeSet() {
        Browser browser = new WebDriverBrowserBuilder(webDriver).withConfiguration(configuration).build();
        assertThat(browser.getConfiguration(), is(sameInstance(configuration)));
        assertThatDefaultPageObjectFactoryWasSet(browser);
    }

    @Test
    public void testThatPageObjectFactoryToBeUsedCanBeSet() {
        Browser browser = new WebDriverBrowserBuilder(webDriver).withFactory(factory).build();
        assertThatDefaultConfigurationWasSet(browser);
        assertThat(browser.getPageObjectFactory(), is(sameInstance(factory)));
    }

    /* utilities */

    private void assertThatDefaultConfigurationWasSet(Browser browser) {
        assertThat(browser.getConfiguration(), is(instanceOf(BaseConfiguration.class)));
    }

    private void assertThatDefaultPageObjectFactoryWasSet(Browser browser) {
        assertThat(browser.getPageObjectFactory(), is(instanceOf(DefaultPageObjectFactory.class)));
    }

}
