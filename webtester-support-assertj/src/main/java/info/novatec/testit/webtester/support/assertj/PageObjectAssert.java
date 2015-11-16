package info.novatec.testit.webtester.support.assertj;

import info.novatec.testit.webtester.pageobjects.PageObject;


/**
 * Contains assertions for {@link PageObject page objects}.
 *
 * @since 0.9.8
 */
public class PageObjectAssert extends AbstractPageObjectAssert<PageObjectAssert, PageObject> {

    public PageObjectAssert(PageObject actual) {
        super(actual, PageObjectAssert.class);
    }

}
