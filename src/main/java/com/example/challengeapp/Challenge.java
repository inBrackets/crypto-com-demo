package com.example.challengeapp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@AllArgsConstructor
@Data
public class Challenge {
    private Long id;
    private String month;
    private String description;
}
