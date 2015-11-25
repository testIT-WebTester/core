package info.novatec.testit.webtester.junit.runner.internal;

import java.lang.reflect.Field;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class ClassTestBrowser extends AbstractTestBrowser {

    private static final Logger LOGGER = LoggerFactory.getLogger(ClassTestBrowser.class);

    public ClassTestBrowser(Field browserField) {
        super(browserField);
    }

    public void beforeClass() throws ReflectiveOperationException {
        LOGGER.debug("beforeClass");
        createBrowserAndSetStaticField();
    }

    @Override
    public void beforeTest() {
        LOGGER.debug("beforeTest");
        openEntryPointIfSet();
    }

    @Override
    public void afterTest() {
        // nothing yet
        LOGGER.debug("afterTest");
    }

    public void afterClass() {
        closeBrowser();
        LOGGER.debug("afterClass");
    }

}
