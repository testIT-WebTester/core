[Home](../README.md)

# Event System

## EventSystem
The `EventSystem` is a static class providing methods for firing `Events`.
`EventListener` instances can be registered at the system as well as deregistered once they are no longer needed.

## Event
An `Event` contains all the information needed to understand what happened.
Since it is an interface implementing a custom event is very easy.

In general it is recommended to treat events as data objects.
They should not contain references to services or larger parts of the system.

The `webtester-core` module provides events for all it's actions:

**Events from Browser:**

- `AcceptedAlertEvent`
- `ClosedBrowserEvent`
- `ClosedWindowEvent`
- `DeclinedAlertEvent`
- `ExceptionEvent`
- `MaximizedWindowEvent`
- `NavigatedBackwardsEvent`
- `NavigatedForwardsEvent`
- `OpenedUrlEvent`
- `RefreshedPageEvent`
- `SavedSourceCodeEvent`
- `SwitchedToDefaultContentEvent`
- `SwitchedToFrameEvent`
- `SwitchedToWindowEvent`
- `TookScreenshotEvent`

**Events from Page Objects:**

- `ClickedEvent`
- `DoubleClickedEvent`
- `FormSubmittedEvent`
- `SelectedByIndexEvent`
- `SelectedByTextEvent`
- `SelectedByValueEvent`
- `SelectionChangedEvent`
- `TextAppendedEvent`
- `TextClearedEvent`
- `TextSetEvent`

## EventListener
An `EventListener` is a simple interface providing a single method `void eventOccurred(Event event);`.
Instances of this interface can be registered at the `EventSystem` in order to be called every time an `Event` is fired.

## Examples
```java
...
private static EventListener customListener;
 
@BeforeClass
public static void registerEventListener () {
    customListener = new EventListener() {
        public void eventOccurred (Event event) {
            if (event instanceof ClickEvent) {
                System.out.println("something was clicked: " + event);
            }
        }
    };
    EventSystem.registerListener(customListener);
}
 
@AfterClass
public static void deregisterEventListener () {
    EventSystem.deregisterListener(customListener);
}
...
```
