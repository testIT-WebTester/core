package info.novatec.testit.webtester.support.hamcrest;

import static info.novatec.testit.webtester.support.hamcrest.GenericListMatcher.hasNumberOfItems;
import static info.novatec.testit.webtester.support.hamcrest.GenericListMatcher.isEmpty;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.doReturn;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import info.novatec.testit.webtester.pageobjects.GenericList;

@RunWith(MockitoJUnitRunner.class)
public class GenericListMatcherTest {
    static final int NUMBER = 2;

    static final int OTHER_NUMBER = 4;

    @Mock
    GenericList emptyList;

    @Mock
    GenericList genericList;

    @Before
    public void setUp() {
        doReturn(NUMBER).when(genericList).getNumberOfItems();
        doReturn(true).when(emptyList).isEmpty();
    }

    /*  number of items */

    @Test
    public void hasNumberOfItemsTrueTest() {
        assertThat(genericList, hasNumberOfItems(NUMBER));
    }

    @Test(expected = AssertionError.class)
    public void hasNumberOfItemsFalseTest() {
        assertThat(genericList, hasNumberOfItems(OTHER_NUMBER));
    }

    @Test
    public void notHasNumberOfItemsTrueTest() {
        assertThat(genericList, not(hasNumberOfItems(OTHER_NUMBER)));
    }

    @Test(expected = AssertionError.class)
    public void notHasNumberOfItemsFalseTest() {
        assertThat(genericList, not(hasNumberOfItems(NUMBER)));
    }

    /*  empty   */

    @Test
    public void isEmptyTrueTest() {
        assertThat(emptyList, isEmpty());
    }

    @Test(expected = AssertionError.class)
    public void isEmptyFalseTest() {
        assertThat(genericList, isEmpty());
    }

    @Test
    public void notIsEmptyTrueTest() {
        assertThat(genericList, not(isEmpty()));
    }

    @Test(expected = AssertionError.class)
    public void notIsEmptyFalseTest() {
        assertThat(emptyList, not(isEmpty()));
    }


}
