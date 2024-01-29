package com.expense.controller;

import com.expense.dto.ReqPaginationDto;
import com.expense.dto.ResponseHandler;
import com.expense.dto.history.ReqCreateHistoryDto;
import com.expense.entity.HistoryEntity;
import com.expense.sevices.HistoryService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/history")
public class HistoryController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private HistoryService historyService;

    @GetMapping("/")
    public ResponseEntity<Object> getAllHistory(
            @RequestParam(defaultValue = "1") int pageNo,
            @RequestParam(defaultValue = "10") int pageSize,
            @RequestParam(defaultValue = "createdAt") String sortBy,
            @RequestParam(defaultValue = "DESC") String sortDirection
    ) throws Exception {


        ReqPaginationDto paging = ReqPaginationDto.builder()
                .pageNo(pageNo - 1)
                .pageSize(pageSize)
                .sortBy(sortBy)
                .sortDirection(sortDirection)
                .build();

        logger.info("GET: /api/v1/history {}", paging.toString());


        return ResponseHandler.generateResponse(
                HttpStatus.OK.series().toString(),
                HttpStatus.OK,
                historyService.getAllHistory(paging));

    }

    public ResponseEntity<Object> createHistoryRecord(@RequestBody @Validated @Valid ReqCreateHistoryDto body) throws Exception {

        HistoryEntity history = HistoryEntity.builder()
                .action(body.getAction())
                .expendId(body.getExpenseId())
                .expend(body.getExpend())
                .expendDate(body.getExpensedDate())
                .note(body.getNote())
                .build();
        logger.info("createHistoryRecord {}", history);


        return ResponseHandler.generateResponse(
                HttpStatus.OK.series().toString(),
                HttpStatus.OK,
                historyService.createHistory(history));

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteHistoryRecord(@PathVariable Integer id) throws Exception {

        historyService.deleteHistoryById(id);
        return ResponseHandler.generateResponse(
                HttpStatus.OK.series().toString(),
                HttpStatus.OK,
                null);
    }


}
