package info.novatec.testit.webtester.pageobjects;


import info.novatec.testit.webtester.api.annotations.Mapping;

@Mapping(tag = "input", attribute = "type", values = { "search" })
public class SearchField extends GenericTextField<SearchField>{
}
