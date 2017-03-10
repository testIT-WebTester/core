package info.novatec.testit.webtester.testng.exceptions;


import info.novatec.testit.webtester.testng.listener.internal.ConfigurationValueInjector;

import java.lang.reflect.Field;

@SuppressWarnings("serial")
public class NotOfInjectableFieldTypeException extends IllegalTestClassStructureException {

    public NotOfInjectableFieldTypeException(Field field) {
        super("the configuration value field '" + field + "' is not of an injectable type! Injectable types are: "
                + ConfigurationValueInjector.getInjectableFieldClasses());
    }

}
