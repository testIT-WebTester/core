package utils;

import org.junit.rules.ExternalResource;

import net.sf.cglib.proxy.UndeclaredThrowableException;

import info.novatec.testit.webtester.internal.ReflectionUtils;


public class StaticFieldReplacer extends ExternalResource {

    private Class<?> clazz;
    private String fieldName;
    private Object replacement;

    private Object original;

    public StaticFieldReplacer(Class<?> clazz, String fieldName, Object replacement) {
        this.clazz = clazz;
        this.fieldName = fieldName;
        this.replacement = replacement;
    }

    @Override
    public void before() throws IllegalAccessException, NoSuchFieldException {
        original = ReflectionUtils.forceGetFieldValue(clazz, fieldName);
        ReflectionUtils.forceSetField(clazz, fieldName, replacement);
    }

    @Override
    @SuppressWarnings("PMD.AvoidCatchingGenericException")
    public void after() {
        try {
            ReflectionUtils.forceSetField(clazz, fieldName, original);
        } catch (Exception e) {
            throw new UndeclaredThrowableException(e);
        }
    }

}
