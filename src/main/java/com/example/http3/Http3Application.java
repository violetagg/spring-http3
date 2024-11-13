package com.example.http3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.HttpProtocol;
import reactor.netty.http.client.HttpClient;

import java.time.Duration;

@SpringBootApplication
public class Http3Application {

	public static void main(String[] args) {
		SpringApplication.run(Http3Application.class, args);
	}

	@Bean
	WebClient webClient(WebClient.Builder builder) {
		HttpClient client =
				HttpClient.create()
						// Configure HTTP/3 protocol
						.protocol(HttpProtocol.HTTP3)
						// Configure HTTP/3 settings
						.http3Settings(spec -> spec.idleTimeout(Duration.ofSeconds(5))
								.maxData(10_000_000)
								.maxStreamDataBidirectionalLocal(1_000_000));

		return builder.clientConnector(new ReactorClientHttpConnector(client)).build();
	}

}
