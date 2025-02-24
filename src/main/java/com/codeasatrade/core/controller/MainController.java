package com.codeasatrade.core.controller;

import com.codeasatrade.core.model.Challenge;
import com.codeasatrade.core.model.UserSolution;
import com.codeasatrade.core.service.ChallengesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "http://localhost:5173") 
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

    @GetMapping("/challenges/{url}")
    public ResponseEntity<Challenge> getChallengeById(@PathVariable String url){
        return challengesService.getChallengeByUrl(url).<ResponseEntity<Challenge>>map(ResponseEntity::ok)
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
