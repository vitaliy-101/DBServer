package com.example.datamanagementserver.dto.corpus;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CorpusRequest {
    private String town;
    private String street;
    private String house;
}
