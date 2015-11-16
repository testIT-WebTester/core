package info.novatec.testit.webtester.support.hamcrest;

import static info.novatec.testit.webtester.support.hamcrest.ListMatcher.empty;
import static info.novatec.testit.webtester.support.hamcrest.ListMatcher.hasNumberOfItems;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsNot.not;
import static org.mockito.Mockito.doReturn;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import info.novatec.testit.webtester.pageobjects.List;


@RunWith(MockitoJUnitRunner.class)
public class ListMatcherTest {

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
        assertThat(list, hasNumberOfItems(NUMBER));
    }

    @Test(expected = AssertionError.class)
    public final void hasNumberOfItemsFalseTest() {
        assertThat(list, hasNumberOfItems(ANOTHER_NUMBER));
    }

    @Test
    public final void notHasNumberOfItemsTrueTest() {
        assertThat(list, not(hasNumberOfItems(ANOTHER_NUMBER)));
    }

    @Test(expected = AssertionError.class)
    public final void notHasNumberOfItemsFalseTest() {
        assertThat(list, not(hasNumberOfItems(NUMBER)));
    }

    /* empty */

    @Test
    public final void emptyTrueTest() {
        assertThat(emptyList, empty());
    }

    @Test(expected = AssertionError.class)
    public final void emptyFalseTest() {
        assertThat(list, empty());
    }

    @Test
    public final void notEmptyTrueTest() {
        assertThat(list, not(empty()));
    }

    @Test(expected = AssertionError.class)
    public final void notEmptyFalseTest() {
        assertThat(emptyList, not(empty()));
    }

}
