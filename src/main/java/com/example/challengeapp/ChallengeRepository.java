package com.example.challengeapp;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

// Data access layer
// Data type, Primary key type
public interface ChallengeRepository extends JpaRepository<Challenge, Long> {
    Optional<Challenge> findByMonthIgnoreCase(String month);
}
