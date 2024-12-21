package com.example.datamanagementserver.service;//package com.example.laboratorywork.service;


import com.example.datamanagementserver.dto.FilterDto;
import com.example.datamanagementserver.dto.auditory.AuditoryRequest;
import com.example.datamanagementserver.entity.Auditory;
import com.example.datamanagementserver.exceptions.NotFoundByFilterFieldException;
import com.example.datamanagementserver.exceptions.NotFoundByIdException;
import com.example.datamanagementserver.repository.AuditoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuditoryService {

    private final AuditoryRepository auditoryRepository;
    private final CorpusService corpusService;

    public Auditory create(AuditoryRequest auditoryRequest) {
        Auditory auditory = new Auditory();
        auditory.setCapacity(auditoryRequest.getCapacity());
        auditory.setNumber(auditoryRequest.getNumber());
        auditory.setCorpus(corpusService.findById(auditoryRequest.getCorpusId()));
        return auditoryRepository.save(auditory);
    }

    public List<Auditory> getAll() {
        return auditoryRepository.findAll();
    }

    public void existById(Long id) {
        if (!auditoryRepository.existsById(id)) {
            throw new NotFoundByIdException("Auditory", id);
        }
    }

    public Auditory findById(Long id) {
        existById(id);
        return auditoryRepository.getReferenceById(id);
    }
    public Auditory updateById(Long id, AuditoryRequest auditoryRequest) {
        Auditory auditory = findById(id);
        auditory.setCapacity(auditoryRequest.getCapacity());
        auditory.setNumber(auditoryRequest.getNumber());
        auditory.setCorpus(corpusService.findById(auditoryRequest.getCorpusId()));
        return auditoryRepository.save(auditory);
    }

    public void deleteById(Long id) {
        existById(id);
        auditoryRepository.deleteById(id);
    }

    public void deleteByFilter(FilterDto filterDto) {
        if (filterDto.getField().equals("number")) {
            auditoryRepository.deleteByNumber(filterDto.getIntFilter());
        }
        else {
            throw new NotFoundByFilterFieldException();
        }
    }

    public void deleteAll() {
        auditoryRepository.deleteAll();
    }



}