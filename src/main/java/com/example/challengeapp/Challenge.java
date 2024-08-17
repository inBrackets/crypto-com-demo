package com.example.challengeapp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Challenge {
    // SQL query for retrieving columns
    // SELECT * from infoRMATION_SCHEMA.cOLUMNS whERE tabLE_NAME = 'CHALLENGE'
    @Id
    private Long id;
    @Column(name = "challengeMonth")
    private String month; // the month keyword is reserved in db
    private String description;
}
