package com.example.lab3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.Collections;

@SpringBootApplication
public class Lab3GateApp {

	public static void main(String[] args) {
		SpringApplication.run(Lab3GateApp.class, args);
	}

	@Bean
	public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
		return builder
				.routes()
				.route("hotels", r -> r
						.host("localhost:8080")
						.and()
						.path("/api/hotels/{name}", "/api/hotels")
						.uri("http://localhost:8081"))
				.route("rooms", r -> r
						.host("localhost:8080")
						.and()
						.path("/api/rooms", "/api/rooms/**", "/api/persons/{name}/rooms", "/api/persons/{name}/rooms/**")
						.uri("http://localhost:8082"))
				.build();
	}

	@Bean
	public CorsWebFilter corsWebFilter() {

		final CorsConfiguration corsConfig = new CorsConfiguration();
		corsConfig.setAllowedOrigins(Collections.singletonList("*"));
		corsConfig.setMaxAge(3600L);
		corsConfig.setAllowedMethods(Arrays.asList("GET", "POST", "DELETE", "PUT"));
		corsConfig.addAllowedHeader("*");

		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", corsConfig);

		return new CorsWebFilter(source);
	}
}
