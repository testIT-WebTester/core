package info.novatec.testit.webtester.api.browser;

import org.openqa.selenium.WebDriver;

import info.novatec.testit.webtester.api.config.Configuration;
import info.novatec.testit.webtester.api.pageobjects.PageObjectFactory;
import info.novatec.testit.webtester.config.DefaultConfigurationBuilder;
import info.novatec.testit.webtester.internal.pageobjects.DefaultPageObjectFactory;


/**
 * Implementations of this interface are used to build {@link Browser browsers}.
 * <p>
 * The builder pattern is used to guarantee that sensible default services are
 * set when creating the instance. But still be open to modifications by
 * allowing you to change any of the browsers services before building the
 * instance.
 * <p>
 * The difference between a browser builder and a {@link BrowserFactory browser
 * factory} is in how they are used. Factories are used to initialize a
 * {@link WebDriver web driver} with a specific implementation and certain
 * capabilities and then packaging that driver as a browser using a builder.
 * Builders are used to wrap any web driver in a browser instance and set
 * certain service implementations needed for the browser abstraction to
 * function. In short:
 * <ul>
 * <li>Browser factory configures web driver and uses browser builder to
 * initialize browser</li>
 * <li>Browser builder configures browser (if needed with sensible default
 * services)</li>
 * </ul>
 * <p>
 * <b>Here are some examples of how to initialize a browser using a browser
 * builder:</b>
 * <ul>
 * <li><code>new BrowserBuilderImpl(...).build();</code> <br>
 * create browser with default services</li>
 * <li>
 * <code>new BrowserBuilderImpl(...).withConfiguration(configuration).build();</code>
 * <br>
 * create browser with specific {@link Configuration configuration}</li>
 * <li>
 * <code>new BrowserBuilderImpl(...).withPageObjectFactory(factory).build();</code>
 * <br>
 * create browser with specific {@link PageObjectFactory page object factory}
 * </li>
 * </ul>
 *
 * @see #withFactory(PageObjectFactory)
 * @see #withConfiguration(Configuration)
 * @see Browser
 * @see BrowserFactory
 * @see PageObjectFactory
 * @see Configuration
 * @since 0.9.6
 */
public interface BrowserBuilder {

    /**
     * Defines the {@link PageObjectFactory page object factory} to be used by
     * the created {@link Browser browser}. If this method is not called a
     * default factory is used.
     *
     * @param factory the factory to use
     * @return the same builder instance for fluent APi
     * @see DefaultPageObjectFactory
     * @since 0.9.6
     */
    BrowserBuilder withFactory(PageObjectFactory factory);

    /**
     * Defines the {@link Configuration configuration} to be used by the created
     * {@link Browser browser}. If this method is not called the
     * {@link DefaultConfigurationBuilder default configuration builder} is used
     * to initialize a default configuration.
     *
     * @param configuration the configuration to use
     * @return the same builder instance for fluent APi
     * @see DefaultConfigurationBuilder
     * @since 0.9.7
     */
    BrowserBuilder withConfiguration(Configuration configuration);

    /**
     * Builds the new {@link Browser browser} instance. All configured services
     * are set. If some services were not set, the will be initialized with
     * default implementations.
     * <p>
     * Each call to this method will create a new browser instance based on the
     * current set of services. So it is possible to reuse a builder if
     * necessary.
     *
     * @return the new browser instance
     * @see #withFactory(PageObjectFactory)
     * @see #withConfiguration(Configuration)
     * @since 0.9.6
     */
    Browser build();

}
