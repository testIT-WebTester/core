package info.novatec.testit.webtester.api.exceptions;

import info.novatec.testit.webtester.pageobjects.Button;
import info.novatec.testit.webtester.pageobjects.PageObject;
import info.novatec.testit.webtester.pageobjects.TextField;


/**
 * This exception is thrown when a {@link PageObject} is initialized with an
 * identification that result in an invalid element for the {@link PageObject}'s
 * class. (e.g. identification of a {@link Button} when initializing a
 * {@link TextField})
 *
 * @since 0.9.0
 */
@SuppressWarnings("serial")
public class WrongElementClassException extends WebTesterException {

    public WrongElementClassException(Class<?> expectedClass) {
        super(String.format("element is not a %s!", expectedClass));
    }

}
