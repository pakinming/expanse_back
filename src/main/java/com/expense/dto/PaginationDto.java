package com.expense.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaginationDto {

    private Integer pageNo;
    private Integer pageSize;
    private String sortBy;
    private String sortDirection;
}
