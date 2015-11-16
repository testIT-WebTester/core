package info.novatec.testit.webtester.support.hamcrest;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

import info.novatec.testit.webtester.pageobjects.List;


/**
 * Contains {@link Matcher matchers} for {@link List lists}.
 *
 * @since 0.9.7
 */
public class ListMatcher extends PageObjectMatcher {

    /**
     * Returns whether the number of items the {@link List list} contains
     * matches the expectation.
     *
     * @param numberOfItems the expected number of items
     * @return true if the numbers match, otherwise false
     * @since 0.9.7
     */
    public static Matcher<List> hasNumberOfItems(final Integer numberOfItems) {
        return new TypeSafeMatcher<List>() {

            @Override
            public void describeTo(Description description) {
                description.appendText("number of items to be '" + numberOfItems + '\'');
            }

            @Override
            protected void describeMismatchSafely(List list, Description mismatchDescription) {
                mismatchDescription.appendText("was '" + list.getNumberOfItems() + '\'');
            }

            @Override
            protected boolean matchesSafely(List list) {
                return numberOfItems.equals(list.getNumberOfItems());
            }
        };

    }

    /**
     * Returns whether the {@link List list} is empty.
     *
     * @return true if the list is empty, otherwise false
     * @since 0.9.7
     */
    public static Matcher<List> empty() {
        return new TypeSafeMatcher<List>() {

            @Override
            public void describeTo(Description description) {
                description.appendText("list to be empty");
            }

            @Override
            protected boolean matchesSafely(List list) {
                return list.isEmpty();
            }

            @Override
            protected void describeMismatchSafely(List list, Description mismatchDescription) {
                mismatchDescription.appendText("empty was '" + list.isEmpty() + '\'');
            }

        };

    }

    protected ListMatcher() {
        // utility constructor
    }

}
