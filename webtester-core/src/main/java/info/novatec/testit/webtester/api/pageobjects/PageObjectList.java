package info.novatec.testit.webtester.api.pageobjects;

import java.util.List;

import com.google.common.base.Predicate;

import info.novatec.testit.webtester.api.pageobjects.traits.Invalidateable;
import info.novatec.testit.webtester.pageobjects.PageObject;


/**
 * Classes implementing this {@link List} extension allow for dynamic filtering
 * of many {@link PageObject page objects}.
 *
 * @param <T> the type of the contained page objects
 */
public interface PageObjectList<T extends PageObject> extends List<T>, Invalidateable {

    /**
     * Creates a new {@link PageObjectList page object list} based on this lists
     * current content after applying the given filter condition. The new list
     * will not be linked to the original one and changes to the original list
     * will not be reflected in the new list!
     *
     * @param condition the {@link Predicate predicate} to use as a filter
     * @return a new page object list
     */
    PageObjectList<T> filter(Predicate<? super T> condition);

    @Override
    void invalidate();

}
