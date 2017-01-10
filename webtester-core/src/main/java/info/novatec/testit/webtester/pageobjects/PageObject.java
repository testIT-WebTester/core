package info.novatec.testit.webtester.pageobjects;

import org.apache.commons.lang.StringUtils;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import info.novatec.testit.webtester.api.annotations.Mapping;
import info.novatec.testit.webtester.api.browser.Browser;
import info.novatec.testit.webtester.api.callbacks.PageObjectCallback;
import info.novatec.testit.webtester.api.callbacks.PageObjectCallbackWithReturnValue;
import info.novatec.testit.webtester.api.events.Event;
import info.novatec.testit.webtester.api.exceptions.WrongElementClassException;
import info.novatec.testit.webtester.api.pageobjects.Identification;
import info.novatec.testit.webtester.api.pageobjects.PageObjectFactory;
import info.novatec.testit.webtester.api.pageobjects.PageObjectList;
import info.novatec.testit.webtester.eventsystem.EventSystem;
import info.novatec.testit.webtester.eventsystem.events.pageobject.ClickedEvent;
import info.novatec.testit.webtester.internal.annotations.SetViaInjection;
import info.novatec.testit.webtester.internal.pageobjects.ActionTemplate;
import info.novatec.testit.webtester.internal.pageobjects.PageObjectModel;
import info.novatec.testit.webtester.internal.validation.MappingValidator;
import info.novatec.testit.webtester.utils.Identifications;
import info.novatec.testit.webtester.utils.Marker;
import info.novatec.testit.webtester.utils.PageObjectFinder;
import info.novatec.testit.webtester.utils.PageObjectFinder.IdentificationFinder;
import info.novatec.testit.webtester.utils.PageObjectFinder.TypedFinder;


/**
 * Base implementation for all page object classes. Any class sub-classing this
 * class must have a default constructor. Objects of this and any subclass
 * should not be initialized manually. Instead a {@linkplain PageObjectFactory}
 * must be used!
 *
 * @since 0.9.0
 */
public class PageObject {

    private static final Logger logger = LoggerFactory.getLogger(PageObject.class);

    @SetViaInjection
    private PageObjectModel model;

    private ActionTemplate actionTemplate;
    private MappingValidator validator;

    /**
     * This is the hard coded {@link WebElement} used in case the page object acts as a wrapper instead of a proxy.
     */
    private WebElement webElement;

    protected PageObject() {
        this.actionTemplate = new ActionTemplate(this);
        this.validator = new MappingValidator(getClass());
    }

    /**
     * Tries to find the {@link WebElement web element} described by this
     * {@link PageObject page object's} {@link PageObjectModel model}.
     * <p>
     * If caching is active the web element will be stored once it was
     * successfully found and future invocations of this method will return the
     * cached instance. If caching is not active the web element will be
     * resolved anew with each invocation.
     *
     * @return the web element of this page object.
     * @throws NoSuchElementException if the web element could not be found.
     * @since 0.9.9
     */
    public WebElement getWebElement() {
        if(webElement != null) {
            return validate(webElement);
        }
        return validate(findWebElement());
    }

    private WebElement findWebElement() {
        SearchContext searchContext = model.getSearchContext();
        return searchContext.findElement(model.getSeleniumBy());
    }

    /**
     * Executes a validation that checks the {@link WebElement} to make sure the
     * correct {@link PageObject} class was used to initialize it. If this check
     * fails an {@link WrongElementClassException} is thrown.
     *
     * @param element the element to check
     * @return the same web element instance in case the validation was successful
     * @throws WrongElementClassException if check fails
     * @since 0.9.9
     */
    protected final WebElement validate(WebElement element) {
        if (validator.canValidate()) {
            validator.assertValidity(element);
        } else {
            if (!isCorrectClassForWebElement(element)) {
                throw new WrongElementClassException(getClass());
            }
        }
        return element;
    }

    /**
     * Check if a given {@linkplain WebElement} is a valid instance of this
     * specific {@linkplain PageObject} type.<br>
     * The default implementation always returns true for all
     * {@linkplain WebElement WebElements}.<br>
     * <b>Child Classes should override this method!</b>
     *
     * @param webElementToBeChecked the element to check
     * @return true if the {@linkplain WebElement} is a valid instance of this
     * specific {@linkplain PageObject} type, false otherwise
     * @since 0.9.0
     * @deprecated will be removed with v1.3.0 - the annotation based {@link Mapping} approach should be used instead
     */
    @Deprecated
    protected boolean isCorrectClassForWebElement(WebElement webElementToBeChecked) {
        return true;
    }

    /**
     * @return the {@linkplain Browser browser} in which this
     * {@linkplain PageObject page object} is displayed.
     * @since 0.9.0
     */
    public Browser getBrowser() {
        return model.getBrowser();
    }

    /**
     * @return this {@linkplain PageObject page object's} parent. Might be null
     * if this page object has no parent.
     * @since 0.9.0
     */
    public PageObject getParent() {
        return model.getParent();
    }

    /**
     * @return this {@link PageObject page object's} human readable name. Might
     * be empty in case no name was given.
     * @since 0.9.9
     */
    public String getHumanReadableName() {
        return model.getName();
    }

    protected PageObjectModel getModel() {
        return model;
    }

    /**
     * Executes a click on this {@linkplain PageObject page object}. Will throw
     * an exception if the page object is invisible but not if it is disabled!
     *
     * @return the same instance for fluent API
     * @since 0.9.0
     */
    public PageObject click() {
        executeAction(new PageObjectCallback() {

            @Override
            public void execute(PageObject pageObject) {
                getWebElement().click();
                logger.debug(logMessage("clicked"));
                fireEventAndMarkAsUsed(new ClickedEvent(pageObject));
            }

        });
        return this;
    }

    /**
     * @return the visible text between the opening and closing tag represented
     * by this {@linkplain PageObject}.
     * @since 0.9.0
     */
    public String getVisibleText() {
        return executeAction(new PageObjectCallbackWithReturnValue<String>() {

            @Override
            public String execute(PageObject pageObject) {
                pageObject.markAsRead();
                return getWebElement().getText();
            }

        });
    }

    /**
     * @return whether or not the {@linkplain PageObject} exists and is
     * currently visible
     * @since 0.9.0
     */
    public boolean isVisible() {
        return executeAction(new PageObjectCallbackWithReturnValue<Boolean>() {

            @Override
            public Boolean execute(PageObject pageObject) {
                try {
                    return pageObject.getWebElement().isDisplayed();
                } catch (NoSuchElementException e) {
                    return Boolean.FALSE;
                }
            }

        });
    }

    /**
     * Returns whether or not this {@link PageObject} is part of the current page's DOM.
     * This can be used to avoid catching the {@link NoSuchElementException} in order to check if an element exists.
     *
     * @return true if the object is part of the page's DOM
     * @see WebElement
     * @since 1.2.0
     */
    public boolean isPresent() {
        try {
            getWebElement();
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    /**
     * @return whether or not the {@linkplain PageObject} is currently enabled
     * @since 0.9.0
     */
    public boolean isEnabled() {
        return executeAction(new PageObjectCallbackWithReturnValue<Boolean>() {

            @Override
            public Boolean execute(PageObject pageObject) {
                return getWebElement().isEnabled();
            }

        });
    }

    /**
     * Returns the underlying elements tag name.
     * <p>
     * This method is preferred to something like
     * <code>pageObject.getWebElement().getTageName();</code> because it uses
     * the page objects action mechanism to allow for default exception handling
     * and other features.
     *
     * @return the attribute's value as an integer
     * @since 0.9.7
     */
    public String getTagName() {
        return executeAction(new PageObjectCallbackWithReturnValue<String>() {

            @Override
            public String execute(PageObject pageObject) {
                return pageObject.getWebElement().getTagName();
            }

        });
    }

    /**
     * Sets the value of an attribute of this {@link PageObject} using JavaScript.
     * <p>
     * <b>Example:</b>
     * <pre>
     * // will change the value of a text field to 'foo'
     * textField.setAttribute("value", "foo");
     * </pre>
     *
     * @param attributeName the name of the attribute to set
     * @param value the value to set the attribute to
     * @since 1.2.0
     */
    public void setAttribute(String attributeName, String value) {
        String escapedValue = StringUtils.replace(value, "\"", "\\\"");
        String script = "arguments[0]." + attributeName + " = \"" + escapedValue + "\"";
        getBrowser().executeJavaScript(script, this, value);
    }

    /**
     * @param attributeName the name of the attribute for which the value should
     * be returned
     * @return the value of the given attribute, or null if the attribute is not
     * set
     * @since 0.9.0
     */
    public String getAttribute(final String attributeName) {
        return executeAction(new PageObjectCallbackWithReturnValue<String>() {

            @Override
            public String execute(PageObject pageObject) {
                String attributeValue = getWebElement().getAttribute(attributeName);
                return log(attributeValue);
            }

            private String log(String value) {
                logger.trace(logMessage("returning '{}' for attribute '{}'"), value, attributeName);
                return value;
            }

        });
    }

    /**
     * Returns the given attribute's value as an integer. If that attribute
     * could not be found <code>null</code> is returned!
     *
     * @param attributeName name of the attribute
     * @return the attribute's value as an integer
     */
    protected Integer getAttributeAsInt(String attributeName) {
        /* handled in two distinct steps in order to guarantee correct exception
         * recognition */
        final String attributeValue = getAttribute(attributeName);
        return executeAction(new PageObjectCallbackWithReturnValue<Integer>() {

            @Override
            public Integer execute(PageObject pageObject) {
                if (attributeValue == null) {
                    return null;
                }
                return Integer.valueOf(attributeValue);
            }

        });
    }

    /**
     * @param propertyName the name of the css property for which the value
     * should be returned
     * @return the value of the given css property or null if the property is
     * not set
     * @since 0.9.0
     */
    public String getCssProperty(final String propertyName) {
        return executeAction(new PageObjectCallbackWithReturnValue<String>() {

            @Override
            public String execute(PageObject pageObject) {
                return getWebElement().getCssValue(propertyName);
            }

        });
    }

    /**
     * Creates a new {@link PageObjectFinder page object finder} for this page
     * object. This finder can be used to programmatically identify and create
     * {@link PageObject page objects}.
     * <p>
     * This page object will be used as the parent of all created page objects
     * an in doing so limit the search scope of the operation. Only elements
     * within this page objects HTML tags will be considered!
     *
     * @return the newly create finder
     * @since 0.9.9
     */
    public PageObjectFinder finder() {
        return new PageObjectFinder(this);
    }

    /**
     * Shorthand method for finding a {@link GenericElement generic page
     * element} via a CSS-Selector expression.
     * <p>
     * This page object will be used as the parent of all created page objects
     * an in doing so limit the search scope of the operation. Only elements
     * within this page objects HTML tags will be considered!
     * <p>
     * <b>Examples:</b>
     * <ul>
     * <li><code>pageObject.find("#username").sendKeys("testuser");</code></li>
     * <li><code>pageObject.find("#button").click();</code></li>
     * <li><code>pageObject.find("#headline").getVisibleText();</code></li>
     * </ul>
     *
     * @param cssSelector the CSS-Selector expression to use
     * @return the generic element for the given selector
     * @since 0.9.9
     */
    public GenericElement find(String cssSelector) {
        return finder().findGeneric().by(cssSelector);
    }

    /**
     * Shorthand method for finding a {@link PageObjectList list} of
     * {@link GenericElement generic page elements} via a CSS-Selector
     * expression.
     * <p>
     * This page object will be used as the parent of all created page objects
     * an in doing so limit the search scope of the operation. Only elements
     * within this page objects HTML tags will be considered!
     * <p>
     * <b>Examples:</b>
     * <ul>
     * <li><code>pageObject.findMany(".button").filter(is(visible()));</code>
     * </li>
     * </ul>
     *
     * @param cssSelector the CSS-Selector expression to use
     * @return the list of generic elements for the given selector
     * @since 0.9.9
     */
    public PageObjectList<GenericElement> findMany(String cssSelector) {
        return finder().findGeneric().manyBy(cssSelector);
    }

    /**
     * Shorthand method for creating a new {@link IdentificationFinder
     * identification based finder}. Matching {@link Identification
     * identification} instances can be created using the
     * {@link Identifications} utility class.
     * <p>
     * This page object will be used as the parent of all created page objects
     * an in doing so limit the search scope of the operation. Only elements
     * within this page objects HTML tags will be considered!
     * <p>
     * <b>Examples:</b>
     * <ul>
     * <li>
     * <code>pageObject.findBy(id("username")).as(TextField.class).setText("testuser");</code>
     * </li>
     * <li>
     * <code>pageObject.findBy(css("#button")).as(Button.class).click();</code>
     * </li>
     * <li>
     * <code>pageObject.findBy(xpath(".//h1")).asGeneric().getVisibleText();</code>
     * </li>
     * </ul>
     *
     * @param identification the identification to use when identifying an
     * element
     * @return the new identification finder
     * @since 0.9.9
     */
    public IdentificationFinder findBy(Identification identification) {
        return finder().findBy(identification);
    }

    /**
     * Shorthand method for creating a new {@link TypedFinder type based finder}
     * . The given page object class is used for all subsequent operations on
     * the finder.
     * <p>
     * This page object will be used as the parent of all created page objects
     * an in doing so limit the search scope of the operation. Only elements
     * within this page objects HTML tags will be considered!
     * <p>
     * <b>Examples:</b>
     * <ul>
     * <li>
     * <code>pageObject.find(TextField.class).by(id("username")).setText("testuser");</code>
     * </li>
     * <li>
     * <code>pageObject.find(Button.class).by(css("#button")).click();</code>
     * </li>
     * <li>
     * <code>pageObject.find(GenericElement.class).by(xpath(".//h1")).getVisibleText();</code>
     * </li>
     * </ul>
     *
     * @param <T> the type of the page object to create a finder for
     * @param pageObjectClass the page object class to use when creating an
     * element
     * @return the new type finder
     * @since 0.9.9
     */
    public <T extends PageObject> TypedFinder<T> find(Class<T> pageObjectClass) {
        return finder().find(pageObjectClass);
    }

    /**
     * Invalidates this {@linkplain PageObject} forcing it to reinitialize
     * itself when it is used again. This can be necessary when the element is
     * modified by the system under test (e.g. moved).
     *
     * @since 0.9.9
     * @deprecated caching was removed in v1.2 - this exists in order to NOT break the API till v1.3
     */
    @Deprecated
    public void invalidate() {
        // TODO: remove in v1.3
        logger.warn("deprecated method 'invalidate()' used...");
    }

    /**
     * Fires the given event using the {@linkplain EventSystem#fireEvent(Event)}
     * method and mark this page object as used using
     * {@linkplain Marker#markAsUsed(PageObject)}.
     *
     * @param event the event to fire.
     * @since 0.9.0
     */
    protected final void fireEventAndMarkAsUsed(Event event) {
        EventSystem.fireEvent(event);
        Marker.markAsUsed(this);
        logger.trace(logMessage("fired event: {}"), event);
    }

    protected final void markAsRead() {
        Marker.markAsRead(this);
    }

    protected final void markAsUsed() {
        Marker.markAsUsed(this);
    }

    /**
     * Creates a new instance for the given {@linkplain PageObject page object}
     * class using the {@linkplain Browser browser's} creation mechanism. This
     * is a convenience method.
     *
     * @param pageClass the class of which an instance should be created.
     * @param <T> the class of the {@link PageObject} to create
     * @return the created instance.
     * @since 0.9.6
     */
    protected final <T extends PageObject> T create(Class<T> pageClass) {
        return getBrowser().create(pageClass);
    }

    /**
     * Executes the given {@link PageObjectCallback callback} with this
     * {@link PageObject page object} as input. This is a convenience method for calling the
     * {@link ActionTemplate#executeAction(PageObjectCallback)} method.
     *
     * @param callback the callback to execute
     * @since 0.9.7
     */
    public final void executeAction(PageObjectCallback callback) {
        actionTemplate.executeAction(callback);
    }

    /**
     * Executes the given {@link PageObjectCallbackWithReturnValue callback}
     * with this {@link PageObject page object} as input and a return value of
     * type B as output. This is a convenience method for calling the
     * {@link ActionTemplate#executeAction(PageObjectCallbackWithReturnValue)} method.
     *
     * @param <B> the type of the return value of the action
     * @param callback the callback to execute
     * @return the return value of the callback
     * @since 0.9.7
     */
    public final <B> B executeAction(PageObjectCallbackWithReturnValue<B> callback) {
        return actionTemplate.executeAction(callback);
    }

    protected String logMessage(String message) {
        return model.getLogPrefix() + message;
    }

    @Override
    public String toString() {

        StringBuilder subject = new StringBuilder(getClass().getSimpleName());

        String name = model.getName();
        if (StringUtils.isNotBlank(name)) {
            subject.append(" \"").append(name).append('\"');
        }

        Identification identification = model.getIdentification();
        if (identification != null) {
            subject.append(" identified by ").append(identification);
        }

        return subject.toString();

    }

}
