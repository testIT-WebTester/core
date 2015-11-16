package info.novatec.testit.webtester.api.exceptions;

import info.novatec.testit.webtester.pageobjects.PageObject;


/**
 * This exception is thrown when a {@link PageObject} loses its reference and
 * the recovery mechanism failed to find it again.
 *
 * @since 0.9.0
 */
@SuppressWarnings("serial")
public class StaleElementRecoveryException extends WebTesterException {

    public StaleElementRecoveryException(Throwable e) {
        super("Recovery of stale element failed!", e);
    }

}
