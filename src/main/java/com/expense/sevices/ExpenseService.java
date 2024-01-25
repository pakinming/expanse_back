package com.expense.sevices;


import com.expense.dto.exponse.HistoryEvent;
import com.expense.dto.exponse.ReqCreateExpenseDto;
import com.expense.dto.exponse.UpdateExpenseDto;
import com.expense.entity.ExpenseEntity;
import com.expense.entity.HistoryEntity;
import com.expense.repository.ExpenseRepository;
import com.expense.repository.HistoryRepository;
import org.apache.coyote.BadRequestException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ExpenseService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ExpenseRepository expenseRepository;

    @Autowired
    private HistoryRepository historyRepository;

    public Page<ExpenseEntity> findAll(int pageNo, int pageSize, String sortBy, String sortDirection) {
        Sort sort = Sort.by(Sort.Direction.fromString(sortDirection), sortBy);
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);

        return expenseRepository.findAll(pageable);
    }

    public ExpenseEntity createExpend(ReqCreateExpenseDto req, HistoryEvent historyEvent) throws Exception {

        if (0.0 >= req.getExpend()) {
            throw new BadRequestException("Value must be greater than 0.0");
        }

        ExpenseEntity expense = new ExpenseEntity();
        expense.setExpend(req.getExpend());
        expense.setNote(req.getNote());
        //TODO: add history and event

        return expenseRepository.save(expense);
    }

    public ExpenseEntity updateExpend(UpdateExpenseDto req, HistoryEvent historyEvent) throws Exception {

        Optional<ExpenseEntity> entity = expenseRepository.findById(req.getId());

        if (entity.isPresent()) {
            ExpenseEntity ee = entity.get();
            ee.setExpend(req.getExpend());
            ee.setNote(req.getNote());
            //TODO: add history and event
            return expenseRepository.save(ee);

        } else {
            throw new BadRequestException("Not found your id " + req.getId());
        }

    }

}
