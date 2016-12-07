package info.novatec.testit.webtester.support.assertj;

import info.novatec.testit.webtester.pageobjects.GenericTextField;
import info.novatec.testit.webtester.pageobjects.TextField;


/**
 * Contains assertions for {@link TextField text fields}.
 *
 * @since 0.9.8
 */
public class TextFieldAssert extends AbstractTextFieldAssert<TextFieldAssert, GenericTextField> {

    public TextFieldAssert(TextField actual) {
        super(actual, TextFieldAssert.class);
    }

}
