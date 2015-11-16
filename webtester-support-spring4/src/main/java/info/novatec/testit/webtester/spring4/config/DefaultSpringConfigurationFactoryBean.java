package info.novatec.testit.webtester.spring4.config;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;

import info.novatec.testit.webtester.api.config.Configuration;
import info.novatec.testit.webtester.config.DefaultConfigurationBuilder;
import info.novatec.testit.webtester.spring4.config.adapters.SpringEnvironmentConfigurationAdapter;


/**
 * This {@link FactoryBean factory bean} creates a default {@link Configuration
 * configuration} based on a {@link DefaultConfigurationBuilder default
 * configuration builder} and adding the
 * {@link SpringEnvironmentConfigurationAdapter spring environment adapter}.
 * <p>
 * The resulting configuration will be populated by using the following
 * adapters:
 * <ol>
 * <li>default adapters - see {@link DefaultConfigurationBuilder}</li>
 * <li>{@link SpringEnvironmentConfigurationAdapter}</li>
 * </ol>
 */
public class DefaultSpringConfigurationFactoryBean
    implements FactoryBean<Configuration>, InitializingBean, EnvironmentAware {

    private Environment environment;
    private Configuration configuration;

    @Override
    public void afterPropertiesSet() {
        configuration =
            new DefaultConfigurationBuilder().withAdapter(new SpringEnvironmentConfigurationAdapter(environment)).build();
    }

    @Override
    public Configuration getObject() {
        return configuration;
    }

    @Override
    public Class<Configuration> getObjectType() {
        return Configuration.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

}
