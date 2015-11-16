package info.novatec.testit.webtester.utils.conditions;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static utils.TestObjectFactory.select;

import org.junit.Test;

import info.novatec.testit.webtester.pageobjects.Select;


public class SelectedTextsTest {

    static final String TXT1 = "text1";
    static final String TXT2 = "text2";
    static final String TXT3 = "text3";

    SelectedTexts cut = new SelectedTexts(TXT1, TXT2);

    @Test
    public void testThatExactlyMatchingSelectedTextsEvaluatesToTrue() {
        Select select = select().withSelectedTexts(TXT1, TXT2).build();
        assertThat(cut.apply(select), is(true));
    }

    @Test
    public void testThatMoreSelectedTextsThenCheckedEvaluatesToTrue() {
        Select select = select().withSelectedTexts(TXT1, TXT2, TXT3).build();
        assertThat(cut.apply(select), is(true));
    }

    @Test
    public void testThatLessSelectedTextsThenCheckedEvaluatesToFalse() {
        Select select = select().withSelectedTexts(TXT1).build();
        assertThat(cut.apply(select), is(false));
    }

    @Test
    public void testThatDifferentSelectedTextsThenCheckedEvaluatesToFalse() {
        Select select = select().withSelectedTexts(TXT1, TXT3).build();
        assertThat(cut.apply(select), is(false));
    }

    @Test
    public void testThatNoSelectedTextsEvaluatesToFalse() {
        Select select = select().withoutSelectedTexts().build();
        assertThat(cut.apply(select), is(false));
    }

}
