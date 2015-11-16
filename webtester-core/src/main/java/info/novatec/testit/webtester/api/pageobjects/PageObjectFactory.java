package info.novatec.testit.webtester.api.pageobjects;

import org.openqa.selenium.WebElement;

import info.novatec.testit.webtester.internal.pageobjects.PageObjectModel;
import info.novatec.testit.webtester.pageobjects.PageObject;


/**
 * Classes implementing this interface are responsible for initializing
 * {@link PageObject page objects}.
 *
 * @since 0.9.6
 */
public interface PageObjectFactory {

    /**
     * Creates an instance of the given {@link PageObject page object} class
     * using the provided {@link PageObjectModel model} information.
     *
     * @param pageClazz the page object class which should be initialized
     * @param model the model to use
     * @param <T> the class of the page object to create
     * @return an instance of the given page object class
     * @since 0.9.9
     */
    <T extends PageObject> T create(Class<T> pageClazz, PageObjectModel model);

    /**
     * Creates an instance of the given {@link PageObject page object} class
     * using the provided {@link PageObjectModel model} information and
     * {@link WebElement web element}. This method is intended to be used in
     * cases where an existing web elements should be wrapped as a page object.
     *
     * @param pageClazz the page object class which should be initialized
     * @param model the model to use
     * @param webElement the web element to wrap
     * @param <T> the class of the page object to create
     * @return an instance of the given page object class
     * @since 0.9.9
     */
    <T extends PageObject> T create(Class<T> pageClazz, PageObjectModel model, WebElement webElement);

    /**
     * Creates a {@link PageObjectList list} of the given {@link PageObject page
     * object} class using the provided {@link PageObjectModel model}
     * information.
     *
     * @param pageClazz type of the page object list
     * @param model the model to use
     * @param <T> the class of the page object list to create
     * @return 0.9.9
     */
    <T extends PageObject> PageObjectList<T> createList(Class<T> pageClazz, PageObjectModel model);

}
