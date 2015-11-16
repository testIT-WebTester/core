package info.novatec.testit.webtester.utils.conditions;

import com.google.common.base.Predicate;

import info.novatec.testit.webtester.pageobjects.Select;


/**
 * Predicate to be used in order to check if a {@link Select select} has a
 * certain selected value. This will <u>not</u> check weather or not it is
 * the only selected value!
 * <p>
 * <b>Example:</b> Given a selection of the values "foo", "bar" and "xuu", any check
 * for "foo", "bar" or "xuu" will return true and a check for "kuu" will return false.
 *
 * @since 0.9.9
 */
public class SelectedValue implements Predicate<Select> {

    private String value;

    public SelectedValue(String value) {
        this.value = value;
    }

    @Override
    public boolean apply(Select select) {
        return select.getAllSelectedValues().contains(value);
    }

    @Override
    public String toString() {
        return String.format("selected value: %s", value);
    }

}
