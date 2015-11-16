package info.novatec.testit.webtester.api.exceptions;

/**
 * Base exception type for all exceptios directly thrown by WebTester.
 *
 * @since 0.9.0
 */
@SuppressWarnings("serial")
public class WebTesterException extends RuntimeException {

    protected WebTesterException(String message) {
        super(message);
    }

    protected WebTesterException(Throwable cause) {
        super(cause);
    }

    protected WebTesterException(String message, Throwable cause) {
        super(message, cause);
    }

}
