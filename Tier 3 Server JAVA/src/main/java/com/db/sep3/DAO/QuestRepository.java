package com.db.sep3.DAO;

import com.db.sep3.entities.Quest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Date;

public interface QuestRepository extends JpaRepository<Quest, Long> {

  // tasks assigned to a given user (ManyToMany)
  Page<Quest> findByAssignees_Id(Long userId, Pageable pageable);

  // same as above but filtered by status too
  @Query("""
         select t from Quest t
         join t.assignees a
         where t.status = :status and a.id = :userId
         """)
  Page<Quest> findByStatusAndAssignee(
      @Param("status") String status,
      @Param("userId") Long userId,
      Pageable pageable);

  // tasks created by a given user (ManyToOne)
  Page<Quest> findByCreatedBy_Id(Long userId, Pageable pageable);

  // by status only
  Page<Quest> findByStatus(String status, Pageable pageable);

  // date-based (pick startDate or endDate as you need)
  Page<Quest> findByEndDateBefore(Date date, Pageable pageable);
  Page<Quest> findByStartDateAfter(Date date, Pageable pageable);

  // title search (case-insensitive)
  Page<Quest> findByTitleContainingIgnoreCase(String phrase, Pageable pageable);
}
