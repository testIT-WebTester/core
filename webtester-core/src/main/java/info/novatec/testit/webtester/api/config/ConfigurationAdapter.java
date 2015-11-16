package info.novatec.testit.webtester.api.config;

/**
 * Classes implementing this interface can be used to adapt {@link Configuration
 * configurations}. This is generally done by a {@link ConfigurationBuilder
 * configuration builder} calling the {@link #adapt(Configuration)} method when
 * building a configuration.
 * <p>
 * This process allows for custom property sources (other then the defaults used
 * by the framework) to be included in the configuration of the framework.
 *
 * @see Configuration
 * @see ConfigurationBuilder
 * @since 0.9.7
 */
public interface ConfigurationAdapter {

    /**
     * Adapts the given {@link Configuration configuration} by changing
     * properties (named an unnamed) as needed.
     *
     * @param configuration the configuration to adapt
     * @return true if adaptation was executed, otherwise false
     * @since 0.9.7
     */
    boolean adapt(Configuration configuration);

}
