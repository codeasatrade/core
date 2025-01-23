package com.codeasatrade.core.computation;

import com.codeasatrade.core.model.Challenge;
import com.codeasatrade.core.model.UserSolution;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ChallengeComputation {
    public Object processSolution(Challenge challenge, UserSolution userSolution) {
        try {
            String s = RuntimeCompiler.compileAndRun(userSolution.getUserSolution());
            return "YEs";
        }catch (Exception e){
            log.error(e.getMessage());
            return e.getMessage();
        }



    }
}
