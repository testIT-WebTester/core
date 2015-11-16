package info.novatec.testit.webtester.utils.conditions;

import java.util.Collection;
import java.util.Set;

import org.apache.commons.lang.StringUtils;

import com.google.common.base.Predicate;
import com.google.common.collect.Sets;

import info.novatec.testit.webtester.pageobjects.Select;


/**
 * Predicate to be used in order to check if a {@link Select multi-select} has a
 * certain set of selected texts. This will <u>not</u> check weather or not they
 * are the only selected texts!
 * <p>
 * <b>Example:</b> Given a selection of the texts "foo", "bar" and "xuu", a check
 * for "foo" and "bar" will return true and a check for "foo" and "kuu" will return false.
 *
 * @since 0.9.9
 */
public class SelectedTexts implements Predicate<Select> {

    private Set<String> expectedTexts;

    public SelectedTexts(String... texts) {
        this(Sets.newHashSet(texts));
    }

    public SelectedTexts(Collection<String> texts) {
        this.expectedTexts = Sets.newHashSet(texts);
    }

    @Override
    public boolean apply(Select select) {
        return select.getAllSelectedTexts().containsAll(expectedTexts);
    }

    @Override
    public String toString() {
        return String.format("selected texts: %s", StringUtils.join(expectedTexts, ", "));
    }

}
