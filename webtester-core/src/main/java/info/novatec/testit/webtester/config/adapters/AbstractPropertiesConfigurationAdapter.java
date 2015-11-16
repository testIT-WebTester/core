package info.novatec.testit.webtester.config.adapters;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Properties;

import com.google.common.base.Charsets;

import info.novatec.testit.webtester.api.config.Configuration;
import info.novatec.testit.webtester.api.config.ConfigurationAdapter;


/**
 * Abstract base implementation of a {@link ConfigurationAdapter} using a
 * {@link Properties properties} object as its source.
 *
 * @see ConfigurationAdapter
 * @since 0.9.7
 */
public abstract class AbstractPropertiesConfigurationAdapter implements ConfigurationAdapter {

    protected void loadPropertiesFromResource(File resource, Properties properties) throws IOException {
        loadPropertiesFromResource(resource.toURI().toURL(), properties);
    }

    protected void loadPropertiesFromResource(URL resource, Properties properties) throws IOException {
        InputStreamReader isr = null;
        try {
            isr = new InputStreamReader(resource.openStream(), Charsets.UTF_8);
            properties.load(isr);
        } finally {
            if (isr != null) {
                isr.close();
            }
        }
    }

    protected void copyInto(Properties source, Configuration target) {
        for (String key : source.stringPropertyNames()) {
            target.setProperty(key, source.getProperty(key));
        }
    }

}
