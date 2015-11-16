package info.novatec.testit.webtester.config.adapters;

import info.novatec.testit.webtester.api.config.Configuration;
import info.novatec.testit.webtester.api.config.ConfigurationAdapter;


/**
 * Loads a file called <code>testit-webtester-default.properties</code> from the
 * classpath's root level in order to {@link ConfigurationAdapter adapt}
 * {@link Configuration configurations}. If the file doesn't exist the
 * adaptation will be canceled and a warning will be logged.
 * <p>
 * This file is provided as part of the WebTester framework!
 *
 * @see ConfigurationAdapter
 * @see ClasspathPropertiesFileConfigurationAdapter
 * @since 0.9.7
 */
public class DefaultFileConfigurationAdapter extends ClasspathPropertiesFileConfigurationAdapter {

    private static final String PROPERTIES_FILE = "testit-webtester-default.properties";

    public DefaultFileConfigurationAdapter() {
        super(PROPERTIES_FILE);
    }

}
