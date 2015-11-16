package info.novatec.testit.webtester.utils.conditions;

import com.google.common.base.Predicate;

import info.novatec.testit.webtester.pageobjects.PageObject;


/**
 * Predicate to be used in order to check if a {@link PageObject page object's}
 * visible text contains a certain string.
 *
 * @since 0.9.9
 */
public class VisibleTextContains implements Predicate<PageObject> {

    private String partialText;

    public VisibleTextContains(String partialText) {
        this.partialText = partialText;
    }

    @Override
    public boolean apply(PageObject pageObject) {
        return pageObject.getVisibleText().contains(partialText);
    }

    @Override
    public String toString() {
        return String.format("visible text contains: %s", partialText);
    }

}
