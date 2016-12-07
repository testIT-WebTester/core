package info.novatec.testit.webtester.support.assertj;

import info.novatec.testit.webtester.pageobjects.GenericTextField;


/**
 * Contains Assertions for {@link GenericTextField text fields}.
 *
 * @since 1.2
 */
public class GenericTextFieldAssert extends AbstractTextFieldAssert<GenericTextFieldAssert, GenericTextField> {

    public GenericTextFieldAssert(GenericTextField actual) {
        super(actual, GenericTextFieldAssert.class);
    }
}
