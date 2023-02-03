package com.pc.hamed.service;

import com.pc.hamed.entity.SampleEntity;
import com.pc.hamed.exception.BusinessException;
import com.pc.hamed.repository.SampleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Optional;

@Service
public class SampleService {

    private final SampleRepository repository;

    public SampleService(SampleRepository repository) {
        this.repository = repository;
    }

    public void saveList(List<SampleEntity> entityList) {

        if(CollectionUtils.isEmpty(entityList))
            throw new BusinessException(HttpStatus.BAD_REQUEST, "List Is Null Or Has No Elements");

        repository.saveAll(entityList);
    }

    public List<SampleEntity> getAllData() {
        return repository.findAll();
    }

    public SampleEntity getByCode(String code) {
        return Optional.ofNullable(repository.findByCode(code))
                .orElseThrow(() -> new BusinessException(HttpStatus.NOT_FOUND, "Record Not Found By The Given Code"));
    }

    public void deleteAll() {
        repository.deleteAll();
    }

}
