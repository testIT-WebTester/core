package info.novatec.testit.webtester.utils.conditions.syntax;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.mock;

import java.util.LinkedList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.google.common.base.Predicate;

import info.novatec.testit.webtester.pageobjects.PageObject;


@SuppressWarnings("unchecked")
@RunWith(MockitoJUnitRunner.class)
public class EitherTest {

    @Mock
    PageObject pageObject;

    @Test
    public void testThatPredicateEvaluatesOrCorrectly_TrueTrue() {
        Either<PageObject> cut = buildClassUnderTest()
            .addPredicateReturning(true)
            .addPredicateReturning(true)
            .build();
        assertThat(cut.apply(pageObject), is(true));
    }

    @Test
    public void testThatPredicateEvaluatesOrCorrectly_TrueFalse() {
        Either<PageObject> cut = buildClassUnderTest()
            .addPredicateReturning(true)
            .addPredicateReturning(false)
            .build();
        assertThat(cut.apply(pageObject), is(true));
    }

    @Test
    public void testThatPredicateEvaluatesOrCorrectly_FalseTrue() {
        Either<PageObject> cut = buildClassUnderTest()
            .addPredicateReturning(false)
            .addPredicateReturning(true)
            .build();
        assertThat(cut.apply(pageObject), is(true));
    }

    @Test
    public void testThatPredicateEvaluatesOrCorrectly_FalseFalse() {
        Either<PageObject> cut = buildClassUnderTest()
            .addPredicateReturning(false)
            .addPredicateReturning(false)
            .build();
        assertThat(cut.apply(pageObject), is(false));
    }

    @Test
    public void testThatPredicatesAreCalledInOrder() {

        Predicate<PageObject> firstPredicate = createPredicateReturning(false);
        Predicate<PageObject> secondPredicate = createPredicateReturning(false);
        Predicate<PageObject> thirdPredicate = createPredicateReturning(false);

        Either<PageObject> cut = buildClassUnderTest().addPredicate(firstPredicate)
            .addPredicate(secondPredicate)
            .addPredicate(thirdPredicate)
            .build();
        cut.apply(pageObject);

        InOrder inOrder = inOrder(firstPredicate, secondPredicate, thirdPredicate);
        inOrder.verify(firstPredicate).apply(pageObject);
        inOrder.verify(secondPredicate).apply(pageObject);
        inOrder.verify(thirdPredicate).apply(pageObject);
        inOrder.verifyNoMoreInteractions();

    }

    @Test
    public void testThatFirstPositiveEvaluationStopsTheEvaluationOfRemainingPredicates() {

        Predicate<PageObject> firstPredicate = createPredicateReturning(true);
        Predicate<PageObject> secondPredicate = createPredicateReturning(true);

        Either<PageObject> cut = buildClassUnderTest().addPredicate(firstPredicate).addPredicate(secondPredicate).build();
        cut.apply(pageObject);

        InOrder inOrder = inOrder(firstPredicate, secondPredicate);
        inOrder.verify(firstPredicate).apply(pageObject);
        inOrder.verifyNoMoreInteractions();

    }

    /* details */

    EitherTestBuilder buildClassUnderTest() {
        return new EitherTestBuilder();
    }

    Predicate<PageObject> createPredicateReturning(boolean result) {
        Predicate<PageObject> predicate = mock(Predicate.class);
        doReturn(result).when(predicate).apply(pageObject);
        return predicate;
    }

    class EitherTestBuilder {

        List<Predicate<PageObject>> predicates = new LinkedList<Predicate<PageObject>>();

        EitherTestBuilder addPredicateReturning(boolean result) {
            Predicate<PageObject> predicate = mock(Predicate.class);
            doReturn(result).when(predicate).apply(pageObject);
            predicates.add(predicate);
            return this;
        }

        EitherTestBuilder addPredicate(Predicate<PageObject> predicate) {
            predicates.add(predicate);
            return this;
        }

        Either<PageObject> build() {
            return new Either<PageObject>(predicates.toArray(new Predicate[predicates.size()]));
        }

    }

}
