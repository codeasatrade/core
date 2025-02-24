package com.codeasatrade.core.repository;

import com.codeasatrade.core.model.Challenge;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface ChallengeRepository extends JpaRepository<Challenge, Integer> {

    Page<Challenge> findAll(Pageable pageable);

    Optional<Challenge> findByUrl(String url);

}

