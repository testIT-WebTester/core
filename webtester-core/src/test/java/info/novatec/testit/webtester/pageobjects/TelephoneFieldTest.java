package info.novatec.testit.webtester.pageobjects;

import info.novatec.testit.webtester.AbstractPageObjectTest;
import info.novatec.testit.webtester.api.exceptions.WrongElementClassException;
import org.junit.Test;
import org.mockito.InjectMocks;

public class TelephoneFieldTest extends AbstractPageObjectTest{

    @InjectMocks
    TelephoneField cut;

    @Test
    public final void testCorrectnessOfClassForWebElement_inputTag_telephoneFieldType() {
        stubWebElementTagAndType("input", "tel");
        cut.validate(webElement);
    }

    @Test (expected = WrongElementClassException.class)
    public final void testCorrectnessOfClassForWebElement_nonInputTag() {
        stubWebElementTagAndType("other", null);
        cut.validate(webElement);
    }

    @Test (expected = WrongElementClassException.class)
    public final void testCorrectnessOfClassForWebElement_nonTelephoneFieldType() {
        stubWebElementTagAndType("input", "other");
        cut.validate(webElement);
    }
}
