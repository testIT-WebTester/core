package info.novatec.testit.webtester.api.exceptions.config;

@SuppressWarnings("serial")
public class SetNullValuesException extends ConfigurationException {

    public SetNullValuesException(String key) {
        super("Null value is not allowed when setting a property! [property:" + key + ']');
    }

}
