package info.novatec.testit.webtester.api.callbacks;

import info.novatec.testit.webtester.api.browser.Browser;


/**
 * A {@link CallbackWithReturnValue callback with return value} specialized for
 * {@link Browser browser} input type.
 *
 * @param <R> the return type of the callback
 * @since 0.9.0
 */
public interface BrowserCallbackWithReturnValue<R> extends CallbackWithReturnValue<Browser, R> {
    // nothing special - just simplifying the API
}
