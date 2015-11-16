package info.novatec.testit.webtester.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import info.novatec.testit.webtester.api.config.Configuration;
import info.novatec.testit.webtester.api.config.ConfigurationAdapter;
import info.novatec.testit.webtester.api.config.ConfigurationBuilder;
import info.novatec.testit.webtester.api.config.ConfigurationExporter;


/**
 * This is a {@link ConfigurationBuilder builder} used to construct
 * {@link BaseConfiguration base configuration} instances. No default
 * {@link ConfigurationAdapter adapters} or {@link ConfigurationExporter
 * exporters} are set at any time.
 *
 * @see Configuration
 * @see BaseConfiguration
 * @see ConfigurationAdapter
 * @see ConfigurationExporter
 * @since 0.9.7
 */
public class BaseConfigurationBuilder implements ConfigurationBuilder {

    private static final Logger logger = LoggerFactory.getLogger(BaseConfigurationBuilder.class);

    private List<ConfigurationAdapter> adapters = new LinkedList<ConfigurationAdapter>();
    private List<ConfigurationExporter> exporters = new LinkedList<ConfigurationExporter>();

    public BaseConfigurationBuilder() {
        logger.trace("started the build of a new configuration");
    }

    @Override
    public ConfigurationBuilder withAdapter(ConfigurationAdapter adapterToAdd) {
        adapters.add(adapterToAdd);
        logger.trace("added adapter to builder: {}", adapterToAdd);
        return this;
    }

    @Override
    public ConfigurationBuilder withAdapters(ConfigurationAdapter... adaptersToAdd) {
        return withAdapters(Arrays.asList(adaptersToAdd));
    }

    @Override
    public ConfigurationBuilder withAdapters(Collection<ConfigurationAdapter> adaptersToAdd) {
        adapters.addAll(adaptersToAdd);
        logger.trace("added adapters to builder: {}", adaptersToAdd);
        return this;
    }

    @Override
    public ConfigurationBuilder withExporter(ConfigurationExporter exporterToAdd) {
        exporters.add(exporterToAdd);
        logger.trace("added exporter to builder: {}", exporterToAdd);
        return this;
    }

    @Override
    public ConfigurationBuilder withExporters(ConfigurationExporter... exportersToAdd) {
        return withExporters(Arrays.asList(exportersToAdd));
    }

    @Override
    public ConfigurationBuilder withExporters(Collection<ConfigurationExporter> exportersToAdd) {
        exporters.addAll(exportersToAdd);
        logger.trace("added exporters to builder: {}", exportersToAdd);
        return this;
    }

    @Override
    public Configuration build() {
        logger.trace("building new configuration using: {}", Configuration.class);
        BaseConfiguration configuration = new BaseConfiguration();
        adaptConfiguration(configuration);
        exportConfiguration(configuration);
        return configuration;
    }

    private void adaptConfiguration(BaseConfiguration configuration) {
        logger.trace("adapting configuration using: {}", adapters);
        for (ConfigurationAdapter adapter : adapters) {
            adapter.adapt(configuration);
            logger.trace("adapted configuration using: {}", adapter);
        }
    }

    private void exportConfiguration(BaseConfiguration configuration) {

        logger.trace("exporting properties of configuration using: {}", exporters);

        /* set exporters on configuration to keep being up to date with
         * configuration changes */
        configuration.addExporters(exporters);

        /* export finished configuration once through all exporters */
        Object value;
        for (String key : sortedKeyList(configuration)) {
            value = configuration.getProperty(key);
            for (ConfigurationExporter exporter : exporters) {
                exporter.export(key, value);
            }
        }

    }

    private List<String> sortedKeyList(Configuration configuration) {
        List<String> keyList = new ArrayList<String>(configuration.getKeys());
        Collections.sort(keyList);
        return keyList;
    }

}
