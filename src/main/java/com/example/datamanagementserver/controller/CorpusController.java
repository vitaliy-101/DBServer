package com.example.datamanagementserver.controller;

import com.example.datamanagementserver.dto.FilterDto;
import com.example.datamanagementserver.dto.corpus.CorpusRequest;
import com.example.datamanagementserver.dto.corpus.CorpusResponse;
import com.example.datamanagementserver.mapper.DataMapper;
import com.example.datamanagementserver.service.CorpusService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/corpus")
public class CorpusController {
    private final CorpusService corpusService;
    private final DataMapper dataMapper;

    @GetMapping()
    public List<CorpusResponse> getAll() {
        return dataMapper.convertFromCorpusListToResponse(corpusService.getAll());
    }

    @PostMapping()
    public CorpusResponse create(@RequestBody CorpusRequest corpusRequest) {
        return dataMapper.convertFromCorpusToResponse(corpusService.create(dataMapper.convertFromCorpusRequestToEntity(corpusRequest)));
    }

    @PutMapping("/update/{id}")
    public CorpusResponse update(@RequestBody CorpusRequest corpusRequest, @PathVariable Long id) {
        return dataMapper.convertFromCorpusToResponse(corpusService.updateById(id, corpusRequest));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id) {
        corpusService.deleteById(id);
        return ResponseEntity.ok("Good");
    }

    @PutMapping("/delete/filter")
    public ResponseEntity<String> deleteByFilter(@RequestBody FilterDto filterDto) {
        corpusService.deleteByFilter(filterDto);
        return ResponseEntity.ok("Good");
    }

    @DeleteMapping("/delete/all")
    public ResponseEntity<String> deleteAll() {
        corpusService.deleteAll();
        return ResponseEntity.ok("Good");
    }
}
