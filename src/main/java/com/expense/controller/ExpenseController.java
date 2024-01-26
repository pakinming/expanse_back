package com.expense.controller;


import com.expense.dto.PaginationDto;
import com.expense.dto.ResponseHandler;
import com.expense.dto.exponse.HistoryEvent;
import com.expense.dto.exponse.ReqCreateExpenseDto;
import com.expense.dto.exponse.UpdateExpenseDto;
import com.expense.sevices.ExpenseService;
import jakarta.validation.constraints.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/expanse")

public class ExpenseController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ExpenseService expenseService;


    @GetMapping
    public ResponseEntity<Object> getExpanse(
            @RequestParam(defaultValue = "1") int pageNo,
            @RequestParam(defaultValue = "10") int pageSize,
            @RequestParam(defaultValue = "expendDate") String sortBy,
            @RequestParam(defaultValue = "DESC") String sortDirection) throws Exception {


        PaginationDto paging = PaginationDto.builder()
                .pageNo(pageNo - 1)
                .pageSize(pageSize)
                .sortBy(sortBy)
                .sortDirection(sortDirection)
                .build();

        logger.info("GET: /api/v1/" + paging.toString());
        return ResponseHandler.generateResponse(
                HttpStatus.OK.series().toString(),
                HttpStatus.OK,
                expenseService.findAll(paging));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getExpanseList(@PathVariable Integer id) throws Exception {

        logger.info("POST: /api/v1/" + id);
        return ResponseHandler.generateResponse(
                HttpStatus.OK.series().toString(),
                HttpStatus.OK,
                expenseService.findOneExpense(id));
    }

    @PostMapping
    public ResponseEntity<Object> createExpense(@RequestBody ReqCreateExpenseDto body) throws Exception {


        logger.info("POST: /api/v1/expense/create");
        return ResponseHandler.generateResponse(
                HttpStatus.CREATED.series().toString(),
                HttpStatus.CREATED,
                expenseService.createExpend(body, HistoryEvent.CREATE));

    }

    @PutMapping
    public ResponseEntity<Object> updateExpense(@RequestBody UpdateExpenseDto body) throws Exception {
        logger.info("PUT: /api/v1/expense");

        return ResponseHandler.generateResponse(
                HttpStatus.OK.series().toString(),
                HttpStatus.OK,
                expenseService.updateExpend(body, HistoryEvent.UPDATE));

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteExpense(@PathVariable @NotNull Integer id) throws Exception {

        expenseService.deleteExpense(id, HistoryEvent.DELETE);

        return ResponseHandler.generateResponse(
                HttpStatus.OK.series().toString(),
                HttpStatus.OK,
                HttpStatus.OK.series()
        );
    }

}
