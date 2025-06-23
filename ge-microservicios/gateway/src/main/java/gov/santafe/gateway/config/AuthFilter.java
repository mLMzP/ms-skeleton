package gov.santafe.gateway.config;


import gov.santafe.gateway.dto.TokenDto;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;


@Component
public class AuthFilter implements GlobalFilter {

	@Autowired
	private WebClient.Builder webClient;
	
	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
		if(!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
			return onError(exchange, HttpStatus.BAD_REQUEST);
		}
		
		String tokenHeader = exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);
		String[] chunks = tokenHeader.split(" ");
		if(chunks.length != 2 || !chunks[0].equals("Bearer")) {
			return onError(exchange, HttpStatus.BAD_REQUEST);
		}
		
		
		return webClient.build()
				.post()
				.uri("http://auth-server/auth/validate?token="+chunks[1])
				.retrieve()
				.bodyToMono(TokenDto.class)
				.map(t -> {
					return exchange;
				})
				.flatMap(chain::filter);
	}
	
	
	private Mono<Void> onError(ServerWebExchange exchange, HttpStatus  status){
		ServerHttpResponse response = exchange.getResponse();
		response.setStatusCode(status);
		return response.setComplete();
	}

}
