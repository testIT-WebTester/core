package info.novatec.testit.webtester.pageobjects;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.doReturn;

import org.junit.Test;
import org.mockito.InjectMocks;

import info.novatec.testit.webtester.AbstractPageObjectTest;


public class TableFieldTest extends AbstractPageObjectTest {

    @InjectMocks
    TableField cut;

    /* header field */

    @Test
    public void testThatHeaderFieldsAreCorrectlyIdentified_IsHeaderField() {
        doReturn("th").when(webElement).getTagName();
        assertThat(cut.isHeaderField(), is(true));
    }

    @Test
    public void testThatHeaderFieldsAreCorrectlyIdentified_IsNotHeaderField() {
        doReturn("td").when(webElement).getTagName();
        assertThat(cut.isHeaderField(), is(false));
    }

    /* correctness of class */

    @Test
    public void testCorrectnessOfClassForWebElement_thTag() {
        stubWebElementTag("th");
        assertThatCorrectnessOfClassIs(true);
    }

    @Test
    public void testCorrectnessOfClassForWebElement_tdTag() {
        stubWebElementTag("td");
        assertThatCorrectnessOfClassIs(true);
    }

    @Test
    public void testCorrectnessOfClassForWebElement_otherTag() {
        stubWebElementTag("other");
        assertThatCorrectnessOfClassIs(false);
    }

    /* utilities */

    private void assertThatCorrectnessOfClassIs(boolean expected) {
        assertThat(cut.isCorrectClassForWebElement(webElement), is(expected));
    }

}
