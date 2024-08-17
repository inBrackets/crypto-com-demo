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

    @PutMapping("/challenges/{id}")
    public ResponseEntity<String> updateChallenge(@PathVariable Long id, @RequestBody Challenge updatedChallenge) {
        boolean isChallengeUpdated = challengeService.updateChallenge(id, updatedChallenge);
        if(isChallengeUpdated) {
            return new ResponseEntity<>("Challenge updated successfully", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Challenge not updated successfully", HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/challenges/{id}")
    public ResponseEntity<String> deleteChallenge(Long id){
        boolean isChallengeDeleted = challengeService.deleteChallenge(id);
        if(isChallengeDeleted) {
            return new ResponseEntity<>("Challenge deleted successfully", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Challenge not deleted successfully", HttpStatus.NOT_FOUND);
        }
    }
}
