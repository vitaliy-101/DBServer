package com.example.datamanagementserver.dto.corpus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CorpusResponse {
    private Long id;
    private String town;
    private String street;
    private String house;
}
