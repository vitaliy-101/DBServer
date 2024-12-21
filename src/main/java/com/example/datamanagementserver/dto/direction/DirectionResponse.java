package com.example.datamanagementserver.dto.direction;

import com.example.datamanagementserver.entity.Group;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DirectionResponse {
    private Long id;
    private String name;
}
