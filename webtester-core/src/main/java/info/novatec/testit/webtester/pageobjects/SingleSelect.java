package info.novatec.testit.webtester.pageobjects;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import info.novatec.testit.webtester.api.annotations.Mapping;
import info.novatec.testit.webtester.api.callbacks.PageObjectCallback;
import info.novatec.testit.webtester.api.callbacks.PageObjectCallbackWithReturnValue;
import info.novatec.testit.webtester.eventsystem.events.pageobject.SelectedByIndexEvent;
import info.novatec.testit.webtester.eventsystem.events.pageobject.SelectedByTextEvent;
import info.novatec.testit.webtester.eventsystem.events.pageobject.SelectedByValueEvent;


@Mapping(tag = "select", attribute = "!multiple")
public class SingleSelect extends GenericSelect<SingleSelect> {

    private static final Logger logger = LoggerFactory.getLogger(Select.class);

    private static final String NO_EXISTING_OPTIONS_TEXT =
        "Could not return selected option's visible text since there are no options!";
    private static final String NO_EXISTING_OPTIONS_VALUE =
        "Could not return selected option's value since there are no options!";
    private static final String NO_EXISTING_OPTIONS_INDEX =
        "Could not return selected option's index since there are no options!";

    /**
     * Select an option by its visible text. If no parameter is given this function
     * will simply deselect the selected option.
     *
     * @param text the text of the option which should be selected
     * @throws NoSuchElementException if there is no option with the given text
     * @see GenericSelect
     * @since 1.2
     */
    public void selectByText(final String text) {
        executeAction(new PageObjectCallback() {

            @Override
            public void execute(PageObject pageObject) {
                getSelect().selectByVisibleText(text);
                logger.debug(logMessage("selected option with text: {}"), text);
                fireEventAndMarkAsUsed(new SelectedByTextEvent(pageObject, text));
            }
        });
    }

    /**
     * Select an option by its value. If no parameter is given this function
     * will simply deselect the selected option.
     *
     * @param value the value of the option which should be selected
     * @throws NoSuchElementException if there is no option with the given value
     * @see GenericSelect
     * @since 1.2
     */
    public void selectByValue(final String value) {
        executeAction(new PageObjectCallback() {

            @Override
            public void execute(PageObject pageObject) {
                getSelect().selectByValue(value);
                logger.debug(logMessage("selected option with value: {}"), value);
                fireEventAndMarkAsUsed(new SelectedByValueEvent(pageObject, value));
            }
        });
    }

    /**
     * Select an option by its index. If no parameter is give this function
     * will simply deselect the selected option.
     *
     * @param index the index ot the option which should be selected
     * @throws NoSuchElementException if there is no option with the give index
     * @see GenericSelect
     * @since 1.2
     */
    public void selectByIndex(final Integer index) {
        executeAction(new PageObjectCallback() {

            @Override
            public void execute(PageObject pageObject) {
                getSelect().selectByIndex(index);
                logger.debug(logMessage("selected option with index: {}"), index);
                fireEventAndMarkAsUsed(new SelectedByIndexEvent(pageObject, index));
            }
        });
    }

    /**
     * Returns the visible text of the selected option. If nothing is selected the
     * text of the first option is returned by default.
     *
     * @return the visible text of the selected option
     * @throws NullPointerException if the {@link SingleSelect single select} is empty
     * @since 1.2
     */
    public String getSelectionText() {
        return executeAction(new PageObjectCallbackWithReturnValue<String>() {

            @Override
            public String execute(PageObject arg) {
                try {
                    String text = getSelectedOption().getText();
                    return text;
                } catch (NoSuchElementException e){
                    logger.warn(logMessage(NO_EXISTING_OPTIONS_TEXT));
                    return null;
                }

            }
        });
    }

    /**
     * Returns the value of the selected option. If nothing is selected the value
     * of the first option is returned by default.
     *
     * @return the value of the selected option
     * @throws NullPointerException if the {@link SingleSelect single select} is empty
     * @since 1.2
     */
    public String getSelectionValue() {
        return executeAction(new PageObjectCallbackWithReturnValue<String>() {

            @Override
            public String execute(PageObject arg) {
                try {
                    String value = getSelectedOption().getAttribute("value");
                    return value;
                } catch (NoSuchElementException e){
                    logger.warn(logMessage(NO_EXISTING_OPTIONS_VALUE));
                    return null;
                }
            }
        });
    }

    /**
     * Returns the index of the selected option. If nothing is selected the index
     * of the first option is returned by default.
     *
     * @return the index of the selected option
     * @throws NullPointerException if the {@link SingleSelect single select} is empty
     * @since 1.2
     */
    public Integer getSelectionIndex() {
        return executeAction(new PageObjectCallbackWithReturnValue<Integer>() {

            @Override
            public Integer execute(PageObject arg) {
                try {
                    String indexAsString = getSelectedOption().getAttribute("index");
                    return Integer.valueOf(indexAsString);
                } catch (NoSuchElementException e){
                    logger.warn(logMessage(NO_EXISTING_OPTIONS_INDEX));
                    return null;
                }
            }
        });
    }

    public WebElement getSelectedOption() {
        return getSelect().getFirstSelectedOption();
    }
}
