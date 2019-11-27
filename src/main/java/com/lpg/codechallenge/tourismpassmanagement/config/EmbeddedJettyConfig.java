package com.lpg.codechallenge.tourismpassmanagement.config;

import lombok.Data;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.handler.HandlerCollection;
import org.eclipse.jetty.server.handler.RequestLogHandler;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.embedded.jetty.JettyServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Configuration
//@ConfigurationProperties("jetty")
//@Data
public class EmbeddedJettyConfig {

	@Bean
	public ConfigurableServletWebServerFactory jettyConfigBean() {
		final JettyServletWebServerFactory jettyServletWebServerFactory = new JettyServletWebServerFactory();
		jettyServletWebServerFactory.addServerCustomizers(server -> {
			final HandlerCollection handlers = new HandlerCollection();

			for (Handler handler : server.getHandlers()) {
				handlers.addHandler(handler);
			}

			final RequestLogHandler requestLogHandler = new RequestLogHandler();

			handlers.addHandler(requestLogHandler);

			server.setHandler(handlers);
		});

		return jettyServletWebServerFactory;
	}
}
