package utils.pageobjects;

import java.util.List;

import info.novatec.testit.webtester.api.annotations.IdentifyUsing;
import info.novatec.testit.webtester.api.enumerations.Method;
import info.novatec.testit.webtester.pageobjects.PageObject;
import info.novatec.testit.webtester.pageobjects.TextField;


public class NestedPageObjectThree extends PageObject {

    @IdentifyUsing("otherTextfield1")
    private TextField textfield1;

    @IdentifyUsing("otherTextfield2")
    private TextField textfield2;

    @IdentifyUsing(method = Method.XPATH, value = ".//input")
    private List<TextField> textfields;

    public TextField getTextfield1() {
        return textfield1;
    }

    public TextField getTextfield2() {
        return textfield2;
    }

    public List<TextField> getTextfields() {
        return textfields;
    }

}
