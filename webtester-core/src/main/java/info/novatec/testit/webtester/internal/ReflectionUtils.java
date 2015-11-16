package info.novatec.testit.webtester.internal;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

import info.novatec.testit.webtester.api.annotations.Internal;


@Internal
public final class ReflectionUtils {

    private static final Map<Class<?>, Set<Field>> FIELDS_OF_CLASS_CACHE = new HashMap<Class<?>, Set<Field>>();

    /* creating instances */

    @Internal
    public static <T> T forceCreateInstance(Class<T> clazz, Object... constructionParameters)
        throws InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {

        Class<?>[] parameterClasses = getParameterClasses(constructionParameters);
        Constructor<T> classConstructor = clazz.getDeclaredConstructor(parameterClasses);

        return forceCreateInstance(classConstructor, constructionParameters);

    }

    @Internal
    public static <T> T forceCreateInstance(Constructor<T> classConstructor, Object... constructionParameters)
        throws InstantiationException, IllegalAccessException, InvocationTargetException {

        classConstructor.setAccessible(true);
        return classConstructor.newInstance(constructionParameters);

    }

    private static Class<?>[] getParameterClasses(Object... constructionParameters) {

        Class<?>[] returnValue = new Class<?>[constructionParameters.length];
        for (int i = 0; i < constructionParameters.length; i++) {
            returnValue[i] = constructionParameters[i].getClass();
        }

        return returnValue;

    }

    /* getting fields */

    @Internal
    public static Object forceGetFieldValue(Class<?> clazz, String fieldName)
        throws NoSuchFieldException, IllegalAccessException {
        return forceGetFieldValue(clazz, null, fieldName);
    }

    @Internal
    public static Object forceGetFieldValue(Class<?> clazz, Object objectInstance, String fieldName)
        throws NoSuchFieldException, IllegalAccessException {
        Field field = clazz.getDeclaredField(fieldName);
        field.setAccessible(true);
        return field.get(objectInstance);
    }

    @Internal
    public static Object forceGetFieldValue(Field field, Object objectInstance) throws IllegalAccessException {
        field.setAccessible(true);
        return field.get(objectInstance);
    }

    /* setting fields */

    @Internal
    public static void forceSetField(Class<?> clazz, String fieldName, Object fieldValue)
        throws NoSuchFieldException, IllegalAccessException {
        Field field = clazz.getDeclaredField(fieldName);
        forceSetField(field, null, fieldValue);
    }

    @Internal
    public static void forceSetField(String fieldName, Object objectInstance, Object fieldValue)
        throws NoSuchFieldException, IllegalAccessException {
        Field field = objectInstance.getClass().getDeclaredField(fieldName);
        forceSetField(field, objectInstance, fieldValue);
    }

    @Internal
    public static void forceSetField(Field field, Object objectInstance, Object fieldValue) throws IllegalAccessException {
        field.setAccessible(true);
        field.set(objectInstance, fieldValue);
    }

    /* invoking methods */

    @Internal
    public static Object forceInvokeMethod(Method method, Object objectInstance, Object... methodParameters)
        throws IllegalAccessException, InvocationTargetException {
        method.setAccessible(true);
        return method.invoke(objectInstance, methodParameters);
    }

    /* getting fields */

    /**
     * Returns all fields of the given class hierarchy (start class and all its
     * super types). This method uses a cache, so each class hierarchy is only
     * resolved once;
     *
     * @param startClass the class of which all fields in the hierarchy should
     * be returned
     * @return all fields of the given class hierarchy
     */
    @Internal
    public static Set<Field> getAllFieldsOfClassHierarchy(Class<?> startClass) {

        Set<Field> fields = FIELDS_OF_CLASS_CACHE.get(startClass);
        if (fields != null) {
            return fields;
        }

        Field[] declaredFields = startClass.getDeclaredFields();

        fields = new HashSet<Field>(declaredFields.length);
        Collections.addAll(fields, declaredFields);

        Class<?> superclass = startClass.getSuperclass();
        if (superclass != null) {
            fields.addAll(getAllFieldsOfClassHierarchy(superclass));
        }

        FIELDS_OF_CLASS_CACHE.put(startClass, fields);
        return fields;

    }

    /* utilities */

    /**
     * Creates a {@link Deque} of classes that represent the ancestry of the
     * given class. The given class is at the end of this {@link Deque} and the
     * class {@link Object} is at the top.
     *
     * @param clazz {@link Class} which ancestry should be returned
     * @return the ancestry of the given class as a {@link Deque}
     */
    @Internal
    public static Deque<Class<?>> getClassAncestry(Class<?> clazz) {

        Deque<Class<?>> classStack = new LinkedList<Class<?>>();

        Class<?> currentClass = clazz;
        while (currentClass != null) {
            classStack.push(currentClass);
            currentClass = currentClass.getSuperclass();
        }

        return classStack;

    }

    private ReflectionUtils() {
    }

}
