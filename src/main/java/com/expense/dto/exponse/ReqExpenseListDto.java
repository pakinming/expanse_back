package com.expense.dto.exponse;

import com.expense.dto.PaginationDto;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class ReqExpenseListDto extends PaginationDto {
    @NotNull
    private List<Integer> ids;

}
