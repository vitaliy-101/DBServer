package com.example.datamanagementserver.controller;

import com.example.datamanagementserver.dto.FilterDto;
import com.example.datamanagementserver.dto.Year.YearRequest;
import com.example.datamanagementserver.dto.Year.YearResponse;
import com.example.datamanagementserver.mapper.DataMapper;
import com.example.datamanagementserver.service.YearService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/year")
public class YearController {
    private final YearService yearService;
    private final DataMapper dataMapper;

    @GetMapping()
    public List<YearResponse> getAll() {
        return dataMapper.convertFromYearListToResponse(yearService.getAll());
    }

    @PostMapping()
    public YearResponse create(@RequestBody YearRequest yearRequest) {
        return dataMapper.convertFromYearToResponse(yearService.create(dataMapper.convertFromYearRequestToEntity(yearRequest)));
    }

    @PutMapping("/update/{id}")
    public YearResponse update(@RequestBody YearRequest yearRequest, @PathVariable Long id) {
        return dataMapper.convertFromYearToResponse(yearService.updateById(id, yearRequest));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id) {
        yearService.deleteById(id);
        return ResponseEntity.ok("Good");
    }

    @DeleteMapping("/delete/filter")
    public ResponseEntity<String> deleteByFilter(@RequestBody FilterDto filterDto) {
        yearService.deleteByFilter(filterDto);
        return ResponseEntity.ok("Good");
    }

    @DeleteMapping("/delete/all")
    public ResponseEntity<String> deleteAll() {
        yearService.deleteAll();
        return ResponseEntity.ok("Good");
    }

}
