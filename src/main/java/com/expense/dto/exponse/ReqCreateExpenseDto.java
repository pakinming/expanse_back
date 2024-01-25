package com.expense.dto.exponse;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class ReqCreateExpenseDto {

    @NotNull(message = "req")
    private Double expend;
    private String note;


}
