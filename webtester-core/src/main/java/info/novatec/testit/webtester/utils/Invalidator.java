package info.novatec.testit.webtester.utils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Deque;
import java.util.List;

import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import info.novatec.testit.webtester.api.annotations.IdentifyUsing;
import info.novatec.testit.webtester.api.exceptions.InvalidatorException;
import info.novatec.testit.webtester.api.pageobjects.PageObjectList;
import info.novatec.testit.webtester.api.pageobjects.traits.Invalidateable;
import info.novatec.testit.webtester.internal.ReflectionUtils;
import info.novatec.testit.webtester.internal.pageobjects.LazyLoadingPageObjectList;
import info.novatec.testit.webtester.pageobjects.PageObject;


/**
 * Utility class providing different methods to invalidate {@link PageObject
 * page objects}.
 *
 * @since 0.9.3
 */
public final class Invalidator {

    private static final Logger logger = LoggerFactory.getLogger(Invalidator.class);

    private Invalidator() {
        // utility constructor
    }

    /**
     * Invalidates a list of {@link PageObject page objects}.
     *
     * @param pageObjectList the list to invalidate
     * @since 0.9.3
     */
    @SuppressWarnings("rawtypes")
    public static void invalidate(List<? extends PageObject> pageObjectList) {
        if (pageObjectList instanceof PageObjectList) {
            (( PageObjectList ) pageObjectList).invalidate();
        } else {
            for (PageObject pageObject : pageObjectList) {
                invalidate(pageObject);
            }
        }
    }

    /**
     * Invalidates all sub elements of the given {@link PageObject page object}.
     * This is done by traversing the class hierarchy of the given page object
     * and calling {@link PageObject#invalidate() invalidate()} on each page
     * object or {@link LazyLoadingPageObjectList page object list} instance.
     *
     * @param pageObject the page object whose sub elements should be invalidated
     * @since 0.9.3
     */
    public static void invalidate(PageObject pageObject) {
        Deque<Class<?>> classStack = ReflectionUtils.getClassAncestry(pageObject.getClass());
        while (!classStack.isEmpty()) {
            invalidatePageObjectsFieldsOfClass(pageObject, classStack.pop());
        }
    }

    @SuppressWarnings("rawtypes")
    private static <T extends PageObject> void invalidatePageObjectsFieldsOfClass(T pageInstance, Class<?> clazz) {
        pageInstance.invalidate();
        for (Field field : clazz.getDeclaredFields()) {
            if (shouldInvalidateField(field)) {
                invalidateFieldOnInstance(field, pageInstance);
            }
        }
    }

    private static <T extends PageObject> void invalidateFieldOnInstance(Field field, T pageInstance) {

        Object value;
        try {
            field.setAccessible(true);
            value = field.get(pageInstance);
        } catch (IllegalAccessException e) {
            throw new InvalidatorException("could not read value of field: " + field, e);
        }

        if (value instanceof Invalidateable) {
            (( Invalidateable ) value).invalidate();
            logger.debug("invalidated instance '{}' in field '{}'", value, field);
        } else {
            logger.debug("not an invalidateable instance '{}' in field '{}'", value, field);
        }

    }

    private static boolean shouldInvalidateField(Field field) {
        return isAnnotatedWith(field, IdentifyUsing.class) || isAnnotatedWith(field, FindBy.class) || isAnnotatedWith(field,
            FindBys.class);
    }

    private static boolean isAnnotatedWith(Field field, Class<? extends Annotation> annotation) {
        return field.getAnnotation(annotation) != null;
    }

}
