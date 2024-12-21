package com.example.datamanagementserver.service;


import com.example.datamanagementserver.dto.FilterDto;
import com.example.datamanagementserver.dto.Year.YearRequest;
import com.example.datamanagementserver.entity.Year;
import com.example.datamanagementserver.exceptions.NotFoundByFilterFieldException;
import com.example.datamanagementserver.exceptions.NotFoundByIdException;
import com.example.datamanagementserver.repository.YearRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class YearService {
    private final YearRepository yearRepository;

    public Year create(Year year) {
        return yearRepository.save(year);
    }

    public List<Year> getAll() {
        return yearRepository.findAll();
    }


    public void existById(Long id) {
        if (!yearRepository.existsById(id)) {
            throw new NotFoundByIdException("Year", id);
        }
    }

    public Year findById(Long id) {
        existById(id);
        return yearRepository.getReferenceById(id);
    }

    public Year updateById(Long id, YearRequest yearRequest) {
        Year year = findById(id);
        year.setYear(yearRequest.getYear());
        return yearRepository.save(year);
    }

    public void deleteById(Long id) {
        existById(id);
        yearRepository.deleteById(id);
    }

    public void deleteByFilter(FilterDto filterDto) {
        if (filterDto.getField().equals("year")) {
            yearRepository.deleteByYear(filterDto.getIntFilter());
        }
        else {
            throw new NotFoundByFilterFieldException();
        }
    }

    public void deleteAll() {
        yearRepository.deleteAll();
    }


}
