package info.novatec.testit.webtester.support.hamcrest;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

import sun.security.krb5.internal.crypto.Des;

import info.novatec.testit.webtester.pageobjects.GenericList;


public class GenericListMatcher extends PageObjectMatcher{

    /**
     * Returns whether the number of items the {@link GenericList generic list} contains
     * matches expectations
     *
     * @param numberOfItems expected number of items
     * @return true if the numbers match, false if not
     * @since 1.2.
     */
    public static Matcher<GenericList> hasNumberOfItems(final Integer numberOfItems) {
        return new TypeSafeMatcher<GenericList>(){

            @Override
            public void describeTo(Description description) {
                description.appendText("number of items to be '" + numberOfItems + '\'');
            }

            @Override
            public void describeMismatchSafely(GenericList genericList, Description mismatchDescription){
                mismatchDescription.appendText("was '" + genericList.getNumberOfItems() + '\'');
            }

            @Override
            public boolean matchesSafely(GenericList genericList) {
                return numberOfItems.equals(genericList.getNumberOfItems());
            }
        };
    }

    /**
     * Returns whether the {@link GenericList generic list}
     * is empty.
     *
     * @return true if the generic list is empty
     * @since 1.2.
     */
    public static Matcher<GenericList> isEmpty() {
        return new TypeSafeMatcher<GenericList>(){

            @Override
            public void describeTo(Description description) {
                description.appendText("list to be empty.");
            }

            @Override
            public void describeMismatchSafely(GenericList genericList, Description mismatchDescription) {
                mismatchDescription.appendText("empty was '" + genericList.isEmpty() + '\'');
            }

            @Override
            public boolean matchesSafely(GenericList genericList) {
                return genericList.isEmpty();
            }
        };

    }

    public GenericListMatcher() {
        /*  utility constructor */
    }

}
