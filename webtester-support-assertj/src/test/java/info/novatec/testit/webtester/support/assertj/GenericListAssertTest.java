package info.novatec.testit.webtester.support.assertj;

import static info.novatec.testit.webtester.support.assertj.WebTesterAssertions.assertThat;
import static org.mockito.Mockito.doReturn;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import info.novatec.testit.webtester.pageobjects.GenericList;

@RunWith(MockitoJUnitRunner.class)
public class GenericListAssertTest {

    static final int NUMBER = 2;
    static final int OTHER_NUMBER = 4;

    @Mock
    GenericList emptyList;

    @Mock
    GenericList genericList;

    @Before
    public void SetUp() {
        doReturn(true).when(emptyList).isEmpty();
        doReturn(NUMBER).when(genericList).getNumberOfItems();
    }

    /* number of items  */

    @Test
    public final void hasNumberOfItemsTrueTest() {
        assertThat(genericList).hasNumberOfItems(NUMBER);
    }

    @Test (expected = AssertionError.class)
    public final void hasNumberOfItemsFalseTest() {
        assertThat(genericList).hasNumberOfItems(OTHER_NUMBER);
    }

    @Test
    public final void hasNotNumberOfItemsTrueTest() {
        assertThat(genericList).hasNotNumberOfItems(OTHER_NUMBER);
    }

    @Test (expected = AssertionError.class)
    public final void hasNotNumberOfItemsFalseTest() {
        assertThat(genericList).hasNotNumberOfItems(NUMBER);
    }

    /*  empty   */

    @Test
    public final void isEmptyTrueTest() {
        assertThat(emptyList).isEmpty();
    }

    @Test(expected = AssertionError.class)
    public final void isEmptyFalseTest() {
        assertThat(genericList).isEmpty();
    }

    @Test
    public final void isNotEmptyTrueTest() {
        assertThat(genericList).isNotEmpty();
    }

    @Test (expected = AssertionError.class)
    public final void isNotEmptyFalseTest() {
        assertThat(emptyList).isNotEmpty();
    }
}
