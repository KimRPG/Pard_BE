package com.pard.pard_backend.domain.project.entity;

import com.pard.pard_backend.domain.project.converter.ListStringConverter;
import jakarta.persistence.Convert;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Tool {
    @Convert(converter = ListStringConverter.class)
    private List<String> tool;
}
