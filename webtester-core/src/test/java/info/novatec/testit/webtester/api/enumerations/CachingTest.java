package info.novatec.testit.webtester.api.enumerations;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

import info.novatec.testit.webtester.api.config.Configuration;
import info.novatec.testit.webtester.config.BaseConfiguration;


@RunWith(Enclosed.class)
public class CachingTest {

    public static class GlobalCacheActivated {

        Configuration cache = new BaseConfiguration().setPageObjectCacheActive(true);

        @Test
        public void defaultEvaluatesToTrue() {
            assertThat(Caching.shouldCache(cache, Caching.DEFAULT), is(true));
        }

        @Test
        public void onEvaluatesToTrue() {
            assertThat(Caching.shouldCache(cache, Caching.ON), is(true));
        }

        @Test
        public void offEvaluatesToFalse() {
            assertThat(Caching.shouldCache(cache, Caching.OFF), is(false));
        }

    }

    public static class GlobalCacheDeactivated {

        Configuration noCache = new BaseConfiguration().setPageObjectCacheActive(false);

        @Test
        public void defaultEvaluatesToFalse() {
            assertThat(Caching.shouldCache(noCache, Caching.DEFAULT), is(false));
        }

        @Test
        public void onEvaluatesToTrue() {
            assertThat(Caching.shouldCache(noCache, Caching.ON), is(true));
        }

        @Test
        public void offEvaluatesToFalse() {
            assertThat(Caching.shouldCache(noCache, Caching.OFF), is(false));
        }

    }

}
