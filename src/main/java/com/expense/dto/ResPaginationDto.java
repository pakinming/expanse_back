package com.expense.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResPaginationDto<T> {
    private T content;
    private long numberOfResult;
    private long totalResult;
    private int totalPages;
    private double expendSummary;
}