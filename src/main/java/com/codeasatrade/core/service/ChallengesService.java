package com.codeasatrade.core.service;

import com.codeasatrade.core.computation.ChallengeComputation;
import com.codeasatrade.core.model.Challenge;
import com.codeasatrade.core.model.UserSolution;
import com.codeasatrade.core.repository.ChallengeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

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

    public Page<Challenge> getChallenges(Pageable pageable) {
        return challengeRepository.findAll(pageable);
    }

    public Optional<Challenge> getChallengeById(int id) {
        return challengeRepository.findById(id);
    }

    public ResponseEntity<Object> processSolution(Challenge challenge, UserSolution userSolution) {
        return new ResponseEntity<>(computation.processSolution(challenge, userSolution), HttpStatus.OK);
    }

    public Optional<Challenge> getChallengeByUrl(String url) {

        return challengeRepository.Endpoint(url);

    }
}
