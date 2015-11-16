package integration;

import static info.novatec.testit.webtester.utils.Conditions.attributeWithValue;
import static info.novatec.testit.webtester.utils.Conditions.has;
import static info.novatec.testit.webtester.utils.Conditions.text;
import static info.novatec.testit.webtester.utils.Identifications.css;
import static info.novatec.testit.webtester.utils.Identifications.id;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;

import java.util.List;

import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

import info.novatec.testit.webtester.pageobjects.GenericElement;
import info.novatec.testit.webtester.pageobjects.PageObject;
import info.novatec.testit.webtester.pageobjects.TextField;


@RunWith(Enclosed.class)
public class AdHocFindIntegrationTest {

    public static class FromBrowser extends AbstractAdHocFindTest {

        /* CSS Selector */

        @Test
        public void usingCssSelectorFindSingleGeneric() {
            GenericElement textField = getBrowser()
                .find("#textField");
            verifyPageObjectWasFound(textField);
        }

        @Test
        public void usingCssSelectorFindManyGenerics() {
            List<GenericElement> textFields = getBrowser()
                .findMany(".textField");
            verifyPageObjectsWereFound(textFields, 5);
        }

        @Test
        public void usingCssSelectorFindManyGenericsAndFilterThem() {
            List<GenericElement> filteredTextFields = getBrowser()
                .findMany(".textField")
                .filter(attributeWithValue("value", "foo"));
            verifyPageObjectsWereFound(filteredTextFields, 2);
        }

        /* Identification First */

        @Test
        public void usingIdentificationFindSingleGeneric() {
            GenericElement textField = getBrowser()
                .findBy(id("textField"))
                .asGeneric();
            verifyPageObjectWasFound(textField);
        }

        @Test
        public void usingIdentificationFindSingleTextField() {
            TextField textField = getBrowser()
                .findBy(id("textField"))
                .as(TextField.class);
            verifyPageObjectWasFound(textField);
        }

        @Test
        public void usingIdentificationFindManyGenerics() {
            List<GenericElement> textFields = getBrowser()
                .findBy(css(".textField"))
                .asManyGenerics();
            verifyPageObjectsWereFound(textFields, 5);
        }

        @Test
        public void usingIdentificationFindManyTextFields() {
            List<TextField> textFields = getBrowser()
                .findBy(css(".textField"))
                .asMany(TextField.class);
            verifyPageObjectsWereFound(textFields, 5);
        }

        @Test
        public void usingIdentificationFindManyGenericsAndFilterThem() {
            List<GenericElement> filteredTextFields = getBrowser()
                .findBy(css(".textField"))
                .asManyGenerics()
                .filter(attributeWithValue("value", "foo"));
            verifyPageObjectsWereFound(filteredTextFields, 2);
        }

        @Test
        public void usingIdentificationFindManyTextFieldsAndFilterThem() {
            List<TextField> filteredTextFields = getBrowser()
                .findBy(css(".textField"))
                .asMany(TextField.class)
                .filter(has(text("foo")));
            verifyPageObjectsWereFound(filteredTextFields, 2);
        }

        /* Class First */

        @Test
        public void usingClassFirstToFindSingleTextFieldWithCssSelector() {
            TextField textField = getBrowser()
                .find(TextField.class)
                .by("#textField");
            verifyPageObjectWasFound(textField);
        }

        @Test
        public void usingClassFirstToFindSingleTextFieldWithIdentification() {
            TextField textField = getBrowser()
                .find(TextField.class)
                .by(id("textField"));
            verifyPageObjectWasFound(textField);
        }

        @Test
        public void usingClassFirstToFindManyTextFieldsWithCssSelector() {
            List<TextField> textFields = getBrowser()
                .find(TextField.class)
                .manyBy(".textField");
            verifyPageObjectsWereFound(textFields, 5);
        }

        @Test
        public void usingClassFirstToFindManyTextFieldsWithIdentification() {
            List<TextField> textFields = getBrowser()
                .find(TextField.class)
                .manyBy(css(".textField"));
            verifyPageObjectsWereFound(textFields, 5);
        }

        @Test
        public void usingClassFirstToFindManyTextFieldsWithCssSelectorAndFilterThem() {
            List<TextField> filteredTextFields = getBrowser()
                .find(TextField.class)
                .manyBy(".textField")
                .filter(has(text("foo")));
            verifyPageObjectsWereFound(filteredTextFields, 2);
        }

        @Test
        public void usingClassFirstToFindManyTextFieldsWithIdentificationAndFilterThem() {
            List<TextField> filteredTextFields = getBrowser()
                .find(TextField.class)
                .manyBy(css(".textField"))
                .filter(has(text("foo")));
            verifyPageObjectsWereFound(filteredTextFields, 2);
        }

    }

    public static class FromPageObject extends AbstractAdHocFindTest {

        @Test
        public void findingPageObjectsWithinPageObjects() {

            List<GenericElement> textFieldsInAllGroups = getBrowser()
                .find("#groups")
                .findMany(".groupTextField");
            verifyPageObjectsWereFound(textFieldsInAllGroups, 7);

            List<GenericElement> textFieldsInGroup1 = getBrowser()
                .find("#group1")
                .findMany(".groupTextField");
            verifyPageObjectsWereFound(textFieldsInGroup1, 5);

            List<GenericElement> textFieldsInGroup2 = getBrowser()
                .find("#group2")
                .findMany(".groupTextField");
            verifyPageObjectsWereFound(textFieldsInGroup2, 2);

        }

        /* CSS Selector */

        @Test
        public void usingCssSelectorFindSingleGeneric() {
            GenericElement textField = getBrowser()
                .find("#group1")
                .find("#group1\\:txt1");
            verifyPageObjectWasFound(textField);
        }

        @Test
        public void usingCssSelectorFindManyGenerics() {
            List<GenericElement> textFields = getBrowser()
                .find("#group1")
                .findMany(".groupTextField");
            verifyPageObjectsWereFound(textFields, 5);
        }

        @Test
        public void usingCssSelectorFindManyGenericsAndFilterThem() {
            List<GenericElement> filteredTextFields = getBrowser()
                .find("#group1")
                .findMany(".groupTextField")
                .filter(attributeWithValue("value", "foo"));
            verifyPageObjectsWereFound(filteredTextFields, 2);
        }

        /* Identification First */

        @Test
        public void usingIdentificationFindSingleGeneric() {
            GenericElement textField = getBrowser()
                .find("#group1")
                .findBy(id("group1:txt1"))
                .asGeneric();
            verifyPageObjectWasFound(textField);
        }

        @Test
        public void usingIdentificationFindSingleTextField() {
            TextField textField = getBrowser()
                .find("#group1")
                .findBy(id("group1:txt1"))
                .as(TextField.class);
            verifyPageObjectWasFound(textField);
        }

        @Test
        public void usingIdentificationFindManyGenerics() {
            List<GenericElement> textFields = getBrowser()
                .find("#group1")
                .findBy(css(".groupTextField"))
                .asManyGenerics();
            verifyPageObjectsWereFound(textFields, 5);
        }

        @Test
        public void usingIdentificationFindManyTextFields() {
            List<TextField> textFields = getBrowser()
                .find("#group1")
                .findBy(css(".groupTextField"))
                .asMany(TextField.class);
            verifyPageObjectsWereFound(textFields, 5);
        }

        @Test
        public void usingIdentificationFindManyGenericsAndFilterThem() {
            List<GenericElement> filteredTextFields = getBrowser()
                .find("#group1")
                .findBy(css(".groupTextField"))
                .asManyGenerics()
                .filter(attributeWithValue("value", "foo"));
            verifyPageObjectsWereFound(filteredTextFields, 2);
        }

        @Test
        public void usingIdentificationFindManyTextFieldsAndFilterThem() {
            List<TextField> filteredTextFields = getBrowser()
                .find("#group1")
                .findBy(css(".groupTextField"))
                .asMany(TextField.class)
                .filter(has(text("foo")));
            verifyPageObjectsWereFound(filteredTextFields, 2);
        }

        /* Class First */

        @Test
        public void usingClassFirstToFindSingleTextFieldWithCssSelector() {
            TextField textField = getBrowser()
                .find("#group1")
                .find(TextField.class)
                .by("#group1\\:txt1");
            verifyPageObjectWasFound(textField);
        }

        @Test
        public void usingClassFirstToFindSingleTextFieldWithIdentification() {
            TextField textField = getBrowser()
                .find("#group1")
                .find(TextField.class)
                .by(id("group1:txt1"));
            verifyPageObjectWasFound(textField);
        }

        @Test
        public void usingClassFirstToFindManyTextFieldsWithCssSelector() {
            List<TextField> textFields = getBrowser()
                .find("#group1")
                .find(TextField.class)
                .manyBy(".groupTextField");
            verifyPageObjectsWereFound(textFields, 5);
        }

        @Test
        public void usingClassFirstToFindManyTextFieldsWithIdentification() {
            List<TextField> textFields = getBrowser()
                .find("#group1")
                .find(TextField.class)
                .manyBy(css(".groupTextField"));
            verifyPageObjectsWereFound(textFields, 5);
        }

        @Test
        public void usingClassFirstToFindManyTextFieldsWithCssSelectorAndFilterThem() {
            List<TextField> filteredTextFields = getBrowser()
                .find("#group1")
                .find(TextField.class)
                .manyBy(".groupTextField")
                .filter(has(text("foo")));
            verifyPageObjectsWereFound(filteredTextFields, 2);
        }

        @Test
        public void usingClassFirstToFindManyTextFieldsWithIdentificationAndFilterThem() {
            List<TextField> filteredTextFields = getBrowser()
                .find("#group1")
                .find(TextField.class)
                .manyBy(css(".groupTextField"))
                .filter(has(text("foo")));
            verifyPageObjectsWereFound(filteredTextFields, 2);
        }

    }

    public static abstract class AbstractAdHocFindTest extends AbstractWebTesterIntegrationTest {

        @Override
        protected final String getHTMLFilePath() {
            return "html/adHocFinding.html";
        }

        final void verifyPageObjectWasFound(PageObject pageObject) {
            // getting the web element triggers the lazy load / verification
            pageObject.getWebElement();
        }

        final void verifyPageObjectsWereFound(List<? extends PageObject> pageObjects, int expectedSize) {
            assertThat(pageObjects, hasSize(expectedSize));
            for (PageObject pageObject : pageObjects) {
                // getting the web element triggers the lazy load / verification
                pageObject.getWebElement();
            }
        }

    }

}
