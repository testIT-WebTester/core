package info.novatec.testit.webtester.api.browser;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertThat;

import org.junit.Test;


public class BrowserIdentificationTest {

    BrowserIdentification one = new BrowserIdentification();
    BrowserIdentification two = new BrowserIdentification();

    @Test
    public void sameInstanceIsEqual() {
        assertThat(one, is(equalTo(one)));
    }

    @Test
    public void differentInstancesAreNotEqual() {
        assertThat(one, is(not(equalTo(two))));
    }

}
