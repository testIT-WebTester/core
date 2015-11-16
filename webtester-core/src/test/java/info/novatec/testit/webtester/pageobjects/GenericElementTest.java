package info.novatec.testit.webtester.pageobjects;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

import org.junit.Test;
import org.mockito.InjectMocks;

import info.novatec.testit.webtester.AbstractPageObjectTest;


public class GenericElementTest extends AbstractPageObjectTest {

    @InjectMocks
    GenericElement cut;

    /* submit form */

    @Test
    public void testThatSubmittingAFormDelegatesToCorrectWebElementMethods() {

        cut.submit();

        verify(webElement).submit();
        verifyNoMoreInteractions(webElement);

    }

    /* send keys */

    @Test
    public void testThatSendingKeysDelegatesToCorrectWebElementMethods() {

        cut.sendKeys("foo", "bar");

        verify(webElement).sendKeys("foo", "bar");
        verifyNoMoreInteractions(webElement);

    }

    /* clear */

    @Test
    public void testThatClearingDelegatesToCorrectWebElementMethods() {

        cut.clear();

        verify(webElement).clear();
        verifyNoMoreInteractions(webElement);

    }

    /* is selected */

    @Test
    public void testThatCheckingSelectionStateDelegatesToCorrectWebElementMethods() {

        cut.isSelected();

        verify(webElement).isSelected();
        verifyNoMoreInteractions(webElement);

    }

    @Test
    public void testThatCheckingSelectionStateReturnsCorrectValue() {
        doReturn(true).when(webElement).isSelected();
        assertThat(cut.isSelected(), is(true));
    }

}
