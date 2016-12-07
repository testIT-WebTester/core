package info.novatec.testit.webtester.support.assertj;

import static info.novatec.testit.webtester.support.assertj.WebTesterAssertions.assertThat;
import static org.mockito.Mockito.doReturn;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import info.novatec.testit.webtester.pageobjects.GenericTextField;


@RunWith(MockitoJUnitRunner.class)
public class GenericTextFieldAssertTest {

    static final String TEXT = "foo";
    static final String ANOTHER_TEXT = "bar";

    @Mock
    GenericTextField genericTextField;

    @Before
    public void setUp() {
        doReturn(TEXT).when(genericTextField).getText();
    }

    /* has text */
    @Test
    public void hasTextTrueTest() {
        assertThat(genericTextField).hasText(TEXT);
    }

    @Test (expected = AssertionError.class)
    public  void hasTextFalseTest() {
        assertThat(genericTextField).hasText(ANOTHER_TEXT);

    }

    @Test
    public void hasNotTextTrueTest() {
        assertThat(genericTextField).hasNotText(ANOTHER_TEXT);

    }

    @Test (expected = AssertionError.class)
    public void hasNotTextFalseTest() {
        assertThat(genericTextField).hasNotText(TEXT);

    }

}
