package com.pc.hamed.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.util.Date;

@Getter
@Setter
public class BusinessError {

    private HttpStatus status;
    private String message;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm")
    private Date timestamp;

    public BusinessError(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
        this.timestamp = new Date();
    }
}
