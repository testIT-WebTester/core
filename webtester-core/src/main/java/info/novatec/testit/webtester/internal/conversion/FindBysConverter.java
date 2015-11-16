package info.novatec.testit.webtester.internal.conversion;

import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.pagefactory.AbstractAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import info.novatec.testit.webtester.api.annotations.Internal;


@Internal
public class FindBysConverter extends AbstractAnnotations {

    private static final Logger logger = LoggerFactory.getLogger(FindBysConverter.class);

    private FindBys findBys;

    public FindBysConverter(FindBys findBys) {
        this.findBys = findBys;
    }

    @Override
    public By buildBy() {
        logger.trace("converting @FindBys {} to by", findBys);
        return buildByFromFindBys(findBys);
    }

    @Override
    public boolean isLookupCached() {
        return false;
    }

}
