package com.codeasatrade.core.model;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class Challenge11 {

    @Id
    protected int id;
    @Column(name = "name")
    protected String title;
    @Column(name = "description")
    protected String description;
}
