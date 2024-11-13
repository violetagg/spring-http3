package com.example.http3;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@RestController
class Http3Controller {

	private final WebClient webClient;

	Http3Controller(WebClient webClient) {
		this.webClient = webClient;
	}

	@GetMapping("/hello")
	String hello() {
		return "Hello HTTP/3!";
	}

	@GetMapping("/remote")
	Mono<String> remote() {
		return webClient.get()
				.uri("https://projectreactor.io/")
				.retrieve()
				.bodyToMono(String.class);
	}
}
