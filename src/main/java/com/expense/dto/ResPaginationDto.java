package com.expense.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ResPaginationDto<T> {
    private List<T> content;
    private long numberOfResult;
    private long totalResult;
    private int totalPages;

}