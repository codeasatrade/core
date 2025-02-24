package com.codeasatrade.core.model;

import com.fasterxml.jackson.databind.JsonNode;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@Entity(name = "challenges")
public class Challenge {

    @Id
    private int id;
    private String name;
    private String description;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "jsonb", name = "test_cases")
    private Map<String, List<String>> testCases;

    private String java;
    private String python;

    @Column(unique = true)
    @NotNull
    private String url;

}
