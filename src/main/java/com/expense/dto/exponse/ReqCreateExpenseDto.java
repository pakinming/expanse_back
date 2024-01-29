package com.expense.dto.exponse;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;


@Data
@NoArgsConstructor
public class ReqCreateExpenseDto {

    @NotNull
    private Double expend;
    @NotNull
    private OffsetDateTime expendDate;
    private String note;


}
