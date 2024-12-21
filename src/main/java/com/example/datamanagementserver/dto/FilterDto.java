package com.example.datamanagementserver.dto;


import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class FilterDto {
    private String field;
    private String textFilter;
    private Integer intFilter;
}
