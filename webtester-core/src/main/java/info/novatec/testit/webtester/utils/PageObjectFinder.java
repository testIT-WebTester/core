package info.novatec.testit.webtester.utils;

import static info.novatec.testit.webtester.utils.Identifications.css;

import info.novatec.testit.webtester.api.browser.Browser;
import info.novatec.testit.webtester.api.pageobjects.Identification;
import info.novatec.testit.webtester.api.pageobjects.PageObjectFactory;
import info.novatec.testit.webtester.api.pageobjects.PageObjectList;
import info.novatec.testit.webtester.internal.pageobjects.PageObjectModel;
import info.novatec.testit.webtester.pageobjects.GenericElement;
import info.novatec.testit.webtester.pageobjects.PageObject;


/**
 * This class provides the means for finding and creating page objects 'Ad-Hoc'
 * in a programmatically fashion (compared with declaring page object models).
 * It can be either initialized with a {@link Browser browser} or
 * {@link PageObject page object} instance.
 * <p>
 * Even though it is possible to initialize a finder directly, the preferred way
 * is to use {@link Browser#finder()}, {@link PageObject#finder()} or any of
 * their shorthand methods.
 *
 * @since 0.9.9
 */
public final class PageObjectFinder {

    private final Browser browser;
    private final PageObjectFactory pageObjectFactory;
    private final PageObject parent;

    /**
     * Creates a new {@link PageObjectFinder finder} instance using the given
     * {@link Browser browser} as a base. The created finder will find / create
     * {@link PageObject page objects} by using the whole HTML page as it's
     * search context.
     *
     * @param browser the browser to use
     * @since 0.9.9
     */
    public PageObjectFinder(Browser browser) {
        this.browser = browser;
        this.pageObjectFactory = this.browser.getPageObjectFactory();
        this.parent = null;
    }

    /**
     * Creates a new {@link PageObjectFinder finder} instance using the given
     * {@link PageObject page object} as a base. The created finder will find /
     * create page objects by using the page object as it's search context.
     * Meaning that only page elements within the page object's HTML tags will
     * be found.
     *
     * @param parent the page object to use as a parent
     * @since 0.9.9
     */
    public PageObjectFinder(PageObject parent) {
        this.browser = parent.getBrowser();
        this.pageObjectFactory = this.browser.getPageObjectFactory();
        this.parent = parent;
    }

    /**
     * Creates an {@link IdentificationFinder identification based finder}. It
     * can be used to create different {@link PageObject page object} instance
     * for a fixed CSS-Selector value. In case you want to use an other
     * identification method use {@link #findBy(Identification)} instead.
     *
     * @param cssSelector the CSS-Selector expression to use
     * @return the new identification based finder
     * @since 0.9.9
     */
    public IdentificationFinder find(String cssSelector) {
        return findBy(Identifications.css(cssSelector));
    }

    /**
     * Creates an {@link IdentificationFinder identification based finder}. It
     * can be used to create different {@link PageObject page object} instance
     * for a given {@link Identification identification definition}. Instances
     * of these definitions can be created using the {@link Identifications}
     * utility class.
     *
     * @param identification the identification to use
     * @return the new identification based finder
     * @since 0.9.9
     */
    public IdentificationFinder findBy(Identification identification) {
        return new IdentificationFinder(identification);
    }

    /**
     * Creates an {@link TypedFinder type based finder} for
     * {@link GenericElement generic elements}.
     * <p>
     * This is equal to calling {@link #find(Class)} with a
     * {@link GenericElement} class reference.
     *
     * @return the new type based finder
     * @since 0.9.9
     */
    public TypedFinder<GenericElement> findGeneric() {
        return new TypedFinder<GenericElement>(GenericElement.class);
    }

    /**
     * Creates an {@link TypedFinder type based finder}. It can be used to
     * create different {@link PageObject page object} instance for a given
     * {@link PageObject page object class}.
     *
     * @param <T> the type of the page object for which a finder should be created
     * @param pageObjectClass the class to use when creating instances
     * @return the new type based finder
     * @since 0.9.9
     */
    public <T extends PageObject> TypedFinder<T> find(Class<T> pageObjectClass) {
        return new TypedFinder<T>(pageObjectClass);
    }

    private PageObjectModel createModel(Identification identification) {
        PageObjectModel model;
        if (hasParent()) {
            model = PageObjectModel.forPageFragment(browser, identification, parent);
        } else {
            model = PageObjectModel.forPageFragment(browser, identification);
        }
        return model;
    }

    private boolean hasParent() {
        return parent != null;
    }

    /**
     * This finder implementation uses a fixed {@link Identification
     * identification} and provides methods for initializing {@link PageObject
     * page objects} based on this identification.
     *
     * @see IdentificationFinder#as(Class)
     * @see IdentificationFinder#asGeneric()
     * @see IdentificationFinder#asMany(Class)
     * @see IdentificationFinder#asManyGenerics()
     * @since 0.9.9
     */
    public final class IdentificationFinder {

        private final Identification identification;

        private IdentificationFinder(Identification identification) {
            this.identification = identification;
        }

        /**
         * Creates a {@link GenericElement generic page element} using the
         * finder's {@link Identification identification}.
         *
         * @return the created page object
         * @since 0.9.9
         */
        public GenericElement asGeneric() {
            return as(GenericElement.class);
        }

        /**
         * Creates a {@link PageObject page object} of the given class using the
         * finder's {@link Identification identification}.
         *
         * @param <T> the type of the page object to create
         * @param pageObjectClass the class of the page object to create
         * @return the created page object
         * @since 0.9.9
         */
        public <T extends PageObject> T as(Class<T> pageObjectClass) {
            PageObjectModel model = createModel(identification);
            return pageObjectFactory.create(pageObjectClass, model);
        }

        /**
         * Creates a {@link PageObjectList list} of {@link GenericElement
         * generic page element} using the finder's {@link Identification
         * identification}.
         *
         * @return the created page object list
         * @since 0.9.9
         */
        public PageObjectList<GenericElement> asManyGenerics() {
            return asMany(GenericElement.class);
        }

        /**
         * Creates a {@link PageObjectList list} of {@link PageObject page
         * objects} of the given class using the finder's {@link Identification
         * identification}.
         *
         * @param <T> the type of the page object list to create
         * @param pageObjectClass the class of the page object to create
         * @return the created page object list
         * @since 0.9.9
         */
        public <T extends PageObject> PageObjectList<T> asMany(Class<T> pageObjectClass) {
            PageObjectModel model = createModel(identification);
            return pageObjectFactory.createList(pageObjectClass, model);
        }

    }

    /**
     * This finder implementation uses a fixed {@link PageObject page object
     * class reference} and provides methods for initializing {@link PageObject
     * page objects} based on this class.
     *
     * @param <T> the type of the page object's to be created by this finder
     * @see TypedFinder#by(String)
     * @see TypedFinder#by(Identification)
     * @see TypedFinder#manyBy(String)
     * @see TypedFinder#manyBy(Identification)
     * @since 0.9.9
     */
    public final class TypedFinder<T extends PageObject> {

        private final Class<T> pageObjectClass;

        private TypedFinder(Class<T> pageObjectClass) {
            this.pageObjectClass = pageObjectClass;
        }

        /**
         * Creates a {@link PageObject page object} using the finder's specified
         * class and the given CSS-Selector expression.
         *
         * @param cssSelector the CSS-Selector expression to use as an
         * identifier
         * @return the created page object
         * @since 0.9.9
         */
        public T by(String cssSelector) {
            return by(css(cssSelector));
        }

        /**
         * Creates a {@link PageObject page object} using the finder's specified
         * class and the given {@link Identification identification definition}.
         * Instances of these definitions can be created using the
         * {@link Identifications} utility class.
         *
         * @param identification the identification to use
         * @return the created page object
         * @since 0.9.9
         */
        public T by(Identification identification) {
            PageObjectModel model = createModel(identification);
            return pageObjectFactory.create(pageObjectClass, model);
        }

        /**
         * Creates a {@link PageObjectList list} of {@link PageObject page
         * objects} using the finder's specified class and the given
         * CSS-Selector expression.
         *
         * @param cssSelector the CSS-Selector expression to use as an
         * identifier
         * @return the created page object list
         * @since 0.9.9
         */
        public PageObjectList<T> manyBy(String cssSelector) {
            return manyBy(css(cssSelector));
        }

        /**
         * Creates a {@link PageObjectList list} of {@link PageObject page
         * objects} using the finder's specified class and the given
         * {@link Identification identification definition}. Instances of these
         * definitions can be created using the {@link Identifications} utility
         * class.
         *
         * @param identification the identification to use
         * @return the created page object list
         * @since 0.9.9
         */
        public PageObjectList<T> manyBy(Identification identification) {
            PageObjectModel model = createModel(identification);
            return pageObjectFactory.createList(pageObjectClass, model);
        }

    }

}
