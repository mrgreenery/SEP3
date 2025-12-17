package com.db.sep3.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.Instant;


@Entity @Table(name = "quest")
public class Quest {
    @Id
    @Getter
    @Setter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Setter
    @Column(nullable = false)
    private String title;

    @Getter
    @Setter
    @Column
    private String description;

    @Getter
    @Setter
    @Column(nullable = false)
    private QuestStatus status;

    @Getter
    @Setter
    @Column(name = "created_date", nullable = false)
    private Instant createdDate;

    @Getter
    @Setter
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "created_by", nullable = false)
    private User createdBy;

    @Getter
    @Setter
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "assignee_id")
    private User assignee;

    @Getter
    @Setter
    @Column
    private Instant startDate;

    @Getter
    @Setter
    @Column
    private Instant deadline;

    @Getter
    @Setter
    @Column
    private Instant finishedDate;
}
