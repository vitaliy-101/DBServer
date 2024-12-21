package com.example.datamanagementserver.service;

import com.example.datamanagementserver.dto.FilterDto;
import com.example.datamanagementserver.dto.direction.DirectionRequest;
import com.example.datamanagementserver.dto.direction.DirectionResponse;
import com.example.datamanagementserver.entity.Direction;
import com.example.datamanagementserver.entity.Group;
import com.example.datamanagementserver.exceptions.NotFoundByFilterFieldException;
import com.example.datamanagementserver.exceptions.NotFoundByIdException;
import com.example.datamanagementserver.mapper.DataMapper;
import com.example.datamanagementserver.repository.DirectionRepository;
import com.example.datamanagementserver.repository.GroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class DirectionService {
    private final DirectionRepository directionRepository;
    private final DataMapper dataMapper;

    public Direction create(DirectionRequest directionRequest) {
        Direction direction = dataMapper.convertFromDirectionRequestToEntity(directionRequest);
        return directionRepository.save(direction);
    }

    public List<Direction> getAll() {
        return directionRepository.findAll();
    }

    public void existById(Long id) {
        if (!directionRepository.existsById(id)) {
            throw new NotFoundByIdException("Direction", id);
        }
    }

    public Direction updateById(Long id, DirectionRequest directionRequest) {
        Direction existingDirection = findById(id);
        if (directionRequest.getName() != null) {
            existingDirection.setName(directionRequest.getName());
        }
        return directionRepository.save(existingDirection);
    }

    public Direction findById(Long id) {
        existById(id);
        return directionRepository.getReferenceById(id);
    }

    public void deleteById(Long id) {
        existById(id);
        directionRepository.deleteById(id);
    }

    public void deleteByFilter(FilterDto filterDto) {
        if (filterDto.getField().equals("name")) {
            directionRepository.deleteByName(filterDto.getTextFilter());
        }
        else {
            throw new NotFoundByFilterFieldException();
        }
    }

    public void deleteAll() {
        directionRepository.deleteAll();
    }

}
