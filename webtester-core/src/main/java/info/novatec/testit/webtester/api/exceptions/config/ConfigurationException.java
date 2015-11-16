package info.novatec.testit.webtester.api.exceptions.config;

import info.novatec.testit.webtester.api.exceptions.WebTesterException;


@SuppressWarnings("serial")
public class ConfigurationException extends WebTesterException {

    protected ConfigurationException(String message, Throwable cause) {
        super(message, cause);
    }

    protected ConfigurationException(String message) {
        super(message);
    }

    protected ConfigurationException(Throwable cause) {
        super(cause);
    }

}
