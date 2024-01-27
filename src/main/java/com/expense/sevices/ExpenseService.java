package com.expense.sevices;


import com.expense.dto.HistoryEvent;
import com.expense.dto.ReqPaginationDto;
import com.expense.dto.ResPaginationDto;
import com.expense.dto.exponse.ReqCreateExpenseDto;
import com.expense.dto.exponse.UpdateExpenseDto;
import com.expense.entity.ExpenseEntity;
import com.expense.entity.HistoryEntity;
import com.expense.repository.ExpenseRepository;
import jakarta.transaction.Transactional;
import org.apache.coyote.BadRequestException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

@Service
public class ExpenseService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ExpenseRepository expenseRepository;

    @Autowired
    private HistoryService historyService;

    public Object findAll(ReqPaginationDto req) throws Exception {

        Sort sort = Sort.by(Sort.Direction.fromString(req.getSortDirection()), req.getSortBy());
        Pageable pageable = PageRequest.of(req.getPageNo(), req.getPageSize(), sort);

        Page<ExpenseEntity> paging = expenseRepository.findAll(pageable);


        return ResPaginationDto.builder()
                .content(Collections.singletonList(paging.getContent()))
                .numberOfResult(paging.getNumberOfElements())
                .totalResult(paging.getTotalElements())
                .totalPages(paging.getTotalPages())
                .build();

    }

    public ExpenseEntity findOneExpense(Integer id) throws Exception {

        Optional<ExpenseEntity> ee = expenseRepository.findById(id);
        if (ee.isPresent()) {
            return ee.get();
        } else {
            throw new BadRequestException("Not found your id " + id);
        }

    }

    @Transactional(rollbackOn = Exception.class)
    public ExpenseEntity createExpend(ReqCreateExpenseDto req, HistoryEvent historyEvent) throws Exception {

        if (0.0 >= req.getExpend()) {
            throw new BadRequestException("Value must be greater than 0.0");
        }

        ExpenseEntity expense = new ExpenseEntity();
        expense.setExpend(req.getExpend());
        expense.setNote(req.getNote());
        var expenseRes = expenseRepository.save(expense);

        historyService.createHistory(HistoryEntity.builder()
                .action(historyEvent.name())
                .expendId(expenseRes.getId())
                .expend(expenseRes.getExpend())
                .expendDate(expenseRes.getExpendDate())
                .note(expenseRes.getNote())
                .build());

        return expenseRes;
    }

    @Transactional(rollbackOn = Exception.class)
    public ExpenseEntity updateExpend(UpdateExpenseDto req, HistoryEvent historyEvent) throws Exception {

        //TODO: add history and event

        ExpenseEntity entity = findOneExpense(req.getId());
        entity.setExpend(req.getExpend());
        entity.setNote(req.getNote());

        var expenseRes = expenseRepository.save(entity);

        historyService.createHistory(HistoryEntity.builder()
                .action(historyEvent.name())
                .expendId(expenseRes.getId())
                .expend(expenseRes.getExpend())
                .expendDate(expenseRes.getExpendDate())
                .note(expenseRes.getNote())
                .build());

        return expenseRes;
    }

    @Transactional(rollbackOn = Exception.class)
    public void deleteExpense(Integer id, HistoryEvent historyEvent) throws Exception {
        //TODO: add history event

        var expenseRes = findOneExpense(id);

        historyService.createHistory(HistoryEntity.builder()
                .action(historyEvent.name())
                .expendId(expenseRes.getId())
                .expend(expenseRes.getExpend())
                .expendDate(expenseRes.getExpendDate())
                .note(expenseRes.getNote())
                .build());

        expenseRepository.delete(findOneExpense(id));
    }


}
