package com.expense.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import static com.expense.constant.Utils.FMT_RFC3339;


@Data
@AllArgsConstructor

public class ErrorResponse {
    private int responseCode;
    private String errorMessage;
    private LocalDateTime timestamp;
    private String path;

    public ErrorResponse(int responseCode, String errorMessage, String path) {
        this.responseCode = responseCode;
        this.errorMessage = errorMessage;
        this.path = path;

        String formattedDate = LocalDateTime.now().format(FMT_RFC3339);
        this.timestamp = LocalDateTime.parse(formattedDate, FMT_RFC3339);
    }

}