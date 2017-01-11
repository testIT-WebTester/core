[Home](../README.md)

# Configuration

The behavior of WebTester can be configured on a browser by browser basis
by providing a `Configuration` instance while creating the `Browser`.

A `Configuration` instance can be created by using a `ConfigurationBuilder` implementation.
That instance can then be used to customize the browser's configuration using a `BrowserBuilder`.

## BaseConfigurationBuilder
The `BaseConfigurationBuilder` will use a `BaseConfiguration` instance as a starting point.
It takes `ConfigurationAdapter` and `ConfigurationExporter` instances as options before the `build()` operation.

## ConfigurationAdapter
A `ConfigurationAdapter` is used to change properties of an existing `Configuration`.
This is done using a callback method `adapt(Configuration c)`.

The following adapters are provided by the `webtester-core` module:

- `DefaultFileConfigurationAdapter`
- `GlobalFileConfigurationAdapter`
- `LocalFileConfigurationAdapter`
- `TestItHomeFileConfigurationAdapter`

**DefaultFileConfigurationAdapter**
This adapter looks up a properties file called `testit-webtester-default.properties` on the classpath's root level
and loads each contained key / value pair into the provided `Configuration` instance.

**GlobalFileConfigurationAdapter**
This adapter looks up a properties file called `testit-webtester-global.properties` on the classpath's root level
and loads each contained key / value pair into the provided `Configuration` instance.

**LocalFileConfigurationAdapter**
This adapter looks up a properties file called `testit-webtester.properties` on the classpath's root level
and loads each contained key / value pair into the provided `Configuration` instance.

**TestItHomeFileConfigurationAdapter**
This adapter looks up a properties file called `testit-webtester.properties` inside the `$TESTIT_HOME/config` folder
and loads each contained key / value pair into the provided `Configuration` instance.

## ConfigurationExporter
A `ConfigurationExporter` is used to "export" a `Configuration` to another System.
This is done by using a callback method `export(String key, Object value)` for each key / value pair of the `Configuration`.

The following exporters are provided by the `webtester-core` module:

- SystemPropertyConfigurationExporter

**SystemPropertyConfigurationExporter**
This exporter can be used to export each key / value pair as system properties in order to make them accessible using `System#getProperty(String key)`.

## Default Configuration
Since not everyone needs to customize the configuration in the context of his or her project
a 'DefaultConfigurationBuilder' is provided which uses the following adapters (in order)
and no exporters to build a Configuration :
   
- `DefaultFileConfigurationAdapter`
- `GlobalFileConfigurationAdapter`
- `LocalFileConfigurationAdapter`
   
**Note:** This builder is also used in case a `Browser` is [build](browser.md) without providing a custom `Configuration` instance.

## Example
```java
ConfigurationAdapter adapter1 = ...;
ConfigurationAdapter adapter2 = ...;
ConfigurationExporter exporter = ...;
 
// this will adapt a base configuration first with 'adapter1' and then with 'adapter2'
// after that the final configuration will be exported using the 'exporter'
Configuration config = new BaseConfigurationBuilder()
                            .withAdapters(adapter1, adapter2)
                            .withExporter(exporter)
                            .build();
```

# Default Properties
The following shows all default properties loaded by the `DefaultFileConfigurationAdapter`.
The values are the same as the fallback values of the `BaseConfiguration` implementation.

```properties
# URL of the default entry point for the application under test.
# TYPE: String [Resource URL]
# browser.defaultEntryPoint =

# Whether or not leftover browsers should be closed automatically when the JVM is shut down.
# Setting this property will have no impact on the browser's close method or other mechanisms for closing the browser either automatically or manually (i.e. JUnitRunner extension)
# TYPE: boolean [true, false]
cleanup.closeLeftoverBrowsers = false
 
# Format pattern for timestamps to be used when printing timestamps.
# TYPE: String [Java SimpleDateFormat style as described here: https://docs.oracle.com/javase/6/docs/api/java/text/SimpleDateFormat.html]
timestamp.pattern = MMM dd HH:mm:ss yyyy
 
# Folder in which to save screenshots if not otherwise specified.
# TYPE: String [absolute or relative path to be initialized as a java.io.File instance]
folders.screenshot = screenshots
 
# Folder in which to save source code of pages if not otherwise specified.
# TYPE: String [absolute or relative path to be initialized as a java.io.File instance]
folders.sourcecode = sourcecode
 
# Folder in which to save log files if not otherwise specified.
# TYPE: String [absolute or relative path to be initialized as a java.io.File instance]
folders.log = logs
 
# Weather or not color highlighting of used elements should be active or not.
# TYPE: boolean [true, false]
markings.activated = false
 
# Color to use for the background of used elements if color highlighting is active.
# TYPE: String [HEX RGB code starting with'#']
markings.color.used.background = #ffd2a5

# Color to use for the outline of used elements if color highlighting is active.
# TYPE: String [HEX RGB code starting with'#']
markings.color.used.outline = #916f22

# Color to use for the background of read elements if color highlighting is active.
# TYPE: String [HEX RGB code starting with'#']
markings.color.read.background = #90ee90

# Color to use for the outline of read elements if color highlighting is active.
# TYPE: String [HEX RGB code starting with'#']
markings.color.read.outline = #008000
 
# Default timeout for wait operations.
# TYPE: int [seconds]
wait.timeout = 2
 
# Default interval in which to check a condition for wait operations.
# TYPE: int [miliseconds]
wait.interval = 100
 
# Whether or not page object's wrapped web elements will be cached or resolved for each action. Has a great impact on stability when testing AJAX heavy application at the cost of performance.
# TYPE: boolean [true, false]
pageobjects.caching = true
```

# Linked Documentation

- [Browser](browser.md)
