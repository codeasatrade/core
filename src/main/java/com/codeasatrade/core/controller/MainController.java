package com.codeasatrade.core.controller;

import com.codeasatrade.core.model.Challenge;
import com.codeasatrade.core.model.UserSolution;
import com.codeasatrade.core.service.ChallengesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/")
public class MainController {

    @Autowired
    private final ChallengesService challengesService;

    public MainController(ChallengesService challengesService) {
        this.challengesService = challengesService;
    }

    @GetMapping("/challenges")
    public ResponseEntity<List<Challenge>> getChallenges(){
        return ResponseEntity.ok(challengesService.getChallenges());
    }

    @GetMapping("/challenges/{id}")
    public ResponseEntity<Object> getChallengeById(@PathVariable int id){
        return challengesService.getChallengeById(id).<ResponseEntity<Object>>map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.noContent().build());
    }

    @PostMapping("/solution/{id}")
    public ResponseEntity<Object> processSolution(@RequestBody @Validated UserSolution userSolution, @PathVariable int id){
        Optional<Challenge> challengeOptional = challengesService.getChallengeById(id);
        if(challengeOptional.isPresent()){
           return new ResponseEntity<>(challengesService.processSolution(challengeOptional.get(), userSolution),
                   HttpStatus.OK);
        }else{
            return ResponseEntity.noContent().build();
        }

    }
}
