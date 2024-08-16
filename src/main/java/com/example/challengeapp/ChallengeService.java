package com.example.challengeapp;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ChallengeService {

    private List<Challenge> challenges = new ArrayList<>();
    private Long nextId = 1L;

    public ChallengeService() {
        Challenge challenge = new Challenge(1L, "January", "Do something");
        challenges.add(challenge);
    }

    public List<Challenge> getAllChallenges() {
        return challenges;
    }

    public boolean addChallenge(Challenge challenge) {
        if (challenge != null) {
            challenge.setId(nextId++);
            challenges.add(challenge);
            return true;
        }
        return false;
    }

    public Challenge getChallengeByMonth(String month) {
        for (Challenge challenge: challenges) {
            if (challenge.getMonth().equalsIgnoreCase(month)) {
                return challenge;
            }
        }
        return null;
    }
}
