package info.novatec.testit.webtester.pageobjects;

import info.novatec.testit.webtester.api.annotations.Mapping;

/**
 * Represents a HTML text field. The following HTML elements are supported text
 * fields:
 * <ul>
 * <li><b>tag:</b> input <b>type:</b> -</li>
 * <li><b>tag:</b> input <b>type:</b> ""</li>
 * <li><b>tag:</b> input <b>type:</b> text</li>
 * </ul>
 * The following HTML elements are supported but not strictly text fields:
 * <ul>
 * <li><b>tag:</b> input <b>type:</b> password</li>
 * <li><b>tag:</b> input <b>type:</b> number</li>
 * </ul>
 *
 * @since 0.9.0
 */
@Mapping(tag = "input", attribute = "type", values = { "", "text", "password", "number" })
public class TextField extends GenericTextField<TextField>{

}
