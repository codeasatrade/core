package com.codeasatrade.core.controller;

import com.codeasatrade.core.model.Challenge;
import com.codeasatrade.core.model.UserSubmission;
import com.codeasatrade.core.service.ChallengesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.MediaType;
@RestController
@RequestMapping("/api/v1")

public class MainController {

    @Autowired
    private final ChallengesService challengesService;

    public MainController(ChallengesService challengesService) {
        this.challengesService = challengesService;
    }

    @GetMapping("/challenges")
    public ResponseEntity<Page<Challenge>> getChallenges(@RequestParam(defaultValue = "0") int page,
                                                         @RequestParam(defaultValue = "10") int size,
                                                         @RequestParam(defaultValue = "id") String sortBy,
                                                         @RequestParam(defaultValue = "ASC") String direction) {
        Sort sort = Sort.by(Sort.Direction.fromString(direction), sortBy);
        Pageable pageable = PageRequest.of(page, size, sort);
        return ResponseEntity.ok(challengesService.getChallenges(pageable));
    }

    @GetMapping("/challenges/{id}")
    public ResponseEntity<Challenge> getChallengeById(@PathVariable int id){
        return challengesService.getChallengeById(id).<ResponseEntity<Challenge>>map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.noContent().build());
    }
    @GetMapping("/challenge/{url}")
    public ResponseEntity<Challenge> getChallengeByEndpoint(@PathVariable("url") String url) {
        return challengesService.getChallengeByUrl("/"+url).map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.noContent().build());
    }


    @PostMapping(value = "/challenge/submit", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> processSubmission(@RequestBody UserSubmission userSubmission){
        return  ResponseEntity.ok(challengesService.getChallengeAnswerResult(userSubmission));
    }
}
