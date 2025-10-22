package com.db.sep3.entities;

import jakarta.persistence.*;

import java.sql.Date;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Entity @Table(name = "task")
public class Task
{
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column (nullable=false)
  private String title;

  @Column(nullable=false)
  private String description;

  @Column(nullable=false)
  private String status;

  @Column(nullable=false)
  private Date createdAt;

  @ManyToOne(optional = false, fetch = FetchType.LAZY)
  @JoinColumn(name = "created_by", nullable = false)
  private User createdBy;


  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(
      name = "task_assignees",
      joinColumns = @JoinColumn(name = "task_id"),
      inverseJoinColumns = @JoinColumn(name = "user_id")
  )
  private Set<User> assignees = new LinkedHashSet<>();

  @Column(nullable=true)
  private Date startDate;

  @Column(nullable=true)
  private Date endDate;





}
