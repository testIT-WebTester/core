package info.novatec.testit.webtester.utils;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

import java.util.HashMap;
import java.util.Map;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import utils.StaticFieldReplacer;

import info.novatec.testit.webtester.api.enumerations.CSSProperties;
import info.novatec.testit.webtester.api.utils.CSSProperty;
import info.novatec.testit.webtester.api.utils.StyleChanger;
import info.novatec.testit.webtester.pageobjects.PageObject;


@RunWith(MockitoJUnitRunner.class)
public class VisibilityChangerTest {

    @Mock
    PageObject pageObject;

    /* The replace / restore mechanism is needed because we are replacing a
     * static service with a mock! If we don't restore the original service
     * other tests might fail because they are getting the mock instead of the
     * originally intended service. */

    StyleChanger styleChanger = mock(StyleChanger.class);

    @Rule
    public StaticFieldReplacer replacer = new StaticFieldReplacer(VisibilityChanger.class, "styleChanger", styleChanger);

    @Test
    public void testThatMakingAnObjectInvisibleSetsCorrectProperties() {

        VisibilityChanger.makeInvisible(pageObject);

        Map<CSSProperty, String> expectedProperties = new HashMap<CSSProperty, String>();
        expectedProperties.put(CSSProperties.DISPLAY, "none");
        expectedProperties.put(CSSProperties.VISIBILITY, "hidden");

        verify(styleChanger).changeStyleInformation(pageObject, expectedProperties);
        verifyNoMoreInteractions(styleChanger);

    }

    @Test
    public void testThatMakingAnObjectVisibleSetsCorrectProperties() {

        VisibilityChanger.makeVisible(pageObject);

        Map<CSSProperty, String> expectedProperties = new HashMap<CSSProperty, String>();
        expectedProperties.put(CSSProperties.DISPLAY, "inline");
        expectedProperties.put(CSSProperties.VISIBILITY, "visible");

        verify(styleChanger).changeStyleInformation(pageObject, expectedProperties);
        verifyNoMoreInteractions(styleChanger);

    }

}
