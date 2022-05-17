package ru.kpfu.itis.ibragimovaidar.moviesearcherapp.controller.handler;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@Builder
public class ExceptionMessage {

    private String endpoint;

    private String message;

    private String detailMessage;

    private String exceptionName;

}
