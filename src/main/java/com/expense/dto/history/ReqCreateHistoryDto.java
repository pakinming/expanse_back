package com.expense.dto.history;


import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.OffsetDateTime;

@Data
@AllArgsConstructor
public class ReqCreateHistoryDto {

    @NotNull
    Integer expenseId;

    @NotNull
    Double expend;

    @NotNull
    OffsetDateTime expensedDate;

    String note;

    @NotNull
    String action;

}
