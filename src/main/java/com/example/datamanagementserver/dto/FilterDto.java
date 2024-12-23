package com.example.datamanagementserver.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class FilterDto {
    private String field;
    private String textFilter;
    private Integer intFilter;
}
