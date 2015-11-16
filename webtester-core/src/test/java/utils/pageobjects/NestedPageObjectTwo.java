package utils.pageobjects;

import java.util.List;

import info.novatec.testit.webtester.api.annotations.IdentifyUsing;
import info.novatec.testit.webtester.api.enumerations.Method;
import info.novatec.testit.webtester.pageobjects.PageObject;
import info.novatec.testit.webtester.pageobjects.TextField;


public class NestedPageObjectTwo extends PageObject {

    @IdentifyUsing("textfield")
    private TextField textfield;

    @IdentifyUsing(method = Method.XPATH, value = ".//input")
    private List<TextField> textfields;

    @IdentifyUsing("divThree")
    private NestedPageObjectThree nestedPageObjectThree;

    public TextField getTextfield() {
        return textfield;
    }

    public List<TextField> getTextfields() {
        return textfields;
    }

    public NestedPageObjectThree getNestedPageObjectThree() {
        return nestedPageObjectThree;
    }

}
