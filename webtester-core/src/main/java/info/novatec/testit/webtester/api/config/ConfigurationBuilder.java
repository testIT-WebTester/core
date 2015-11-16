package info.novatec.testit.webtester.api.config;

import java.util.Collection;


/**
 * Implementations of this interface are used to build {@link Configuration
 * configurations}.
 * <p>
 * The builder pattern is used to allow for the dynamic construction of
 * configurations by providing {@link ConfigurationAdapter adapters}. These
 * adapter allow for the use of different property sources when building the
 * configuration. In addition {@link ConfigurationExporter exporters} can be
 * declared as well. Exporters are used to propagate changes to properties of
 * the configuration to other interested system.
 * <p>
 * The order in which adapters and exporters are defined is the same order in
 * which they will be executed on the configuration!
 * <p>
 * <b>Here an examples of how to initialize a configuration using a builder:</b>
 * <br>
 * <code>new ConfigurationBuilderImpl().withAdapters(adapter1, adapter2).withExporter(exporter).build();</code>
 * <br>
 * Creates a new configuration by using two adapters to change properties and
 * one exporter to propagate those changes to another component.
 *
 * @see Configuration
 * @see ConfigurationAdapter
 * @see ConfigurationExporter
 * @since 0.9.7
 */
public interface ConfigurationBuilder {

    /**
     * Adds the given {@link ConfigurationAdapter adapter} to the pool of
     * adapters to use when building the {@link Configuration configuration}.
     *
     * @param adapterToAdd the adapter to add
     * @return the same builder instance for fluent API
     * @since 0.9.7
     */
    ConfigurationBuilder withAdapter(ConfigurationAdapter adapterToAdd);

    /**
     * Adds the given {@link ConfigurationAdapter adapters} to the pool of
     * adapters to use when building the {@link Configuration configuration}.
     *
     * @param adaptersToAdd the adapters to add
     * @return the same builder instance for fluent API
     * @since 0.9.7
     */
    ConfigurationBuilder withAdapters(ConfigurationAdapter... adaptersToAdd);

    /**
     * Adds the given {@link ConfigurationAdapter adapters} to the pool of
     * adapters to use when building the {@link Configuration configuration}.
     *
     * @param adaptersToAdd the adapters to add
     * @return the same builder instance for fluent API
     * @since 0.9.7
     */
    ConfigurationBuilder withAdapters(Collection<ConfigurationAdapter> adaptersToAdd);

    /**
     * Adds the given {@link ConfigurationExporter exporter} to the pool of
     * exporters to use when building the {@link Configuration configuration}.
     *
     * @param exporterToAdd the exporter to add
     * @return the same builder instance for fluent API
     * @since 0.9.7
     */
    ConfigurationBuilder withExporter(ConfigurationExporter exporterToAdd);

    /**
     * Adds the given {@link ConfigurationExporter exporters} to the pool of
     * exporters to use when building the {@link Configuration configuration}.
     *
     * @param exportersToAdd the exporters to add
     * @return the same builder instance for fluent API
     * @since 0.9.7
     */
    ConfigurationBuilder withExporters(ConfigurationExporter... exportersToAdd);

    /**
     * Adds the given {@link ConfigurationExporter exporters} to the pool of
     * exporters to use when building the {@link Configuration configuration}.
     *
     * @param exportersToAdd the exporters to add
     * @return the same builder instance for fluent API
     * @since 0.9.7
     */
    ConfigurationBuilder withExporters(Collection<ConfigurationExporter> exportersToAdd);

    /**
     * Builds the new {@link Configuration configuration} instance.
     * <p>
     * All {@link ConfigurationAdapter adapters} are used to construct the
     * configuration after which all {@link ConfigurationExporter exporters} are
     * informed about the new configuration properties.
     * <p>
     * Each call to this method will create a new configuration instance based
     * on the current pool of adapters and exporters. So it is possible to reuse
     * a builder if necessary.
     *
     * @return the new configuration instance
     * @see #withAdapter(ConfigurationAdapter)
     * @see #withExporter(ConfigurationExporter)
     * @since 0.9.7
     */
    Configuration build();

}
