package info.novatec.testit.webtester.api.pageobjects.traits;

import info.novatec.testit.webtester.pageobjects.PageObject;


/**
 * Trait interface used to signal that a {@link PageObject page object} has a
 * label.
 *
 * @since 0.9.9
 */
public interface HasLabel {

    String getLabel();

}
