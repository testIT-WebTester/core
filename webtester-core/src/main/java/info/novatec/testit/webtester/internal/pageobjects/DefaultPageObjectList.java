package info.novatec.testit.webtester.internal.pageobjects;

import java.util.LinkedList;

import com.google.common.base.Predicate;

import info.novatec.testit.webtester.api.pageobjects.PageObjectList;
import info.novatec.testit.webtester.pageobjects.PageObject;


@SuppressWarnings("serial")
public class DefaultPageObjectList<E extends PageObject> extends LinkedList<E> implements PageObjectList<E> {

    @Override
    public PageObjectList<E> filter(Predicate<? super E> condition) {
        DefaultPageObjectList<E> filtered = new DefaultPageObjectList<E>();
        for (E pageObject : this) {
            if (condition.apply(pageObject)) {
                filtered.add(pageObject);
            }
        }
        return filtered;
    }

    @Override
    public void invalidate() {
        // NOOP
    }

}
