package com.example.datamanagementserver.dto.group;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GroupResponse {
    private Long id;
    private Integer number;
    private Integer userCount;
    private Long directionId;
    private Long yearId;
}
