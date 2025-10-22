package com.db.sep3.DAO;

import com.db.sep3.entities.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Date;

public interface TaskRepository extends JpaRepository<Task, Long> {

  // tasks assigned to a given user (ManyToMany)
  Page<Task> findByAssignees_Id(Long userId, Pageable pageable);

  // same as above but filtered by status too
  @Query("""
         select t from Task t
         join t.assignees a
         where t.status = :status and a.id = :userId
         """)
  Page<Task> findByStatusAndAssignee(
      @Param("status") String status,
      @Param("userId") Long userId,
      Pageable pageable);

  // tasks created by a given user (ManyToOne)
  Page<Task> findByCreatedBy_Id(Long userId, Pageable pageable);

  // by status only
  Page<Task> findByStatus(String status, Pageable pageable);

  // date-based (pick startDate or endDate as you need)
  Page<Task> findByEndDateBefore(Date date, Pageable pageable);
  Page<Task> findByStartDateAfter(Date date, Pageable pageable);

  // title search (case-insensitive)
  Page<Task> findByTitleContainingIgnoreCase(String phrase, Pageable pageable);
}
