package info.novatec.testit.webtester.internal.pageobjects;

import static info.novatec.testit.webtester.utils.Conditions.is;
import static info.novatec.testit.webtester.utils.Conditions.present;

import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.StaleElementReferenceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import info.novatec.testit.webtester.api.annotations.Internal;
import info.novatec.testit.webtester.api.callbacks.PageObjectCallback;
import info.novatec.testit.webtester.api.callbacks.PageObjectCallbackWithReturnValue;
import info.novatec.testit.webtester.api.exceptions.PageObjectIsInvisibleException;
import info.novatec.testit.webtester.api.exceptions.StaleElementRecoveryException;
import info.novatec.testit.webtester.eventsystem.EventSystem;
import info.novatec.testit.webtester.eventsystem.events.browser.ExceptionEvent;
import info.novatec.testit.webtester.pageobjects.PageObject;
import info.novatec.testit.webtester.utils.Invalidator;
import info.novatec.testit.webtester.utils.Waits;

@Internal
@SuppressWarnings("PMD.AvoidCatchingGenericException")
public class ActionTemplate {

	private static final Logger logger = LoggerFactory.getLogger(ActionTemplate.class);

	private PageObject pageObject;

	public ActionTemplate(PageObject pageObject) {
		this.pageObject = pageObject;
	}

	public void executeAction(PageObjectCallback callback) {
		try {
			callback.execute(pageObject);
		} catch (StaleElementReferenceException e) {
			logStaleElementRecoveryAttempt(e);
			tryToResolveStaleElementException(callback);
		} catch (ElementNotVisibleException e) {
			throw fireExceptionEventAndReturn(new PageObjectIsInvisibleException(pageObject, e));
		} catch (RuntimeException e) {
			throw fireExceptionEventAndReturn(e);
		}
	}

	public <B> B executeAction(PageObjectCallbackWithReturnValue<B> callback) {
		B value;
		try {
			value = callback.execute(pageObject);
		} catch (StaleElementReferenceException e) {
			logStaleElementRecoveryAttempt(e);
			value = tryToResolveStaleElementException(callback);
		} catch (ElementNotVisibleException e) {
			throw fireExceptionEventAndReturn(new PageObjectIsInvisibleException(pageObject, e));
		} catch (RuntimeException e) {
			throw fireExceptionEventAndReturn(e);
		}
		return value;
	}

	private void tryToResolveStaleElementException(PageObjectCallback callback) {
		try {
			refreshAndWaitOnPageObjectsPresence();
			callback.execute(pageObject);
			logStaleElementRecoverySuccess();
		} catch (RuntimeException e) {
			handleStaleElementRecoveryFailure(e);
		}
	}

	private <B> B tryToResolveStaleElementException(PageObjectCallbackWithReturnValue<B> callback) {
		B value = null;
		try {
			refreshAndWaitOnPageObjectsPresence();
			value = callback.execute(pageObject);
			logStaleElementRecoverySuccess();
		} catch (RuntimeException e) {
			handleStaleElementRecoveryFailure(e);
		}
		return value;
	}

	private void refreshAndWaitOnPageObjectsPresence() {
		Waits.waitUntil(Invalidator.invalidate(pageObject), is(present()));
	}

	private void logStaleElementRecoveryAttempt(StaleElementReferenceException e) {
		logger.trace("trying to resolve stale element exception", e);
	}

	private void logStaleElementRecoverySuccess() {
		logger.trace("succeeded in resolving stale element exception");
	}

	private void handleStaleElementRecoveryFailure(RuntimeException e) {
		logger.trace("failed in resolving stale element exception");
		throw fireExceptionEventAndReturn(new StaleElementRecoveryException(e));
	}

	private <T extends RuntimeException> T fireExceptionEventAndReturn(T exception) {
		EventSystem.fireEvent(new ExceptionEvent(pageObject, exception));
		return exception;
	}

}
