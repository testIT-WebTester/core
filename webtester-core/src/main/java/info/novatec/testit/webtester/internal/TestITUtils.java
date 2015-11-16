package info.novatec.testit.webtester.internal;

import java.io.File;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import info.novatec.testit.webtester.api.annotations.Internal;


@Internal
public final class TestITUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(TestITUtils.class);

    private static final String ENV_TESTIT_HOME = "TESTIT_HOME";
    private static final String PROPERTY_USER_HOME = "user.home";
    private static final String FOLDER_TESTIT = ".testit";

    private TestITUtils() {
    }

    @Internal
    public static File getOrCreateTestITHomeFolder() {

        File testITHomeFolder = getTestITHomeFolder();
        if (!testITHomeFolder.isDirectory() && testITHomeFolder.mkdirs()) {
            logInfo("created testIT home folder: %s", testITHomeFolder);
        }

        return testITHomeFolder;

    }

    @Internal
    public static File getTestITHomeFolder() {

        String testITHome = System.getenv(ENV_TESTIT_HOME);
        if (!StringUtils.isEmpty(testITHome)) {
            return new File(testITHome);
        }

        String userHome = System.getProperty(PROPERTY_USER_HOME);
        if (!StringUtils.isEmpty(userHome)) {
            File userHomeFolder = new File(userHome);
            return new File(userHomeFolder, FOLDER_TESTIT);
        }

        return new File("home");

    }

    @Internal
    public static File getTestITTempFolder() {
        File tempFolder = getTestITHomeSubFolder("temp");
        if (!tempFolder.isDirectory() && tempFolder.mkdirs()) {
            logInfo("created testIT temp folder: %s", tempFolder);
        }
        return tempFolder;
    }

    @Internal
    public static File getTestITConfigFolder() {
        File confFolder = getTestITHomeSubFolder("config");
        if (!confFolder.isDirectory() && confFolder.mkdirs()) {
            logInfo("created testIT configuration folder: %s", confFolder);
        }
        return confFolder;
    }

    @Internal
    public static File getTestITHomeSubFolder(String subFolderName) {
        return new File(getTestITHomeFolder(), subFolderName);
    }

    private static void logInfo(String msg, Object... args) {
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info(String.format(msg, args));
        }
    }

}
