package info.novatec.testit.webtester.junit.runner.internal;

import java.lang.reflect.Field;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


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
        closeBrowser();
        LOGGER.debug("afterTest");
    }

}
