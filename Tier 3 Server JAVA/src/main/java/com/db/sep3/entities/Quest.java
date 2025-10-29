package com.db.sep3.entities;

import jakarta.persistence.*;

import java.sql.Date;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity @Table(name = "quest")
public class Quest
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

  @ManyToOne(optional = true, fetch = FetchType.LAZY)
  @JoinColumn(name = "created_by", nullable = true)
  private User createdBy;


  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(
      name = "quest_assignees",
      joinColumns = @JoinColumn(name = "quest_id"),
      inverseJoinColumns = @JoinColumn(name = "user_id")
  )
  private Set<User> assignees = new LinkedHashSet<>();

  @Column(nullable=true)
  private Date startDate;

  @Column(nullable=true)
  private Date endDate;

  public Long getId()
  {
    return id;
  }

  public void setId(Long id)
  {
    this.id = id;
  }

  public String getTitle()
  {
    return title;
  }

  public void setTitle(String title)
  {
    this.title = title;
  }

  public String getDescription()
  {
    return description;
  }

  public void setDescription(String description)
  {
    this.description = description;
  }

  public String getStatus()
  {
    return status;
  }

  public void setStatus(String status)
  {
    this.status = status;
  }

  public Date getCreatedAt()
  {
    return createdAt;
  }

  public void setCreatedAt(Date createdAt)
  {
    this.createdAt = createdAt;
  }

  public User getCreatedBy()
  {
    return createdBy;
  }

  public void setCreatedBy(User createdBy)
  {
    this.createdBy = createdBy;
  }

  public Set<User> getAssignees()
  {
    return assignees;
  }

  public Date getStartDate()
  {
    return startDate;
  }

  public void setStartDate(Date startDate)
  {
    this.startDate = startDate;
  }

  public Date getEndDate()
  {
    return endDate;
  }

  public void setEndDate(Date endDate)
  {
    this.endDate = endDate;
  }
}
