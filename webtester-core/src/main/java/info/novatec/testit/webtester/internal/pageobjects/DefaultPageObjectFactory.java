package info.novatec.testit.webtester.internal.pageobjects;

import static info.novatec.testit.webtester.utils.Conditions.is;
import static info.novatec.testit.webtester.utils.Conditions.visible;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.Deque;
import java.util.List;
import javax.annotation.PostConstruct;

import org.apache.commons.lang.StringUtils;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import info.novatec.testit.webtester.api.annotations.AfterInitialization;
import info.novatec.testit.webtester.api.annotations.IdentifyUsing;
import info.novatec.testit.webtester.api.annotations.Internal;
import info.novatec.testit.webtester.api.annotations.Visible;
import info.novatec.testit.webtester.api.browser.Browser;
import info.novatec.testit.webtester.api.enumerations.Caching;
import info.novatec.testit.webtester.api.exceptions.PageObjectFactoryException.ConstructorException;
import info.novatec.testit.webtester.api.exceptions.PageObjectFactoryException.GettingPageObjectFieldException;
import info.novatec.testit.webtester.api.exceptions.PageObjectFactoryException.GettingPageObjectListFieldException;
import info.novatec.testit.webtester.api.exceptions.PageObjectFactoryException.ModelFieldException;
import info.novatec.testit.webtester.api.exceptions.PageObjectFactoryException.PageObjectFieldException;
import info.novatec.testit.webtester.api.exceptions.PageObjectFactoryException.PostConstructMethodException;
import info.novatec.testit.webtester.api.exceptions.PageObjectFactoryException.UnsupportedFieldClassException;
import info.novatec.testit.webtester.api.exceptions.PageObjectFactoryException.VisiblePageObjectFieldException;
import info.novatec.testit.webtester.api.exceptions.PageObjectFactoryException.VisiblePageObjectListFieldException;
import info.novatec.testit.webtester.api.exceptions.PageObjectFactoryException.WebElementFieldException;
import info.novatec.testit.webtester.api.pageobjects.Identification;
import info.novatec.testit.webtester.api.pageobjects.PageObjectFactory;
import info.novatec.testit.webtester.api.pageobjects.PageObjectList;
import info.novatec.testit.webtester.eventsystem.EventSystem;
import info.novatec.testit.webtester.eventsystem.events.browser.ExceptionEvent;
import info.novatec.testit.webtester.internal.ReflectionUtils;
import info.novatec.testit.webtester.pageobjects.PageObject;
import info.novatec.testit.webtester.utils.Identifications;
import info.novatec.testit.webtester.utils.Waits;


/**
 * This class is responsible for initializing {@link PageObject} instances.
 * These classes should only be initialized via this Factory and never manually
 * via a constructor!
 */
@Internal
@SuppressWarnings({ "unchecked", "PMD.AvoidCatchingGenericException" })
public final class DefaultPageObjectFactory implements PageObjectFactory {

    private static final Logger logger = LoggerFactory.getLogger(DefaultPageObjectFactory.class);

    private static final String FIELD_NAME_MODEL = "model";
    private static final String FIELD_NAME_WEB_ELEMENT = "cachedWebElement";

    @Override
    public <T extends PageObject> T create(Class<T> pageClazz, PageObjectModel model) {
        return create(pageClazz, model, null);
    }

    @Override
    public <T extends PageObject> T create(Class<T> pageClazz, PageObjectModel model, WebElement webElement) {

        try {

            T pageInstance = createInstance(pageClazz);
            initializeModel(pageInstance, model);
            initializeWebElement(pageInstance, webElement);

            initializeOtherPageObjectTypeFields(pageInstance, pageClazz);

            invokePostConstructMethods(pageInstance, pageClazz);
            checkVisibilityOfAnnotatedFields(pageInstance, pageClazz);

            return pageInstance;

        } catch (RuntimeException e) {
            EventSystem.fireEvent(new ExceptionEvent(model.getBrowser(), e));
            throw e;
        }

    }

    @Override
    public <T extends PageObject> PageObjectList<T> createList(Class<T> pageClazz, PageObjectModel model) {
        return new LazyLoadingPageObjectList<T>(pageClazz, model);
    }

    /* details */

    private <T extends PageObject> T createInstance(Class<T> pageClazz) {
        try {
            Constructor<T> classConstructor = pageClazz.getDeclaredConstructor();
            return ReflectionUtils.forceCreateInstance(classConstructor);
        } catch (Exception e) {
            throw exception(pageClazz, e).inConstructor();
        }
    }

    private <T extends PageObject> void initializeModel(T pageInstance, PageObjectModel model) {
        try {
            Field field = PageObject.class.getDeclaredField(FIELD_NAME_MODEL);
            ReflectionUtils.forceSetField(field, pageInstance, model);
        } catch (Exception e) {
            throw exception(pageInstance, e).inModelFieldInjection();
        }
    }

    private <T extends PageObject> void initializeWebElement(T pageInstance, WebElement webElement) {
        try {
            Field field = PageObject.class.getDeclaredField(FIELD_NAME_WEB_ELEMENT);
            ReflectionUtils.forceSetField(field, pageInstance, webElement);
        } catch (Exception e) {
            throw exception(pageInstance, e).inWebElementFieldInjection();
        }
    }

    private <T extends PageObject> void initializeOtherPageObjectTypeFields(T pageInstance, Class<T> pageClazz) {
        Browser browser = pageInstance.getBrowser();
        Deque<Class<?>> classAncestry = ReflectionUtils.getClassAncestry(pageClazz);
        while (!classAncestry.isEmpty()) {
            initializeFieldsOfClass(classAncestry.pop(), pageInstance, browser);
        }
    }

    private <T extends PageObject> void initializeFieldsOfClass(Class<?> clazz, T pageInstance, Browser browser) {
        for (Field field : clazz.getDeclaredFields()) {
            if (shouldInitializeField(field)) {
                initializeField(field, pageInstance, browser);
            }
        }
    }

    private boolean shouldInitializeField(Field field) {
        return field.getAnnotation(IdentifyUsing.class) != null || field.getAnnotation(FindBy.class) != null
            || field.getAnnotation(FindBys.class) != null;
    }

    private <T extends PageObject> void initializeField(Field field, T pageInstance, Browser browser) {
        Class<?> fieldClass = field.getType();
        if (PageObject.class.isAssignableFrom(fieldClass)) {
            initializeFieldAsPageObject(field, pageInstance, browser);
        } else if (List.class.isAssignableFrom(fieldClass) || PageObjectList.class.isAssignableFrom(fieldClass)) {
            initializeFieldAsPageObjectList(field, pageInstance, browser);
        } else {
            throw exception(pageInstance).unsupportedFieldClass(field, field.getType());
        }
    }

    private <T extends PageObject> void initializeFieldAsPageObject(Field field, T pageInstance, Browser browser) {

        Identification identification = getIdentificationForField(field);

        PageObjectModel metaData = PageObjectModel.forPageFragment(browser, identification, pageInstance);
        metaData.setCaching(getCachingForField(field));
        metaData.setName(getNameForField(field));

        try {
            PageObject pageObject = create(( Class<? extends PageObject> ) field.getType(), metaData);
            ReflectionUtils.forceSetField(field, pageInstance, pageObject);
        } catch (Exception e) {
            throw exception(pageInstance, e).inPageObjectFieldInjection(field);
        }

    }

    @SuppressWarnings("rawtypes")
    private <T extends PageObject> void initializeFieldAsPageObjectList(Field field, T pageInstance, Browser browser) {

        Identification identification = getIdentificationForField(field);
        Class<? extends PageObject> listType = getPageObjectClassFromListField(pageInstance, field);

        PageObjectModel listMetaData = PageObjectModel.forPageFragment(browser, identification, pageInstance);
        listMetaData.setCaching(getCachingForField(field));
        listMetaData.setName(getNameForField(field));

        try {
            PageObjectList<? extends PageObject> pageObjectList = new LazyLoadingPageObjectList(listType, listMetaData);
            ReflectionUtils.forceSetField(field, pageInstance, pageObjectList);
        } catch (Exception e) {
            throw exception(pageInstance, e).inPageObjectFieldInjection(field);
        }

    }

    private <T extends PageObject> Class<T> getPageObjectClassFromListField(T pageInstance, Field listField) {
        ParameterizedType genericType = ( ParameterizedType ) listField.getGenericType();
        Class<T> genericTypeClass = ( Class<T> ) genericType.getActualTypeArguments()[0];
        if (!PageObject.class.isAssignableFrom(genericTypeClass)) {
            throw exception(pageInstance).unsupportedFieldClass(listField, genericTypeClass);
        }
        return genericTypeClass;
    }

    private <T extends PageObject> void invokePostConstructMethods(T pageInstance, Class<T> pageClazz) {
        Deque<Class<?>> classStack = ReflectionUtils.getClassAncestry(pageClazz);
        while (!classStack.isEmpty()) {
            invokePostConstructMethodsOfClass(pageInstance, classStack.pop());
        }
    }

    private <T extends PageObject> void invokePostConstructMethodsOfClass(T pageInstance, Class<?> clazz) {
        for (Method method : clazz.getDeclaredMethods()) {
            if (shouldExecuteMethod(method)) {
                tryToInvokePostConstructMethod(pageInstance, method);
            }
        }
    }

    private <T extends PageObject> void tryToInvokePostConstructMethod(T pageInstance, Method method) {
        try {
            ReflectionUtils.forceInvokeMethod(method, pageInstance);
        } catch (Exception e) {
            throw exception(pageInstance, e).whenExecutingPostConstructMethod(method);
        }
    }

    private boolean shouldExecuteMethod(Method method) {
        return method.getAnnotation(PostConstruct.class) != null || method.getAnnotation(AfterInitialization.class) != null;
    }

    private <T extends PageObject> void checkVisibilityOfAnnotatedFields(T pageInstance, Class<T> pageClazz) {
        Deque<Class<?>> classStack = ReflectionUtils.getClassAncestry(pageClazz);
        while (!classStack.isEmpty()) {
            checkVisibilityOfAnnotatedFieldsOfClass(pageInstance, classStack.pop());
        }
    }

    private <T extends PageObject> void checkVisibilityOfAnnotatedFieldsOfClass(T pageInstance, Class<?> clazz) {
        for (Field field : clazz.getDeclaredFields()) {
            checkVisibilityOfAnnotatedFieldOfClass(pageInstance, field);
        }
    }

    private <T extends PageObject> void checkVisibilityOfAnnotatedFieldOfClass(T pageInstance, Field field) {

        if (!isAnnotatedWithVisible(field)) {
            return;
        }

        if (isPageObjectField(field)) {
            tryToWaitOnPageObjectsVisibility(pageInstance, field);
        } else if (isListField(field) || isPageObjectListField(field)) {
            tryToWaitOnPageObjectListVisibility(pageInstance, field);
        }

    }

    private boolean isAnnotatedWithVisible(Field field) {
        return field.getAnnotation(Visible.class) != null;
    }

    private boolean isPageObjectField(Field field) {
        return PageObject.class.isAssignableFrom(field.getType());
    }

    private boolean isListField(Field field) {
        return List.class.isAssignableFrom(field.getType());
    }

    private boolean isPageObjectListField(Field field) {
        return PageObjectList.class.isAssignableFrom(field.getType());
    }

    private <T extends PageObject> void tryToWaitOnPageObjectsVisibility(T pageInstance, Field field) {
        try {
            waitOnPageObjectsVisibility(pageInstance, field);
        } catch (RuntimeException e) {
            throw exception(pageInstance, e).whenWaitingForVisibilityOfPageObjectField(field);
        }
    }

    private <T extends PageObject> void waitOnPageObjectsVisibility(T pageInstance, Field field) {
        PageObject pageObject = getPageObjectFromOf(field, pageInstance);
        Waits.waitUntil(pageObject, is(visible()));
    }

    private <T extends PageObject> void tryToWaitOnPageObjectListVisibility(T pageInstance, Field field) {
        try {
            waitOnPageObjectListsVisibility(pageInstance, field);
        } catch (RuntimeException e) {
            throw exception(pageInstance, e).whenWaitingForVisibilityOfPageObjectListField(field);
        }
    }

    private <T extends PageObject> void waitOnPageObjectListsVisibility(T pageInstance, Field field) {

        PageObjectList<PageObject> list = getPageObjectListFromOf(field, pageInstance);
        int expected = field.getAnnotation(Visible.class).value();

        int actual = 0;
        for (PageObject pageObject : list) {
            try {
                Waits.waitUntil(pageObject, is(visible()));
                actual++;
            } catch (TimeoutException e) {
                // ignore timeout for present but not visible objects
                logger.debug("page object {} of list wasn't visible within the timeout - ignoring");
            }
        }

        if (actual != expected) {
            String message = "Expected %s elements of page object list (%s) to be visible, but there were %s.";
            throw new IllegalStateException(String.format(message, expected, field, actual));
        }

    }

    private <T extends PageObject> PageObject getPageObjectFromOf(Field field, T pageInstance) {
        try {
            return ( PageObject ) ReflectionUtils.forceGetFieldValue(field, pageInstance);
        } catch (Exception e) {
            throw exception(pageInstance, e).whenGettingPageObjectField(field);
        }
    }

    private <T extends PageObject> PageObjectList<PageObject> getPageObjectListFromOf(Field field, T pageInstance) {
        try {
            return ( PageObjectList<PageObject> ) ReflectionUtils.forceGetFieldValue(field, pageInstance);
        } catch (Exception e) {
            throw exception(pageInstance, e).whenGettingPageObjectListField(field);
        }
    }

    private Identification getIdentificationForField(Field field) {
        IdentifyUsing identifyUsing = field.getAnnotation(IdentifyUsing.class);
        if (identifyUsing != null) {
            return Identifications.fromAnnotation(identifyUsing);
        }
        FindBy findBy = field.getAnnotation(FindBy.class);
        if (findBy != null) {
            return Identifications.fromAnnotation(findBy);
        }
        FindBys findBys = field.getAnnotation(FindBys.class);
        if (findBys != null) {
            return Identifications.fromAnnotation(findBys);
        }
        return null;
    }

    private Caching getCachingForField(Field field) {
        IdentifyUsing identifyUsing = field.getAnnotation(IdentifyUsing.class);
        if (identifyUsing != null) {
            return identifyUsing.caching();
        }
        return Caching.DEFAULT;
    }

    private String getNameForField(Field field) {
        IdentifyUsing identificationUsing = field.getAnnotation(IdentifyUsing.class);
        if (identificationUsing != null) {
            return StringUtils.defaultString(identificationUsing.elementname());
        }
        return StringUtils.EMPTY;
    }

    /* exception handling */

    private static PageObjectFactoryExceptions exception(Class<? extends PageObject> pageObjectClass, Throwable cause) {
        return new PageObjectFactoryExceptions(pageObjectClass, cause);
    }

    private static PageObjectFactoryExceptions exception(PageObject pageObjectInstance) {
        return new PageObjectFactoryExceptions(pageObjectInstance);
    }

    private static PageObjectFactoryExceptions exception(PageObject pageObjectInstance, Throwable cause) {
        return new PageObjectFactoryExceptions(pageObjectInstance, cause);
    }

    private static class PageObjectFactoryExceptions {

        private final Class<? extends PageObject> pageObjectClass;
        private final Throwable cause;

        PageObjectFactoryExceptions(Class<? extends PageObject> pageObjectClass, Throwable cause) {
            this.pageObjectClass = pageObjectClass;
            this.cause = cause;
        }

        PageObjectFactoryExceptions(PageObject pageObjectInstance) {
            this.pageObjectClass = pageObjectInstance.getClass();
            this.cause = null;
        }

        PageObjectFactoryExceptions(PageObject pageObjectInstance, Throwable cause) {
            this.pageObjectClass = pageObjectInstance.getClass();
            this.cause = cause;
        }

        public ConstructorException inConstructor() {
            String messageFormat = "exception while invoking page object constructor of class: %s";
            String formattedMessage = String.format(messageFormat, pageObjectClass);
            return new ConstructorException(formattedMessage, cause);
        }

        public ModelFieldException inModelFieldInjection() {
            String messageFormat = "could not inject field '%s' in instance of page object class: %s";
            String formattedMessage = String.format(messageFormat, FIELD_NAME_MODEL, pageObjectClass);
            return new ModelFieldException(formattedMessage, cause);
        }

        public WebElementFieldException inWebElementFieldInjection() {
            String messageFormat = "could not inject field '%s' in instance of page object class: %s";
            String formattedMessage = String.format(messageFormat, FIELD_NAME_WEB_ELEMENT, pageObjectClass);
            return new WebElementFieldException(formattedMessage, cause);
        }

        public PageObjectFieldException inPageObjectFieldInjection(Field field) {
            String messageFormat = "could not inject page object field '%s' in instance of page object class: %s";
            String formattedMessage = String.format(messageFormat, field, pageObjectClass);
            return new PageObjectFieldException(formattedMessage, cause);
        }

        public UnsupportedFieldClassException unsupportedFieldClass(Field field, Class<?> fieldClass) {
            String messageFormat =
                "could not inject unsupported field '%s' of type '%s' in instance of page object class: %s";
            String formattedMessage = String.format(messageFormat, field, fieldClass, pageObjectClass);
            return new UnsupportedFieldClassException(formattedMessage);
        }

        public PostConstructMethodException whenExecutingPostConstructMethod(Method method) {
            String messageFormat =
                "exception while invoking post construct method '%s' of instance of page object class: %s";
            String formattedMessage = String.format(messageFormat, method, pageObjectClass);
            return new PostConstructMethodException(formattedMessage);
        }

        public GettingPageObjectFieldException whenGettingPageObjectField(Field field) {
            String messageFormat = "exception while retrieving page object field '%s' of instance of page object class: %s";
            String formattedMessage = String.format(messageFormat, field, pageObjectClass);
            return new GettingPageObjectFieldException(formattedMessage);
        }

        public GettingPageObjectListFieldException whenGettingPageObjectListField(Field field) {
            String messageFormat =
                "exception while retrieving page object list field '%s' of instance of page object class: %s";
            String formattedMessage = String.format(messageFormat, field, pageObjectClass);
            return new GettingPageObjectListFieldException(formattedMessage);
        }

        public VisiblePageObjectFieldException whenWaitingForVisibilityOfPageObjectField(Field field) {
            String messageFormat =
                "exception while waiting on page object field '%s' of instance of page object class: %s to become visible";
            String formattedMessage = String.format(messageFormat, field, pageObjectClass);
            return new VisiblePageObjectFieldException(formattedMessage);
        }

        public VisiblePageObjectListFieldException whenWaitingForVisibilityOfPageObjectListField(Field field) {
            String messageFormat =
                "exception while waiting on page object list field '%s' of instance of page object class: %s to become visible";
            String formattedMessage = String.format(messageFormat, field, pageObjectClass);
            return new VisiblePageObjectListFieldException(formattedMessage);
        }

    }

}
