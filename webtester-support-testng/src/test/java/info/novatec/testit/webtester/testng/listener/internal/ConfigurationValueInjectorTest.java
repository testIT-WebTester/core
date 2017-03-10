package info.novatec.testit.webtester.testng.listener.internal;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import info.novatec.testit.webtester.api.config.Configuration;
import info.novatec.testit.webtester.config.DefaultConfigurationBuilder;
import info.novatec.testit.webtester.testng.annotations.ConfigurationValue;

import org.apache.commons.lang.IllegalClassException;

import org.testng.annotations.Test;

public class ConfigurationValueInjectorTest {

    Configuration configuration = DefaultConfigurationBuilder.create();

    @Test
    public void testInstanceFieldInjection() {

        TestClassForInstance testClass = new TestClassForInstance();
        ConfigurationValueInjector.inject(configuration, testClass);

        assertThat(testClass.stringValue, is("foo bar"));
        assertThat(testClass.integerValue, is(1));
        assertThat(testClass.longValue, is(2L));
        assertThat(testClass.floatValue, is(1.0f));
        assertThat(testClass.doubleValue, is(2.0d));
        assertThat(testClass.booleanValue, is(true));

    }



    @Test
    public void testStaticFieldInjection() {

        ConfigurationValueInjector.injectStatics(configuration, TestClassForStatics.class);

        assertThat(TestClassForStatics.stringValue, is("foo bar"));
        assertThat(TestClassForStatics.integerValue, is(1));
        assertThat(TestClassForStatics.longValue, is(2L));
        assertThat(TestClassForStatics.floatValue, is(1.0f));
        assertThat(TestClassForStatics.doubleValue, is(2.0d));
        assertThat(TestClassForStatics.booleanValue, is(true));

    }

    @Test(expectedExceptions = IllegalClassException.class)
    public void testThatUnmappedFieldTypesLeadToException() {

        TestClassForException testClass = new TestClassForException();
        ConfigurationValueInjector.inject(configuration, testClass);

    }

    public static class TestClassForInstance {

        @ConfigurationValue("test.string")
        String stringValue;

        @ConfigurationValue("test.integer")
        Integer integerValue;

        @ConfigurationValue("test.long")
        Long longValue;

        @ConfigurationValue("test.float")
        Float floatValue;

        @ConfigurationValue("test.double")
        Double doubleValue;

        @ConfigurationValue("test.boolean")
        Boolean booleanValue;

    }

    public static class TestClassForStatics {

        @ConfigurationValue("test.string")
        static String stringValue;

        @ConfigurationValue("test.integer")
        static Integer integerValue;

        @ConfigurationValue("test.long")
        static Long longValue;

        @ConfigurationValue("test.float")
        static Float floatValue;

        @ConfigurationValue("test.double")
        static Double doubleValue;

        @ConfigurationValue("test.boolean")
        static Boolean booleanValue;

    }

    public static class TestClassForException {

        @ConfigurationValue("unknownClass")
        Object stringValue;

    }

}
