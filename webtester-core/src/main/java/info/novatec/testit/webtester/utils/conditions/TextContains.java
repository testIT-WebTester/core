package info.novatec.testit.webtester.utils.conditions;

import com.google.common.base.Predicate;

import info.novatec.testit.webtester.api.pageobjects.traits.HasText;
import info.novatec.testit.webtester.pageobjects.PageObject;


/**
 * Predicate to be used in order to check if a {@link PageObject page object's}
 * text contains a certain partial text.
 *
 * @since 0.9.9
 */
public class TextContains implements Predicate<HasText> {

    private String partialText;

    public TextContains(String partialText) {
        this.partialText = partialText;
    }

    @Override
    public boolean apply(HasText hasText) {
        return hasText.getText().contains(partialText);
    }

    @Override
    public String toString() {
        return String.format("text contains: %s", partialText);
    }

}
