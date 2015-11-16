package info.novatec.testit.webtester.internal.pageobjects;

import org.apache.commons.lang.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;

import info.novatec.testit.webtester.api.annotations.Internal;
import info.novatec.testit.webtester.api.browser.Browser;
import info.novatec.testit.webtester.api.config.Configuration;
import info.novatec.testit.webtester.api.enumerations.Caching;
import info.novatec.testit.webtester.api.pageobjects.Identification;
import info.novatec.testit.webtester.pageobjects.PageObject;
import info.novatec.testit.webtester.utils.Identifications;


@Internal
public class PageObjectModel {

    public static PageObjectModel forPage(Browser browser) {
        PageObjectModel metaData = new PageObjectModel();
        metaData.browser = browser;
        metaData.identification = Identifications.tagName("html");
        return metaData;
    }

    public static PageObjectModel forPageFragment(Browser browser, Identification identification) {
        return forPageFragment(browser, identification, null);
    }

    public static PageObjectModel forPageFragment(Browser browser, Identification identification, PageObject parent) {
        PageObjectModel metaData = new PageObjectModel();
        metaData.browser = browser;
        metaData.identification = identification;
        metaData.parent = parent;
        return metaData;
    }

    public static PageObjectModel forPageObjectListElement(Browser browser, PageObject parent) {
        PageObjectModel metaData = new PageObjectModel();
        metaData.browser = browser;
        metaData.parent = parent;
        metaData.partOfList = true;
        return metaData;
    }

    private Browser browser;
    private Identification identification;
    private PageObject parent;

    private Caching caching = Caching.DEFAULT;
    private String name;
    private boolean partOfList;

    protected PageObjectModel() {
    }

    public boolean cachingIsEnabled() {
        Configuration configuration = getBrowser().getConfiguration();
        return Caching.shouldCache(configuration, caching);
    }

    public SearchContext getSearchContext() {
        return parent != null ? parent.getWebElement() : browser.getWebDriver();
    }

    public By getSeleniumBy() {
        return identification.getSeleniumBy();
    }

    public String getIdentificationString() {
        return identification != null ? identification.toString() : StringUtils.EMPTY;
    }

    public String getLogPrefix() {
        return identification != null ? identification + " - " : StringUtils.EMPTY;
    }

    /* setters and getters */

    public Browser getBrowser() {
        return browser;
    }

    public Identification getIdentification() {
        return identification;
    }

    public PageObject getParent() {
        return parent;
    }

    public String getName() {
        return StringUtils.defaultString(name);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCaching(Caching caching) {
        this.caching = caching;
    }

    public boolean isPartOfList() {
        return partOfList;
    }

}
