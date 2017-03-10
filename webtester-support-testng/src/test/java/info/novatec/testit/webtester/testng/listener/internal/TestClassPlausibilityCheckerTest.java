package info.novatec.testit.webtester.testng.listener.internal;

import info.novatec.testit.webtester.api.browser.Browser;
import info.novatec.testit.webtester.testng.annotations.ConfigurationValue;
import info.novatec.testit.webtester.testng.annotations.Primary;
import info.novatec.testit.webtester.testng.exceptions.*;
import org.testng.annotations.Test;

import javax.annotation.Resource;
import java.io.File;

public class TestClassPlausibilityCheckerTest {

    @Test
    public void testPlausibleClass() {
        assertTestClass(PlausibleTestClass.class);
    }

    private static class PlausibleTestClass {

        @Primary
        @Resource
        static Browser staticBrowser;

        @Resource
        private Browser browser;

        @ConfigurationValue("static.property")
        static String staticProperty;

        @ConfigurationValue("property")
        private String property;

    }

    @Test(expectedExceptions = NoStaticPrimaryBrowserException.class)
    public void testThatStaticConfigurationValuesNeedStaticPrimaryBrowser() {
        assertTestClass(NonStaticPrimaryBrowserWithStaticConfigurationPropertyTestClass.class);
    }

    private static class NonStaticPrimaryBrowserWithStaticConfigurationPropertyTestClass {

        @Resource
        static Browser staticBrowser;

        @Primary
        @Resource
        private Browser browser;

        @ConfigurationValue("static.property")
        static String staticProperty;

        @ConfigurationValue("property")
        private String property;

    }

    @Test(expectedExceptions = NoManagedBrowserException.class)
    public void testThatManagedBrowserIsNeededForConfigurationValues() {
        assertTestClass(NoBrowsersTestClass.class);
    }

    private static class NoBrowsersTestClass {

        @ConfigurationValue("property")
        String property;

    }

    @Test(expectedExceptions = NoUniquePrimaryBrowserException.class)
    public void testThatOnlyOnePrimaryBrowserCanBeDeclared() {
        assertTestClass(TwoPrimaryBrowsersTestClass.class);
    }

    private static class TwoPrimaryBrowsersTestClass {

        @Primary
        @Resource
        Browser browserOne;

        @Primary
        @Resource
        Browser browserTwo;

    }

    @Test(expectedExceptions = NoPrimaryBrowserException.class)
    public void testThatMultipleBrowsersNeedToDeclareOneAsPrimary() {
        assertTestClass(TwoBrowsersButNonePrimary.class);
    }

    private static class TwoBrowsersButNonePrimary {

        @Resource
        Browser browserOne;

        @Resource
        Browser browserTwo;

        @ConfigurationValue("property")
        String property;

    }

    @Test(expectedExceptions = NotOfInjectableFieldTypeException.class)
    public void testThatFieldsNeedToBeOfInejctableType() {
        assertTestClass(InjectableTypeTestClass.class);
    }

    private static class InjectableTypeTestClass {

        @Resource
        private Browser browser;

        @ConfigurationValue("property")
        private File property;

    }

    /* utilities */

    private void assertTestClass(Class<?> testClass) {
        new TestClassPlausibilityChecker(testClass).assertPlausibilityOfTestClass();
    }

}
