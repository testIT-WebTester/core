package info.novatec.testit.webtester.utils;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;

import java.util.Map;
import java.util.TreeMap;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.openqa.selenium.WebDriverException;

import info.novatec.testit.webtester.api.browser.Browser;
import info.novatec.testit.webtester.api.enumerations.CSSProperties;
import info.novatec.testit.webtester.api.utils.CSSProperty;
import info.novatec.testit.webtester.pageobjects.PageObject;


@RunWith(MockitoJUnitRunner.class)
public class JavaScriptStyleChangerTest {

    @Mock
    PageObject pageObject;
    @Mock
    Browser browser;

    @InjectMocks
    JavaScriptStyleChanger cut;

    @Before
    public void stubPageObjectToReturnBrowser() {
        doReturn(browser).when(pageObject).getBrowser();
    }

    @Test
    public void testThatCorrectScriptIsBuildWhenChangingSingleProperty() {
        boolean styleWasChanged = changeSingleProperty();
        assertThat(styleWasChanged, is(true));
        verify(browser).executeJavaScript("arguments[0].style.backgroundColor='#214284';", pageObject);
    }

    @Test
    public void testThatExceptionsAreHandledWhenChangingSingleProperty() {
        throwExceptionOnJavaScriptExectution();
        boolean styleWasChanged = changeSingleProperty();
        assertThat(styleWasChanged, is(false));
    }

    private boolean changeSingleProperty() {
        return cut.changeStyleInformation(pageObject, CSSProperties.BACKGROUND_COLOR, "#214284");
    }

    @Test
    public void testThatCorrectScriptIsBuildWhenChangingMultipleProperties() {

        boolean styleWasChanged = changeMultipleProperties();
        assertThat(styleWasChanged, is(true));

        String expectedScript = "arguments[0].style.outlineColor='#214284';" + "arguments[0].style.outlineStyle='solid';"
            + "arguments[0].style.outlineWidth='2px';";
        verify(browser).executeJavaScript(expectedScript, pageObject);

    }

    @Test
    public void testThatExceptionsAreHandledWhenChangingMultipleProperties() {
        throwExceptionOnJavaScriptExectution();
        boolean styleWasChanged = changeMultipleProperties();
        assertThat(styleWasChanged, is(false));
    }

    private boolean changeMultipleProperties() {
        Map<CSSProperty, String> properties = new TreeMap<CSSProperty, String>();
        properties.put(CSSProperties.OUTLINE_COLOR, "#214284");
        properties.put(CSSProperties.OUTLINE_STYLE, "solid");
        properties.put(CSSProperties.OUTLINE_WIDTH, "2px");
        return cut.changeStyleInformation(pageObject, properties);
    }

    private void throwExceptionOnJavaScriptExectution() {
        doThrow(new WebDriverException()).when(browser).executeJavaScript(anyString(), any(PageObject.class));
    }

}
