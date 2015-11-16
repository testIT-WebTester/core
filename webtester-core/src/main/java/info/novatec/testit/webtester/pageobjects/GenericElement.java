package info.novatec.testit.webtester.pageobjects;

import org.openqa.selenium.WebElement;

import info.novatec.testit.webtester.api.browser.Browser;
import info.novatec.testit.webtester.api.callbacks.PageObjectCallback;
import info.novatec.testit.webtester.api.callbacks.PageObjectCallbackWithReturnValue;
import info.novatec.testit.webtester.utils.PageObjectFinder;


/**
 * This special {@link PageObject} functions as middle ground between Selenium's
 * {@link WebElement} style generic instances and WebTesters page object
 * oriented approach. It exposes all web element methods otherwise hidden or
 * re-declared by other page objects.
 * <p>
 * It is intended to be used in the following situations:
 * <ul>
 * <li>Declaring a field in a page object where the type is fluent or
 * unimportant.</li>
 * <li>As a return value when using a {@link PageObjectFinder page object
 * finder} scripted style. (see {@link Browser Browser's} and {@link PageObject
 * PageObject's} <code>find(..)</code> methods)</li>
 * </ul>
 *
 * @since 0.9.9
 */
public class GenericElement extends PageObject {

    @Override
    public GenericElement click() {
        return ( GenericElement ) super.click();
    }

    /**
     * Executes {@link WebElement web element's} submit method.
     *
     * @return same instance for fluent API
     * @see WebElement#submit()
     * @since 0.9.9
     */
    public GenericElement submit() {
        executeAction(new PageObjectCallback() {

            @Override
            public void execute(PageObject pageObject) {
                pageObject.getWebElement().submit();
            }

        });
        return this;
    }

    /**
     * Executes {@link WebElement web element's} sendKeys method.
     *
     * @param keysToSend the keys to send
     * @return same instance for fluent API
     * @see WebElement#sendKeys(CharSequence...)
     * @since 0.9.9
     */
    public GenericElement sendKeys(final CharSequence... keysToSend) {
        executeAction(new PageObjectCallback() {

            @Override
            public void execute(PageObject pageObject) {
                pageObject.getWebElement().sendKeys(keysToSend);
            }

        });
        return this;
    }

    /**
     * Executes {@link WebElement web element's} clear method.
     *
     * @return same instance for fluent API
     * @see WebElement#clear()
     * @since 0.9.9
     */
    public GenericElement clear() {
        executeAction(new PageObjectCallback() {

            @Override
            public void execute(PageObject pageObject) {
                pageObject.getWebElement().clear();
            }

        });
        return this;
    }

    /**
     * Executes {@link WebElement web element's} isSelected method.
     *
     * @return same instance for fluent API
     * @see WebElement#isSelected()
     * @since 0.9.9
     */
    public boolean isSelected() {
        return executeAction(new PageObjectCallbackWithReturnValue<Boolean>() {

            @Override
            public Boolean execute(PageObject pageObject) {
                return pageObject.getWebElement().isSelected();
            }

        });
    }

    /**
     * Repackages this {@link GenericElement generic element} as a new instance
     * of the given {@link PageObject page object class}. This could be used to
     * 'cast' page objects returned by {@link Browser Browser's}
     * {@link Browser#find(String)} in a single fluent API style command
     * sequence.
     * <p>
     * <b>Example:</b>
     * <p>
     * <code>browser.find("#username").as(TextField.class).setText("testuser");</code>
     *
     * @param <T> the type of the page object to be created
     * @param pageObjectClass the page object class to instantiate using this
     * element's information
     * @return the newly created instance
     * @since 0.9.9
     */
    public <T extends PageObject> T as(Class<T> pageObjectClass) {
        return getBrowser().getPageObjectFactory().create(pageObjectClass, getModel());
    }

}
