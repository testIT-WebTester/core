package info.novatec.testit.webtester.support.assertj;

import info.novatec.testit.webtester.pageobjects.*;
import org.assertj.core.api.Assertions;


/**
 * Entry point for assertion methods for different WebTester {@link PageObject
 * page object} types. Each method in this class is a static factory for the
 * type-specific assertion objects. The purpose of this class is to make test
 * code more readable.
 *
 * @since 0.9.8
 */
public final class WebTesterAssertions extends Assertions {

    public static ButtonAssert assertThat(Button actual) {
        return new ButtonAssert(actual);
    }

    public static CheckboxAssert assertThat(Checkbox actual) {
        return new CheckboxAssert(actual);
    }

    public static IFrameAssert assertThat(IFrame actual) {
        return new IFrameAssert(actual);
    }

    public static ImageAssert assertThat(Image actual) {
        return new ImageAssert(actual);
    }

    public static ListAssert assertThat(List actual) {
        return new ListAssert(actual);
    }

    public static NumberFieldAssert assertThat(NumberField actual) {
        return new NumberFieldAssert(actual);
    }

    public static PageObjectAssert assertThat(PageObject actual) {
        return new PageObjectAssert(actual);
    }

    public static RadioButtonAssert assertThat(RadioButton actual) {
        return new RadioButtonAssert(actual);
    }

    public static TableAssert assertThat(Table actual) {
        return new TableAssert(actual);
    }

    public static TableFieldAssert assertThat(TableField actual) {
        return new TableFieldAssert(actual);
    }

    public static TableRowAssert assertThat(TableRow actual) {
        return new TableRowAssert(actual);
    }

    public static TextAreaAssert assertThat(TextArea actual) {
        return new TextAreaAssert(actual);
    }

    public static TextFieldAssert assertThat(TextField actual) {
        return new TextFieldAssert(actual);
    }

    public static GenericTextFieldAssert assertThat(GenericTextField actual) {
        return new GenericTextFieldAssert(actual);
    }

    private WebTesterAssertions() {
        // utility constructor
    }

}
