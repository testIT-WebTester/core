package info.novatec.testit.webtester.utils.conditions;

import com.google.common.base.Predicate;

import info.novatec.testit.webtester.pageobjects.PageObject;


/**
 * Predicate to be used in order to check if a {@link PageObject page object} is
 * 'editable'. A page object is considered editable if it is not read-only,
 * enabled and visible.
 *
 * @see ReadOnly
 * @see Enabled
 * @see Visible
 * @since 0.9.9
 */
public class Editable implements Predicate<PageObject> {

    private ReadOnly readOnly = new ReadOnly();
    private Enabled enabled = new Enabled();
    private Visible visible = new Visible();

    @Override
    public boolean apply(PageObject pageObject) {
        return isNotReadOnly(pageObject) && isEnabled(pageObject) && isVisible(pageObject);
    }

    private boolean isNotReadOnly(PageObject pageObject) {
        return !readOnly.apply(pageObject);
    }

    private boolean isEnabled(PageObject pageObject) {
        return enabled.apply(pageObject);
    }

    private boolean isVisible(PageObject pageObject) {
        return visible.apply(pageObject);
    }

    @Override
    public String toString() {
        return "editable";
    }

}
