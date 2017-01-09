package info.novatec.testit.webtester.config;

import java.io.File;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.openqa.selenium.support.Color;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import info.novatec.testit.webtester.api.config.Configuration;
import info.novatec.testit.webtester.api.config.ConfigurationAdapter;
import info.novatec.testit.webtester.api.config.ConfigurationBuilder;
import info.novatec.testit.webtester.api.config.ConfigurationExporter;
import info.novatec.testit.webtester.api.exceptions.config.InvalidValueTypeException;
import info.novatec.testit.webtester.api.exceptions.config.SetNullValuesException;
import info.novatec.testit.webtester.internal.NamedProperties;


/**
 * This is the base implementation of a {@link Configuration configuration}.
 * <p>
 * It can be initialized using a {@link BaseConfigurationBuilder base
 * configuration builder}. And provides defaults for all major named properties
 * in case no {@link ConfigurationAdapter adapters} are used to construct the
 * configuration.
 * <p>
 * <b>Examples:</b><br>
 * <code>new BaseConfigurationBuilder().build();</code><br>
 * <code>new BaseConfigurationBuilder().withAdapter(adapter).build();</code>
 *
 * @see Configuration
 * @see ConfigurationBuilder
 * @see ConfigurationAdapter
 * @see BaseConfigurationBuilder
 * @since 0.9.7
 */
public class BaseConfiguration implements Configuration {

    private static final Logger logger = LoggerFactory.getLogger(BaseConfiguration.class);

    private static final Set<Class<?>> ALLOWED_TYPES =
        asSet(String.class, Integer.class, Long.class, Float.class, Double.class, Boolean.class);

    private Map<String, String> properties = new HashMap<String, String>();
    private List<ConfigurationExporter> configurationExporters = new LinkedList<ConfigurationExporter>();

    /* named properties */

    @Override
    public String getDefaultApplicationEntryPoint() {
        return getStringProperty(key(NamedProperties.BROWSER_DEFAULT_ENTRY_POINT));
    }

    @Override
    public BaseConfiguration setDefaultApplicationEntryPoint(String entryPoint) {
        return setProperty(key(NamedProperties.BROWSER_DEFAULT_ENTRY_POINT), entryPoint);
    }

    @Override
    public String getTimestampPattern() {
        return getStringProperty(key(NamedProperties.TIMESTAMP_PATTERN), "MMM dd HH:mm:ss yyyy");
    }

    @Override
    public BaseConfiguration setTimestampPattern(String pattern) {
        return setProperty(key(NamedProperties.TIMESTAMP_PATTERN), pattern);
    }

    @Override
    public File getScreenshotFolder() {
        return new File(getStringProperty(key(NamedProperties.FOLDERS_SCREENSHOT), "screenshots"));
    }

    @Override
    public BaseConfiguration setScreenshotFolder(File folder) {
        return setProperty(key(NamedProperties.FOLDERS_SCREENSHOT), folder.getAbsolutePath());
    }

    @Override
    public File getSourceCodeFolder() {
        return new File(getStringProperty(key(NamedProperties.FOLDERS_SOURCECODE), "sourcecode"));
    }

    @Override
    public BaseConfiguration setSourceCodeFolder(File folder) {
        return setProperty(key(NamedProperties.FOLDERS_SOURCECODE), folder.getAbsolutePath());
    }

    @Override
    public File getLogFolder() {
        return new File(getStringProperty(key(NamedProperties.FOLDERS_LOG), "logs"));
    }

    @Override
    public BaseConfiguration setLogFolder(File folder) {
        return setProperty(key(NamedProperties.FOLDERS_LOG), folder.getAbsolutePath());
    }

    @Override
    public boolean markingsAreActivated() {
        return getBooleanProperty(key(NamedProperties.MARKINGS_ACTIVATED), Boolean.FALSE);
    }

    @Override
    public BaseConfiguration setMarkingsActivated(boolean activated) {
        return setProperty(key(NamedProperties.MARKINGS_ACTIVATED), activated);
    }

    @Override
    public Color getMarkingsColorUsedBackground() {
        return Color.fromString(getStringProperty(key(NamedProperties.MARKINGS_COLOR_USED_BACKGROUND), "#ffd2a5"));
    }

    @Override
    public BaseConfiguration setMarkingsColorUsedBackground(Color color) {
        return setProperty(key(NamedProperties.MARKINGS_COLOR_USED_BACKGROUND), color.toString());
    }

    @Override
    public Color getMarkingsColorUsedOutline() {
        return Color.fromString(getStringProperty(key(NamedProperties.MARKINGS_COLOR_USED_OUTLINE), "#916f22"));
    }

    @Override
    public BaseConfiguration setMarkingsColorUsedOutline(Color color) {
        return setProperty(key(NamedProperties.MARKINGS_COLOR_USED_OUTLINE), color.toString());
    }

    @Override
    public Color getMarkingsColorReadBackground() {
        return Color.fromString(getStringProperty(key(NamedProperties.MARKINGS_COLOR_READ_BACKGROUND), "#90ee90"));
    }

    @Override
    public BaseConfiguration setMarkingsColorReadBackground(Color color) {
        return setProperty(key(NamedProperties.MARKINGS_COLOR_READ_BACKGROUND), color.toString());
    }

    @Override
    public Color getMarkingsColorReadOutline() {
        return Color.fromString(getStringProperty(key(NamedProperties.MARKINGS_COLOR_READ_OUTLINE), "#008000"));
    }

    @Override
    public BaseConfiguration setMarkingsColorReadOutline(Color color) {
        return setProperty(key(NamedProperties.MARKINGS_COLOR_READ_OUTLINE), color.toString());
    }

    @Override
    public int getWaitTimeout() {
        return getIntegerProperty(key(NamedProperties.WAIT_TIMEOUT), 2);
    }

    @Override
    public BaseConfiguration setWaitTimeout(int waitTimeout) {
        return setProperty(key(NamedProperties.WAIT_TIMEOUT), waitTimeout);
    }

    @Override
    public long getWaitInterval() {
        return getLongProperty(key(NamedProperties.WAIT_INTERVAL), 100L);
    }

    @Override
    public BaseConfiguration setWaitInterval(long waitInterval) {
        return setProperty(key(NamedProperties.WAIT_INTERVAL), waitInterval);
    }

    @Override
    public boolean cleanupLeftoverBrowsers() {
        return getBooleanProperty(key(NamedProperties.CLEANUP_LEFTOVER_BROWSERS), Boolean.FALSE);
    }

    @Override
    public BaseConfiguration setCleanupLeftoverBrowsers(boolean enabled) {
        return setProperty(key(NamedProperties.CLEANUP_LEFTOVER_BROWSERS), enabled);
    }

    private String key(NamedProperties property) {
        return property.getKey();
    }

    /* low-level function */

    @Override
    public BaseConfiguration removeProperty(String key) {
        properties.remove(key);
        logger.debug("removed property '{}'", key);
        return this;
    }

    @Override
    public BaseConfiguration setProperty(String key, Object value) {

        assertNotNull(key, value);
        assertAllowedType(value);

        changeValue(key, value);
        exportProperty(key, value);

        return this;

    }

    private void assertNotNull(String key, Object value) {
        if (value == null) {
            throw new SetNullValuesException(key);
        }
    }

    private void assertAllowedType(Object value) {
        if (!ALLOWED_TYPES.contains(value.getClass())) {
            throw new InvalidValueTypeException(value.getClass(), ALLOWED_TYPES);
        }
    }

    private void changeValue(String key, Object value) {
        properties.put(key, String.valueOf(value));
        logger.debug("changed value of property '{}' to: {}", key, value);
    }

    private void exportProperty(String key, Object value) {
        if (!configurationExporters.isEmpty()) {
            logger.debug("exporting change of property '{}' to exporters", key);
            for (ConfigurationExporter exporter : configurationExporters) {
                logger.trace("exporting change of property '{}'='{}' to {}", key, value, exporter);
                exporter.export(key, value);
            }
        }
    }

    @Override
    public String getStringProperty(String key) {
        return getStringProperty(key, null);
    }

    @Override
    public String getStringProperty(String key, String defaultValue) {
        String value = properties.get(key);
        return value != null ? value : defaultValue;
    }

    @Override
    public Integer getIntegerProperty(String key) {
        return getIntegerProperty(key, null);
    }

    @Override
    public Integer getIntegerProperty(String key, Integer defaultValue) {
        String value = properties.get(key);
        return value != null ? Integer.valueOf(value) : defaultValue;
    }

    @Override
    public Long getLongProperty(String key) {
        return getLongProperty(key, null);
    }

    @Override
    public Long getLongProperty(String key, Long defaultValue) {
        String value = properties.get(key);
        return value != null ? Long.valueOf(value) : defaultValue;
    }

    @Override
    public Float getFloatProperty(String key) {
        return getFloatProperty(key, null);
    }

    @Override
    public Float getFloatProperty(String key, Float defaultValue) {
        String value = properties.get(key);
        return value != null ? Float.valueOf(value) : defaultValue;
    }

    @Override
    public Double getDoubleProperty(String key) {
        return getDoubleProperty(key, null);
    }

    @Override
    public Double getDoubleProperty(String key, Double defaultValue) {
        String value = properties.get(key);
        return value != null ? Double.valueOf(value) : defaultValue;
    }

    @Override
    public Boolean getBooleanProperty(String key) {
        return getBooleanProperty(key, null);
    }

    @Override
    public Boolean getBooleanProperty(String key, Boolean defaultValue) {
        String value = properties.get(key);
        return value != null ? Boolean.valueOf(value) : defaultValue;
    }

    @Override
    public Object getProperty(String key) {
        return getProperty(key, null);
    }

    @Override
    public Object getProperty(String key, Object defaultValue) {
        Object value = properties.get(key);
        return value != null ? value : defaultValue;
    }

    @Override
    public Set<String> getKeys() {
        return Collections.unmodifiableSet(properties.keySet());
    }

    @Override
    public BaseConfiguration addExporters(ConfigurationExporter exporter, ConfigurationExporter... additionalExporters) {
        return addExporter(exporter).addExporters(Arrays.asList(additionalExporters));
    }

    @Override
    public BaseConfiguration addExporter(ConfigurationExporter exporter) {
        configurationExporters.add(exporter);
        logger.debug("added configuration exporter: {}", exporter);
        return this;
    }

    @Override
    public BaseConfiguration addExporters(Collection<ConfigurationExporter> exportersToAdd) {
        configurationExporters.addAll(exportersToAdd);
        logger.debug("added configuration exporters: {}", exportersToAdd);
        return this;
    }

    /* utilities */

    private static Set<Class<?>> asSet(Class<?>... types) {
        Set<Class<?>> typeSet = new HashSet<Class<?>>();
        Collections.addAll(typeSet, types);
        return typeSet;
    }

}
