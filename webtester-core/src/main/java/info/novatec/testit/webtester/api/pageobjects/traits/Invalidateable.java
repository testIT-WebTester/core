package info.novatec.testit.webtester.api.pageobjects.traits;

import info.novatec.testit.webtester.api.pageobjects.PageObjectList;
import info.novatec.testit.webtester.pageobjects.PageObject;


/**
 * Trait interface used to signal that an object can be invalidated.
 *
 * @since 0.9.9
 */
public interface Invalidateable {

    /**
     * Invalidates this {@linkplain Invalidateable} forcing it to reinitialize
     * itself when it is used again.
     * For more details see the documentation on the implementation.
     *
     * @see PageObject#invalidate()
     * @see PageObjectList#invalidate()
     * @since 0.9.9
     */
    void invalidate();

}
