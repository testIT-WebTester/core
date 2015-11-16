package info.novatec.testit.webtester.internal.pageobjects;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import org.apache.commons.lang.StringUtils;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;

import com.google.common.base.Predicate;

import info.novatec.testit.webtester.api.annotations.Internal;
import info.novatec.testit.webtester.api.pageobjects.PageObjectList;
import info.novatec.testit.webtester.pageobjects.PageObject;


@Internal
public class LazyLoadingPageObjectList<E extends PageObject> implements PageObjectList<E> {

    private static final String LIST_ELEMENT_NAME = "%s - element no. %s";
    private static final String THIS_LIST_IS_IMMUTABLE = "This list is immutable!";

    private Class<E> elementClass;
    private PageObjectModel model;

    private PageObjectList<E> internalList;

    public LazyLoadingPageObjectList(Class<E> elementClass, PageObjectModel model) {
        this.elementClass = elementClass;
        this.model = model;
    }

    private PageObjectList<E> getInternalList() {

        if (internalList != null) {
            return internalList;
        }

        PageObjectList<E> list = getListOfPageObjects();
        rememberListIfCachingIsEnabled(list);
        return list;

    }

    private void rememberListIfCachingIsEnabled(PageObjectList<E> list) {
        if (model.cachingIsEnabled()) {
            internalList = list;
        }
    }

    private PageObjectList<E> getListOfPageObjects() {
        int number = 0;
        PageObjectList<E> list = new DefaultPageObjectList<E>();
        for (WebElement element : findElementsForList()) {
            list.add(wrapElement(element, getElementName(++number)));
        }
        return list;
    }

    private List<WebElement> findElementsForList() {
        SearchContext searchContext = model.getSearchContext();
        return searchContext.findElements(model.getSeleniumBy());
    }

    private String getElementName(int number) {
        String prefix = model.getName();
        return StringUtils.isBlank(prefix) ? StringUtils.EMPTY : String.format(LIST_ELEMENT_NAME, prefix, number);
    }

    private E wrapElement(WebElement element, String name) {
        PageObjectModel elementMetaData = PageObjectModel.forPageObjectListElement(model.getBrowser(), model.getParent());
        elementMetaData.setName(name);
        return model.getBrowser().getPageObjectFactory().create(elementClass, elementMetaData, element);
    }

    /**
     * Invalidates this {@link LazyLoadingPageObjectList} forcing it to
     * reinitialize itself when it is used again. This can be necessary when
     * elements of this list are modified by the system under test. (e.g. moved)
     */
    @Override
    public void invalidate() {
        internalList = null;
    }

    /* These are all the methods defined by the List interface. They all
     * delegate to the internal List of this class. The internal list is lazily
     * created when any of these methods are called for the first time. */

    @Override
    public int size() {
        return getInternalList().size();
    }

    @Override
    public boolean isEmpty() {
        return getInternalList().isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return getInternalList().contains(o);
    }

    @Override
    public Iterator<E> iterator() {
        return getInternalList().iterator();
    }

    @Override
    public Object[] toArray() {
        return getInternalList().toArray();
    }

    @Override
    public <E> E[] toArray(E[] a) {
        return getInternalList().toArray(a);
    }

    @Override
    public boolean add(E e) {
        throw new UnsupportedOperationException(THIS_LIST_IS_IMMUTABLE);
    }

    @Override
    public boolean remove(Object o) {
        throw new UnsupportedOperationException(THIS_LIST_IS_IMMUTABLE);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return getInternalList().containsAll(c);
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        throw new UnsupportedOperationException(THIS_LIST_IS_IMMUTABLE);
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        throw new UnsupportedOperationException(THIS_LIST_IS_IMMUTABLE);
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        throw new UnsupportedOperationException(THIS_LIST_IS_IMMUTABLE);
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        throw new UnsupportedOperationException(THIS_LIST_IS_IMMUTABLE);
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException(THIS_LIST_IS_IMMUTABLE);
    }

    @Override
    public E get(int index) {
        return getInternalList().get(index);
    }

    @Override
    public E set(int index, E element) {
        throw new UnsupportedOperationException(THIS_LIST_IS_IMMUTABLE);
    }

    @Override
    public void add(int index, E element) {
        throw new UnsupportedOperationException(THIS_LIST_IS_IMMUTABLE);
    }

    @Override
    public E remove(int index) {
        throw new UnsupportedOperationException(THIS_LIST_IS_IMMUTABLE);
    }

    @Override
    public int indexOf(Object o) {
        return getInternalList().indexOf(o);
    }

    @Override
    public int lastIndexOf(Object o) {
        return getInternalList().lastIndexOf(o);
    }

    @Override
    public ListIterator<E> listIterator() {
        return getInternalList().listIterator();
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        return getInternalList().listIterator(index);
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        return getInternalList().subList(fromIndex, toIndex);
    }

    @Override
    public PageObjectList<E> filter(Predicate<? super E> condition) {
        return getInternalList().filter(condition);
    }

}
