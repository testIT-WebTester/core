package info.novatec.testit.webtester.api.config;

/**
 * Classes implementing this interface can be used to export
 * {@link Configuration configurations}. This is useful in cases where you want
 * to synchronize changes to the WebTester configuration with another property
 * store like system properties.
 * <p>
 * This is generally done by a {@link ConfigurationBuilder configuration
 * builder} calling the {@link #export(String, Object)} method for each property
 * of the configuration when a configuration was build. These exporters are also
 * set as part of the final configuration to be informed of future changes to
 * properties.
 * <p>
 * This process allows for custom property stores to be informed about changes
 * to the configuration's properties.
 *
 * @see Configuration
 * @see ConfigurationBuilder
 * @since 0.9.7
 */
public interface ConfigurationExporter {

    /**
     * Informs the {@link ConfigurationExporter exporter} about changes to a
     * specific property. By providing the property's key and value.
     *
     * @param key the property's key
     * @param value the property's value
     * @since 0.9.7
     */
    void export(String key, Object value);

}
