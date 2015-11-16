package utils.pageobjects;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

import javax.annotation.PostConstruct;

import info.novatec.testit.webtester.api.annotations.IdentifyUsing;
import info.novatec.testit.webtester.api.enumerations.Method;
import info.novatec.testit.webtester.pageobjects.Button;
import info.novatec.testit.webtester.pageobjects.Headline;
import info.novatec.testit.webtester.pageobjects.PageObject;
import info.novatec.testit.webtester.pageobjects.PasswordField;
import info.novatec.testit.webtester.pageobjects.TextField;


public class PageOne extends PageObject {

    @IdentifyUsing(method = Method.XPATH, value = ".//h1[@id='title']", elementname = "Title")
    private Headline title;

    @IdentifyUsing("username")
    private TextField username;

    @IdentifyUsing(value = "password", elementname = "Password")
    private PasswordField password;

    @IdentifyUsing("submit")
    private Button login;

    @PostConstruct
    protected void assertPageTitleIsCorrect() {
        assertThat(title.getVisibleText(), is(equalTo("PageObject Test")));
    }

    public PageTwo executeLogin(String usernameValue, String passwordValue) {
        username.setText(usernameValue);
        password.setText(passwordValue);
        login.click();
        return create(PageTwo.class);
    }

}
