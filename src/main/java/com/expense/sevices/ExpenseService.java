package com.expense.sevices;


import com.expense.dto.PaginationDto;
import com.expense.dto.exponse.HistoryEvent;
import com.expense.dto.exponse.ReqCreateExpenseDto;
import com.expense.dto.exponse.UpdateExpenseDto;
import com.expense.entity.ExpenseEntity;
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

import java.util.HashMap;
import java.util.Optional;

@Service
public class ExpenseService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ExpenseRepository expenseRepository;

    @Autowired
    private HistoryRepository historyRepository;

    public Object findAll(PaginationDto req) {

        Sort sort = Sort.by(Sort.Direction.fromString(req.getSortDirection()), req.getSortBy());
        Pageable pageable = PageRequest.of(req.getPageNo(), req.getPageSize(), sort);


        Page<ExpenseEntity> paging = expenseRepository.findAll(pageable);

        HashMap<String, Object> response = new HashMap<>();

        response.put("content", paging.getContent());
        response.put("totalResult", paging.getTotalElements());
        response.put("numberOfResult", paging.getNumberOfElements());
        response.put("totalPages", paging.getTotalPages());

        return response;
    }

    public ExpenseEntity findOneExpense(Integer id) throws Exception {

        Optional<ExpenseEntity> ee = expenseRepository.findById(id);
        if (ee.isPresent()) {
            return ee.get();
        } else {
            throw new BadRequestException("Not found your id " + id);
        }

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

        //TODO: add history and event
        ExpenseEntity entity = findOneExpense(req.getId());
        entity.setExpend(req.getExpend());
        entity.setNote(req.getNote());
        return expenseRepository.save(entity);
    }

    public void deleteExpense(Integer id, HistoryEvent historyEvent) throws Exception {
        //TODO: add history event
        expenseRepository.delete(findOneExpense(id));
    }


}
