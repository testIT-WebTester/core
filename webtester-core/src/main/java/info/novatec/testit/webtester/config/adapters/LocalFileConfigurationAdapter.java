package info.novatec.testit.webtester.config.adapters;

import info.novatec.testit.webtester.api.config.Configuration;
import info.novatec.testit.webtester.api.config.ConfigurationAdapter;


/**
 * Loads a file called <code>testit-webtester.properties</code> from the
 * classpath's root level in order to {@link ConfigurationAdapter adapt}
 * {@link Configuration configurations}. If the file doesn't exist the
 * adaptation will be canceled and a warning will be logged.
 * <p>
 * This file should be used to define properties for the current test project.
 *
 * @see ConfigurationAdapter
 * @see ClasspathPropertiesFileConfigurationAdapter
 * @since 0.9.7
 */
public class LocalFileConfigurationAdapter extends ClasspathPropertiesFileConfigurationAdapter {

    private static final String PROPERTIES_FILE = "testit-webtester.properties";

    public LocalFileConfigurationAdapter() {
        super(PROPERTIES_FILE);
    }

}
