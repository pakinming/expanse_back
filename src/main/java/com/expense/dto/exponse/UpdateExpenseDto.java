package com.expense.dto.exponse;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

@Data
@NoArgsConstructor
public class UpdateExpenseDto {

    private Integer id;
    private Double expend;
    private String note;
}
