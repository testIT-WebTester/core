package info.novatec.testit.webtester.utils;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.sameInstance;
import static org.mockito.Mockito.atMost;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.openqa.selenium.TimeoutException;

import com.google.common.base.Supplier;


@RunWith(MockitoJUnitRunner.class)
public class WaitsTest {

    @Mock
    Supplier<Boolean> supplier;

    @Test
    public void testGenericWaitUntil_GoodCase() {
        when(supplier.get()).thenReturn(false, false, true);
        Waits.waitUntil(100, TimeUnit.MILLISECONDS, 20, supplier);
        verify(supplier, times(3)).get();
    }

    @Test(expected = TimeoutException.class)
    public void testGenericWaitUntil_BadCase() {
        try {
            when(supplier.get()).thenReturn(false);
            Waits.waitUntil(50, TimeUnit.MILLISECONDS, 25, supplier);
        } finally {
            verify(supplier, atMost(2)).get();
        }
    }

    @Test
    public void testGenericWaitUntil_OriginalExceptionAsCauseOfTimeout() {
        Throwable expected = new IllegalStateException();
        try {
            when(supplier.get()).thenThrow(expected);
            Waits.waitUntil(50, TimeUnit.MILLISECONDS, 25, supplier);
            Assert.fail("exception not reached");
        } catch (TimeoutException e) {
            assertThat(e.getCause(), is(sameInstance(expected)));
        }
    }

}
