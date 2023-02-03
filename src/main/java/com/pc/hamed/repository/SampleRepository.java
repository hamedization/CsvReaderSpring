package com.pc.hamed.repository;

import com.pc.hamed.entity.SampleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface SampleRepository extends JpaRepository<SampleEntity, Long> {

    SampleEntity findByCode(String code);

}
