package info.novatec.testit.webtester.internal;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Deque;
import java.util.Set;

import org.junit.Test;

import utils.testclasses.ClassWithFields;
import utils.testclasses.ClassWithMethods;
import utils.testclasses.ClassWithPackageProtectedConstructors;
import utils.testclasses.ClassWithPrivateConstructors;
import utils.testclasses.ClassWithProtectedConstructors;
import utils.testclasses.ClassWithPublicConstructors;
import utils.testclasses.ancestry.Child;
import utils.testclasses.ancestry.Father;
import utils.testclasses.ancestry.Grandfather;


public class ReflectionUtilsTest {

    private static final String TEST_VALUE = "testValue";

    /* testForceCreateInstance */

    @Test
    public final void testForceCreateInstance_PublicDefaultConstructor_InstanceCreated() throws Exception {
        ClassWithPublicConstructors instance = ReflectionUtils.forceCreateInstance(ClassWithPublicConstructors.class);
        assertThat(instance.getValue(), equalTo(ClassWithPublicConstructors.DEFAULT_VALUE));
    }

    @Test
    public final void testForceCreateInstance_PublicConstructorWithValue_InstanceCreated() throws Exception {
        ClassWithPublicConstructors instance =
            ReflectionUtils.forceCreateInstance(ClassWithPublicConstructors.class, TEST_VALUE);
        assertThat(instance.getValue(), equalTo(TEST_VALUE));
    }

    @Test
    public final void testForceCreateInstance_ProtectedDefaultConstructor_InstanceCreated() throws Exception {
        ClassWithProtectedConstructors instance = ReflectionUtils.forceCreateInstance(ClassWithProtectedConstructors.class);
        assertThat(instance.getValue(), equalTo(ClassWithProtectedConstructors.DEFAULT_VALUE));
    }

    @Test
    public final void testForceCreateInstance_ProtectedConstructorWithValue_InstanceCreated() throws Exception {
        ClassWithProtectedConstructors instance =
            ReflectionUtils.forceCreateInstance(ClassWithProtectedConstructors.class, TEST_VALUE);
        assertThat(instance.getValue(), equalTo(TEST_VALUE));
    }

    @Test
    public final void testForceCreateInstance_PackageProtectedDefaultConstructor_InstanceCreated() throws Exception {
        ClassWithPackageProtectedConstructors instance =
            ReflectionUtils.forceCreateInstance(ClassWithPackageProtectedConstructors.class);
        assertThat(instance.getValue(), equalTo(ClassWithPackageProtectedConstructors.DEFAULT_VALUE));
    }

    @Test
    public final void testForceCreateInstance_PackageProtectedConstructorWithValue_InstanceCreated() throws Exception {
        ClassWithPackageProtectedConstructors instance =
            ReflectionUtils.forceCreateInstance(ClassWithPackageProtectedConstructors.class, TEST_VALUE);
        assertThat(instance.getValue(), equalTo(TEST_VALUE));
    }

    @Test
    public final void testForceCreateInstance_PrivateDefaultConstructor_InstanceCreated() throws Exception {
        ClassWithPrivateConstructors instance = ReflectionUtils.forceCreateInstance(ClassWithPrivateConstructors.class);
        assertThat(instance.getValue(), equalTo(ClassWithPrivateConstructors.DEFAULT_VALUE));
    }

    @Test
    public final void testForceCreateInstance_PrivateConstructorWithValue_InstanceCreated() throws Exception {
        ClassWithPrivateConstructors instance =
            ReflectionUtils.forceCreateInstance(ClassWithPrivateConstructors.class, TEST_VALUE);
        assertThat(instance.getValue(), equalTo(TEST_VALUE));
    }

    /* testForceSetField */

    @Test
    public final void testForceSetField_PublicField_FieldSet() throws Exception {

        ClassWithFields instance = new ClassWithFields();
        assertThat(instance.getPublicValue(), is(nullValue()));

        ReflectionUtils.forceSetField("publicValue", instance, TEST_VALUE);
        assertThat(instance.getPublicValue(), is(TEST_VALUE));

    }

    @Test
    public final void testForceSetField_PrivateField_FieldSet() throws Exception {

        ClassWithFields instance = new ClassWithFields();
        assertThat(instance.getPrivateValue(), is(nullValue()));

        ReflectionUtils.forceSetField("privateValue", instance, TEST_VALUE);
        assertThat(instance.getPrivateValue(), is(TEST_VALUE));

    }

    @Test
    public final void testForceSetField_ProtectedField_FieldSet() throws Exception {

        ClassWithFields instance = new ClassWithFields();
        assertThat(instance.getProtectedValue(), is(nullValue()));

        ReflectionUtils.forceSetField("protectedValue", instance, TEST_VALUE);
        assertThat(instance.getProtectedValue(), is(TEST_VALUE));

    }

    @Test
    public final void testForceSetField_PackageProtectedField_FieldSet() throws Exception {

        ClassWithFields instance = new ClassWithFields();
        assertThat(instance.getPackageProtectedValue(), is(nullValue()));

        ReflectionUtils.forceSetField("packageProtectedValue", instance, TEST_VALUE);
        assertThat(instance.getPackageProtectedValue(), is(TEST_VALUE));

    }

    /* testForceInvokeMethod */

    @Test
    public final void testForceInvokeMethod_PublicMethod_MethodInvoked() throws Exception {

        ClassWithMethods instance = new ClassWithMethods();
        Method method = ClassWithMethods.class.getDeclaredMethod("publicMethod");

        String returnValue = ( String ) ReflectionUtils.forceInvokeMethod(method, instance);
        assertThat(returnValue, equalTo("public"));

    }

    @Test
    public final void testForceInvokeMethod_PrivateMethod_MethodInvoked() throws Exception {

        ClassWithMethods instance = new ClassWithMethods();
        Method method = ClassWithMethods.class.getDeclaredMethod("privateMethod");

        String returnValue = ( String ) ReflectionUtils.forceInvokeMethod(method, instance);
        assertThat(returnValue, equalTo("private"));

    }

    @Test
    public final void testForceInvokeMethod_ProtectedMethod_MethodInvoked() throws Exception {

        ClassWithMethods instance = new ClassWithMethods();
        Method method = ClassWithMethods.class.getDeclaredMethod("protectedMethod");

        String returnValue = ( String ) ReflectionUtils.forceInvokeMethod(method, instance);
        assertThat(returnValue, equalTo("protected"));

    }

    @Test
    public final void testForceInvokeMethod_PackageProtectedMethod_MethodInvoked() throws Exception {

        ClassWithMethods instance = new ClassWithMethods();
        Method method = ClassWithMethods.class.getDeclaredMethod("packageProtectedMethod");

        String returnValue = ( String ) ReflectionUtils.forceInvokeMethod(method, instance);
        assertThat(returnValue, equalTo("package protected"));

    }

    /* testGetClassAncestry */

    @Test
    @SuppressWarnings("unchecked")
    public final void testGetClassAncestry_AncestryOfThreeClassesToObject_DequeWithFourClasses() throws Exception {
        Deque<Class<?>> ancestry = ReflectionUtils.getClassAncestry(Child.class);
        assertThat(ancestry, contains(Object.class, Grandfather.class, Father.class, Child.class));
    }

    /* testGetAllFieldsOfClassHierarchy */

    @Test
    public final void testGetAllFieldsOfClassHierarchy() throws Exception {

        Field childField = Child.class.getDeclaredField("childField");
        Field fatherField = Father.class.getDeclaredField("fatherField");
        Field grandfatherField = Grandfather.class.getDeclaredField("grandfatherField");

        Set<Field> fields = ReflectionUtils.getAllFieldsOfClassHierarchy(Child.class);

        assertThat(fields, hasSize(3));
        assertThat(fields, containsInAnyOrder(childField, fatherField, grandfatherField));

    }

}
