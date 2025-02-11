package com.example.challengeapp.todo;

import com.example.challengeapp.ChallengeAppApplication;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service
public class TodoExternalApiService {

    private final RestClient restClient;

    public TodoExternalApiService(RestClient restClient) {
        this.restClient = restClient;
    }

    public ResponseEntity<Todo> getExternalSingleTodoData(int id) {
        return restClient.get()
                .uri("/{id}", id)
                .retrieve()
                .toEntity(Todo.class);
    }
}
