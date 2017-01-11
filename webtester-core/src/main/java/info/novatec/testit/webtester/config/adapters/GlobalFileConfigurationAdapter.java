package info.novatec.testit.webtester.config.adapters;

import info.novatec.testit.webtester.api.config.Configuration;
import info.novatec.testit.webtester.api.config.ConfigurationAdapter;


/**
 * Loads a file called <code>testit-webtester-global.properties</code> from the
 * classpath's root level in order to {@link ConfigurationAdapter adapt}
 * {@link Configuration configurations}. If the file doesn't exist the
 * adaptation will be canceled and a warning will be logged.
 * <p>
 * This file should be used to define common properties in an environment where
 * multiple test projects are using a common base.
 *
 * @see ConfigurationAdapter
 * @see ClasspathPropertiesFileConfigurationAdapter
 * @since 0.9.7
 */
public class GlobalFileConfigurationAdapter extends ClasspathPropertiesFileConfigurationAdapter {

    private static final String PROPERTIES_FILE = "testit-webtester-global.properties";

    public GlobalFileConfigurationAdapter() {
        super(PROPERTIES_FILE, Importance.OPTIONAL);
    }

}
