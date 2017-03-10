package info.novatec.testit.webtester.testng.listener.internal;

import info.novatec.testit.webtester.internal.ReflectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.lang.reflect.UndeclaredThrowableException;


public class MethodTestBrowser extends AbstractTestBrowser {

    private static final Logger LOGGER = LoggerFactory.getLogger(MethodTestBrowser.class);

    private Object target;

    public MethodTestBrowser(Field browserField, Object target) {
        super(browserField);
        this.target = target;
    }

    @Override
    public void beforeTest() throws ReflectiveOperationException {
        LOGGER.debug("beforeTest");
        createBrowserIfNecessary(target);
        openEntryPointIfSet();
    }

    @Override
    public void afterTest() {
        try {
            closeBrowser();
        } finally {
            setBrowserFieldValueToNull();
        }
        LOGGER.debug("afterTest");
    }

    private void setBrowserFieldValueToNull() {
        try {
            ReflectionUtils.forceSetField(getField(), target, null);
        } catch (ReflectiveOperationException e) {
            throw new UndeclaredThrowableException(e);
        }
    }

}
