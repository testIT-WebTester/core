package info.novatec.testit.webtester.api.exceptions;

import info.novatec.testit.webtester.pageobjects.PageObject;


/**
 * Subclasses of this exception are thrown in cases where the state of an
 * {@link PageObject page object} is invalid or otherwise hindering operations.
 *
 * @since 0.9.6
 */
@SuppressWarnings("serial")
public class PageObjectStateException extends WebTesterException {

    protected PageObjectStateException(String message) {
        super(message);
    }

    protected PageObjectStateException(String message, Throwable t) {
        super(message, t);
    }

}
