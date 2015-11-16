package info.novatec.testit.webtester.utils.conditions;

import com.google.common.base.Predicate;

import info.novatec.testit.webtester.pageobjects.PageObject;


/**
 * Predicate to be used in order to check if a {@link PageObject page object} is
 * interactable (visible and enabled).
 *
 * @since 0.9.9
 */
public class Interactable implements Predicate<PageObject> {

    private Enabled enabled = new Enabled();
    private Visible visible = new Visible();

    @Override
    public boolean apply(PageObject pageObject) {
        return isEnabled(pageObject) && isVisible(pageObject);
    }

    private boolean isEnabled(PageObject pageObject) {
        return enabled.apply(pageObject);
    }

    private boolean isVisible(PageObject pageObject) {
        return visible.apply(pageObject);
    }

    @Override
    public String toString() {
        return "interactable";
    }

}
