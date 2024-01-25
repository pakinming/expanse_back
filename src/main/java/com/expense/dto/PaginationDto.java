package com.expense.dto;


import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldNameConstants;

@Data
@NoArgsConstructor
public class PaginationDto {

    private  Integer page;
    private  Integer pageSize;
    private  String sortBy;
    private  String sortDirection;
}
