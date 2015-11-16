package info.novatec.testit.webtester.spring4.config.adapters;

import static org.hamcrest.Matchers.is;

import org.hamcrest.MatcherAssert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import info.novatec.testit.webtester.api.config.Configuration;
import info.novatec.testit.webtester.config.BaseConfigurationBuilder;
import info.novatec.testit.webtester.config.adapters.LocalFileConfigurationAdapter;
import info.novatec.testit.webtester.spring4.config.adapters.SpringEnvironmentConfigurationAdapterIntegrationTest.SpringEnvironmentConfigurationAdapterTestConfiguration;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = SpringEnvironmentConfigurationAdapterTestConfiguration.class)
public class SpringEnvironmentConfigurationAdapterIntegrationTest {

    @org.springframework.context.annotation.Configuration
    @PropertySource("classpath:spring-environment.properties")
    public static class SpringEnvironmentConfigurationAdapterTestConfiguration {

        @Bean
        public Configuration configuration() {
            BaseConfigurationBuilder builder = new BaseConfigurationBuilder();
            builder.withAdapter(new LocalFileConfigurationAdapter());
            builder.withAdapter(springEnvironmentConfigurationAdapter());
            return builder.build();
        }

        @Bean
        public SpringEnvironmentConfigurationAdapter springEnvironmentConfigurationAdapter() {
            return new SpringEnvironmentConfigurationAdapter();
        }

    }

    @Autowired
    Configuration configuration;

    /**
     * This test verifies that properties from the configuration are correctly
     * resolved against the spring environment and the configuration is
     * correctly adapted if a property could be resolved against the
     * environment.
     */
    @Test
    public void testThatConfigurationIsAdaptedWithPropertiesFromSpringEnvironment() {
        MatcherAssert.assertThat(configuration.getStringProperty("test.property.key1"), is("value 1 from spring environment"));
        MatcherAssert.assertThat(configuration.getStringProperty("test.property.key2"), is("value 2 from webtester"));
    }

}
