package info.novatec.testit.webtester.utils.conditions.syntax;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.google.common.base.Predicate;

import info.novatec.testit.webtester.pageobjects.PageObject;


@RunWith(MockitoJUnitRunner.class)
public class IsTest {

    @Mock
    PageObject pageObject;

    @Test
    public void testThatPredicateEvaluatesCorrectly_True() {
        Is<PageObject> cut = buildClassUnderTest()
            .withPredicateReturning(true)
            .build();
        assertThat(cut.apply(pageObject), is(true));
    }

    @Test
    public void testThatPredicateEvaluatesCorrectly_False() {
        Is<PageObject> cut = buildClassUnderTest()
            .withPredicateReturning(false)
            .build();
        assertThat(cut.apply(pageObject), is(false));
    }

    /* details */

    IsTestBuilder buildClassUnderTest() {
        return new IsTestBuilder();
    }

    @SuppressWarnings("unchecked")
    class IsTestBuilder {

        Predicate<PageObject> predicate;

        IsTestBuilder withPredicateReturning(boolean result) {
            predicate = mock(Predicate.class);
            doReturn(result).when(predicate).apply(pageObject);
            return this;
        }

        Is<PageObject> build() {
            return new Is<PageObject>(predicate);
        }

    }

}
