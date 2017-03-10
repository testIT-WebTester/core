package info.novatec.testit.webtester.testng.listener.internal;


import org.apache.commons.lang.StringUtils;

import info.novatec.testit.webtester.api.browser.Browser;
import info.novatec.testit.webtester.api.browser.ProxyConfiguration;
import info.novatec.testit.webtester.testng.annotations.CreateUsing;
import info.novatec.testit.webtester.testng.annotations.EntryPoint;
import info.novatec.testit.webtester.testng.annotations.KeepAlive;
import info.novatec.testit.webtester.testng.annotations.Primary;
import info.novatec.testit.webtester.testng.exceptions.NoBrowserFactoryProvidedException;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

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

    protected void createBrowserAndSetStaticField() throws ReflectiveOperationException {
        createBrowserIfNecessary(null);
    }

    protected void createBrowserIfNecessary(Object target) throws ReflectiveOperationException {
        Object fieldValue = field.get(target);
        if (fieldValue != null) {
            browser = ( Browser ) fieldValue;
        } else {
            browser = createNewBrowser();
            field.set(target, browser);
        }
    }

    private Browser createNewBrowser() throws ReflectiveOperationException {
        CreateUsing annotation = field.getAnnotation(CreateUsing.class);
        if (annotation == null) {
            throw new NoBrowserFactoryProvidedException();
        }
        return createNewBrowserFromAnnotation(annotation);
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

    public Field getField() {
        return field;
    }

    public abstract void beforeTest() throws ReflectiveOperationException;

    public abstract void afterTest() throws IllegalAccessException;

    public boolean isPrimaryCandidate() {
        return primaryCandidate;
    }

}
