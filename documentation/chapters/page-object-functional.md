[Home](../README.md)

# Functional Page Objects
The `PageObject` subclasses on this page are provided by the `webtester-core` module.
Although they might map in some cases to a specific HTML element (i.e. `Link`) they represent _functional_ groups of HTML elements.

The `Button` class for example maps to `<button/>`, `<input type="reset">`, `<input type="submit">` and `<input type="button">`.

In contrast to the generic interactions offered by Selenium's `WebElement` interface these _functional_ `PageObject` classes
only provide methods which are useful for the given context / their type. A `Select` does not provide methods to change its text, 
but it will have methods to change selection based on index, value or text.

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
**Extends:** PageObject

**HTML Tags:**

- Intended for:

  - `<input/>`
  - `<input type=""/>`
  - `<input type="text"/>`

- Also accepts everything that _isn't_:

  - `<input type="button"/>`
  - `<input type="reset"/>`
  - `<input type="submit"/>`
  - `<input type="checkbox"/>`
  - `<input type="radio"/>`
  - `<input type="image"/>`
  - `<input type="range"/>`
  - `<input type="hidden"/>`
