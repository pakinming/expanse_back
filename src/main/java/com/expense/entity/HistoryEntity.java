package com.expense.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.OffsetDateTime;

@Entity
@Table(name = "history_expend")
@Data
public class HistoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "history_id", updatable = false)
    Integer id;

    @Column(updatable = false)
    Integer expendId;

    @Column(columnDefinition = "numeric", updatable = false)
    Double expend;

    @Column(columnDefinition = "timestamp", updatable = false)
    OffsetDateTime expendDate;

    @Column(length = 50, updatable = false)
    String action;

    @Column(columnDefinition = "timestamp default now()", updatable = false, insertable = false)
    OffsetDateTime created_at;

}
