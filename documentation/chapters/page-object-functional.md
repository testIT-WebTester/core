[Home](../README.md)

# Functional Page Objects
The `PageObject` subclasses on this page are provided by the `webtester-core` module.
Although they might map in some cases to a specific HTML element (i.e. `Link`) they represent _functional_ groups of HTML elements.

The `Button` class for example maps to `<button/>`, `<input type="reset">`, `<input type="submit">` and `<input type="button">`.

In contrast to the generic interactions offered by Selenium's `WebElement` interface these _functional_ `PageObject` classes
only provide methods which are useful for the given context / their type. A `Select` does not provide methods to change its text, 
but it will have methods to change selection based on index, value or text.

## Validation of Page Objects
By default page objects will match any HTML tag in form of a WebElement. Functional page objects on the other hand are 
limited to a certain amount of valid HTML tags and attribute combinations. This is done by annotating them with 
```@Mapping``` or ```@Mappings``` in case there is more then one valid combination.

Annotating any page object with ```@Mapping``` will trigger a validation logic anytime the underlying web element is 
resolved. Depending on the cache level of the page object this will be the first (Caching = ON) or every (Caching = OFF) 
time an interaction method is called. In case the validation fails a ```WrongElementClassException``` is thrown.

### @Mapping
The ```@Mapping``` annotation is used to define a valid combination of tag, attribute and attribute values of a web 
element to be used with a page object class.

**There are a number of different ways to use this:**

* ```@Mapping(tag="div")``` Will be evaluated as 'valid' in case the web element has the tag 'div'.
* ```@Mapping(tag="select", attribute="multiple")``` Will be evaluated as 'valid' in case the web element has the tag 
'select' and the 'multiple' attribute is
present.
* ```@Mapping(tag="select", attribute="!multiple")``` Will be evaluated as 'valid' in case the web element has the tag 
'select' and the 'multiple' attribute is not present.
* ```@Mapping(tag="input", attribute="type", values={"text", "password"})``` Will be evaluated as 'valid' in case the web 
element has the tag 'input' and the 'type' attribute has either the 'text' oder 'password' value.
* ```@Mapping(validator=FooValidator.class)``` Will create a new instance of the given validator class and use it to 
evaluate the web element.

### @Mappings
The ```@Mappings``` annotation is a grouping annotation that can combine a number of ```@Mapping``` annotations.

**Example:***
```java
@Mappings({@Mapping(tag="h1", @Mapping(tag="h2")})
public class Headline extends PageObject {
    /// this page object will be valid for 'h1' and 'h2' tags
}
```

## Button
**Extends:** PageObject

**HTML Tags:**

- `<button/>`
- `<input type="reset"/>`
- `<input type="submit"/>`
- `<input type="button"/>`

## Checkbox
**Extends:** PageObject

**HTML Tags:**

- `<input type="checkbox"/>`

## Div
**Extends:** PageObject

**HTML Tags:**

- `<div/>`

## Form
**Extends:** PageObject

**HTML Tags:**

- `<form/>`

## GenericTextField
**Extends:** PageObject

**HTML Tags:**

- `<input/>`
- `<input type=""/>`
- `<input type="text"/>`
- `<input type="password"/>`
- `<input type="number"/>`

## Headline
**Extends:** PageObject

**HTML Tags:**

- `<h1/>`
- `<h2/>`
- `<h3/>`
- `<h4/>`
- `<h5/>`
- `<h6/>`

## IFrame
**Extends:** PageObject

**HTML Tags:**

- `<iframe/>`

## Image
**Extends:** PageObject

**HTML Tags:**

- `<img/>`

## Link
**Extends:** PageObject

**HTML Tags:**

- `<a/>`

## List
**Extends:** PageObject

**HTML Tags:**

- `<ul/>`
- `<ol/>`

## ListItem
**Extends:** PageObject

**HTML Tags:**

- `<li/>`

## NumberField
**Extends:** TextField

**HTML Tags:**

- `<input type="number"/>`

## PasswordField
**Extends:** TextField

**HTML Tags:**

- `<input type="password"/>`

## RadioButton
**Extends:** PageObject

**HTML Tags:**

- `<input type="radio"/>`

## SearchField
**Extends:** GenericTextField

**HTML Tags:**

- `<input type="search"/>`

## Select
**Extends:** PageObject

**HTML Tags:**

- `<select/>`

## Span
**Extends:** PageObject

**HTML Tags:**

- `<span/>`

## Table
**Extends:** PageObject

**HTML Tags:**

- `<table/>`

## TableField
**Extends:** PageObject

**HTML Tags:**

- `<td/>`
- `<th/>`

## TableRow
**Extends:** PageObject

**HTML Tags:**

- `<tr/>`

## TextArea
**Extends:** TextField

**HTML Tags:**

- `<textarea/>`

## TextField
**Extends:** GenericTextField

**HTML Tags:**

- `<input/>`
- `<input type=""/>`
- `<input type="text"/>`
- `<input type="password"/>`
- `<input type="number"/>`
