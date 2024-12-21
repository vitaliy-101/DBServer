package com.example.datamanagementserver.dto.group;

import com.example.datamanagementserver.entity.Direction;
import com.example.datamanagementserver.entity.Year;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GroupRequest {
    private Integer number;
    private Integer userCount;
    private Long directionId;
    private Long yearId;
}
