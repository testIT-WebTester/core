package info.novatec.testit.webtester.support.assertj;

import info.novatec.testit.webtester.pageobjects.Checkbox;


/**
 * Contains assertions for {@link Checkbox checkboxes}.
 *
 * @since 0.9.8
 */
public class CheckboxAssert extends AbstractPageObjectAssert<CheckboxAssert, Checkbox> {

    public CheckboxAssert(Checkbox actual) {
        super(actual, CheckboxAssert.class);
    }

    /**
     * Asserts that the {@link Checkbox checkbox} is selected.
     *
     * @return same assertion instance for fluent API
     * @since 0.9.8
     */
    public CheckboxAssert isSelected() {
        failOnActualBeingNull();
        if (!actual.isSelected()) {
            failWithMessage("Expected checkbox to be selected, but is wasn't.");
        }
        return this;
    }

    /**
     * Asserts that the {@link Checkbox checkbox} isn't selected.
     *
     * @return same assertion instance for fluent API
     * @since 0.9.8
     */
    public CheckboxAssert isNotSelected() {
        failOnActualBeingNull();
        if (actual.isSelected()) {
            failWithMessage("Expected checkbox not to be selected, but it was.");
        }
        return this;
    }

}
