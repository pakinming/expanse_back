package com.expense.entity;

import com.expense.constant.Utils;
import com.fasterxml.jackson.annotation.JsonFormat;
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

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Utils.PATTERN_DATE)
    @Column(columnDefinition = "date", nullable = false)
    private OffsetDateTime expendDate;

    @Column(columnDefinition = "text")
    private String note;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Utils.PATTERN_RFC3999, timezone = Utils.GMT_7)
    @Column(columnDefinition = "TIMESTAMP WITH TIME ZONE", updatable = false, nullable = false)
    private OffsetDateTime createdAt;

    @PrePersist
    private void onPresist() {
        createdAt = OffsetDateTime.now();
        expendDate = OffsetDateTime.now();

    }


}
