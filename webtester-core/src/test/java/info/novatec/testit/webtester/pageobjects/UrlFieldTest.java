package info.novatec.testit.webtester.pageobjects;

import info.novatec.testit.webtester.AbstractPageObjectTest;
import info.novatec.testit.webtester.api.exceptions.WrongElementClassException;
import org.junit.Test;
import org.mockito.InjectMocks;

public class UrlFieldTest extends AbstractPageObjectTest{

    @InjectMocks
    UrlField cut;

    @Test
    public final void testCorrectnessOfClassForWebElement_inputTag_urlFieldType() {
        stubWebElementTagAndType("input", "url");
        cut.validate(webElement);
    }

    @Test (expected = WrongElementClassException.class)
    public final void testCorrectnessOfClassForWebElement_nonInputTag() {
        stubWebElementTagAndType("other", null);
        cut.validate(webElement);
    }

    @Test (expected = WrongElementClassException.class)
    public final void testCorrectnessOfClassForWebElement_nonUrlFieldType() {
        stubWebElementTagAndType("input", "other");
        cut.validate(webElement);
    }
}
