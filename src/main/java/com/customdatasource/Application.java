package com.customdatasource;

import java.io.IOException;
import java.util.Locale;
import java.util.logging.Logger;

import org.apache.log4j.spi.LoggerFactory;
import org.springframework.context.ApplicationContext;

/**
 * Boot class for spring-boot application.
 *
 * 
 */
@SpringBootApplication
public class Application extends SpringBootServletInitializer {

    private static final Logger log = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) throws IOException {
        ApplicationContext context = SpringApplication.run(Application.class, args);
        if (log.isInfoEnabled() && context instanceof EmbeddedWebApplicationContext) {
            int port = ((EmbeddedWebApplicationContext) context).getEmbeddedServletContainer().getPort();
            String contextPath = context.getApplicationName();
            String url = String.format(Locale.US, "http://localhost:%d%s", port, contextPath);
            String dashes = "------------------------------------------------------------------------";
            log.info("Access URLs:\n{}\n\tLocal: \t\t{}\n{}", dashes, url, dashes);
        }

    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.bannerMode(Banner.Mode.OFF)
                .addCommandLineProperties(false)
                .logStartupInfo(true)
                .sources(Application.class);
    }

}
