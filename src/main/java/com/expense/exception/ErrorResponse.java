package com.expense.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;


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
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").withLocale(Locale.of("th","TH"));
        String formattedDate = LocalDateTime.now().format(formatter);
        this.timestamp = LocalDateTime.parse(formattedDate, formatter);
    }

}