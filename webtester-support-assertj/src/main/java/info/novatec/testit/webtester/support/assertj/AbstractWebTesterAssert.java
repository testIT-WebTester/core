package info.novatec.testit.webtester.support.assertj;

import org.assertj.core.api.AbstractAssert;


/**
 * Abstract base class for all of WebTester's AssertJ assertion classes.
 *
 * @param <A> the "self" type of this assertion class. Please read &quot;
 * <a href="http://bit.ly/anMa4g" target="_blank">Emulating 'self types'
 * using Java Generics to simplify fluent API implementation</a>&quot;
 * for more details.
 * @param <B> the type of the "actual" value.
 * @since 0.9.8
 */
public abstract class AbstractWebTesterAssert<A, B> extends AbstractAssert<AbstractWebTesterAssert<A, B>, B> {

    public AbstractWebTesterAssert(B actual, Class<?> selfType) {
        super(actual, selfType);
    }

    protected void failOnActualBeingNull() {
        if (actual == null) {
            failWithMessage("Object is null");
        }
    }

}
