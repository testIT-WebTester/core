package info.novatec.testit.webtester.utils;

import static info.novatec.testit.webtester.utils.Conditions.is;
import static info.novatec.testit.webtester.utils.Conditions.visible;

import java.util.Arrays;
import java.util.Collection;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

import info.novatec.testit.webtester.api.callbacks.PageObjectCallback;
import info.novatec.testit.webtester.api.exceptions.PageObjectIsDisabledException;
import info.novatec.testit.webtester.api.exceptions.PageObjectIsInvisibleException;
import info.novatec.testit.webtester.eventsystem.EventSystem;
import info.novatec.testit.webtester.eventsystem.events.pageobject.ClickedEvent;
import info.novatec.testit.webtester.eventsystem.events.pageobject.DoubleClickedEvent;
import info.novatec.testit.webtester.pageobjects.PageObject;


/**
 * This class is used to perform a variety of mouse related actions.
 *
 * @since 0.9.6
 */
public final class Mouse {

    /**
     * Executes a single-click on the given {@linkplain PageObject page object}.
     * Will throw an exception if the page object is disabled or invisible!
     * <p>
     * The actual behavior might vary between different {@link WebDriver}
     * implementations. Some implementations might move the actual mouse cursor,
     * some might simulate the behavior.
     *
     * @param pageObject the page object the click should be executed on
     * @throws PageObjectIsDisabledException if the page object is disabled
     * @throws PageObjectIsInvisibleException if the page object is invisible
     * @since 0.9.6
     */
    public static void click(PageObject pageObject) {
        pageObject.executeAction(new PageObjectCallback() {

            @Override
            public void execute(PageObject po) {
                startActionSequence(po).click(po.getWebElement()).perform();
                EventSystem.fireEvent(new ClickedEvent(po));
                Marker.markAsUsed(po);
            }

        });
    }

    /**
     * Executes a double-click on the given {@linkplain PageObject page object}.
     * Will throw an exception if the page object is disabled or invisible!
     * <p>
     * The actual behavior might vary between different {@link WebDriver}
     * implementations. Some implementations might move the actual mouse cursor,
     * some might simulate the behavior.
     *
     * @param pageObject the page object the double click should be executed on
     * @throws PageObjectIsDisabledException if the page object is disabled
     * @throws PageObjectIsInvisibleException if the page object is invisible
     * @since 0.9.6
     */
    public static void doubleClick(PageObject pageObject) {
        pageObject.executeAction(new PageObjectCallback() {

            @Override
            public void execute(PageObject po) {
                startActionSequence(po).doubleClick(po.getWebElement()).perform();
                EventSystem.fireEvent(new DoubleClickedEvent(po));
                Marker.markAsUsed(po);
            }

        });
    }

    /**
     * Moves the mouse to each of the given {@link PageObject page objects} in
     * the order they are given. Before each move the page object is first
     * checked for visibility. Invisible or non existing page objects will lead
     * to an exception.
     * <p>
     * The actual behavior might vary between different {@link WebDriver}
     * implementations. Some implementations might move the actual mouse cursor,
     * some might simulate the behavior.
     *
     * @param pageObject the first page object the mouse should be moved to
     * @param pageObjects subsequent page objects the mouse should be moved to
     * in order
     * @throws TimeoutException if page object does not become visible in the
     * configured amount of time
     * @see WebDriver
     * @see Actions#moveToElement(org.openqa.selenium.WebElement)
     * @since 0.9.6
     */
    public static void moveToEach(PageObject pageObject, PageObject... pageObjects) {
        moveTo(pageObject);
        moveToEach(Arrays.asList(pageObjects));
    }

    /**
     * Moves the mouse to each of the given {@link PageObject page objects} in
     * the order they are given. Before each move the page object is first
     * checked for visibility. Invisible or non existing page objects will lead
     * to an exception.
     * <p>
     * The actual behavior might vary between different {@link WebDriver}
     * implementations. Some implementations might move the actual mouse cursor,
     * some might simulate the behavior.
     *
     * @param pageObjects the page objects the mouse should be moved to in order
     * @throws TimeoutException if page object does not become visible in the
     * configured amount of time
     * @see WebDriver
     * @see Actions#moveToElement(org.openqa.selenium.WebElement)
     * @since 0.9.6
     */
    public static void moveToEach(Collection<PageObject> pageObjects) {
        for (PageObject pageObject : pageObjects) {
            moveTo(pageObject);
        }
    }

    /**
     * Moves the mouse to the given {@link PageObject page object}. Before the
     * move the page object is first checked for visibility. An invisible or non
     * existing page object will lead to an exception.
     * <p>
     * The actual behavior might vary between different {@link WebDriver}
     * implementations. Some implementations might move the actual mouse cursor,
     * some might simulate the behavior.
     *
     * @param pageObject the page object the mouse should be moved to
     * @throws TimeoutException if page object does not become visible in the
     * configured amount of time
     * @see WebDriver
     * @see Actions#moveToElement(org.openqa.selenium.WebElement)
     * @since 0.9.6
     */
    public static void moveTo(PageObject pageObject) {
        pageObject.executeAction(new PageObjectCallback() {

            @Override
            public void execute(PageObject po) {
                Waits.waitUntil(po, is(visible()));
                startActionSequence(po).moveToElement(po.getWebElement()).perform();
            }

        });
    }

    private static Actions startActionSequence(PageObject pageObject) {
        return new Actions(pageObject.getBrowser().getWebDriver());
    }

    private Mouse() {
        // utility class constructor
    }

}
