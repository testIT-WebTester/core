package info.novatec.testit.webtester.pageobjects;

import org.openqa.selenium.WebElement;


/**
 * Represents a HTML text area. The following HTML elements are supported:
 * <ul>
 * <li><b>tag:</b> textarea</li>
 * </ul>
 *
 * @since 0.9.0
 */
public class TextArea extends TextField {

    /**
     * Returns the number of columns of this {@link TextArea text area}. If no
     * column limit is set <code>-1</code> is returned!
     * <p>
     * <b>Note:</b> The case where no limit is set might not occur in real live
     * scenarios because Selenium and or the Browser is returning a default
     * value if no limit is set.
     *
     * @return the number of columns for the text area
     * @since 0.9.0
     */
    public Integer getColumnCount() {
        Integer columnCount = getAttributeAsInt("cols");
        return columnCount != null ? columnCount : -1;
    }

    /**
     * Returns the number of rows of this {@link TextArea text area}. If no row
     * limit is set <code>-1</code> is returned!
     * <p>
     * <b>Note:</b> The case where no limit is set might not occur in real live
     * scenarios because Selenium and or the Browser is returning a default
     * value if no limit is set.
     *
     * @return the number of rows for the text area
     * @since 0.9.0
     */
    public Integer getRowCount() {
        Integer rowCount = getAttributeAsInt("rows");
        return rowCount != null ? rowCount : -1;
    }

    @Override
    public TextArea clearText() {
        // Overridden for fluent API use
        super.clearText();
        return this;
    }

    @Override
    public TextArea setText(String textToSet) {
        // Overridden for fluent API use
        super.setText(textToSet);
        return this;
    }

    @Override
    public TextArea appendText(String textToAppend) {
        // Overridden for fluent API use
        super.appendText(textToAppend);
        return this;
    }

    @Override
    protected boolean isCorrectClassForWebElement(WebElement webElement) {
        return "textarea".equalsIgnoreCase(webElement.getTagName());
    }

}
