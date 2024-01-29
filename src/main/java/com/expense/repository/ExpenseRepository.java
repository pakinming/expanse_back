package com.expense.repository;


import com.expense.entity.ExpenseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ExpenseRepository extends JpaRepository<ExpenseEntity, Integer> {



    @Query(value = "SELECT SUM(expend) FROM expense", nativeQuery = true)
    Double getSumExpend();


}