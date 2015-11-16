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
public class NotTest {

    @Mock
    PageObject pageObject;

    @Test
    public void testThatPredicateEvaluatesCorrectly_True() {
        Not<PageObject> cut = buildClassUnderTest()
            .withPredicateReturning(true)
            .build();
        assertThat(cut.apply(pageObject), is(false));
    }

    @Test
    public void testThatPredicateEvaluatesCorrectly_False() {
        Not<PageObject> cut = buildClassUnderTest()
            .withPredicateReturning(false)
            .build();
        assertThat(cut.apply(pageObject), is(true));
    }

    /* details */

    NotTestBuilder buildClassUnderTest() {
        return new NotTestBuilder();
    }

    class NotTestBuilder {

        Predicate<PageObject> predicate;

        @SuppressWarnings("unchecked")
        NotTestBuilder withPredicateReturning(boolean result) {
            predicate = mock(Predicate.class);
            doReturn(result).when(predicate).apply(pageObject);
            return this;
        }

        Not<PageObject> build() {
            return new Not<PageObject>(predicate);
        }

    }

}
