package info.novatec.testit.webtester.internal.conversion;

import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.pagefactory.AbstractAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import info.novatec.testit.webtester.api.annotations.Internal;


@Internal
public class FindByConverter extends AbstractAnnotations {

    private static final Logger logger = LoggerFactory.getLogger(FindByConverter.class);

    private FindBy findBy;

    public FindByConverter(FindBy findBy) {
        this.findBy = findBy;
    }

    @Override
    public By buildBy() {
        logger.trace("converting @FindBy {} to by", findBy);
        return buildByFromFindBy(findBy);
    }

    @Override
    public boolean isLookupCached() {
        return false;
    }

}
