package info.novatec.testit.webtester.utils.conditions;

import com.google.common.base.Predicate;

import info.novatec.testit.webtester.api.pageobjects.traits.Selectable;
import info.novatec.testit.webtester.pageobjects.PageObject;


/**
 * Predicate to be used in order to check if {@link Selectable selectable}
 * {@link PageObject page object} is selected.
 *
 * @since 0.9.9
 */
public class Selected implements Predicate<Selectable> {

    @Override
    public boolean apply(Selectable selectable) {
        return selectable.isSelected();
    }

    @Override
    public String toString() {
        return "selected";
    }

}
