[Home](../README.md)

# AssertJ Assertions
The support module `webtester-support-assertj` provides AssertJ assertion implementations for each of the functional
page objects provided by the `webtester-core module`:

- `ButtonAssert`
- `CheckboxAssert`
- `GenericListAssert`
- `GenericTextFieldAssert`
- `IFrameAssert`
- `ImageAssert`
- `ListAssert`
- `NumberFieldAssert`
- `PageObjectAssert`
- `RadioButtonAssert`
- `TableAssert`
- `TableFieldAssert`
- `TableRowAssert`
- `TextAreaAssert`
- `TextFieldAssert`

All of these can be easily accessed via the `WebTesterAssertions` utility class.
This class provides factory methods returning the appropriate assertion implementation depending on the given type.

All state getter methods of supported page objects have a corresponding assertion implementation.

## Example
```java
TextField username = ...;
WebTesterAssertions.assertThat(username).hasText("fooUser");
```

# Linked Documentation

- [Functional Page Objects](page-object-functional.md)
