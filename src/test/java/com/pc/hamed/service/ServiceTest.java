package com.pc.hamed.service;

import com.pc.hamed.entity.SampleEntity;
import com.pc.hamed.repository.SampleRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Collections;
import java.util.Optional;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest
public class ServiceTest {

    @Autowired
    private SampleService service;

    @MockBean
    private SampleRepository repository;

    @Test
    void saveOne_success() {
        SampleEntity entity = new SampleEntity();
        entity.setCode("ABC123");
        Collections.singletonList(entity);

        Mockito.doReturn(Optional.empty()).when(repository).save(entity);

        verify(repository, times(1)).save(entity);
    }
}
