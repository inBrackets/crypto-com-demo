package com.example.challengeapp;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ChallengeController {

    private ChallengeService challengeService;

    public ChallengeController(ChallengeService challengeService) {
        this.challengeService = challengeService;
    }

    @GetMapping("/challenges")
    private ResponseEntity<List<Challenge>> getAllChallenges() {
        return new ResponseEntity<>(challengeService.getAllChallenges(), HttpStatus.OK);
    }

    @PostMapping("/challenges")
    private ResponseEntity<String> addChallenge(@RequestBody Challenge challenge) {
        boolean isChallengeAdded = challengeService.addChallenge(challenge);
        if(isChallengeAdded) {
            return new ResponseEntity<>("Challenge added successfully", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Challenge not added successfully", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/challenges/{month}")
    private ResponseEntity<Challenge> getAllChallenge(@PathVariable String month) {

        Challenge challenge = challengeService.getChallengeByMonth(month);
        if(challenge!=null)
            return new ResponseEntity<>(challenge, HttpStatus.OK);
        else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
