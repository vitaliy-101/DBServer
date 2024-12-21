package com.example.datamanagementserver.controller;

import com.example.datamanagementserver.dto.FilterDto;
import com.example.datamanagementserver.dto.direction.DirectionRequest;
import com.example.datamanagementserver.dto.direction.DirectionResponse;
import com.example.datamanagementserver.dto.group.GroupRequest;
import com.example.datamanagementserver.dto.group.GroupResponse;
import com.example.datamanagementserver.mapper.DataMapper;
import com.example.datamanagementserver.service.GroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/group")
public class GroupController {
    private final GroupService groupService;
    private final DataMapper dataMapper;

    @GetMapping()
    public List<GroupResponse> getAll() {
        return dataMapper.convertFromGroupListToResponse(groupService.getAll());
    }

    @PostMapping()
    public GroupResponse create(@RequestBody GroupRequest groupRequest) {
        return dataMapper.convertFromGroupToResponse(groupService.create(groupRequest));
    }

    @PutMapping("/update/{id}")
    public GroupResponse update(@RequestBody GroupRequest groupRequest, @PathVariable Long id) {
        return dataMapper.convertFromGroupToResponse(groupService.updateById(id, groupRequest));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id) {
        groupService.deleteById(id);
        return ResponseEntity.ok("Good");
    }

    @DeleteMapping("/delete/filter")
    public ResponseEntity<String> deleteByFilter(@RequestBody FilterDto filterDto) {
        groupService.deleteByFilter(filterDto);
        return ResponseEntity.ok("Good");
    }

    @DeleteMapping("/delete/all")
    public ResponseEntity<String> deleteAll() {
        groupService.deleteAll();
        return ResponseEntity.ok("Good");
    }
}
