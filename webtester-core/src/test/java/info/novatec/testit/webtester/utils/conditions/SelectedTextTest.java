package info.novatec.testit.webtester.utils.conditions;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static utils.TestObjectFactory.select;

import org.junit.Test;

import info.novatec.testit.webtester.pageobjects.Select;


public class SelectedTextTest {

    static final String TXT1 = "text1";
    static final String TXT2 = "text2";
    static final String TXT3 = "text3";

    SelectedText cut = new SelectedText(TXT1);

    @Test
    public void testThatMatchingSelectedTextEvaluatesToTrue_SingleSelection() {
        Select select = select().withSelectedTexts(TXT1).build();
        assertThat(cut.apply(select), is(true));
    }

    @Test
    public void testThatMatchingSelectedTextEvaluatesToTrue_MultiSelection() {
        Select select = select().withSelectedTexts(TXT2, TXT1).build();
        assertThat(cut.apply(select), is(true));
    }

    @Test
    public void testThatNotMatchingSelectedTextEvaluatesToFalse() {
        Select select = select().withSelectedTexts(TXT2, TXT3).build();
        assertThat(cut.apply(select), is(false));
    }

    @Test
    public void testThatNoSelectedTextEvaluatesToFalse() {
        Select select = select().withoutSelectedTexts().build();
        assertThat(cut.apply(select), is(false));
    }

}
