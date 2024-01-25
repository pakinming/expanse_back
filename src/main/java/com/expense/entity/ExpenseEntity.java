package com.expense.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.OffsetDateTime;

@Entity
@Table(name = "expense")
@Data
public class ExpenseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "expanse_id")
    private Integer id;

    @Column(columnDefinition = "numeric")
    private Double expend;

    @Column(columnDefinition = "timestamp default now()")
    private OffsetDateTime expendDate;

    @Column(columnDefinition = "text")
    private String note;

    @Column(columnDefinition = "timestamp default now()", updatable = false)
    private OffsetDateTime createdAt;

    @PreUpdate
    public void preUpdate() {
        expendDate = OffsetDateTime.now();

    }
}
