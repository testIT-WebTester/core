package info.novatec.testit.webtester.pageobjects;

import org.junit.Test;
import org.mockito.InjectMocks;

import info.novatec.testit.webtester.AbstractPageObjectTest;
import info.novatec.testit.webtester.api.exceptions.WrongElementClassException;


public class ParagraphTest extends AbstractPageObjectTest{

    @InjectMocks
    Paragraph cut;

    /* correctness of class */

    @Test
    public final void testCorrectnessOfClassForWebElement_paragraphTag() {
        stubWebElementTag("p");
        cut.validate(webElement);
    }

    @Test(expected = WrongElementClassException.class)
    public final void testCorrectnesssOfClassForWebElement_otherTag() {
        stubWebElementTag("other");
        cut.validate(webElement);
    }
}
