package info.novatec.testit.webtester.pageobjects;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import org.junit.Test;
import org.mockito.InjectMocks;

import info.novatec.testit.webtester.AbstractPageObjectTest;


public class ListItemTest extends AbstractPageObjectTest {

    @InjectMocks
    ListItem cut;

    /* correctness of class */

    @Test
    public void testCorrectnessOfClassForWebElement_listItemTag() {
        stubWebElementTag("li");
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
