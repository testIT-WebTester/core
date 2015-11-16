package info.novatec.testit.webtester.junit.runner.internal;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.UndeclaredThrowableException;

import org.apache.commons.lang.StringUtils;

import info.novatec.testit.webtester.api.browser.Browser;
import info.novatec.testit.webtester.api.browser.ProxyConfiguration;
import info.novatec.testit.webtester.internal.annotations.Java7FeaturePossibility;
import info.novatec.testit.webtester.junit.annotations.CreateUsing;
import info.novatec.testit.webtester.junit.annotations.EntryPoint;
import info.novatec.testit.webtester.junit.annotations.KeepAlive;
import info.novatec.testit.webtester.junit.annotations.Primary;
import info.novatec.testit.webtester.junit.exceptions.NoBrowserFactoryProvidedException;


public abstract class AbstractTestBrowser {

    private Field field;

    private Browser browser;
    private String entryPoint;
    private boolean primaryCandidate;
    private boolean closeBrowser;

    public AbstractTestBrowser(Field browserField) {
        this.field = browserField;
        this.field.setAccessible(true);
        initializeEntryPoint();
        initializePrimaryCandidate();
        initializeCloseBrowserFlag();
    }

    private void initializeEntryPoint() {
        EntryPoint annotation = this.field.getAnnotation(EntryPoint.class);
        if (annotation != null) {
            entryPoint = annotation.value();
        }
    }

    private void initializeCloseBrowserFlag() {
        closeBrowser = !annotationIsPresent(KeepAlive.class);
    }

    private void initializePrimaryCandidate() {
        primaryCandidate = annotationIsPresent(Primary.class);
    }

    private boolean annotationIsPresent(Class<? extends Annotation> annotationClass) {
        return this.field.getAnnotation(annotationClass) != null;
    }

    protected void createBrowserAndSetStaticField() throws IllegalAccessException {
        createBrowserIfNecessary(null);
    }

    protected void createBrowserIfNecessary(Object target) throws IllegalAccessException {
        Object fieldValue = field.get(target);
        if (fieldValue != null) {
            browser = ( Browser ) fieldValue;
        } else {
            browser = createNewBrowser();
            field.set(target, browser);
        }
    }

    @Java7FeaturePossibility("Both Exceptions could be cought using ReflectiveOperationException type.")
    private Browser createNewBrowser() {
        try {

            CreateUsing annotation = field.getAnnotation(CreateUsing.class);
            if (annotation == null) {
                throw new NoBrowserFactoryProvidedException();
            }

            return createNewBrowserFromAnnotation(annotation);

        } catch (InstantiationException e) {
            throw new UndeclaredThrowableException(e);
        } catch (IllegalAccessException e) {
            throw new UndeclaredThrowableException(e);
        }
    }

    private Browser createNewBrowserFromAnnotation(CreateUsing annotation)
        throws InstantiationException, IllegalAccessException {
        ProxyConfiguration proxyConfiguration = annotation.proxy().newInstance();
        return annotation.value().newInstance().withProxyConfiguration(proxyConfiguration).createBrowser();
    }

    protected void openEntryPointIfSet() {

        if (entryPoint == null) {
            return;
        }

        if (StringUtils.isNotBlank(entryPoint)) {
            browser.open(entryPoint);
        } else {
            String defaultEntryPoint = browser.getConfiguration().getDefaultApplicationEntryPoint();
            if (defaultEntryPoint != null) {
                browser.open(defaultEntryPoint);
            }
        }

    }

    public Browser getBrowser() {
        return browser;
    }

    protected void closeBrowser() {
        if (browser != null && closeBrowser) {
            browser.close();
        }
    }

    public abstract void beforeTest() throws IllegalAccessException;

    public abstract void afterTest();

    public boolean isPrimaryCandidate() {
        return primaryCandidate;
    }

}
