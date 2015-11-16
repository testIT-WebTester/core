package info.novatec.testit.webtester.config.exporters;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import info.novatec.testit.webtester.api.config.ConfigurationExporter;


/**
 * This {@link ConfigurationExporter} exports changed properties as system
 * properties using {@link System#setProperty(String, String)}.
 *
 * @see ConfigurationExporter
 * @since 0.9.7
 */
public class SystemPropertyConfigurationExporter implements ConfigurationExporter {

    private static final Logger logger = LoggerFactory.getLogger(SystemPropertyConfigurationExporter.class);

    @Override
    public void export(String key, Object value) {
        String stringValue = value != null ? String.valueOf(value) : null;
        if (StringUtils.isNotBlank(key) && StringUtils.isNotBlank(stringValue)) {
            System.setProperty(key, String.valueOf(value));
            logger.debug("exposed configuration key '{}' with value '{}' as system property", key, value);
        }
    }

}
