package info.novatec.testit.webtester.api.exceptions;

import javax.annotation.PostConstruct;

import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;

import info.novatec.testit.webtester.api.annotations.AfterInitialization;
import info.novatec.testit.webtester.api.annotations.IdentifyUsing;
import info.novatec.testit.webtester.api.annotations.Visible;
import info.novatec.testit.webtester.api.pageobjects.PageObjectFactory;
import info.novatec.testit.webtester.internal.pageobjects.PageObjectModel;
import info.novatec.testit.webtester.pageobjects.PageObject;


/**
 * Base exception class for all exceptions intentionally thrown by a {@link PageObjectFactory}.
 *
 * @since 0.9.9
 */
@SuppressWarnings("serial")
public class PageObjectFactoryException extends WebTesterException {

    /**
     * This exception is thrown in case there is no default constructor
     * to use when initializing a {@link PageObject page object}.
     *
     * @since 0.9.9
     */
    public static class ConstructorException extends PageObjectFactoryException {
        public ConstructorException(String message, Throwable e) {
            super(message, e);
        }
    }

    /**
     * This exception is thrown in case the {@link PageObjectModel page object model} field
     * could not be set when initializing a {@link PageObject page object}. It is not very likely
     * that it is ever thrown in production - it mainly exists to warn WebTester developers
     * in case they break the page initialization mechanism with some change.
     *
     * @since 0.9.9
     */
    public static class ModelFieldException extends PageObjectFactoryException {
        public ModelFieldException(String message, Throwable e) {
            super(message, e);
        }
    }

    /**
     * This exception is thrown in case the web element field
     * could not be set when initializing a {@link PageObject page object}. It is not very likely
     * that it is ever thrown in production - it mainly exists to warn WebTester developers
     * in case they break the page initialization mechanism with some change.
     *
     * @since 0.9.9
     */
    public static class WebElementFieldException extends PageObjectFactoryException {
        public WebElementFieldException(String message, Throwable e) {
            super(message, e);
        }
    }

    /**
     * This exception is thrown in case a {@link PageObject page object} field
     * could not be set when initializing a {@link PageObject page object}.
     *
     * @since 0.9.9
     */
    public static class PageObjectFieldException extends PageObjectFactoryException {
        public PageObjectFieldException(String message, Throwable e) {
            super(message, e);
        }
    }

    /**
     * This exception is thrown in case a field annotated with {@link IdentifyUsing @IdentifyUsing}
     * or {@link FindBy @FindBy} / {@link FindBys @FindBys} is not of a {@link PageObject page object} type.
     *
     * @since 0.9.9
     */
    public static class UnsupportedFieldClassException extends PageObjectFactoryException {
        public UnsupportedFieldClassException(String message) {
            super(message);
        }
    }

    /**
     * This exception is thrown in case anything goes wrong (exception is thrown) when executing
     * a post construct method (annotated with {@link PostConstruct @PostConstruct}
     * or {@link AfterInitialization @AfterInitialization}).
     *
     * @since 0.9.9
     */
    public static class PostConstructMethodException extends PageObjectFactoryException {
        public PostConstructMethodException(String message) {
            super(message);
        }
    }

    /**
     * This exception is thrown in case a {@link PageObject page object} field could not be read.
     *
     * @since 0.9.9
     */
    public static class GettingPageObjectFieldException extends PageObjectFactoryException {
        public GettingPageObjectFieldException(String message) {
            super(message);
        }
    }

    /**
     * This exception is thrown in case a {@link PageObject page object} list field could not be read.
     *
     * @since 0.9.9
     */
    public static class GettingPageObjectListFieldException extends PageObjectFactoryException {
        public GettingPageObjectListFieldException(String message) {
            super(message);
        }
    }

    /**
     * This exception is thrown in case a {@link PageObject page object} field annotated
     * with {@link Visible @Visible} is not actually visible.
     *
     * @since 0.9.9
     */
    public static class VisiblePageObjectFieldException extends PageObjectFactoryException {
        public VisiblePageObjectFieldException(String message) {
            super(message);
        }
    }

    /**
     * This exception is thrown in case a {@link PageObject page object} list field
     * annotated with {@link Visible @Visible} is not actually visible or the expected
     * count is not equal to the actual count of visible {@link PageObject page object}s in the list.
     *
     * @since 0.9.9
     */
    public static class VisiblePageObjectListFieldException extends PageObjectFactoryException {
        public VisiblePageObjectListFieldException(String message) {
            super(message);
        }
    }

    protected PageObjectFactoryException(String message) {
        super(message);
    }

    protected PageObjectFactoryException(String message, Throwable cause) {
        super(message, cause);
    }

}
