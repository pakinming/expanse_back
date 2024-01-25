package com.expense.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.OffsetDateTime;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "history_expend")
@Data
public class HistoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "history_id", updatable = false)
    private Integer id;

    @Column(name = "expend_id",updatable = false, nullable = false)
     
    private Integer expendId;

    @Column(columnDefinition = "numeric", updatable = false, nullable = false)
     
    private Double expend;

    @Column(columnDefinition = "timestamp", updatable = false, nullable = false)
     
    private OffsetDateTime expendDate;

    @Column(updatable = false)
    private String note;

    @Column(updatable = false)
     
    private String action;

    @Column(columnDefinition = "timestamp", updatable = false, insertable = false, nullable = false)
     
    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    private OffsetDateTime createdAt;

}
