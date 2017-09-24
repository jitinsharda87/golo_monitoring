package com.golo.golo_monitoring.controller.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Jitin Sharda
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class StatusValueNotValid extends RuntimeException{
    public StatusValueNotValid() {
        super(String.format("value of status should be STOP or START "));
    }

}
