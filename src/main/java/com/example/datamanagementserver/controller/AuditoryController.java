package com.example.datamanagementserver.controller;

import com.example.datamanagementserver.dto.FilterDto;
import com.example.datamanagementserver.dto.auditory.AuditoryRequest;
import com.example.datamanagementserver.dto.auditory.AuditoryResponse;
import com.example.datamanagementserver.mapper.DataMapper;
import com.example.datamanagementserver.service.AuditoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auditory")
public class AuditoryController {
    private final AuditoryService auditoryService;
    private final DataMapper dataMapper;

    @GetMapping()
    public List<AuditoryResponse> getAll() {
        return dataMapper.convertFromAuditoryListToResponse(auditoryService.getAll());
    }

    @PostMapping()
    public AuditoryResponse create(@RequestBody AuditoryRequest auditoryRequest) {
        return dataMapper.convertFromAuditoryToResponse(auditoryService.create(auditoryRequest));
    }

    @PutMapping("/update/{id}")
    public AuditoryResponse update(@RequestBody AuditoryRequest auditoryRequest, @PathVariable Long id) {
        return dataMapper.convertFromAuditoryToResponse(auditoryService.updateById(id, auditoryRequest));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id) {
        auditoryService.deleteById(id);
        return ResponseEntity.ok("Good");
    }

    @DeleteMapping("/delete/filter")
    public ResponseEntity<String> deleteByFilter(@RequestBody FilterDto filterDto) {
        auditoryService.deleteByFilter(filterDto);
        return ResponseEntity.ok("Good");
    }

    @DeleteMapping("/delete/all")
    public ResponseEntity<String> deleteAll() {
        auditoryService.deleteAll();
        return ResponseEntity.ok("Good");
    }
}
