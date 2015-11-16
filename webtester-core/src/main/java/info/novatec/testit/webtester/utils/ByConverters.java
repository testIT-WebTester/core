package info.novatec.testit.webtester.utils;

import org.openqa.selenium.By;

import info.novatec.testit.webtester.api.pageobjects.ByConverter;
import info.novatec.testit.webtester.utils.converters.ClassNameByConverter;
import info.novatec.testit.webtester.utils.converters.CssByConverter;
import info.novatec.testit.webtester.utils.converters.IdByConverter;
import info.novatec.testit.webtester.utils.converters.IdEndsWithByConverter;
import info.novatec.testit.webtester.utils.converters.IdStartsWithByConverter;
import info.novatec.testit.webtester.utils.converters.LinkTextByConverter;
import info.novatec.testit.webtester.utils.converters.NameByConverter;
import info.novatec.testit.webtester.utils.converters.PartialLinkTextByConverter;
import info.novatec.testit.webtester.utils.converters.TagNameByConverter;
import info.novatec.testit.webtester.utils.converters.XpathByConverter;


/**
 * Utility class used to produce all kinds of {@link ByConverter By-converter}
 * instances. By-converters are used to convert simple input strings into
 * Selenium {@link By} instances.
 *
 * @since 0.9.9
 */
public final class ByConverters {

    private ByConverters() {
        // utility class constructor
    }

    public static ClassNameByConverter className() {
        return new ClassNameByConverter();
    }

    public static CssByConverter css() {
        return new CssByConverter();
    }

    public static IdEndsWithByConverter idEndsWith() {
        return new IdEndsWithByConverter();
    }

    public static IdStartsWithByConverter idStartsWith() {
        return new IdStartsWithByConverter();
    }

    public static IdByConverter id() {
        return new IdByConverter();
    }

    public static LinkTextByConverter linkText() {
        return new LinkTextByConverter();
    }

    public static NameByConverter name() {
        return new NameByConverter();
    }

    public static PartialLinkTextByConverter partialLinkText() {
        return new PartialLinkTextByConverter();
    }

    public static TagNameByConverter tagName() {
        return new TagNameByConverter();
    }

    public static XpathByConverter xpath() {
        return new XpathByConverter();
    }

}
