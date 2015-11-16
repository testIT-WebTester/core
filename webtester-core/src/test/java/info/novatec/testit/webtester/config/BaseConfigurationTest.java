package info.novatec.testit.webtester.config;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.mockito.Mockito.inOrder;

import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import info.novatec.testit.webtester.api.config.ConfigurationExporter;
import info.novatec.testit.webtester.api.exceptions.config.InvalidValueTypeException;
import info.novatec.testit.webtester.api.exceptions.config.SetNullValuesException;


@RunWith(Enclosed.class)
public class BaseConfigurationTest {

    private static class BaseTest {
        BaseConfiguration cut = new BaseConfiguration();
    }

    public static class SettingOfProperties extends BaseTest {

        /* setProperty(...) good-cases are part of the "GettingOfProperties" set
         * of tests */

        @Test(expected = SetNullValuesException.class)
        public void testThatSettingNullValueLeadsToException() {
            cut.setProperty("null-value", null);
        }

        @Test(expected = InvalidValueTypeException.class)
        public void testThatSettingInvalidTypeValueLeadsToException() {
            cut.setProperty("invalid-type", this);
        }

    }

    public static class GettingOfProperties extends BaseTest {

        @Test
        public void testSetAndGetString() {
            cut.setProperty("string", "42");
            String stringProperty = cut.getStringProperty("string");
            assertThat(stringProperty, is(equalTo("42")));
        }

        @Test
        public void testSetAndGetInteger() {
            cut.setProperty("integer", 42);
            Integer integerProperty = cut.getIntegerProperty("integer");
            assertThat(integerProperty, is(equalTo(42)));
        }

        @Test
        public void testSetAndGetLong() {
            cut.setProperty("long", 42L);
            Long longProperty = cut.getLongProperty("long");
            assertThat(longProperty, is(equalTo(42L)));
        }

        @Test
        public void testSetAndGetFloat() {
            cut.setProperty("float", 42.0f);
            Float floatProperty = cut.getFloatProperty("float");
            assertThat(floatProperty, is(equalTo(42.0f)));
        }

        @Test
        public void testSetAndGetDouble() {
            cut.setProperty("double", 42.0d);
            Double doubleProperty = cut.getDoubleProperty("double");
            assertThat(doubleProperty, is(equalTo(42.0d)));
        }

        @Test
        public void testSetAndGetBoolean() {
            cut.setProperty("boolean", true);
            Boolean booleanProperty = cut.getBooleanProperty("boolean");
            assertThat(booleanProperty, is(equalTo(true)));
        }

        @Test
        public void testSetAndGetProperty() {
            cut.setProperty("property", "value");
            Object property = cut.getProperty("property");
            assertThat(property, is(equalTo(( Object ) "value")));
        }

    }

    public static class GettingOfDefaultProperties extends BaseTest {

        @Test
        public void testGetStringWithDefault() {
            String stringProperty = cut.getStringProperty("string", "42");
            assertThat(stringProperty, is(equalTo("42")));
        }

        @Test
        public void testGetIntegerWithDefault() {
            Integer integerProperty = cut.getIntegerProperty("integer", 42);
            assertThat(integerProperty, is(equalTo(42)));
        }

        @Test
        public void testGetLongWithDefault() {
            Long longProperty = cut.getLongProperty("long", 42L);
            assertThat(longProperty, is(equalTo(42L)));
        }

        @Test
        public void testGetFloatWithDefault() {
            Float floatProperty = cut.getFloatProperty("float", 42.0f);
            assertThat(floatProperty, is(equalTo(42.0f)));
        }

        @Test
        public void testGetDoubleWithDefault() {
            Double doubleProperty = cut.getDoubleProperty("double", 42.0d);
            assertThat(doubleProperty, is(equalTo(42.0d)));
        }

        @Test
        public void testGetBooleanWithDefault() {
            Boolean booleanProperty = cut.getBooleanProperty("boolean", true);
            assertThat(booleanProperty, is(equalTo(true)));
        }

        @Test
        public void testGetPropertyWithDefault() {
            Object property = cut.getProperty("property", "value");
            assertThat(property, is(equalTo(( Object ) "value")));
        }

    }

    public static class RemovalOfProperties extends BaseTest {

        @Test
        public void testRemoveProperty() {
            cut.setProperty("property", "value");
            cut.removeProperty("property");
            assertThat(cut.getProperty("property"), is(nullValue()));
        }

    }

    public static class GettingOfProperyKeys extends BaseTest {

        @Test
        public void testGetPropertyKeys() {
            cut.setProperty("property-one", "value");
            cut.setProperty("property-two", "value");
            assertThat(cut.getKeys(), containsInAnyOrder("property-one", "property-two"));
        }

        @Test(expected = UnsupportedOperationException.class)
        public void testThatKeysAreReturnedAsUmmutableSet_Add() {
            cut.setProperty("property-one", "value");
            cut.setProperty("property-two", "value");
            cut.getKeys().add("property-three");
        }

        @Test(expected = UnsupportedOperationException.class)
        public void testThatKeysAreReturnedAsUmmutableSet_Remove() {
            cut.setProperty("property-one", "value");
            cut.setProperty("property-two", "value");
            cut.getKeys().remove("property-three");
        }

    }

    @RunWith(MockitoJUnitRunner.class)
    public static class BehaviorTests extends BaseTest {

        @Mock
        ConfigurationExporter firstExporter;
        @Mock
        ConfigurationExporter secondExporter;

        @Before
        public void addExporters() {
            cut.addExporters(firstExporter, secondExporter);
        }

        @Test
        public void testThatSettingPropertiesCallsExportersInOrder() {

            cut.setProperty("property", "foo");
            cut.setProperty("another-property", "bar");

            InOrder inOrder = inOrder(firstExporter, secondExporter);
            inOrder.verify(firstExporter).export("property", "foo");
            inOrder.verify(secondExporter).export("property", "foo");
            inOrder.verify(firstExporter).export("another-property", "bar");
            inOrder.verify(secondExporter).export("another-property", "bar");
            inOrder.verifyNoMoreInteractions();

        }

    }

}
