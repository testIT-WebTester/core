package info.novatec.testit.webtester.utils.conditions;

import com.google.common.base.Predicate;

import info.novatec.testit.webtester.api.pageobjects.traits.HasLabel;
import info.novatec.testit.webtester.internal.Objects;
import info.novatec.testit.webtester.pageobjects.PageObject;


/**
 * Predicate to be used in order to check if a {@link PageObject page object}
 * has a certain {@link HasLabel label}.
 *
 * @since 0.9.9
 */
public class Label implements Predicate<HasLabel> {

    private String label;

    public Label(String label) {
        this.label = label;
    }

    @Override
    public boolean apply(HasLabel hasLabel) {
        return Objects.equals(label, hasLabel.getLabel());
    }

    @Override
    public String toString() {
        return String.format("label: %s", label);
    }

}
