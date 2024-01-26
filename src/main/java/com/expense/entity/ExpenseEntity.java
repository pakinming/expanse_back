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

    @Column(columnDefinition = "numeric", nullable = false)
    private Double expend;

    @Column(columnDefinition = "timestamp", nullable = false)
    private OffsetDateTime expendDate;

    @Column(columnDefinition = "text")
    private String note;

    @Column(columnDefinition = "timestamp", updatable = false, nullable = false)
    private OffsetDateTime createdAt;

    @PreUpdate
    public void preUpdate() {
        expendDate = OffsetDateTime.now();
    }

    @PrePersist
    private void onPresist() {
        createdAt = OffsetDateTime.now();
        expendDate = OffsetDateTime.now();

    }




}
