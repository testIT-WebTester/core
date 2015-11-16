package info.novatec.testit.webtester.utils.conditions;

import com.google.common.base.Predicate;

import info.novatec.testit.webtester.internal.Objects;
import info.novatec.testit.webtester.pageobjects.PageObject;


/**
 * Predicate to be used in order to check if a {@link PageObject page object}
 * has a certain visible text.
 *
 * @since 0.9.9
 */
public class VisibleTextEquals implements Predicate<PageObject> {

    private String text;

    public VisibleTextEquals(String text) {
        this.text = text;
    }

    @Override
    public boolean apply(PageObject pageObject) {
        return Objects.equals(text, pageObject.getVisibleText());
    }

    @Override
    public String toString() {
        return String.format("visible text equals: %s", text);
    }

}
