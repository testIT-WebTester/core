package info.novatec.testit.webtester.support.assertj;

import info.novatec.testit.webtester.pageobjects.RadioButton;


/**
 * Contains assertions for {@link RadioButton radio buttons} .
 *
 * @since 0.9.8
 */
public class RadioButtonAssert extends AbstractPageObjectAssert<RadioButtonAssert, RadioButton> {

    public RadioButtonAssert(RadioButton actual) {
        super(actual, RadioButtonAssert.class);
    }

    /**
     * Asserts that the {@link RadioButton radio button} is selected.
     *
     * @param selected the radio button's expected selected status
     * @return same assertion instance for fluent API
     * @since 0.9.8
     */
    public RadioButtonAssert isSelected(boolean selected) {
        failOnActualBeingNull();
        if (!actual.isSelected()) {
            failWithMessage("Expected radiobutton to be selected, but is wasn't.");
        }
        return this;
    }

    /**
     * Asserts that the {@link RadioButton radio button} isn't selected.
     *
     * @param selected not the radio button's expected selected status
     * @return same assertion instance for fluent API
     * @since 0.9.8
     */
    public RadioButtonAssert isNotSelected(boolean selected) {
        failOnActualBeingNull();
        if (actual.isSelected()) {
            failWithMessage("Expected radiobutton not to be selected, but it was.");
        }
        return this;
    }

}
