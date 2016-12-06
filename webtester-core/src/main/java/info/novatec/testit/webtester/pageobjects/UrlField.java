package info.novatec.testit.webtester.pageobjects;


import info.novatec.testit.webtester.api.annotations.Mapping;

@Mapping(tag = "input", attribute = "type", values = { "url" })
public class UrlField extends GenericTextField<UrlField>{
}
