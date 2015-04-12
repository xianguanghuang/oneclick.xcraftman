package impatient.springboot.metric.configuration;

import org.springframework.boot.actuate.metrics.export.Exporter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by xianguanghuang on 15-4-12.
 */
@Configuration
public class ExportConfiguration {


    @Bean(name="metricExporter")
    public Exporter metricExporter() {
        return null;
    }
}
