package com.example.datamanagementserver.dto.auditory;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AuditoryRequest {
    private Integer number;
    private Integer capacity;
    private Long corpusId;
}
