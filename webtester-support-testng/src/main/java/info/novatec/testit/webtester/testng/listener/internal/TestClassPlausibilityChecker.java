package info.novatec.testit.webtester.testng.listener.internal;


import info.novatec.testit.webtester.api.browser.Browser;
import info.novatec.testit.webtester.internal.ReflectionUtils;
import info.novatec.testit.webtester.testng.annotations.ConfigurationValue;
import info.novatec.testit.webtester.testng.annotations.Primary;
import info.novatec.testit.webtester.testng.exceptions.NoManagedBrowserException;
import info.novatec.testit.webtester.testng.exceptions.NoPrimaryBrowserException;
import info.novatec.testit.webtester.testng.exceptions.NoUniquePrimaryBrowserException;
import info.novatec.testit.webtester.testng.exceptions.NotOfInjectableFieldTypeException;
import info.novatec.testit.webtester.testng.exceptions.NoStaticPrimaryBrowserException;

import javax.annotation.Resource;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class TestClassPlausibilityChecker {

    private Set<Field> allFields;

    public TestClassPlausibilityChecker(Class<?> testClass) {
        this.allFields = ReflectionUtils.getAllFieldsOfClassHierarchy(testClass);
    }

    public void assertPlausibilityOfTestClass() {

        assertThatNoMoreThenOnePrimaryBrowserIsDeclared();

        List<Field> configurationValueFields = getConfigurationValueFields();
        // TODO start page ticket - get all fields
        if (!configurationValueFields.isEmpty()) {
            Field primaryBrowserField = getRequiredPrimaryBrowserInstance();
            assertPlausibilityOfConfigurationValueFields(configurationValueFields, primaryBrowserField);
            // TODO start page ticket - assert plausibility
        }

    }

    private void assertThatNoMoreThenOnePrimaryBrowserIsDeclared() {
        List<Field> managedBrowserFields = getManagedBrowserFields();
        List<Field> primaryBrowserFields = getPrimaryBrowserFields(managedBrowserFields);
        if (primaryBrowserFields.size() > 1) {
            throw new NoUniquePrimaryBrowserException();
        }
    }

    private void assertPlausibilityOfConfigurationValueFields(List<Field> configurationValueFields,
                                                              Field primaryBrowserField) {
        for (Field field : configurationValueFields) {
            assertInjectableTypeForConfigurationValueField(field);
            assertStaticPrimaryBrowserForStaticConfigurationValueField(field, primaryBrowserField);
        }
    }

    /* details */

    private Field getRequiredPrimaryBrowserInstance() {

        List<Field> managedBrowserFields = getManagedBrowserFields();
        if (managedBrowserFields.isEmpty()) {
            throw new NoManagedBrowserException();
        }

        Field primaryBrowserField;
        if (managedBrowserFields.size() == 1) {
            primaryBrowserField = managedBrowserFields.get(0);
        } else {
            List<Field> primaryBrowserFields = getPrimaryBrowserFields(managedBrowserFields);
            if (primaryBrowserFields.isEmpty()) {
                throw new NoPrimaryBrowserException();
            }
            if (primaryBrowserFields.size() == 1) {
                primaryBrowserField = primaryBrowserFields.get(0);
            } else {
                throw new NoUniquePrimaryBrowserException();
            }
        }

        return primaryBrowserField;

    }

    private List<Field> getManagedBrowserFields() {
        List<Field> managedBrowserFields = new LinkedList<Field>();
        for (Field field : allFields) {
            if (isBrowser(field) && isManaged(field)) {
                managedBrowserFields.add(field);
            }
        }
        return managedBrowserFields;
    }

    private boolean isBrowser(Field field) {
        return Browser.class.isAssignableFrom(field.getType());
    }

    private boolean isManaged(Field field) {
        return field.isAnnotationPresent(Resource.class);
    }

    private List<Field> getPrimaryBrowserFields(List<Field> managedBrowserFields) {
        List<Field> primaryBrowserFields = new LinkedList<Field>();
        for (Field field : managedBrowserFields) {
            if (field.isAnnotationPresent(Primary.class)) {
                primaryBrowserFields.add(field);
            }
        }
        return primaryBrowserFields;
    }

    private List<Field> getConfigurationValueFields() {
        List<Field> configurationValueFields = new LinkedList<Field>();
        for (Field field : allFields) {
            if (field.isAnnotationPresent(ConfigurationValue.class)) {
                configurationValueFields.add(field);
            }
        }
        return configurationValueFields;
    }

    private void assertInjectableTypeForConfigurationValueField(Field field) {
        if (!ConfigurationValueInjector.canInjectValue(field)) {
            throw new NotOfInjectableFieldTypeException(field);
        }
    }

    private void assertStaticPrimaryBrowserForStaticConfigurationValueField(Field field, Field primaryBrowserField) {
        if (isStatic(field) && !isStatic(primaryBrowserField)) {
            throw new NoStaticPrimaryBrowserException(
                    "the static configuration value field '" + field.getName() + "' needs a static primary browser!");
        }
    }

    private boolean isStatic(Field field) {
        return Modifier.isStatic(field.getModifiers());
    }

}