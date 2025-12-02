package com.db.sep3.DAO;

import com.db.sep3.entities.Quest;
import com.db.sep3.entities.QuestStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Date;
import java.time.Instant;

public interface QuestRepository extends JpaRepository<Quest, Long> {

  // quests assigned to a given user (ManyToOne)
  Page<Quest> findByAssignee_Id(Long userId, Pageable pageable);

  // same as above but filtered by status too
  @Query("""
         select t from Quest t
         where t.status = :status and t.assignee = :userId
         """)
  Page<Quest> findByStatusAndAssignee(
      @Param("status") String status,
      @Param("userId") Long userId,
      Pageable pageable);

  // quests created by a given user (ManyToOne)
  Page<Quest> findByCreatedBy_Id(Long userId, Pageable pageable);

    // by status only
    Page<Quest> findByStatus(QuestStatus status, Pageable pageable);

    // date-based (use field names that actually exist on Quest)
    Page<Quest> findByDeadlineBefore(Instant date, Pageable pageable);
    Page<Quest> findByStartDateAfter(Instant date, Pageable pageable);


  // title search (case-insensitive)
  Page<Quest> findByTitleContainingIgnoreCase(String phrase, Pageable pageable);
}
