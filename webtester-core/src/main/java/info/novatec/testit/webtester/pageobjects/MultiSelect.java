package info.novatec.testit.webtester.pageobjects;

import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import info.novatec.testit.webtester.api.annotations.Mapping;
import info.novatec.testit.webtester.api.callbacks.PageObjectCallback;
import info.novatec.testit.webtester.eventsystem.events.pageobject.SelectedByIndexEvent;
import info.novatec.testit.webtester.eventsystem.events.pageobject.SelectedByTextEvent;
import info.novatec.testit.webtester.eventsystem.events.pageobject.SelectedByValueEvent;
import info.novatec.testit.webtester.utils.Asserts;


@Mapping(tag = "select", attribute = "multiple")
public class MultiSelect extends GenericSelect<MultiSelect> {

    private static final Logger logger = LoggerFactory.getLogger(Select.class);

    /**
     * Selects all options with the given texts. If no value is given,
     *
     * @param texts the texts of the options which should be selected
     * @throws NoSuchElementException in case there is no option with the
     * given text(s)
     * @since 1.2
      */
    public void selectByText(final String... texts) {
        executeAction(new PageObjectCallback() {

            @Override
            public void execute(PageObject pageObject) {
                Asserts.assertEnabledAndVisible(pageObject);
                for (String text : texts) {
                    getSelect().selectByVisibleText(text);
                    logger.debug("selected option with text: {}", text);
                    fireEventAndMarkAsUsed(new SelectedByTextEvent(pageObject, text));
                }
            }

        });
    }

    /**
     * Selects all options with the given values.
     *
     * @param values the values of the options which should be selected
     * @throws NoSuchElementException in case there is no option with the
     * given value(s)
     * @since 1.2
     */
    public void selectByValues(final String... values) {
        executeAction(new PageObjectCallback() {

            @Override
            public void execute(PageObject pageObject) {
                Asserts.assertEnabledAndVisible(pageObject);
                for (String value : values) {
                    getSelect().selectByValue(value);
                    logger.debug("selected option with value: {}", value);
                    fireEventAndMarkAsUsed(new SelectedByValueEvent(pageObject, value));
                }
            }

        });
    }

    /**
     * Selects all options with the given indices.
     *
     * @param indices the indices of the options which should be selected
     * @throws NoSuchElementException in case there is no option with the
     * give index/indices
     * @since 1.2
     */
    public void selectByIndices(final Integer... indices) {
        executeAction(new PageObjectCallback() {

            @Override
            public void execute(PageObject pageObject) {
                Asserts.assertEnabledAndVisible(pageObject);
                for (Integer index : indices) {
                    getSelect().selectByIndex(index);
                    logger.debug("selected option with index: {}", index);
                    fireEventAndMarkAsUsed(new SelectedByIndexEvent(pageObject, index));
                }
            }

        });
    }

    /**
     * Deselects all options.
     *
     * @since 1.2
     */
    public void deselectAll() {
        this.markAsUsed();
        getSelect().deselectAll();
    }

    /**
     * Deselects all options with the given texts.
     *
     * @param texts the texts of the option which should be deselected
     * @throws NoSuchElementException in case there is no option with the
     * given text(s)
     * @since 1.2
     */
    public void deselectByTexts(final String... texts) {
        this.markAsUsed();
        for (String text : texts) {
            getSelect().deselectByVisibleText(text);
        }
    }

    /**
     *  Deselects all options with the given values.
     *
     *  @param values the values of the options which shoudl be deselected
     *  @throws NoSuchElementException in case there is no option with the
     *  give value(s)
     *  @since 1.2
     */
    public void deselectByValues(final String... values) {
        this.markAsUsed();
        for (String value : values) {
            getSelect().deselectByValue(value);
        }
    }

    /**
     * Deselects all options with the given indices.
     *
     * @param indices the indices of the options which should be deselected
     * @throws NoSuchElementException in case there is no option with the
     * give index/indices
     * @since 1.2
     */
    public void deselectByIndices(final Integer... indices) {
        this.markAsUsed();
        for (Integer index : indices) {
            getSelect().deselectByIndex(index);
        }
    }

    /**
     * Returns a {@link List linked list} of all selected option's visible texts.
     * If nothing is selected
     *
     * @return the visible texts of all selected options
     * @since 1.2
     */
    public List<String> getAllSelectedTexts() {
        List<WebElement> allSelectedOptions = getAllSelectedOptions();
        List<String> allSelectedTexts = new LinkedList<>();
        for (WebElement option : allSelectedOptions) {
            allSelectedTexts.add(option.getText());
        }
        this.markAsRead();
        return allSelectedTexts;
    }

    /**
     * Return a {@link List linked list} of all selected option's value.
     * If nothing is selected...
     *
     * @return the values of all selected options
     * @since 1.2
     */
    public List<String> getAllSelectedValues() {
        List<WebElement> allSelectedOptions = getAllSelectedOptions();
        List<String> allSelectedValues = new LinkedList<>();
        for (WebElement option : allSelectedOptions) {
            allSelectedValues.add(option.getAttribute("value"));
        }
        this.markAsRead();
        return allSelectedValues;
    }

    /**
     * Returns a {@link List linked list} of all selected option's indices.
     * If nothing is selected...
     *
     * @return the indices of all selected options
     * @since 1.2
     */
    public List<Integer> getAllSelectedIndices() {
        List<WebElement> allSelectedOptions = getAllSelectedOptions();
        List<Integer> allSelectedIndices = new LinkedList<>();
        for (WebElement option : allSelectedOptions) {
            String index = option.getAttribute("index");
            allSelectedIndices.add(Integer.valueOf(index));
        }
        this.markAsRead();
        return allSelectedIndices;
    }


    public List<WebElement> getAllSelectedOptions() {
        return getSelect().getAllSelectedOptions();
    }

    public Integer getNumberOfSelectedOptions() {
        return getAllSelectedOptions().size();
    }
}
