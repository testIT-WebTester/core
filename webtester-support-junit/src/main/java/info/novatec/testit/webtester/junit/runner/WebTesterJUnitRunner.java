package info.novatec.testit.webtester.junit.runner;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import javax.annotation.Resource;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.InitializationError;
import org.junit.runners.model.Statement;

import info.novatec.testit.webtester.api.browser.Browser;
import info.novatec.testit.webtester.api.config.Configuration;
import info.novatec.testit.webtester.internal.ReflectionUtils;
import info.novatec.testit.webtester.junit.annotations.ConfigurationValue;
import info.novatec.testit.webtester.junit.annotations.CreateUsing;
import info.novatec.testit.webtester.junit.annotations.EntryPoint;
import info.novatec.testit.webtester.junit.annotations.Primary;
import info.novatec.testit.webtester.junit.exceptions.NoManagedBrowserException;
import info.novatec.testit.webtester.junit.exceptions.NoPrimaryBrowserException;
import info.novatec.testit.webtester.junit.exceptions.NoUniquePrimaryBrowserException;
import info.novatec.testit.webtester.junit.runner.internal.AbstractTestBrowser;
import info.novatec.testit.webtester.junit.runner.internal.ClassTestBrowser;
import info.novatec.testit.webtester.junit.runner.internal.ConfigurationValueInjector;
import info.novatec.testit.webtester.junit.runner.internal.MethodTestBrowser;
import info.novatec.testit.webtester.junit.runner.internal.TestClassPlausibilityChecker;


/**
 * This {@link BlockJUnit4ClassRunner JUnit runner} takes care of the following
 * life cycle management issues:
 * <ul>
 * <li>Creating {@link Browser browsers} for tests.</li>
 * <li>Injecting {@link Browser browser} instances into static and instance
 * fields of the test.</li>
 * <li>Injection of configuration properties into static and instance fields.
 * </li>
 * <li>Closing {@link Browser browsers} at the end of their natural scope.</li>
 * </ul>
 * More precisely, any {@link Field field} of type {@link Browser browser}
 * annotated with {@link Resource @Resource} and a value of <code>null</code>
 * will have a new {@link Browser browser} instance injected. Non
 * <code>null</code> fields which are annotated with {@link Resource @Resource}
 * will still be managed, but the original {@link Browser browser} instance will
 * be used. The {@link Browser browser} instances are created by using the
 * factory provided with the {@link CreateUsing @CreateUsing} annotation.
 * <p>
 * Fields annotated with {@link ConfigurationValue @ConfigurationValue} will be
 * injected with values from the primary browser's configuration. In case you
 * are using more then one browser, {@link Primary @Primary} has to be used to
 * declare one of them as the primary browser.
 * <p>
 * All of these operations are done regardless of field visibility (private,
 * default, protected or public). Reflection is used to break open all
 * visibility modifiers.
 * <p>
 * <b>Example life cycle for a test class with two test methods:</b>
 * <ol>
 * <li>static rules' before() methods</li>
 * <li>static browser creation and injection</li>
 * <li>injection of configuration properties into static fields annotated with
 * {@link ConfigurationValue @ConfigurationValue}</li>
 * <li>static methods annotated with {@link BeforeClass @BeforeClass}</li>
 * <li>instance rules' before() methods</li>
 * <li>instance browser creation and injection</li>
 * <li>injection of configuration properties into instance fields annotated with
 * {@link ConfigurationValue @ConfigurationValue}</li>
 * <li>instance methods annotated with {@link Before @Before}</li>
 * <li>test method 1</li>
 * <li>instance methods annotated with {@link After @After}</li>
 * <li>instance browsers are closed</li>
 * <li>instance rules' after() methods</li>
 * <li>instance rules' before() methods</li>
 * <li>instance browser creation and injection</li>
 * <li>injection of configuration properties into instance fields annotated with
 * {@link ConfigurationValue @ConfigurationValue}</li>
 * <li>instance methods annotated with {@link Before @Before}</li>
 * <li>test method 2</li>
 * <li>instance methods annotated with {@link After @After}</li>
 * <li>instance browsers are closed</li>
 * <li>instance rules' after() methods</li>
 * <li>static methods annotated with {@link AfterClass @AfterClass}</li>
 * <li>static browsers are closed</li>
 * <li>static rules' after() methods</li>
 * </ol>
 * <p>
 * <b>Exaple test class:</b>
 * <pre>
 * <code>
 * public class FooTest {
 *
 * &#64;Resource
 * &#64;CreateUsing(FooBrowserFactory.class)
 * &#64;EntryPoint("http://localhost:8080/login")
 * private static Browser browser;
 *
 * &#64;ConfigurationValue("auth.username")
 * private String username;
 * &#64;ConfigurationValue("auth.password")
 * private String password;
 *
 * private HomePage home;
 *
 * &#64;Before
 * public void executeLogin(){
 * home = browser.create(LoginPage.class).login(username, password);
 * }
 *
 * &#64;Test
 * public void testFooOnHomePage(){
 * ...
 * }
 *
 * ...
 *
 * }
 * </code>
 * </pre>
 *
 * @see Browser
 * @see ConfigurationValue
 * @see CreateUsing
 * @see EntryPoint
 * @see Primary
 * @see Resource
 * @since 0.9.7 Changed order in which configuration and browser initialization
 * is done. Browsers need to be initialized before configuration values
 * can be injected.
 */
public class WebTesterJUnitRunner extends BlockJUnit4ClassRunner {

    private List<ClassTestBrowser> classBrowsers = new ArrayList<ClassTestBrowser>();
    private List<MethodTestBrowser> methodBrowsers = new ArrayList<MethodTestBrowser>();

    public WebTesterJUnitRunner(Class<?> testClass) throws InitializationError {
        super(testClass);
        new TestClassPlausibilityChecker(testClass).assertPlausibilityOfTestClass();
    }

    @Override
    protected Statement withBeforeClasses(final Statement statement) {
        return new Statement() {

            @Override
            public void evaluate() throws Throwable {
                initializeClassLevel();
                executeBeforeClassForAllBrowsers();
                injectConfigurationValuesIntoStaticFields();
                evaluateWithBeforeClassesFromSuperClass();
            }

            private void initializeClassLevel() {
                classBrowsers.clear();
                Class<?> testClass = getTestClass().getJavaClass();
                for (Field field : ReflectionUtils.getAllFieldsOfClassHierarchy(testClass)) {
                    boolean fieldIsStatic = Modifier.isStatic(field.getModifiers());
                    boolean fieldIsABrowser = Browser.class.isAssignableFrom(field.getType());
                    boolean fieldIsAnnotatedAsResource = field.getAnnotation(Resource.class) != null;
                    if (fieldIsStatic && fieldIsABrowser && fieldIsAnnotatedAsResource) {
                        classBrowsers.add(new ClassTestBrowser(field));
                    }
                }
            }

            private void injectConfigurationValuesIntoStaticFields() {
                if (configurationValuesAnnotationIsUsedOnClassLevel()) {
                    Configuration configuration = getPrimaryBrowser().getBrowser().getConfiguration();
                    ConfigurationValueInjector.injectStatics(configuration, getTestClass().getJavaClass());
                }
            }

            private void executeBeforeClassForAllBrowsers() throws IllegalAccessException {
                for (ClassTestBrowser browser : classBrowsers) {
                    browser.beforeClass();
                }
            }

            private void evaluateWithBeforeClassesFromSuperClass() throws Throwable {
                WebTesterJUnitRunner.super.withBeforeClasses(statement).evaluate();
            }

        };
    }

    private boolean configurationValuesAnnotationIsUsedOnClassLevel() {
        boolean isUsed = false;
        Class<?> testClass = getTestClass().getJavaClass();
        for (Field field : ReflectionUtils.getAllFieldsOfClassHierarchy(testClass)) {
            boolean isStatic = Modifier.isStatic(field.getModifiers());
            boolean isAnnotated = field.isAnnotationPresent(ConfigurationValue.class);
            if (isStatic && isAnnotated) {
                isUsed = true;
            }
        }
        return isUsed;
    }

    @Override
    protected Statement withAfterClasses(final Statement statement) {
        return new Statement() {

            @Override
            public void evaluate() throws Throwable {
                try {
                    evaluateWithAfterClassesFromSuperClass();
                } finally {
                    executeAfterClassForAllBrowsers();
                }
            }

            private void evaluateWithAfterClassesFromSuperClass() throws Throwable {
                WebTesterJUnitRunner.super.withAfterClasses(statement).evaluate();
            }

            private void executeAfterClassForAllBrowsers() {
                for (ClassTestBrowser browser : classBrowsers) {
                    browser.afterClass();
                }
            }

        };
    }

    @Override
    protected Statement withBefores(final FrameworkMethod method, final Object target, final Statement statement) {
        return new Statement() {

            @Override
            public void evaluate() throws Throwable {
                initializeMethodLevel();
                executeBeforeTestForAllBrowsers();
                injectConfigurationValuesIntoInstanceFields();
                evaluateWithBeforesFromSuperClass();
            }

            private void initializeMethodLevel() {
                methodBrowsers.clear();
                Class<?> testClass = getTestClass().getJavaClass();
                for (Field field : ReflectionUtils.getAllFieldsOfClassHierarchy(testClass)) {
                    boolean fieldIsNonStatic = !Modifier.isStatic(field.getModifiers());
                    boolean fieldIsABrowser = Browser.class.isAssignableFrom(field.getType());
                    boolean fieldIsAnnotatedAsResource = field.getAnnotation(Resource.class) != null;
                    if (fieldIsNonStatic && fieldIsABrowser && fieldIsAnnotatedAsResource) {
                        methodBrowsers.add(new MethodTestBrowser(field, target));
                    }
                }
            }

            private void executeBeforeTestForAllBrowsers() throws IllegalAccessException {
                for (ClassTestBrowser browser : classBrowsers) {
                    browser.beforeTest();
                }
                for (MethodTestBrowser browser : methodBrowsers) {
                    browser.beforeTest();
                }
            }

            private void injectConfigurationValuesIntoInstanceFields() {
                if (configurationValuesAnnotationIsUsedOnMethodLevel()) {
                    Configuration configuration = getPrimaryBrowser().getBrowser().getConfiguration();
                    ConfigurationValueInjector.inject(configuration, target);
                }
            }

            private void evaluateWithBeforesFromSuperClass() throws Throwable {
                WebTesterJUnitRunner.super.withBefores(method, target, statement).evaluate();
            }

        };
    }

    private boolean configurationValuesAnnotationIsUsedOnMethodLevel() {
        boolean isUsed = false;
        Class<?> testClass = getTestClass().getJavaClass();
        for (Field field : ReflectionUtils.getAllFieldsOfClassHierarchy(testClass)) {
            boolean isStatic = Modifier.isStatic(field.getModifiers());
            boolean isAnnotated = field.isAnnotationPresent(ConfigurationValue.class);
            if (!isStatic && isAnnotated) {
                isUsed = true;
            }
        }
        return isUsed;
    }

    @Override
    protected Statement withAfters(final FrameworkMethod method, final Object target, final Statement statement) {
        return new Statement() {

            @Override
            public void evaluate() throws Throwable {
                try {
                    evaluateWithAftersFromSuperClass();
                } finally {
                    executeAfterTestForAllBrowsers();
                }

            }

            private void evaluateWithAftersFromSuperClass() throws Throwable {
                WebTesterJUnitRunner.super.withAfters(method, target, statement).evaluate();
            }

            private void executeAfterTestForAllBrowsers() {
                for (MethodTestBrowser browser : methodBrowsers) {
                    browser.afterTest();
                }
                for (ClassTestBrowser browser : classBrowsers) {
                    browser.afterTest();
                }
            }

        };
    }

    /* START primary browser calculation */

    private AbstractTestBrowser getPrimaryBrowser() {
        assertThatAtLeastOneBrowserIsManaged();
        if (numberOfManagedBrowsers() == 1) {
            return getAllBrowsersRegardlessOfScope().get(0);
        }
        return getUniquePrimaryBrowserCandidate();
    }

    private void assertThatAtLeastOneBrowserIsManaged() {
        if (numberOfManagedBrowsers() == 0) {
            throw new NoManagedBrowserException();
        }
    }

    private int numberOfManagedBrowsers() {
        return classBrowsers.size() + methodBrowsers.size();
    }

    private AbstractTestBrowser getUniquePrimaryBrowserCandidate() {
        AbstractTestBrowser primaryBrowserCandidate = null;
        for (AbstractTestBrowser browser : getAllBrowsersRegardlessOfScope()) {
            if (browser.isPrimaryCandidate()) {
                if (primaryBrowserCandidate == null) {
                    primaryBrowserCandidate = browser;
                } else {
                    throw new NoUniquePrimaryBrowserException();
                }
            }
        }
        if (primaryBrowserCandidate == null) {
            throw new NoPrimaryBrowserException();
        }
        return primaryBrowserCandidate;
    }

    private List<AbstractTestBrowser> getAllBrowsersRegardlessOfScope() {
        List<AbstractTestBrowser> allBrowser = new LinkedList<AbstractTestBrowser>();
        allBrowser.addAll(classBrowsers);
        allBrowser.addAll(methodBrowsers);
        return allBrowser;
    }

    /* END primary browser calculation */

}
