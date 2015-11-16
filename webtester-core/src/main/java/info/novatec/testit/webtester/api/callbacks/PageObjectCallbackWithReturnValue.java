package info.novatec.testit.webtester.api.callbacks;

import info.novatec.testit.webtester.pageobjects.PageObject;


/**
 * A {@link CallbackWithReturnValue callback with return value} specialized for
 * {@link PageObject page object} input type.
 *
 * @param <B> the return type of the callback
 * @since 0.9.0
 */
public interface PageObjectCallbackWithReturnValue<B> extends CallbackWithReturnValue<PageObject, B> {
    // nothing special - just simplifying the API
}
