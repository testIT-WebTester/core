package info.novatec.testit.webtester.api.exceptions;

import info.novatec.testit.webtester.pageobjects.PageObject;


/**
 * This type of exception is generally thrown when a {@link PageObject page
 * object} is interacted with but it is disabled. Normally these interactions
 * are only direct actions, not the getting of information from the page object.
 *
 * @since 0.9.6
 */
@SuppressWarnings("serial")
public class PageObjectIsDisabledException extends PageObjectStateException {

    private static final String MESSAGE_FORMAT = "The page object %s is disabled!";

    public PageObjectIsDisabledException(PageObject pageObject) {
        super(String.format(MESSAGE_FORMAT, pageObject));
    }

    public PageObjectIsDisabledException(PageObject pageObject, Throwable t) {
        super(String.format(MESSAGE_FORMAT, pageObject), t);
    }

}
