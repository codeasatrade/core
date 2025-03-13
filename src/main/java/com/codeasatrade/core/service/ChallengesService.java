package com.codeasatrade.core.service;

import com.codeasatrade.core.model.Challenge;
import com.codeasatrade.core.model.UserSubmission;
import com.codeasatrade.core.repository.ChallengeRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

@Service
public class ChallengesService {

    private final RestTemplate restTemplate = new RestTemplate();


    private static final Logger log = LoggerFactory.getLogger(ChallengesService.class);
    private final ChallengeRepository challengeRepository;

    public ChallengesService(ChallengeRepository challengeRepository) {
        this.challengeRepository = challengeRepository;
    }

    public Page<Challenge> getChallenges(Pageable pageable) {
        return challengeRepository.findAll(pageable);
    }

    public Optional<Challenge> getChallengeById(int id) {
        return challengeRepository.findById(id);
    }

    public Optional<Challenge> getChallengeByUrl(String url) {
        return challengeRepository.Endpoint(url);
    }

    public String getChallengeAnswerResult(UserSubmission userSubmission) {
        log.info("userSubmission : {}", userSubmission.toString());
    Optional<Challenge> optionalChallenge = getChallengeById(userSubmission.getChallengeId());
    if(optionalChallenge.isPresent()){
        return makePostRequest(userSubmission);
//        return "adadsf";
    }else {
        return "Challenge not found";
    }

    }

    private String makePostRequest(UserSubmission userSubmission){
        String url = "http://localhost:49200/api/v1/execute/solution";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, String> reqBody = Map.of("code", userSubmission.getUserSolution());
        HttpEntity<String> httpEntity = null;
        try {
            httpEntity = new HttpEntity<>(objectMapper.writeValueAsString(reqBody), headers);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        var response = "";
        try {
            ResponseEntity<String> responseEntity = restTemplate.postForEntity(url, httpEntity, String.class);
            if(responseEntity.getStatusCode() == HttpStatus.OK){
                response = responseEntity.getBody();
            }else{
                response = "Invalid Submission";
            }
        }catch (Exception e){
            log.info(e.getMessage());
        }




        return response;
    }
}
