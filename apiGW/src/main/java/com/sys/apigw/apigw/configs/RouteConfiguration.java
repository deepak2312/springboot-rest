package com.sys.apigw.apigw.configs;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Predicate;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpRequest;
import org.springframework.http.server.reactive.ServerHttpRequest;

import com.sys.apigw.apigw.security.JWTokenFiler;

@Configuration
public class RouteConfiguration {

	@Autowired
	JWTokenFiler jwTokenFiler;
   
	public static final List<String> openApiEndpoints= Arrays.asList(
	            "/auth/register",
	            "/auth/login",
	            "/auth/refreshToken"
	);

    public Predicate isSecured =
	            request -> openApiEndpoints.stream().noneMatch((uri)-> ((HttpRequest) request).getURI().getPath().equals(uri));
	
	
	@Bean
	public RouteLocator routes(RouteLocatorBuilder routeLocatorBuilder) {
		return routeLocatorBuilder.routes()
				.route("product-service", r -> r.path("/api/product/*").filters(f -> f.filter(jwTokenFiler)).uri("lb://product-service"))
				.route("inventory-service",r-> r.path("/api/inventory/*").filters(f-> f.filter(jwTokenFiler)).uri("lb://inventory-service"))
				.route("auth-service",r-> r.path("/auth/*").filters(f-> f.filter(jwTokenFiler)).uri("lb://auth-service"))
				.build();
	}
	
	
}
