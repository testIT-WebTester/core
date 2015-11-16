package info.novatec.testit.webtester.utils.conditions;

import com.google.common.base.Predicate;

import info.novatec.testit.webtester.api.pageobjects.traits.HasText;
import info.novatec.testit.webtester.internal.Objects;
import info.novatec.testit.webtester.pageobjects.PageObject;


/**
 * Predicate to be used in order to check if a {@link PageObject page object}
 * has a certain {@link HasText text}.
 *
 * @since 0.9.9
 */
public class TextEquals implements Predicate<HasText> {

    private String text;

    public TextEquals(String text) {
        this.text = text;
    }

    @Override
    public boolean apply(HasText hasText) {
        return Objects.equals(text, hasText.getText());
    }

    @Override
    public String toString() {
        return String.format("text equals: %s", text);
    }

}
