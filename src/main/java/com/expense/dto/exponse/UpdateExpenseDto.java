package com.expense.dto.exponse;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UpdateExpenseDto {

    @NotNull
    private Integer id;
    @NotNull
    private Double expend;
    private String note;
}
