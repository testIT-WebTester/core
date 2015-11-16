package info.novatec.testit.webtester.config.exporters;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;

import java.util.UUID;

import org.junit.Test;


public class SystemPropertyConfigurationExporterTest {

    String key = UUID.randomUUID().toString();

    SystemPropertyConfigurationExporter cut = new SystemPropertyConfigurationExporter();

    @Test
    public void testThatPropertiesAreExposedAsSystemProperties() {
        cut.export(key, "fooValue");
        assertThat(System.getProperty(key), is("fooValue"));
    }

    @Test
    public void testThatPropertiesWithNullValueAreNotExposedAsSystemProperties() {
        cut.export(key, null);
        assertThat(System.getProperty(key), is(nullValue()));
    }

    @Test
    public void testThatPropertiesWithEmptyValueAreNotExposedAsSystemProperties() {
        cut.export(key, "");
        assertThat(System.getProperty(key), is(nullValue()));
    }

    @Test
    public void testThatPropertiesWithBlankValueAreNotExposedAsSystemProperties() {
        cut.export(key, "  ");
        assertThat(System.getProperty(key), is(nullValue()));
    }

}
