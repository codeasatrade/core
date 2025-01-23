package com.codeasatrade.core.repository;

import com.codeasatrade.core.model.Challenge;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ChallengeRepository extends JpaRepository<Challenge, Integer> {
}
