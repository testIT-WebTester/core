package info.novatec.testit.webtester.utils.conditions;

import java.util.Collection;
import java.util.Set;

import org.apache.commons.lang.StringUtils;

import com.google.common.base.Predicate;
import com.google.common.collect.Sets;

import info.novatec.testit.webtester.pageobjects.Select;


/**
 * Predicate to be used in order to check if a {@link Select multi-select} has a
 * certain set of selected indices. This will <u>not</u> check weather or not they
 * are the only selected indices!
 * <p>
 * <b>Example:</b> Given a selection of the indices #0, #1 and #2, a check
 * for #1 and #2 will return true and a check for #1 and #3 will return false.
 *
 * @since 0.9.9
 */
public class SelectedIndices implements Predicate<Select> {

    private Set<Integer> expectedIndices;

    public SelectedIndices(Integer... indices) {
        this(Sets.newHashSet(indices));
    }

    public SelectedIndices(Collection<Integer> indices) {
        this.expectedIndices = Sets.newHashSet(indices);
    }

    @Override
    public boolean apply(Select select) {
        return select.getAllSelectedIndices().containsAll(expectedIndices);
    }

    @Override
    public String toString() {
        return String.format("selected indices: %s", StringUtils.join(expectedIndices, ", "));
    }

}
