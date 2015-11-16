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
public class HasTest {

    @Mock
    PageObject pageObject;

    @Test
    public void testThatPredicateEvaluatesCorrectly_True() {
        Has<PageObject> cut = buildClassUnderTest()
            .withPredicateReturning(true)
            .build();
        assertThat(cut.apply(pageObject), is(true));
    }

    @Test
    public void testThatPredicateEvaluatesCorrectly_False() {
        Has<PageObject> cut = buildClassUnderTest()
            .withPredicateReturning(false)
            .build();
        assertThat(cut.apply(pageObject), is(false));
    }

    /* details */

    HasTestBuilder buildClassUnderTest() {
        return new HasTestBuilder();
    }

    @SuppressWarnings("unchecked")
    class HasTestBuilder {

        Predicate<PageObject> predicate;

        HasTestBuilder withPredicateReturning(boolean result) {
            predicate = mock(Predicate.class);
            doReturn(result).when(predicate).apply(pageObject);
            return this;
        }

        Has<PageObject> build() {
            return new Has<PageObject>(predicate);
        }

    }

}
