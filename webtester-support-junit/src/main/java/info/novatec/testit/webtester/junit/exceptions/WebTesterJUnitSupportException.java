package info.novatec.testit.webtester.junit.exceptions;

import info.novatec.testit.webtester.api.exceptions.WebTesterException;


@SuppressWarnings("serial")
public class WebTesterJUnitSupportException extends WebTesterException {

    protected WebTesterJUnitSupportException(String message, Throwable cause) {
        super(message, cause);
    }

    protected WebTesterJUnitSupportException(String message) {
        super(message);
    }

    protected WebTesterJUnitSupportException(Throwable cause) {
        super(cause);
    }

}
