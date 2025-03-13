package com.codeasatrade.core.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserSubmission {

    private CodeLang codeLang;
    private int challengeId;
    private String userSolution;

    @Override
    public String toString() {

        return String.format("challengeId: {}, codeLang: {}, userSolution: {}", this.challengeId, this.codeLang, this.userSolution) ;
    }
}
