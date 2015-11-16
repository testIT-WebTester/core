package utils;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.openqa.selenium.NoSuchElementException;

import info.novatec.testit.webtester.api.pageobjects.traits.HasFileName;
import info.novatec.testit.webtester.api.pageobjects.traits.HasLabel;
import info.novatec.testit.webtester.api.pageobjects.traits.HasSourcePath;
import info.novatec.testit.webtester.api.pageobjects.traits.HasText;
import info.novatec.testit.webtester.api.pageobjects.traits.HasValue;
import info.novatec.testit.webtester.api.pageobjects.traits.Selectable;
import info.novatec.testit.webtester.pageobjects.PageObject;
import info.novatec.testit.webtester.pageobjects.Select;


public class TestObjectFactory {

    public static PageObjectMockBuilder pageObject() {
        return new PageObjectMockBuilder();
    }

    public static SelectMockBuilder select() {
        return new SelectMockBuilder();
    }

    public static HasFileName hasFileName(String fileName) {
        HasFileName hasFileName = mock(HasFileName.class);
        doReturn(fileName).when(hasFileName).getFileName();
        return hasFileName;
    }

    public static HasLabel hasLabel(String label) {
        HasLabel hasLabel = mock(HasLabel.class);
        doReturn(label).when(hasLabel).getLabel();
        return hasLabel;
    }

    public static Selectable selectable(boolean selected) {
        Selectable selectable = mock(Selectable.class);
        doReturn(selected).when(selectable).isSelected();
        return selectable;
    }

    public static HasSourcePath hasSourcePath(String sourcePath) {
        HasSourcePath hasSourcePath = mock(HasSourcePath.class);
        doReturn(sourcePath).when(hasSourcePath).getSourcePath();
        return hasSourcePath;
    }

    public static HasText hasText(String text) {
        HasText hasText = mock(HasText.class);
        doReturn(text).when(hasText).getText();
        return hasText;
    }

    @SuppressWarnings("unchecked")
    public static <T> HasValue<T> hasValue(T value) {
        HasValue<T> hasValue = mock(HasValue.class);
        doReturn(value).when(hasValue).getValue();
        return hasValue;
    }

    public static class PageObjectMockBuilder {

        private Map<String, String> attributes = new HashMap<String, String>();
        private String visibleText = "";
        private boolean isEnabled = true;
        private boolean isVisible = true;
        private boolean isReadOnly;
        private Throwable getWebElementException;

        public PageObjectMockBuilder withAttribute(String attribute, String value) {
            attributes.put(attribute, value);
            return this;
        }

        public PageObjectMockBuilder withoutAttribute(String attribute) {
            attributes.remove(attribute);
            return this;
        }

        public PageObjectMockBuilder withVisibleText(String text) {
            visibleText = text;
            return this;
        }

        public PageObjectMockBuilder editable() {
            return enabled().visible().writable();
        }

        public PageObjectMockBuilder enabled() {
            isEnabled = true;
            return this;
        }

        public PageObjectMockBuilder disabled() {
            isEnabled = false;
            return this;
        }

        public PageObjectMockBuilder visible() {
            isVisible = true;
            return this;
        }

        public PageObjectMockBuilder invisible() {
            isVisible = false;
            return this;
        }

        public PageObjectMockBuilder readOnly() {
            isReadOnly = true;
            return this;
        }

        public PageObjectMockBuilder writable() {
            isReadOnly = false;
            return this;
        }

        public PageObjectMockBuilder throwsNoSuchElementException() {
            getWebElementException = new NoSuchElementException("test exception");
            return this;
        }

        public PageObject build() {

            PageObject pageObject = mock(PageObject.class);

            for (Entry<String, String> attribute : attributes.entrySet()) {
                doReturn(attribute.getValue()).when(pageObject).getAttribute(attribute.getKey());
            }

            doReturn(visibleText).when(pageObject).getVisibleText();

            doReturn(isEnabled).when(pageObject).isEnabled();
            doReturn(isVisible).when(pageObject).isVisible();
            doReturn(String.valueOf(isReadOnly)).when(pageObject).getAttribute("readonly");

            if (getWebElementException != null) {
                doThrow(getWebElementException).when(pageObject).getWebElement();
            }

            return pageObject;

        }

    }

    public static class SelectMockBuilder {

        private List<Integer> selectedIndices = new LinkedList<Integer>();
        private List<String> selectedValues = new LinkedList<String>();
        private List<String> selectedTexts = new LinkedList<String>();

        public SelectMockBuilder withSelectedIndices(Integer... indices) {
            selectedIndices.addAll(Arrays.asList(indices));
            return this;
        }

        public SelectMockBuilder withoutSelectedIndices() {
            selectedIndices.clear();
            return this;
        }

        public SelectMockBuilder withSelectedValues(String... values) {
            selectedValues.addAll(Arrays.asList(values));
            return this;
        }

        public SelectMockBuilder withoutSelectedValues() {
            selectedValues.clear();
            return this;
        }

        public SelectMockBuilder withSelectedTexts(String... texts) {
            selectedTexts.addAll(Arrays.asList(texts));
            return this;
        }

        public SelectMockBuilder withoutSelectedTexts() {
            selectedTexts.clear();
            return this;
        }

        public Select build() {

            Select select = mock(Select.class);

            if (selectedIndices.isEmpty()) {
                doReturn(null).when(select).getFirstSelectedIndex();
            } else {
                doReturn(selectedIndices.get(0)).when(select).getFirstSelectedIndex();
            }
            doReturn(selectedIndices).when(select).getAllSelectedIndices();

            if (selectedValues.isEmpty()) {
                doReturn(null).when(select).getFirstSelectedValue();
            } else {
                doReturn(selectedValues.get(0)).when(select).getFirstSelectedValue();
            }
            doReturn(selectedValues).when(select).getAllSelectedValues();

            if (selectedTexts.isEmpty()) {
                doReturn(null).when(select).getFirstSelectedText();
            } else {
                doReturn(selectedTexts.get(0)).when(select).getFirstSelectedText();
            }
            doReturn(selectedTexts).when(select).getAllSelectedTexts();

            return select;

        }

    }

}
