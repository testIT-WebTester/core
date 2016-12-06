package info.novatec.testit.webtester.pageobjects;


import info.novatec.testit.webtester.api.annotations.Mapping;

@Mapping(tag = "input", attribute = "type", values = { "email" })
public class EmailField extends GenericTextField<EmailField> {
}
