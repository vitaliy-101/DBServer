package com.example.datamanagementserver.dto.event;


import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EventRequest {
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Integer userCount;
    private String description;
    private Long auditoryId;
    private Long userId;
}
