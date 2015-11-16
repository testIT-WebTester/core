package integration;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

import java.util.List;

import org.junit.Test;

import utils.pageobjects.NestedPageObjectOne;
import utils.pageobjects.NestedPageObjectThree;
import utils.pageobjects.NestedPageObjectTwo;
import utils.pageobjects.NestedPageObjectsPage;

import info.novatec.testit.webtester.pageobjects.TextField;


public class NestedPageObjectsIntegrationTest extends AbstractWebTesterIntegrationTest {

    @Override
    protected String getHTMLFilePath() {
        return "html/pageobject/nestedPageObjects.html";
    }

    @Test
    public final void test() {

        NestedPageObjectsPage page = getBrowser().create(NestedPageObjectsPage.class);

        String operand = "divOne";
        String operand2 = "divTwo";
        String operand3 = "divThree1";
        String operand4 = "divThree2";

        List<TextField> textfieldOnPage = page.getTextfields();
        assertThat(textfieldOnPage.size(), is(equalTo(4)));
        assertThat(textfieldOnPage.get(0).getText(), is(equalTo(operand)));
        assertThat(textfieldOnPage.get(1).getText(), is(equalTo(operand2)));
        assertThat(textfieldOnPage.get(2).getText(), is(equalTo(operand3)));
        assertThat(textfieldOnPage.get(3).getText(), is(equalTo(operand4)));

        NestedPageObjectOne nestedOne = page.getNestedPageObjectOne();
        assertThat(nestedOne.getTextfield().getText(), is(equalTo(operand)));

        List<TextField> textfieldsInAreaOne = nestedOne.getTextfields();
        assertThat(textfieldsInAreaOne.size(), is(equalTo(1)));
        assertThat(textfieldsInAreaOne.get(0).getText(), is(equalTo(operand)));

        NestedPageObjectTwo nestedTwo = page.getNestedPageObjectTwo();
        assertThat(nestedTwo.getTextfield().getText(), is(equalTo(operand2)));

        List<TextField> textfieldsInAreaTwo = nestedTwo.getTextfields();
        assertThat(textfieldsInAreaTwo.size(), is(equalTo(3)));
        assertThat(textfieldsInAreaTwo.get(0).getText(), is(equalTo(operand2)));
        assertThat(textfieldsInAreaTwo.get(1).getText(), is(equalTo(operand3)));
        assertThat(textfieldsInAreaTwo.get(2).getText(), is(equalTo(operand4)));

        NestedPageObjectThree nestedThree = nestedTwo.getNestedPageObjectThree();
        assertThat(nestedThree.getTextfield1().getText(), is(equalTo(operand3)));
        assertThat(nestedThree.getTextfield2().getText(), is(equalTo(operand4)));

        List<TextField> textfieldsInAreaThree = nestedThree.getTextfields();
        assertThat(textfieldsInAreaThree.size(), is(equalTo(2)));
        assertThat(textfieldsInAreaThree.get(0).getText(), is(equalTo(operand3)));
        assertThat(textfieldsInAreaThree.get(1).getText(), is(equalTo(operand4)));

    }

}
