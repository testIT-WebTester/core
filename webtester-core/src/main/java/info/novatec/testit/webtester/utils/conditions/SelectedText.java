package info.novatec.testit.webtester.utils.conditions;

import com.google.common.base.Predicate;

import info.novatec.testit.webtester.pageobjects.Select;


/**
 * Predicate to be used in order to check if a {@link Select select} has a
 * certain selected text. This will <u>not</u> check weather or not it is
 * the only selected text!
 * <p>
 * <b>Example:</b> Given a selection of the texts "foo", "bar" and "xuu", any check
 * for "foo", "bar" or "xuu" will return true and a check for "kuu" will return false.
 *
 * @since 0.9.9
 */
public class SelectedText implements Predicate<Select> {

    private String text;

    public SelectedText(String text) {
        this.text = text;
    }

    @Override
    public boolean apply(Select select) {
        return select.getAllSelectedTexts().contains(text);
    }

    @Override
    public String toString() {
        return String.format("selected text: %s", text);
    }

}
