package info.novatec.testit.webtester.api.exceptions.config;

import java.util.Collection;

import org.apache.commons.lang.StringUtils;


@SuppressWarnings("serial")
public class InvalidValueTypeException extends ConfigurationException {

    public InvalidValueTypeException(Class<?> type, Collection<Class<?>> supportedTypes) {
        super("Unsupported value type: " + type + " - allowed types are: " + StringUtils.join(supportedTypes, ", "));
    }

}
