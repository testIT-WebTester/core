package info.novatec.testit.webtester.support.assertj;

import static info.novatec.testit.webtester.support.assertj.WebTesterAssertions.assertThat;
import static org.mockito.Mockito.doReturn;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import info.novatec.testit.webtester.pageobjects.List;


@RunWith(MockitoJUnitRunner.class)
public class ListAssertTest {

    static final int NUMBER = 3;
    static final int ANOTHER_NUMBER = 5;

    @Mock
    List emptyList;
    @Mock
    List list;

    @Before
    public void setUp() {
        doReturn(NUMBER).when(list).getNumberOfItems();
        doReturn(true).when(emptyList).isEmpty();
    }

    /* hasNumberOfItems */

    @Test
    public final void hasNumberOfItemsTrueTest() {
        assertThat(list).hasNumberOfItems(NUMBER);
    }

    @Test(expected = AssertionError.class)
    public final void hasNumberOfItemsFalseTest() {
        assertThat(list).hasNumberOfItems(ANOTHER_NUMBER);
    }

    @Test
    public final void notHasNumberOfItemsTrueTest() {
        assertThat(list).hasNotNumberOfItems(ANOTHER_NUMBER);
    }

    @Test(expected = AssertionError.class)
    public final void notHasNumberOfItemsFalseTest() {
        assertThat(list).hasNotNumberOfItems(NUMBER);
    }

    /* empty */

    @Test
    public final void emptyTrueTest() {
        assertThat(emptyList).isEmpty();
    }

    @Test(expected = AssertionError.class)
    public final void emptyFalseTest() {
        assertThat(list).isEmpty();
    }

    @Test
    public final void notEmptyTrueTest() {
        assertThat(list).isNotEmpty();
    }

    @Test(expected = AssertionError.class)
    public final void notEmptyFalseTest() {
        assertThat(emptyList).isNotEmpty();
    }

}
