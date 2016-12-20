[Home](../README.md)

# Hamcrest Matchers
The support module `webtester-support-hamcrest` provides Hamcrest `Matcher` implementations for each of the functional
page objects provided by the `webtester-core module`:

- `ButtonMatcher`
- `CheckboxMatcher`
- `GenericListMatcher`
- `GenericTextFieldMatcher`
- `IFrameMatcher`
- `ImageMatcher`
- `ListMatcher`
- `NumberFieldMatcher`
- `PageObjectMatcher`
- `RadioButtonMatcher`
- `TableFieldMatcher`
- `TableMatcher`
- `TableRowMatcher`
- `TextAreaMatcher`
- `TextFieldMatcher`

All state getter methods of supported page objects have a corresponding matcher implementation.

## Example
```java
TextField username = ...;
TextFieldMatcher.assertThat(username, hasText("fooUser"));
```

# Linked Documentation

- [Functional Page Objects](page-object-functional.md)
