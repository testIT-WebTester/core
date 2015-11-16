package info.novatec.testit.webtester.utils;

import info.novatec.testit.webtester.api.callbacks.PageObjectCallback;
import info.novatec.testit.webtester.api.exceptions.PageObjectIsDisabledException;
import info.novatec.testit.webtester.api.exceptions.PageObjectIsInvisibleException;
import info.novatec.testit.webtester.pageobjects.PageObject;


/**
 * This class provides a variety of different assertion methods for WebTester
 * elements..
 *
 * @since 0.9.6
 */
public final class Asserts {

    /**
     * Asserts that the given {@link PageObject page object} is enabled and
     * visible.
     *
     * @param pageObject the page object to assert
     * @throws PageObjectIsDisabledException if this page object is disabled
     * @throws PageObjectIsInvisibleException if this page object is invisible
     * @since 0.9.6
     */
    public static void assertEnabledAndVisible(PageObject pageObject) {
        assertEnabled(pageObject);
        assertVisible(pageObject);
    }

    /**
     * Asserts that the given {@link PageObject page object} is enabled.
     *
     * @param pageObject the page object to assert
     * @throws PageObjectIsDisabledException if this page object is disabled
     * @since 0.9.6
     */
    public static void assertEnabled(PageObject pageObject) {
        pageObject.executeAction(new PageObjectCallback() {

            @Override
            public void execute(PageObject po) {
                if (!po.isEnabled()) {
                    throw new PageObjectIsDisabledException(po);
                }
            }

        });
    }

    /**
     * Asserts that the given {@link PageObject page object} is visible.
     *
     * @param pageObject the page object to assert
     * @throws PageObjectIsInvisibleException if this page object is invisible
     * @since 0.9.6
     */
    public static void assertVisible(PageObject pageObject) {
        pageObject.executeAction(new PageObjectCallback() {

            @Override
            public void execute(PageObject po) {
                if (!po.isVisible()) {
                    throw new PageObjectIsInvisibleException(po);
                }
            }

        });
    }

    private Asserts() {
    }

}
