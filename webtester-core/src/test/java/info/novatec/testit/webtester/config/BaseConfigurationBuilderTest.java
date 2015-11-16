package info.novatec.testit.webtester.config;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import info.novatec.testit.webtester.api.config.Configuration;
import info.novatec.testit.webtester.api.config.ConfigurationAdapter;
import info.novatec.testit.webtester.api.config.ConfigurationExporter;


@RunWith(MockitoJUnitRunner.class)
public class BaseConfigurationBuilderTest {

    @Mock
    ConfigurationAdapter adapter1;
    @Mock
    ConfigurationAdapter adapter2;
    @Mock
    ConfigurationExporter exporter1;
    @Mock
    ConfigurationExporter exporter2;

    @Test
    public void testThatSimplyBuildingAConfigurationReturnsAValidConfiguration() {
        Configuration configuration = new BaseConfigurationBuilder().build();
        assertThat(configuration, is(instanceOf(BaseConfiguration.class)));
    }

    @Test
    public void testThatThatAdaptersAreUsedToAdaptConfigurations() {
        Configuration configuration = new BaseConfigurationBuilder().withAdapter(adapter1).build();
        verify(adapter1).adapt(configuration);
        verifyNoMoreInteractions(adapter1);
    }

    @Test
    public void testThatThatExportersAreUsedToExportConfigurationProperties() {

        new BaseConfigurationBuilder().withAdapter(new TestAdapter()).withExporter(exporter1).build();

        verify(exporter1).export("foo", "fooValue");
        verifyNoMoreInteractions(exporter1);

    }

    @Test
    public void testThatThatMultipleAdaptersAndExportersAreExecutedInOrder() {

        Configuration configuration = new BaseConfigurationBuilder().withAdapter(new TestAdapter())
            .withAdapters(adapter1, adapter2)
            .withExporters(exporter1, exporter2)
            .build();

        InOrder inOrder = inOrder(adapter1, adapter2, exporter1, exporter2);
        inOrder.verify(adapter1).adapt(configuration);
        inOrder.verify(adapter2).adapt(configuration);
        inOrder.verify(exporter1).export("foo", "fooValue");
        inOrder.verify(exporter2).export("foo", "fooValue");
        inOrder.verifyNoMoreInteractions();

    }

    private static class TestAdapter implements ConfigurationAdapter {

        @Override
        public boolean adapt(Configuration configuration) {
            configuration.setProperty("foo", "fooValue");
            return true;
        }

    }

}
