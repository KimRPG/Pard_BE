package com.pard.pard_backend.project.entity;

import jakarta.persistence.Embeddable;

import java.util.List;

@Embeddable
public class Tool {
    private String part;
    private List<String> tool;
}
