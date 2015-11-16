package info.novatec.testit.webtester.utils.conditions;

import com.google.common.base.Predicate;

import info.novatec.testit.webtester.pageobjects.Select;


/**
 * Predicate to be used in order to check if a {@link Select select} has a
 * certain selected index. This will <u>not</u> check weather or not it is
 * the only selected index!
 * <p>
 * <b>Example:</b> Given a selection of the indices #0, #1 and #2, any check
 * for #0, #1 or #2 will return true and a check for #3 will return false.
 *
 * @since 0.9.9
 */
public class SelectedIndex implements Predicate<Select> {

    private Integer index;

    public SelectedIndex(Integer index) {
        this.index = index;
    }

    @Override
    public boolean apply(Select select) {
        return select.getAllSelectedIndices().contains(index);
    }

    @Override
    public String toString() {
        return String.format("selected index: %s", index);
    }

}
