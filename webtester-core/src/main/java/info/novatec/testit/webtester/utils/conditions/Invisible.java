package info.novatec.testit.webtester.utils.conditions;

import com.google.common.base.Predicate;

import info.novatec.testit.webtester.pageobjects.PageObject;


/**
 * Predicate to be used in order to check if a {@link PageObject page object} is
 * invisible.
 *
 * @since 0.9.9
 */
public class Invisible implements Predicate<PageObject> {

    private Visible visible = new Visible();

    @Override
    public boolean apply(PageObject pageObject) {
        return !visible.apply(pageObject);
    }

    @Override
    public String toString() {
        return "invisible";
    }

}
