package com.example.datamanagementserver.service;

import com.example.datamanagementserver.dto.FilterDto;
import com.example.datamanagementserver.dto.direction.DirectionResponse;
import com.example.datamanagementserver.dto.group.GroupRequest;
import com.example.datamanagementserver.entity.Group;
import com.example.datamanagementserver.exceptions.NotFoundByFilterFieldException;
import com.example.datamanagementserver.exceptions.NotFoundByIdException;
import com.example.datamanagementserver.repository.GroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GroupService {
    private final GroupRepository groupRepository;
    private final DirectionService directionService;
    private final YearService yearService;

    public Group create(GroupRequest groupRequest) {
        Group group = new Group();
        group.setDirection(directionService.findById(groupRequest.getDirectionId()));
        group.setYear(yearService.findById(groupRequest.getYearId()));
        group.setNumber(groupRequest.getNumber());
        group.setUserCount(groupRequest.getUserCount());
        return groupRepository.save(group);
    }

    public List<Group> getAll() {
        return groupRepository.findAll();
    }

    public void existById(Long id) {
        if (!groupRepository.existsById(id)) {
            throw new NotFoundByIdException("Group", id);
        }
    }

    public Group findById(Long id) {
        existById(id);
        return groupRepository.getReferenceById(id);
    }

    public Group updateById(Long id, GroupRequest groupRequest) {
        Group group = findById(id);
        group.setDirection(directionService.findById(groupRequest.getDirectionId()));
        group.setYear(yearService.findById(groupRequest.getYearId()));
        group.setNumber(groupRequest.getNumber());
        group.setUserCount(group.getUserCount());
        return groupRepository.save(group);
    }

    public void deleteById(Long id) {
        existById(id);
        groupRepository.deleteById(id);
    }

    public void deleteByFilter(FilterDto filterDto) {
        if (filterDto.getField().equals("number")) {
            groupRepository.deleteGroupByNumber(filterDto.getIntFilter());
        }
        else {
            throw new NotFoundByFilterFieldException();
        }
    }

    public void deleteAll() {
        groupRepository.deleteAll();
    }

}
