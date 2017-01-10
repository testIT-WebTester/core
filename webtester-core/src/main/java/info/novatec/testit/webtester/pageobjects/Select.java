package info.novatec.testit.webtester.pageobjects;

import java.util.LinkedList;
import java.util.List;

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
import info.novatec.testit.webtester.utils.Asserts;
import info.novatec.testit.webtester.pageobjects.utils.EnhancedSelect;


/**
 * Represents a HTML select (single and multi). The following HTML elements are
 * supported:
 * <ul>
 * <li><b>tag:</b> select</li>
 * </ul>
 *
 * @since 0.9.0
 */
@Mapping(tag = "select")
public class Select extends PageObject {

    private static final Logger logger = LoggerFactory.getLogger(Select.class);

    private static final String NOTHING_SELECTED_TEXT =
        "Could not return selected option's visible text since there is nothing selected!";
    private static final String NOTHING_SELECTED_VALUE =
        "Could not return selected option's value since there is nothing selected!";
    private static final String NOTHING_SELECTED_INDEX =
        "Could not return selected option's index since there is nothing selected!";

    /**
     * Selects all options by their visible text. Giving no texts as parameters
     * will simply deselect all options. If this {@link Select select} is not a
     * multi-select field and more than one text is given, every text will be
     * selected in sequence.
     *
     * @param texts the texts of the options which should be selected
     * @throws NoSuchElementException in case there is no option with the given
     * text(s)
     * @since 0.9.0
     */
    public void selectByText(final String... texts) {
        executeAction(new PageObjectCallback() {

            @Override
            public void execute(PageObject pageObject) {
                Asserts.assertEnabledAndVisible(pageObject);
                deselectIfMultiple();
                for (String text : texts) {
                    getSelect().selectByVisibleText(text);
                    logger.debug(logMessage("selected option with text: {}"), text);
                    fireEventAndMarkAsUsed(new SelectedByTextEvent(pageObject, text));
                }
            }

        });
    }

    /**
     * Selects all options by their value. Giving no value as parameters will
     * simply deselect all options. If this {@link Select select} is not a
     * multi-select field and more than one value is given, every value will be
     * selected in sequence.
     *
     * @param values the values of the options which should be selected
     * @throws NoSuchElementException in case there is no option with the given
     * value(s)
     * @since 0.9.0
     */
    public void selectByValue(final String... values) {
        executeAction(new PageObjectCallback() {

            @Override
            public void execute(PageObject pageObject) {
                Asserts.assertEnabledAndVisible(pageObject);
                deselectIfMultiple();
                EnhancedSelect select = getSelect();
                for (String value : values) {
                    select.selectByValue(value);
                    logger.debug(logMessage("selected option with value: {}"), value);
                    fireEventAndMarkAsUsed(new SelectedByValueEvent(pageObject, value));
                }
            }

        });
    }

    /**
     * Selects all options by their index. Giving no index as parameters will
     * simply deselect all options. If this {@link Select select} is not a
     * multi-select field and more than one index is given, every index will be
     * selected in sequence.
     *
     * @param indices the indices of the options which should be selected
     * @throws NoSuchElementException in case there is no option with the given
     * index/indices
     * @since 0.9.0
     */
    public void selectByIndex(final int... indices) {
        executeAction(new PageObjectCallback() {

            @Override
            public void execute(PageObject pageObject) {
                Asserts.assertEnabledAndVisible(pageObject);
                deselectIfMultiple();
                EnhancedSelect select = getSelect();
                for (int index : indices) {
                    select.selectByIndex(index);
                    logger.debug(logMessage("selected option with index: {}"), index);
                    fireEventAndMarkAsUsed(new SelectedByIndexEvent(pageObject, index));
                }
            }

        });
    }

    /**
     * Returns the first selected option's visible text. If nothing is selected
     * <code>null</code> is returned.
     *
     * @return the visible text of the first selected option.
     * @since 0.9.0
     */
    public String getFirstSelectedText() {
        return executeAction(new PageObjectCallbackWithReturnValue<String>() {

            @Override
            public String execute(PageObject pageObject) {
                try {
                    pageObject.markAsRead();
                    return getFirstSelectedOption().getText();
                } catch (NoSuchElementException e) {
                    logger.warn(logMessage(NOTHING_SELECTED_TEXT));
                    return null;
                }
            }

        });
    }

    /**
     * Returns all selected option's visible texts. If nothing is selected the
     * returned list will be empty.
     *
     * @return the visible texts of all selected options.
     * @since 0.9.0
     */
    public List<String> getAllSelectedTexts() {
        return executeAction(new PageObjectCallbackWithReturnValue<List<String>>() {

            @Override
            public List<String> execute(PageObject pageObject) {
                pageObject.markAsRead();
                List<String> selectedTexts = new LinkedList<String>();
                for (WebElement option : getAllSelectedOptions()) {
                    selectedTexts.add(option.getText());
                }
                return selectedTexts;
            }

        });
    }

    /**
     * Returns the first selected option's value. If nothing is selected
     * <code>null</code> is returned.
     *
     * @return the value of the first selected option.
     * @since 0.9.0
     */
    public String getFirstSelectedValue() {
        return executeAction(new PageObjectCallbackWithReturnValue<String>() {

            @Override
            public String execute(PageObject pageObject) {
                try {
                    pageObject.markAsRead();
                    return getFirstSelectedOption().getAttribute("value");
                } catch (NoSuchElementException e) {
                    logger.warn(logMessage(NOTHING_SELECTED_VALUE));
                    return null;
                }
            }

        });
    }

    /**
     * Returns all selected option's values. If nothing is selected the returned
     * list will be empty.
     *
     * @return the values of all selected options.
     * @since 0.9.0
     */
    public List<String> getAllSelectedValues() {
        return executeAction(new PageObjectCallbackWithReturnValue<List<String>>() {

            @Override
            public List<String> execute(PageObject pageObject) {
                pageObject.markAsRead();
                List<String> selectedValues = new LinkedList<String>();
                for (WebElement option : getAllSelectedOptions()) {
                    selectedValues.add(option.getAttribute("value"));
                }
                return selectedValues;
            }

        });
    }

    /**
     * Returns the first selected option's index. If nothing is selected
     * <code>null</code> is returned.
     *
     * @return the index of the first selected option.
     * @since 0.9.0
     */
    public Integer getFirstSelectedIndex() {
        return executeAction(new PageObjectCallbackWithReturnValue<Integer>() {

            @Override
            public Integer execute(PageObject pageObject) {
                try {
                    pageObject.markAsRead();
                    String indexAsString = getFirstSelectedOption().getAttribute("index");
                    return Integer.valueOf(indexAsString);
                } catch (NoSuchElementException e) {
                    logger.warn(logMessage(NOTHING_SELECTED_INDEX));
                    return null;
                }
            }

        });
    }

    /**
     * Returns all selected option's indices. If nothing is selected the
     * returned list will be empty.
     *
     * @return the indices of all selected options.
     * @since 0.9.0
     */
    public List<Integer> getAllSelectedIndices() {
        return executeAction(new PageObjectCallbackWithReturnValue<List<Integer>>() {

            @Override
            public List<Integer> execute(PageObject pageObject) {
                pageObject.markAsRead();
                List<Integer> selectedIndices = new LinkedList<Integer>();
                for (WebElement option : getAllSelectedOptions()) {
                    String indexAsString = option.getAttribute("index");
                    selectedIndices.add(Integer.valueOf(indexAsString));
                }
                return selectedIndices;
            }

        });
    }

    /**
     * Returns all available option's visible texts. If there are no options the
     * returned list will be empty. The returned list is in the same order as
     * the options of the {@link Select select}. It can therefore be used to
     * calculate the index of a specific option by visible text.
     *
     * @return the visible texts of all available options.
     * @since 0.9.0
     */
    public List<String> getAllTexts() {
        return executeAction(new PageObjectCallbackWithReturnValue<List<String>>() {

            @Override
            public List<String> execute(PageObject pageObject) {
                pageObject.markAsRead();
                List<String> texts = new LinkedList<String>();
                for (WebElement option : getAllOptions()) {
                    texts.add(option.getText());
                }
                return texts;
            }

        });
    }

    /**
     * Returns all available option's values. If there are no options the
     * returned list will be empty. The returned list is in the same order as
     * the options of the {@link Select select}. It can therefore be used to
     * calculate the index of a specific option by value.
     *
     * @return the values of all available options.
     * @since 0.9.0
     */
    public List<String> getAllValues() {
        return executeAction(new PageObjectCallbackWithReturnValue<List<String>>() {

            @Override
            public List<String> execute(PageObject pageObject) {
                pageObject.markAsRead();
                List<String> values = new LinkedList<String>();
                for (WebElement option : getAllOptions()) {
                    values.add(option.getAttribute("value"));
                }
                return values;
            }

        });
    }

    /**
     * Returns the number of selected options.
     *
     * @return the number of selected options.
     * @since 0.9.0
     */
    public Integer getNumberOfSelectedOptions() {
        return executeAction(new PageObjectCallbackWithReturnValue<Integer>() {

            @Override
            public Integer execute(PageObject pageObject) {
                return getAllSelectedOptions().size();
            }

        });
    }

    /**
     * Returns the number of available options.
     *
     * @return the number of all available options.
     * @since 0.9.0
     */
    public Integer getNumberOfOptions() {
        return executeAction(new PageObjectCallbackWithReturnValue<Integer>() {

            @Override
            public Integer execute(PageObject pageObject) {
                return getAllOptions().size();
            }

        });
    }

    /**
     * Returns whether or not this {@link Select select} is a multi-select where
     * more then one option can be selected at any given time.
     *
     * @return true if it is, false otherwise
     * @since 0.9.0
     */
    public Boolean isMultiSelect() {
        return executeAction(new PageObjectCallbackWithReturnValue<Boolean>() {

            @Override
            public Boolean execute(PageObject pageObject) {
                return getSelect().isMultiple();
            }

        });
    }

    private EnhancedSelect getSelect() {
        return new EnhancedSelect(getWebElement());
    }

    private List<WebElement> getAllOptions() {
        return getSelect().getOptions();
    }

    private List<WebElement> getAllSelectedOptions() {
        return getSelect().getAllSelectedOptions();
    }

    private WebElement getFirstSelectedOption() {
        return getSelect().getFirstSelectedOption();
    }

    private void deselectIfMultiple() {
        if (isMultiSelect()) {
            getSelect().deselectAll();
        }
    }

}
