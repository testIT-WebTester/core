package info.novatec.testit.webtester.support.assertj;

import static info.novatec.testit.webtester.support.assertj.WebTesterAssertions.assertThat;
import static org.mockito.Mockito.doReturn;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import info.novatec.testit.webtester.pageobjects.TextField;


@RunWith(MockitoJUnitRunner.class)
public class TextFieldAssertTest {

    static final String TEXT = "foo";
    static final String ANOTHER_TEXT = "bar";

    @Mock
    TextField textField;

    @Before
    public void setUp() {
        doReturn(TEXT).when(textField).getText();
    }

    /* hasText */

    @Test
    public void hasTextTrueTest() {
        assertThat(textField).hasText(TEXT);
    }

    @Test(expected = AssertionError.class)
    public void hasTextFalseTest() {
        assertThat(textField).hasText(ANOTHER_TEXT);
    }

    @Test
    public void notHasTextTrueTest() {
        assertThat(textField).hasNotText(ANOTHER_TEXT);
    }

    @Test(expected = AssertionError.class)
    public void notHasTextFalseTest() {
        assertThat(textField).hasNotText(TEXT);
    }

}
