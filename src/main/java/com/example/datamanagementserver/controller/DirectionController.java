package com.example.datamanagementserver.controller;

import com.example.datamanagementserver.dto.FilterDto;
import com.example.datamanagementserver.dto.direction.DirectionRequest;
import com.example.datamanagementserver.dto.direction.DirectionResponse;
import com.example.datamanagementserver.mapper.DataMapper;
import com.example.datamanagementserver.service.DirectionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/direction")
public class DirectionController {
    private final DirectionService directionService;
    private final DataMapper dataMapper;

    @GetMapping()
    public List<DirectionResponse> getAll() {
        return dataMapper.convertFromDirectionListToResponse(directionService.getAll());
    }

    @PostMapping()
    public DirectionResponse create(@RequestBody DirectionRequest directionRequest) {
        return dataMapper.convertFromDirectionToResponse(directionService.create(directionRequest));
    }

    @PutMapping("/update/{id}")
    public DirectionResponse update(@RequestBody DirectionRequest directionRequest, @PathVariable Long id) {
        return dataMapper.convertFromDirectionToResponse(directionService.updateById(id, directionRequest));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id) {
        directionService.deleteById(id);
        return ResponseEntity.ok("Good");
    }

    @DeleteMapping("/delete/filter")
    public ResponseEntity<String> deleteByFilter(@RequestBody FilterDto filterDto) {
        directionService.deleteByFilter(filterDto);
        return ResponseEntity.ok("Good");
    }

    @DeleteMapping("/delete/all")
    public ResponseEntity<String> deleteAll() {
        directionService.deleteAll();
        return ResponseEntity.ok("Good");
    }

}
