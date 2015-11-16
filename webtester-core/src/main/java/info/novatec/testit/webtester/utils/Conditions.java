package info.novatec.testit.webtester.utils;

import java.util.Collection;

import com.google.common.base.Predicate;

import info.novatec.testit.webtester.utils.conditions.AttributeWithValue;
import info.novatec.testit.webtester.utils.conditions.Disabled;
import info.novatec.testit.webtester.utils.conditions.Editable;
import info.novatec.testit.webtester.utils.conditions.Enabled;
import info.novatec.testit.webtester.utils.conditions.FileName;
import info.novatec.testit.webtester.utils.conditions.Interactable;
import info.novatec.testit.webtester.utils.conditions.Invisible;
import info.novatec.testit.webtester.utils.conditions.Label;
import info.novatec.testit.webtester.utils.conditions.Present;
import info.novatec.testit.webtester.utils.conditions.ReadOnly;
import info.novatec.testit.webtester.utils.conditions.Selected;
import info.novatec.testit.webtester.utils.conditions.SelectedIndex;
import info.novatec.testit.webtester.utils.conditions.SelectedIndices;
import info.novatec.testit.webtester.utils.conditions.SelectedText;
import info.novatec.testit.webtester.utils.conditions.SelectedTexts;
import info.novatec.testit.webtester.utils.conditions.SelectedValue;
import info.novatec.testit.webtester.utils.conditions.SelectedValues;
import info.novatec.testit.webtester.utils.conditions.SourcePath;
import info.novatec.testit.webtester.utils.conditions.TextContains;
import info.novatec.testit.webtester.utils.conditions.TextEquals;
import info.novatec.testit.webtester.utils.conditions.Value;
import info.novatec.testit.webtester.utils.conditions.Visible;
import info.novatec.testit.webtester.utils.conditions.VisibleTextContains;
import info.novatec.testit.webtester.utils.conditions.VisibleTextEquals;
import info.novatec.testit.webtester.utils.conditions.syntax.Has;
import info.novatec.testit.webtester.utils.conditions.syntax.Is;
import info.novatec.testit.webtester.utils.conditions.syntax.Not;


/**
 * Utility class used to produce all kinds of predicate conditions to be used by
 * the {@link Waits} API.
 *
 * @since 0.9.9
 */
public final class Conditions {

    private Conditions() {
        // utility class constructor
    }

    /* syntax */

    public static <T> Is<T> is(Predicate<T> predicate) {
        return new Is<T>(predicate);
    }

    public static <T> Has<T> has(Predicate<T> predicate) {
        return new Has<T>(predicate);
    }

    public static <T> Not<T> not(Predicate<T> predicate) {
        return new Not<T>(predicate);
    }

    /* other */

    public static AttributeWithValue attributeWithValue(String attributeName, Object value) {
        return new AttributeWithValue(attributeName, value);
    }

    public static Disabled disabled() {
        return new Disabled();
    }

    public static Editable editable() {
        return new Editable();
    }

    public static Enabled enabled() {
        return new Enabled();
    }

    public static FileName fileName(String fileName) {
        return new FileName(fileName);
    }

    public static Interactable interactable() {
        return new Interactable();
    }

    public static Invisible invisible() {
        return new Invisible();
    }

    public static Label label(String label) {
        return new Label(label);
    }

    public static Present present() {
        return new Present();
    }

    public static ReadOnly readOnly() {
        return new ReadOnly();
    }

    public static Selected selected() {
        return new Selected();
    }

    public static SelectedIndex selectedIndex(Integer index) {
        return new SelectedIndex(index);
    }

    public static SelectedIndices selectedIndices(Integer... indices) {
        return new SelectedIndices(indices);
    }

    public static SelectedIndices selectedIndices(Collection<Integer> indices) {
        return new SelectedIndices(indices);
    }

    public static SelectedText selectedText(String text) {
        return new SelectedText(text);
    }

    public static SelectedTexts selectedTexts(String... texts) {
        return new SelectedTexts(texts);
    }

    public static SelectedTexts selectedTexts(Collection<String> texts) {
        return new SelectedTexts(texts);
    }

    public static SelectedValue selectedValue(String value) {
        return new SelectedValue(value);
    }

    public static SelectedValues selectedValues(String... values) {
        return new SelectedValues(values);
    }

    public static SelectedValues selectedValues(Collection<String> values) {
        return new SelectedValues(values);
    }

    public static SourcePath sourcePath(String sourcePath) {
        return new SourcePath(sourcePath);
    }

    public static TextEquals text(String text) {
        return new TextEquals(text);
    }

    public static TextContains textContaining(String partialText) {
        return new TextContains(partialText);
    }

    public static <T> Value<T> value(T value) {
        return new Value<T>(value);
    }

    public static Visible visible() {
        return new Visible();
    }

    public static VisibleTextEquals visibleText(String text) {
        return new VisibleTextEquals(text);
    }

    public static VisibleTextContains visibleTextContaining(String partialText) {
        return new VisibleTextContains(partialText);
    }

}
