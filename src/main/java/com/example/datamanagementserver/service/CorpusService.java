package com.example.datamanagementserver.service;


import com.example.datamanagementserver.dto.FilterDto;
import com.example.datamanagementserver.dto.corpus.CorpusRequest;
import com.example.datamanagementserver.entity.Corpus;
import com.example.datamanagementserver.exceptions.NotFoundByFilterFieldException;
import com.example.datamanagementserver.exceptions.NotFoundByIdException;
import com.example.datamanagementserver.repository.CorpusRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CorpusService {

    private final CorpusRepository corpusRepository;

    public Corpus create(Corpus corpus) {
        return corpusRepository.save(corpus);
    }

    public List<Corpus> getAll() {
        return corpusRepository.findAll();
    }

    public void existById(Long id) {
        if (!corpusRepository.existsById(id)) {
            throw new NotFoundByIdException("Corpus", id);
        }
    }

    public Corpus findById(Long id) {
        existById(id);
        return corpusRepository.getReferenceById(id);
    }
    public Corpus updateById(Long id, CorpusRequest corpusRequest) {
        Corpus corpus = findById(id);
        corpus.setTown(corpusRequest.getTown());
        corpus.setHouse(corpusRequest.getHouse());
        corpus.setStreet(corpusRequest.getStreet());
        return corpusRepository.save(corpus);
    }

    public void deleteById(Long id) {
        existById(id);
        corpusRepository.deleteById(id);
    }

    public void deleteByFilter(FilterDto filterDto) {
        if (filterDto.getField().equals("street")) {
            corpusRepository.deleteByStreet(filterDto.getTextFilter());
        }
        else {
            throw new NotFoundByFilterFieldException();
        }
    }

    public void deleteAll() {
        corpusRepository.deleteAll();
    }

}