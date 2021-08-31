package com.sys.apigw.apiGW.security;

import java.nio.charset.StandardCharsets;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.server.ServerWebExchange;

import com.sys.apigw.apiGW.configs.JWTUtils;
import com.sys.apigw.apiGW.configs.RouteConfiguration;

import io.jsonwebtoken.ExpiredJwtException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Configuration
public class JWTokenFiler implements GatewayFilter {
	
	Logger logger=LogManager.getLogger(JWTokenFiler.class);
	
	@Autowired
	RouteConfiguration routeConfiguration;
	
	@Autowired
	JWTUtils jwtUtils;

	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
		// TODO Auto-generated method stub
		logger.info("inside filer request landed "+exchange.getRequest());
		ServerHttpRequest request=exchange.getRequest();
		
		if(routeConfiguration.isSecured.evaluate(request)) {
			logger.info("This request is secured request ");
			if(this.isAuthMissing(request)) {
				logger.error("Token is not available in request so sending unauthorized ");
				 return this.onError(exchange, "Authorization header is missing in request", HttpStatus.UNAUTHORIZED);
			}
		
			final String token = this.getAuthHeader(request);
			
			System.out.println("extracted token "+token);
			try {
				if(jwtUtils.isTokenExpired(token)) {
					return this.onError(exchange, "Token expired", HttpStatus.UNAUTHORIZED);
				}
			}catch(ExpiredJwtException expiredJwtException) {
				return this.onError(exchange, expiredJwtException.getLocalizedMessage(), HttpStatus.UNAUTHORIZED);
			}
			
		}
		
		return chain.filter(exchange);
	}
	
	
	private boolean isAuthMissing(ServerHttpRequest httpRequest) {
		  return ! (httpRequest.getHeaders().containsKey("Authorization") && httpRequest.getHeaders().get("Authorization").get(0).startsWith("Bearer"));
	}
	
	private String getAuthHeader(ServerHttpRequest httpRequest) {
		return httpRequest.getHeaders().getOrEmpty("Authorization").get(0).split(" ")[1];
    }
	
	private Mono<Void> onError(ServerWebExchange exchange, String err, HttpStatus httpStatus) {
        ServerHttpResponse response = exchange.getResponse();
        byte[] bytes = err.getBytes(StandardCharsets.UTF_8);
        response.setStatusCode(httpStatus);
        DataBuffer buffer = response.bufferFactory().wrap(bytes);
        return response.writeWith(Flux.just(buffer));
      
    } 

}
