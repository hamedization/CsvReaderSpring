package com.pc.hamed.mapper;

import com.pc.hamed.dto.SampleDto;
import com.pc.hamed.entity.SampleEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper
public interface SampleEntityMapper {

    @Mapping(target = "id", ignore = true)
    List<SampleEntity> dtoListToEntityList(List<SampleDto> dtoList);

    List<SampleDto> entityListToDtoList(List<SampleEntity> entityList);

    SampleDto entityToDto(SampleEntity entity);

    SampleEntity dtoToEntity(SampleDto sampleDto);


}
