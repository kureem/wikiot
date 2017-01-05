package org.castafiore.iot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@SpringBootApplication
@Configuration
@EnableWebSocket
public class Boot extends WebMvcConfigurerAdapter implements WebSocketConfigurer {

	@Autowired
	private CastafioreIOTProtocolHandler handler;

	@Override
	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
		registry.addHandler(handler, "/ws").setAllowedOrigins("*");
	}

	@Override
	public void addCorsMappings(CorsRegistry registry) {

		super.addCorsMappings(registry);
		registry.addMapping("/**/**").allowedHeaders("origin", "content-type", "accept", "x-requested-with")
				.allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS").maxAge(31536000).allowedOrigins("*").allowCredentials(true);

	}

	public static void main(String[] args) {
		SpringApplication.run(Boot.class, args);
	}

}
