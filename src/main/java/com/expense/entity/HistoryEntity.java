package com.expense.entity;

import com.expense.constant.Utils;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

@Entity
@Table(name = "history_expend")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HistoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "history_id", updatable = false)
    private Integer id;

    @Column(name = "expend_id", updatable = false, nullable = false)
    private Integer expendId;

    @Column(columnDefinition = "numeric", updatable = false, nullable = false)
    private Double expend;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Utils.PATTERN_DATE)
    @Column(columnDefinition = "date", updatable = false, nullable = false)
    private OffsetDateTime expendDate;

    @Column(updatable = false)
    private String note;

    @Column(updatable = false)
    private String action;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Utils.PATTERN_RFC3999, timezone = "GMT+7")
    @Column(columnDefinition = "TIMESTAMP WITH TIME ZONE", updatable = false, nullable = false)
    private OffsetDateTime createdAt;

    @PrePersist
    private void onPersist() {
        createdAt = OffsetDateTime.now();
    }


}
