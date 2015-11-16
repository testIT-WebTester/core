package info.novatec.testit.webtester.api.enumerations;

import info.novatec.testit.webtester.api.utils.CSSProperty;


/**
 * Enumeration of useful {@link CSSProperty} instances.
 *
 * @see CSSProperty
 * @since 0.9.0
 */
public enum CSSProperties implements CSSProperty {

    BACKGROUND_COLOR("backgroundColor"),

    OUTLINE_COLOR("outlineColor"),

    OUTLINE_STYLE("outlineStyle"),

    OUTLINE_WIDTH("outlineWidth"),

    DISPLAY("display"),

    VISIBILITY("visibility ");

    private final String attributeName;

    CSSProperties(String attributeName) {
        this.attributeName = attributeName;
    }

    @Override
    public String getName() {
        return attributeName;
    }

    @Override
    public String toString() {
        return getName();
    }

}
