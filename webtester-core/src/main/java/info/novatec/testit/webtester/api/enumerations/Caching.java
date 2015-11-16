package info.novatec.testit.webtester.api.enumerations;

import info.novatec.testit.webtester.api.config.Configuration;
import info.novatec.testit.webtester.pageobjects.PageObject;


/**
 * Enumeration of modes that can be used to configure a {@link PageObject page
 * object's} caching behavior.
 *
 * @since 0.9.9
 */
public enum Caching {

    /**
     * Use caching behavior as set in the {@link Configuration}.
     *
     * @since 0.9.9
     */
    DEFAULT,

    /**
     * Explicitly activate caching for the {@link PageObject}, ignoring whatever
     * is set in the {@link Configuration}.
     *
     * @since 0.9.9
     */
    ON,

    /**
     * Explicitly deactivate caching for the {@link PageObject}, ignoring
     * whatever is set in the {@link Configuration}.
     *
     * @since 0.9.9
     */
    OFF;

    /**
     * Calculates whether or not caching should be activated by checking the
     * given {@link Configuration} as well as the given {@link Caching} setting.
     *
     * @param configuration the configuration to use in order to get the global
     * caching setting
     * @param caching a caching instance to consider in the decision
     * @return true if caching should be active otherwise false
     * @since 0.9.9
     */
    public static boolean shouldCache(Configuration configuration, Caching caching) {
        switch (caching) {
            case ON:
                return true;
            case OFF:
                return false;
            default:
                return configuration.isPageObjectCacheActive();
        }
    }

}
