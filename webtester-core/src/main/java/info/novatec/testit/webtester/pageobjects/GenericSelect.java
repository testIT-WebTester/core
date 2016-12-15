package info.novatec.testit.webtester.pageobjects;

import org.openqa.selenium.WebElement;

import java.util.LinkedList;
import java.util.List;

import info.novatec.testit.webtester.api.annotations.Mapping;
import info.novatec.testit.webtester.api.callbacks.PageObjectCallbackWithReturnValue;
import info.novatec.testit.webtester.pageobjects.utils.EnhancedSelect;


@Mapping(tag = "select")
public class GenericSelect<T extends GenericSelect<T>> extends PageObject {

    /**
     * Returns a {@link List linked list} with the texts of all options of
     * a {@link GenericSelect generic select}. If there are no option the
     * returned list will be empty. The returned list is in the same order
     * as the options of the {@link GenericSelect generic select}.
     *
     * @return the visible texts of all available options
     * @since 1.2
     */
    public List<String> getAllOptionTexts() {
        return executeAction(new PageObjectCallbackWithReturnValue<List<String>>() {
            @Override
            public List<String> execute(PageObject arg) {
                List<String> texts = new LinkedList<>();
                for(WebElement option : getAllOptions()) {
                    texts.add(option.getText());
                }
                return texts;
            }
        });
    }

    /**
     * Returns a {@link List linked list} with all available option's values.
     * If there are no option the returned list will be empty. The returned
     * list is in the same order as the options of the
     * {@link GenericSelect generic select}.
     *
     * @return the values of all available options
     * @since 1.2
     */
    public List<String> getAllOptionValues() {
        return executeAction(new PageObjectCallbackWithReturnValue<List<String>>() {
            @Override
            public List<String> execute(PageObject arg) {
                List<String> values = new LinkedList<>();
                for(WebElement option : getAllOptions()) {
                    values.add(option.getAttribute("value"));
                }
                return values;
            }
        });
    }

    /**
     * Returns the number of all available options.
     *
     * @return the number of all available options
     * @since 1.2
     */
    public Integer getNumberOfOptions() {
        return executeAction(new PageObjectCallbackWithReturnValue<Integer>() {

            @Override
            public Integer execute(PageObject pageObject) {
                return getAllOptions().size();
            }

        });
    }

    public List<WebElement> getAllOptions() {
        return getSelect().getOptions();
    }

    public EnhancedSelect getSelect() {
        return new EnhancedSelect(getWebElement());
    }

}
