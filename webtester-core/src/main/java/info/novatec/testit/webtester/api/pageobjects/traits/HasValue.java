package info.novatec.testit.webtester.api.pageobjects.traits;

import info.novatec.testit.webtester.pageobjects.PageObject;


/**
 * Trait interface used to signal that a {@link PageObject page object} has a
 * value.
 *
 * @param <T> the type of the value
 * @since 0.9.9
 */
public interface HasValue<T> {

    T getValue();

}
