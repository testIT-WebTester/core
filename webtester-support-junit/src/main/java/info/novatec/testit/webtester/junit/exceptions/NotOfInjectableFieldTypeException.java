package info.novatec.testit.webtester.junit.exceptions;

import java.lang.reflect.Field;

import info.novatec.testit.webtester.junit.runner.internal.ConfigurationValueInjector;


@SuppressWarnings("serial")
public class NotOfInjectableFieldTypeException extends IllegalTestClassStructureException {

    public NotOfInjectableFieldTypeException(Field field) {
        super("the configuration value field '" + field + "' is not of an injectable type! Injectable types are: "
            + ConfigurationValueInjector.getInjectableFieldClasses());
    }

}
