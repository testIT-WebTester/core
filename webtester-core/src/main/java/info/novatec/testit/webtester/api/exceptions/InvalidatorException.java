package info.novatec.testit.webtester.api.exceptions;

import info.novatec.testit.webtester.utils.Invalidator;


/**
 * Exception class for all exceptions intentionally thrown by the {@link Invalidator}.
 *
 * @since 0.9.9
 */
@SuppressWarnings("serial")
public class InvalidatorException extends WebTesterException {

    public InvalidatorException(String message, Throwable cause) {
        super(message, cause);
    }

}
