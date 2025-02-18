package com.example.challengeapp;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClient;

@SpringBootApplication
public class ChallengeAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(ChallengeAppApplication.class, args);
    }

    // Note: the name of the method testClient(...), has to be equal to the injected variable/dependency testRestClient to avoid conflicts with other beans

    @Bean
    RestClient testRestClient(RestClient.Builder builder) {
        return builder.baseUrl("https://jsonplaceholder.typicode.com/todos/").build();
    }

    @Bean
    ApplicationRunner applicationRunner(RestClient testRestClient) {
        return args -> {
            ResponseEntity<Todo> entity = testRestClient.get()
                    .uri("/{id}", 10)
                    .retrieve()
                    .toEntity(Todo.class);
            System.out.println(entity.getBody());
        };
    }

    record Todo(Long id, Long userId, String title) {}

}
