package info.novatec.testit.webtester.testng.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import info.novatec.testit.webtester.api.browser.Browser;
import info.novatec.testit.webtester.api.config.Configuration;
import info.novatec.testit.webtester.testng.listener.WebTesterTestNGListener;

/**
 * This annotation can be used to annotate fields in order to mark them for
 * configuration property injection by the {@link WebTesterTestNGListener
 * webtester testng listener}. The annotated fields will be resolved against
 * the current {@link Primary primary} {@link Browser browser's}
 * {@link Configuration configuration} using the annotations {@link #value() key}.
 * <p>
 * The following field types are currently supported for injection:
 * <ul>
 * <li>String</li>
 * <li>Integer</li>
 * <li>Long</li>
 * <li>Float</li>
 * <li>Double</li>
 * <li>Boolean</li>
 * </ul>
 *
 * @see WebTesterTestNGListener
 * @see Primary
 * @see Browser
 * @see Configuration
 * @since 1.2 changed source of configuration
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.FIELD })
public @interface ConfigurationValue {

    /**
     * The key of the {@link Configuration configuration's} property to inject.
     *
     * @return the key
     * @since 1.2
     */
    String value();

}
