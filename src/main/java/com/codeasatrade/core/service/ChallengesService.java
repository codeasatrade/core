package com.codeasatrade.core.service;

import com.codeasatrade.core.computation.ChallengeComputation;
import com.codeasatrade.core.model.Challenge;
import com.codeasatrade.core.model.UserSolution;
import com.codeasatrade.core.repository.ChallengeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ChallengesService {

    private final ChallengeComputation computation;

    private static final Logger log = LoggerFactory.getLogger(ChallengesService.class);
    private final ChallengeRepository challengeRepository;

    public ChallengesService(ChallengeComputation computation, ChallengeRepository challengeRepository) {
        this.computation = computation;
        this.challengeRepository = challengeRepository;
    }

    public List<Challenge> getChallenges(){
        return challengeRepository.findAll();
    }

    public Optional<Challenge> getChallengeById(int id){
        return challengeRepository.findById(id);
    }

    public ResponseEntity<Object> processSolution(Challenge challenge, UserSolution userSolution) {
        return new ResponseEntity<>(computation.processSolution(challenge, userSolution), HttpStatus.OK) ;
    }
}
