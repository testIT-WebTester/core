package utils.pageobjects;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

import java.util.List;
import javax.annotation.PostConstruct;

import info.novatec.testit.webtester.api.annotations.IdentifyUsing;
import info.novatec.testit.webtester.api.enumerations.Method;
import info.novatec.testit.webtester.pageobjects.Headline;
import info.novatec.testit.webtester.pageobjects.PageObject;
import info.novatec.testit.webtester.pageobjects.TextField;


public class NestedPageObjectsPage extends PageObject {

    @IdentifyUsing("title")
    private Headline title;

    @IdentifyUsing(method = Method.XPATH, value = ".//input")
    private List<TextField> textfields;

    @IdentifyUsing("divOne")
    private NestedPageObjectOne nestedPageObjectOne;

    @IdentifyUsing("divTwo")
    private NestedPageObjectTwo nestedPageObjectTwo;

    @PostConstruct
    protected void assertPageTitleIsCorrect() {
        assertThat(title.getVisibleText(), is(equalTo("ContentArea Test")));
    }

    public NestedPageObjectOne getNestedPageObjectOne() {
        return nestedPageObjectOne;
    }

    public NestedPageObjectTwo getNestedPageObjectTwo() {
        return nestedPageObjectTwo;
    }

    public List<TextField> getTextfields() {
        return textfields;
    }

}
