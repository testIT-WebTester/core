package info.novatec.testit.webtester.config.adapters;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import info.novatec.testit.webtester.api.config.Configuration;
import info.novatec.testit.webtester.api.config.ConfigurationAdapter;
import info.novatec.testit.webtester.internal.TestITUtils;


/**
 * Loads a file called <code>testit-webtester.properties</code> from the
 * <code>$TESTIT_HOME/config</code> folder in order to
 * {@link ConfigurationAdapter adapt} {@link Configuration configurations}. If
 * the file doesn't exist the adaptation will be canceled.
 * <p>
 * This file should be used to define properties for the current machine / user.
 *
 * @see ConfigurationAdapter
 * @see AbstractPropertiesConfigurationAdapter
 * @since 0.9.7
 */
public class TestItHomeFileConfigurationAdapter extends AbstractPropertiesConfigurationAdapter {

    private static final Logger logger = LoggerFactory.getLogger(TestItHomeFileConfigurationAdapter.class);
    private static final String PROPERTIES_FILE = "testit-webtester.properties";

    @Override
    public boolean adapt(Configuration configuration) {

        File testITConfigFolder = TestITUtils.getTestITConfigFolder();
        File propertiesFile = new File(testITConfigFolder, PROPERTIES_FILE);
        if (!propertiesFile.isFile()) {
            return false;
        }

        logger.debug("loading properties from TESTIT_HOME configuration file: {}", propertiesFile);

        Properties properties = new Properties();

        try {
            loadPropertiesFromResource(propertiesFile, properties);
        } catch (IOException e) {
            logger.error("exception while loading property file " + propertiesFile, e);
            return false;
        }

        logger.debug("...merging with current configuration...");
        copyInto(properties, configuration);
        logger.debug("finished loading properties from TESTIT_HOME configuration file: {}", propertiesFile);

        return true;

    }

}
