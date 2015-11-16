package info.novatec.testit.webtester.spring4.config.adapters;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;

import info.novatec.testit.webtester.api.config.Configuration;
import info.novatec.testit.webtester.api.config.ConfigurationAdapter;


/**
 * This {@link ConfigurationAdapter configuration adapter} resolves all
 * currently set property keys from the given {@link Configuration
 * configuration} against the Spring context's {@link Environment environment}.
 * Those properties which could successfully be resolved will be adapted in the
 * configuration. Properties which could not be resolved will be ignored.
 * <p>
 * If the environment is not initialized the adaptation will be canceled with a
 * warning.
 *
 * @see Configuration
 * @see ConfigurationAdapter
 * @see Environment
 * @since 0.9.7
 */
public class SpringEnvironmentConfigurationAdapter implements ConfigurationAdapter, EnvironmentAware {

    private static final Logger logger = LoggerFactory.getLogger(SpringEnvironmentConfigurationAdapter.class);

    /**
     * The {@link Environment environment} to resolve properties against.
     */
    private Environment environment;

    public SpringEnvironmentConfigurationAdapter() {
    }

    public SpringEnvironmentConfigurationAdapter(Environment environment) {
        this.environment = environment;
    }

    @Override
    public boolean adapt(Configuration configuration) {

        if (environmentIsNotAvailable()) {
            logger.warn("Spring context environment is not available - no adaption of configuration executed!");
            return false;
        }

        return adaptConfigurationByResolvingExistingKeysAgainstTheEnvironment(configuration);

    }

    private boolean environmentIsNotAvailable() {
        return environment == null;
    }

    private boolean adaptConfigurationByResolvingExistingKeysAgainstTheEnvironment(Configuration configuration) {

        logger.info("Adapting configuration by resolving keys against the spring context environment...");

        String value;
        for (String key : configuration.getKeys()) {
            value = environment.getProperty(key);
            if (StringUtils.isNotBlank(value)) {
                configuration.setProperty(key, value);
                logger.debug("-- Adapted configuration by setting resolved property '{}' to '{}'.", key, value);
            } else {
                logger.debug("-- Property '{}' could not be resolved.", key);
            }
        }

        return true;

    }

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

}
