package info.novatec.testit.webtester.api.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import info.novatec.testit.webtester.pageobjects.PageObject;


/**
 * This annotation can be added to methods of {@link PageObject} subclasses.
 * These methods will then be invoked via reflection by the framework. After it
 * completed the initialization of the {@link PageObject}.
 * <p>
 * <b>Notes:</b>
 * <ul>
 * <li>The visibility (private, protected, public) will be ignored by this
 * mechanism.</li>
 * <li>The method must not have any arguments, if it does the invocation will
 * fail and an exception is thrown.</li>
 * <li>Multiple methods can be annotated, the order in which they are invoked is
 * random!</li>
 * </ul>
 *
 * @since 0.9.0
 * @deprecated use {@link javax.annotation.PostConstruct @PostConstruct} instead - will be removed with v1.1.0
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Inherited
@Deprecated
public @interface AfterInitialization {
    // no parameters
}
