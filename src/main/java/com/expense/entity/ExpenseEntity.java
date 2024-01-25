package com.expense.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NonNull;
import lombok.Value;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

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
