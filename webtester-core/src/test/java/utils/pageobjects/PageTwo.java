package utils.pageobjects;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

import java.util.List;
import javax.annotation.PostConstruct;

import info.novatec.testit.webtester.api.annotations.IdentifyUsing;
import info.novatec.testit.webtester.api.enumerations.Method;
import info.novatec.testit.webtester.pageobjects.Headline;
import info.novatec.testit.webtester.pageobjects.ListItem;
import info.novatec.testit.webtester.pageobjects.PageObject;
import info.novatec.testit.webtester.pageobjects.TextField;


public class PageTwo extends PageObject {

    @IdentifyUsing("title")
    private Headline title;

    @IdentifyUsing("message")
    private TextField message;

    @IdentifyUsing(method = Method.XPATH, value = ".//li")
    private List<ListItem> listItems;

    @PostConstruct
    protected void assertPageTitleIsCorrect() {
        assertThat(title.getVisibleText(), is(equalTo("Welcome to Page 2!")));
    }

    @PostConstruct
    protected void setValueInMessageTextfield() {
        message.setText("hello world!");
    }

    public String getMessageValue() {
        return message.getText();
    }

    public String[] getListItemValues() {
        String[] returnValue = new String[listItems.size()];
        for (int i = 0; i < listItems.size(); i++) {
            returnValue[i] = listItems.get(i).getVisibleText();
        }
        return returnValue;
    }

}
