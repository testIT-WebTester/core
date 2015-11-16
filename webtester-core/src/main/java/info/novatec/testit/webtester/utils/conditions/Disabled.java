package info.novatec.testit.webtester.utils.conditions;

import com.google.common.base.Predicate;

import info.novatec.testit.webtester.pageobjects.PageObject;


/**
 * Predicate to be used in order to check if a {@link PageObject page object} is
 * disabled.
 *
 * @since 0.9.9
 */
public class Disabled implements Predicate<PageObject> {

    private Enabled enabled = new Enabled();

    @Override
    public boolean apply(PageObject pageObject) {
        return !enabled.apply(pageObject);
    }

    @Override
    public String toString() {
        return "disabled";
    }

}
