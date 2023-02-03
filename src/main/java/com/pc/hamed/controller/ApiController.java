package com.pc.hamed.controller;

import com.opencsv.bean.CsvToBeanBuilder;
import com.pc.hamed.dto.SampleDto;
import com.pc.hamed.entity.SampleEntity;
import com.pc.hamed.exception.BusinessException;
import com.pc.hamed.mapper.SampleEntityMapper;
import com.pc.hamed.messages.Messages;
import com.pc.hamed.service.SampleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.factory.Mappers;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.Size;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE;

@Slf4j
@RestController
@Validated
@RequestMapping(path = {"/api/v1"}, produces = MediaType.APPLICATION_JSON_VALUE)
public class ApiController {

    private final SampleService service;
    private SampleEntityMapper mapper = Mappers.getMapper(SampleEntityMapper.class);

    public ApiController(SampleService service) {
        this.service = service;
    }

    @PostMapping(value = "/upload", consumes = MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "Stores all CSV data into database")
    @ApiResponse(
            responseCode = "201",
            description = Messages.SAVE_SUCCESS_MESSAGE,
            content = {@Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = SampleDto.class))}
    )
    public ResponseEntity<List<SampleDto>> upload(@Valid @RequestParam(value = "csvFile") MultipartFile multipartFile) {

        List dtoList;
        try (Reader reader = new BufferedReader(new InputStreamReader(multipartFile.getInputStream()))) {
            dtoList = new CsvToBeanBuilder(reader)
                    .withType(SampleDto.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build()
                    .parse();
        } catch (Exception e) {
            e.printStackTrace();
            throw new BusinessException(HttpStatus.BAD_REQUEST, "Error In Parsing File");
        }

        List<SampleEntity> entityList = mapper.dtoListToEntityList(dtoList);
        service.saveList(entityList);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(mapper.entityListToDtoList(entityList));

    }

    @GetMapping()
    @Operation(summary = "Show All Database DataList To The Client")
    @ApiResponse(
            responseCode = "200",
            description = Messages.GET_ALL_DATA_MESSAGE,
            content = {@Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = SampleDto.class))}
    )
    public ResponseEntity<List<SampleDto>> getAll() {
        List<SampleEntity> allData = service.getAllData();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(mapper.entityListToDtoList(allData));
    }

    @GetMapping(value = "/{code}", consumes = APPLICATION_JSON_VALUE)
    @Operation(summary = "Get a Specific Record Via Code")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = Messages.RECORD_FOUND_MESSAGE,
                    content = {
                            @Content(mediaType = APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = SampleDto.class))
                    }
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = Messages.RECORD_NOT_FOUND_MESSAGE,
                    content = {@Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema)}
            )
    })
    public ResponseEntity<SampleDto> getByCode(@PathVariable @Size(min = 1) String code) {
        SampleEntity entity = service.getByCode(code);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(mapper.entityToDto(entity));
    }

    @DeleteMapping()
    @Operation(summary = "Delete All Entities In Database")
    @ApiResponse(
            responseCode = "204",
            description = Messages.DELETE_SUCCESS_MESSAGE
    )
    public ResponseEntity<Void> deleteAll() {
        service.deleteAll();
        return ResponseEntity.noContent().build();
    }

}
