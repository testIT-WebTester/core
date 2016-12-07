package info.novatec.testit.webtester.support.hamcrest;

import info.novatec.testit.webtester.pageobjects.GenericTextField;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static info.novatec.testit.webtester.support.hamcrest.GenericTextFieldMatcher.hasText;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.not;
import static org.mockito.Mockito.doReturn;

@RunWith(MockitoJUnitRunner.class)
public class GenericTextFieldMatcherTest {

    static final String TEXT = "foo";

    static final String ANOTHER_TEXT = "bar";

    @Mock
    GenericTextField genericTextField;

    @Before
    public void setUp(){
        doReturn(TEXT).when(genericTextField).getText();
    }

    @Test
    public void hasTextTrueTest(){
        assertThat(genericTextField, hasText(TEXT));
    }

    @Test (expected = AssertionError.class)
    public void hasTextFalseTest(){
        assertThat(genericTextField, hasText(ANOTHER_TEXT));
    }

    @Test
    public void notHasTextTrueTest(){
        assertThat(genericTextField, not(hasText(ANOTHER_TEXT)));
    }

    @Test (expected = AssertionError.class)
    public void notHasTextFalseTest(){
        assertThat(genericTextField, not(hasText(TEXT)));
    }
}
