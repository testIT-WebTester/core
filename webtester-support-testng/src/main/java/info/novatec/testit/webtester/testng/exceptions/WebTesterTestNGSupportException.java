package info.novatec.testit.webtester.testng.exceptions;


import info.novatec.testit.webtester.api.exceptions.WebTesterException;

@SuppressWarnings("serial")
public class WebTesterTestNGSupportException extends WebTesterException {

    protected WebTesterTestNGSupportException(String message, Throwable cause) {
        super(message, cause);
    }

    protected WebTesterTestNGSupportException(String message) {
        super(message);
    }

    protected WebTesterTestNGSupportException(Throwable cause) {
        super(cause);
    }

}
