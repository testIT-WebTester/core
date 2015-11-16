package info.novatec.testit.webtester.config.adapters;

import java.io.IOException;
import java.net.URL;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import info.novatec.testit.webtester.api.config.Configuration;
import info.novatec.testit.webtester.api.config.ConfigurationAdapter;


/**
 * Base implementation of a {@link ConfigurationAdapter configuration adapter}
 * using a {@link Properties properties} file from the classpath as its source.
 * If the file doesn't exist the adaptation will be canceled and a warning will
 * be logged.
 *
 * @see ConfigurationAdapter
 * @see AbstractPropertiesConfigurationAdapter
 * @since 0.9.7
 */
public class ClasspathPropertiesFileConfigurationAdapter extends AbstractPropertiesConfigurationAdapter {

    private static final Logger logger = LoggerFactory.getLogger(ClasspathPropertiesFileConfigurationAdapter.class);

    private String propertyFilePath;

    public ClasspathPropertiesFileConfigurationAdapter(String propertyFilePath) {
        this.propertyFilePath = propertyFilePath;
    }

    @Override
    public boolean adapt(Configuration configuration) {

        logger.debug("loading properties from classpath resource: {}", propertyFilePath);

        URL resource = getClass().getClassLoader().getResource(propertyFilePath);
        if (resource == null) {
            logger.warn("Could not load configuration file! {} file is not on the classpath.", propertyFilePath);
            return false;
        }

        Properties properties = new Properties();

        try {
            loadPropertiesFromResource(resource, properties);
        } catch (IOException e) {
            logger.error("exception while loading property file " + propertyFilePath, e);
            return false;
        }

        logger.debug("...merging with current configuration...");
        copyInto(properties, configuration);
        logger.debug("finished loading properties from classpath resource: {}", propertyFilePath);

        return true;

    }

}
