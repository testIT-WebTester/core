package info.novatec.testit.webtester.utils;

import org.junit.Test;
import org.mockito.InjectMocks;

import info.novatec.testit.webtester.AbstractPageObjectTest;
import info.novatec.testit.webtester.api.exceptions.PageObjectIsDisabledException;
import info.novatec.testit.webtester.api.exceptions.PageObjectIsInvisibleException;
import info.novatec.testit.webtester.pageobjects.PageObject;


public class AssertsTest extends AbstractPageObjectTest {

    @InjectMocks
    PageObject pageObject;

    /* enabled */

    @Test
    public void testAssertEnabled_IsEnabled_NoException() {
        elementIsEnabled();
        Asserts.assertEnabled(pageObject);
    }

    @Test(expected = PageObjectIsDisabledException.class)
    public void testAssertEnabled_IsNotEnabled_Exception() {
        elementIsDisabled();
        Asserts.assertEnabled(pageObject);
    }

    /* visible */

    @Test
    public void testAssertVisible_IsVisible_NoException() {
        elementIsVisible();
        Asserts.assertVisible(pageObject);
    }

    @Test(expected = PageObjectIsInvisibleException.class)
    public void testAssertVisible_IsNotVisible_Exception() {
        elementIsInvisible();
        Asserts.assertVisible(pageObject);
    }

    /* enabled and visible */

    @Test
    public void testAssertEnabledAndVisible_IsEnabledAndVisible_NoException() {
        elementIsVisibleAndEnabled();
        Asserts.assertEnabledAndVisible(pageObject);
    }

    @Test(expected = PageObjectIsDisabledException.class)
    public void testAssertEnabledAndVisible_IsNotEnabledButVisible_Exception() {
        elementIsDisabled();
        elementIsVisible();
        Asserts.assertEnabledAndVisible(pageObject);
    }

    @Test(expected = PageObjectIsInvisibleException.class)
    public void testAssertEnabledAndVisible_IsEnabledButNotVisible_Exception() {
        elementIsEnabled();
        elementIsInvisible();
        Asserts.assertEnabledAndVisible(pageObject);
    }

}
