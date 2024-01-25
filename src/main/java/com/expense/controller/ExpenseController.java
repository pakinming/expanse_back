package com.expense.controller;


import com.expense.dto.exponse.HistoryEvent;
import com.expense.dto.exponse.ReqCreateExpenseDto;
import com.expense.dto.exponse.ReqExpenseListDto;
import com.expense.sevices.ExpenseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/expanse")

public class ExpenseController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ExpenseService expenseService;


    @GetMapping
    public ResponseEntity<Object> getExpanseList(@PathVariable  Integer id) throws Exception {
        //int pageNo, int pageSize, String sortBy, String sortDirection
        return null;
    }
    @PostMapping("getExpenseList")
    public ResponseEntity<Object> getExpanseList(@RequestBody ReqExpenseListDto body) throws Exception {
        //int pageNo, int pageSize, String sortBy, String sortDirection
        return null;
    }

    @PostMapping
    public ResponseEntity<Object> createExpense(@RequestBody @Validated ReqCreateExpenseDto body) throws Exception {

        logger.info("POST: /api/v1/expanse");

        return ResponseEntity.ok(expenseService.createExpend(body, HistoryEvent.CREATE));
    }

}
