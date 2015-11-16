package integration.annotations;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.sameInstance;

import java.util.Iterator;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebElement;

import integration.AbstractWebTesterIntegrationTest;

import info.novatec.testit.webtester.api.annotations.IdentifyUsing;
import info.novatec.testit.webtester.api.enumerations.Caching;
import info.novatec.testit.webtester.api.enumerations.Method;
import info.novatec.testit.webtester.pageobjects.PageObject;
import info.novatec.testit.webtester.pageobjects.TextField;


/**
 * This test verifies the caching behavior of page objects. It is divided into several sub classes of tests:
 * <p>
 * <ol>
 * <li>Testing the default behavior when caching is globally enabled {@link EnabledGlobally}</li>
 * <li>Testing the default behavior when caching is globally disabled {@link DisabledGlobally}</li>
 * <li>Testing the override behavior when caching is globally disabled but locally enabled {@link EnabledLocally}</li>
 * <li>Testing the override behavior when caching is globally enabled but locally disabled {@link DisabledLocally}</li>
 * </ol>
 */
@RunWith(Enclosed.class)
public class CachingIntegrationTest {

    public static class EnabledGlobally extends AbstractGlobalCachingTest {

        @Override
        boolean globalCacheIsActive() {
            return true;
        }

        @Test
        public void everyCallToGetWebElementReturnsSameWebElementInstance() {

            WebElement firstGetInstance = page.single.getWebElement();
            WebElement secondGetInstance = page.single.getWebElement();

            assertThat(firstGetInstance, is(sameInstance(secondGetInstance)));

        }

        @Test
        public void everyInteractionWithListReturnsSamePageObjectInstances() {

            Iterator<TextField> firstIterator = page.multiple.iterator();
            Iterator<TextField> secondIterator = page.multiple.iterator();

            assertIteratorsContainSameInstancesOfSameElements(firstIterator, secondIterator);

        }

    }

    public static class DisabledGlobally extends AbstractGlobalCachingTest {

        @Override
        boolean globalCacheIsActive() {
            return false;
        }

        @Test
        public void everyInteractionWithPageObjectReturnsNewWebElementInstance() {

            WebElement firstGetInstance = page.single.getWebElement();
            WebElement secondGetInstance = page.single.getWebElement();

            assertThat(firstGetInstance, is(not(sameInstance(secondGetInstance))));

        }

        @Test
        public void everyInteractionWithPageObjectListReturnsNewPageObjectInstances() {

            Iterator<TextField> firstIterator = page.multiple.iterator();
            Iterator<TextField> secondIterator = page.multiple.iterator();

            assertIteratorsContainDifferentInstancesOfSameElements(firstIterator, secondIterator);

        }

    }

    public static class EnabledLocally extends AbstractGlobalCachingTest {

        @Override
        boolean globalCacheIsActive() {
            return false;
        }

        @Test
        public void everyInteractionWithPageObjectReturnsSameWebElementInstance() {

            WebElement firstGetInstance = page.singleWithCache.getWebElement();
            WebElement secondGetInstance = page.singleWithCache.getWebElement();

            assertThat(firstGetInstance, is(sameInstance(secondGetInstance)));

        }

        @Test
        public void everyInteractionWithPageObjectListReturnsSamePageObjectInstances() {

            Iterator<TextField> firstIterator = page.multipleWithCache.iterator();
            Iterator<TextField> secondIterator = page.multipleWithCache.iterator();

            assertIteratorsContainSameInstancesOfSameElements(firstIterator, secondIterator);

        }

    }

    public static class DisabledLocally extends AbstractGlobalCachingTest {

        @Override
        boolean globalCacheIsActive() {
            return true;
        }

        @Test
        public void everyInteractionWithPageObjectReturnsNewWebElementInstance() {

            WebElement firstGetInstance = page.singleWithoutCache.getWebElement();
            WebElement secondGetInstance = page.singleWithoutCache.getWebElement();

            assertThat(firstGetInstance, is(not(sameInstance(secondGetInstance))));

        }

        @Test
        public void everyInteractionWithPageObjectListReturnsNewPageObjectInstances() {

            Iterator<TextField> firstIterator = page.multipleWithoutCache.iterator();
            Iterator<TextField> secondIterator = page.multipleWithoutCache.iterator();

            assertIteratorsContainDifferentInstancesOfSameElements(firstIterator, secondIterator);

        }

    }

    public static abstract class AbstractGlobalCachingTest extends AbstractWebTesterIntegrationTest {

        GlobalCachingTestPage page;

        @Override
        protected final String getHTMLFilePath() {
            return "html/annotations/caching.html";
        }

        @Before
        public final void initPage() {
            getBrowser().getConfiguration().setPageObjectCacheActive(globalCacheIsActive());
            page = getBrowser().create(GlobalCachingTestPage.class);
        }

        @After
        public final void restoreGlobalCaching() {
            getBrowser().getConfiguration().setPageObjectCacheActive(true);
        }

        boolean globalCacheIsActive() {
            return true;
        }

        final void assertIteratorsContainSameInstancesOfSameElements(Iterator<TextField> firstIterator,
            Iterator<TextField> secondIterator) {
            int counter = 0;
            while (firstIterator.hasNext() && secondIterator.hasNext()) {
                counter++;
                TextField firstElement = firstIterator.next();
                TextField secondElement = secondIterator.next();
                assertThat(firstElement.getText(), is(equalTo(secondElement.getText())));
                assertThat(firstElement, is(sameInstance(secondElement)));
            }
            assertThat("There were no elements in the iterators!", counter > 0);
        }

        final void assertIteratorsContainDifferentInstancesOfSameElements(Iterator<TextField> firstIterator,
            Iterator<TextField> secondIterator) {
            int counter = 0;
            while (firstIterator.hasNext() && secondIterator.hasNext()) {
                counter++;
                TextField firstElement = firstIterator.next();
                TextField secondElement = secondIterator.next();
                assertThat(firstElement.getText(), is(equalTo(secondElement.getText())));
                assertThat(firstElement, is(not(sameInstance(secondElement))));
            }
            assertThat("There were no elements in the iterators!", counter > 0);
        }

        public static class GlobalCachingTestPage extends PageObject {

            @IdentifyUsing(method = Method.ID, value = "textfield", caching = Caching.DEFAULT)
            TextField single;

            @IdentifyUsing(method = Method.CLASS_NAME, value = "multiple", caching = Caching.DEFAULT)
            List<TextField> multiple;

            @IdentifyUsing(method = Method.ID, value = "textfield", caching = Caching.ON)
            TextField singleWithCache;
            @IdentifyUsing(method = Method.ID, value = "textfield", caching = Caching.OFF)
            TextField singleWithoutCache;

            @IdentifyUsing(method = Method.CLASS_NAME, value = "multiple", caching = Caching.ON)
            List<TextField> multipleWithCache;
            @IdentifyUsing(method = Method.CLASS_NAME, value = "multiple", caching = Caching.OFF)
            List<TextField> multipleWithoutCache;

        }

    }

}
