package info.novatec.testit.webtester.junit.runner.internal;

import java.io.File;
import javax.annotation.Resource;

import org.junit.Test;

import info.novatec.testit.webtester.api.browser.Browser;
import info.novatec.testit.webtester.junit.annotations.ConfigurationValue;
import info.novatec.testit.webtester.junit.annotations.Primary;
import info.novatec.testit.webtester.junit.exceptions.NoManagedBrowserException;
import info.novatec.testit.webtester.junit.exceptions.NoPrimaryBrowserException;
import info.novatec.testit.webtester.junit.exceptions.NoStaticPrimaryBrowserException;
import info.novatec.testit.webtester.junit.exceptions.NoUniquePrimaryBrowserException;
import info.novatec.testit.webtester.junit.exceptions.NotOfInjectableFieldTypeException;


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
        Browser browser;

        @ConfigurationValue("static.property")
        static String staticProperty;

        @ConfigurationValue("property")
        String property;

    }

    @Test(expected = NoStaticPrimaryBrowserException.class)
    public void testThatStaticConfigurationValuesNeedStaticPrimaryBrowser() {
        assertTestClass(NonStaticPrimaryBrowserWithStaticConfigurationPropertyTestClass.class);
    }

    private static class NonStaticPrimaryBrowserWithStaticConfigurationPropertyTestClass {

        @Resource
        static Browser staticBrowser;

        @Primary
        @Resource
        Browser browser;

        @ConfigurationValue("static.property")
        static String staticProperty;

        @ConfigurationValue("property")
        String property;

    }

    @Test(expected = NoManagedBrowserException.class)
    public void testThatManagedBrowserIsNeededForConfigurationValues() {
        assertTestClass(NoBrowsersTestClass.class);
    }

    private static class NoBrowsersTestClass {

        @ConfigurationValue("property")
        String property;

    }

    @Test(expected = NoUniquePrimaryBrowserException.class)
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

    @Test(expected = NoPrimaryBrowserException.class)
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

    @Test(expected = NotOfInjectableFieldTypeException.class)
    public void testThatFieldsNeedToBeOfInejctableType() {
        assertTestClass(InjectableTypeTestClass.class);
    }

    private static class InjectableTypeTestClass {

        @Resource
        Browser browser;

        @ConfigurationValue("property")
        File property;

    }

    /* utilities */

    private void assertTestClass(Class<?> testClass) {
        new TestClassPlausibilityChecker(testClass).assertPlausibilityOfTestClass();
    }

}
