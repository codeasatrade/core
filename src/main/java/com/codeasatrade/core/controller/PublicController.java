package com.codeasatrade.core.controller;

import com.codeasatrade.core.model.Challenge;
import com.codeasatrade.core.service.ChallengesService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/")
public class PublicController {

    private ChallengesService challengesService;

    public PublicController(ChallengesService challengesService){
        this.challengesService = challengesService;
    }

//    @GetMapping("/{url}")
//    public ResponseEntity<Challenge> getChallengeByUrl(@PathVariable String url){
//        Optional<Challenge> optionalAlgorithmChallenge = challengesService.getChallengeByUrl(url);
//        if(optionalAlgorithmChallenge.isPresent()){
//            return ResponseEntity.ok(optionalAlgorithmChallenge.get());
//        }else{
//            return (ResponseEntity<Challenge>) ResponseEntity.notFound();
//        }
//    }

    @GetMapping("/")
    public ResponseEntity<Object> greet(){
        return ResponseEntity.ok("Hello World");
    }
}
