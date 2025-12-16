package com.db.sep3.gRPC;

import com.db.sep3.DAO.QuestRepository;
import com.db.sep3.entities.Quest;
import com.sep3.data.grpc.CreateQuestRequest;
import com.sep3.data.grpc.QuestEntity;
import io.grpc.stub.StreamObserver;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class QuestServiceImplTest {

    @Mock
    private QuestRepository questRepository;

    @Mock
    private StreamObserver<QuestEntity> responseObserver;

    @InjectMocks
    private QuestServiceImpl questService;


    @Test
    void createQuest_WithValidData_ShouldCreateQuest() {
        // Arrange
        QuestEntity questEntity = QuestEntity.newBuilder()
                .setTitle("Test Quest")
                .setDescription("Test Description")
                .setStatus("backlog")
                .setStartDate("2025-01-01")
                .setEndDate("2025-12-31")
                .build();

        CreateQuestRequest request = CreateQuestRequest.newBuilder()
                .set(questEntity)
                .build();

        Quest savedQuest = new Quest();
        savedQuest.setId(1L);
        savedQuest.setTitle("Test Quest");
        savedQuest.setDescription("Test Description");
        savedQuest.setStatus("backlog");
        savedQuest.setCreatedDate(java.sql.Date.valueOf("2025-01-01"));
        savedQuest.setStartDate(java.sql.Date.valueOf("2025-01-01"));
        savedQuest.setFinishedDate(java.sql.Date.valueOf("2025-12-31"));

        when(questRepository.save(any(Quest.class))).thenReturn(savedQuest);

        // Act
        questService.createQuest(request, responseObserver);

        // Assert
        verify(questRepository, times(1)).save(any(Quest.class));
        verify(responseObserver, times(1)).onNext(any(QuestEntity.class));
        verify(responseObserver, times(1)).onCompleted();
        verify(responseObserver, never()).onError(any());
    }

    @Test
    void createQuest_WithNullTitle_ShouldHandleError() {
        // Arrange
        QuestEntity questEntity = QuestEntity.newBuilder()
                .setTitle("")  // Empty title
                .setDescription("Test Description")
                .setStatus("backlog")
                .setStartDate("2025-01-01")
                .setEndDate("2025-12-31")
                .build();

        CreateQuestRequest request = CreateQuestRequest.newBuilder()
                .setQuest(questEntity)
                .build();

        // Simulate database constraint violation
        when(questRepository.save(any(Quest.class)))
                .thenThrow(new RuntimeException("NOT NULL constraint violation"));

        // Act
        questService.createQuest(request, responseObserver);

        // Assert
        verify(questRepository, times(1)).save(any(Quest.class));
        verify(responseObserver, never()).onNext(any(QuestEntity.class));
        verify(responseObserver, never()).onCompleted();
        verify(responseObserver, times(1)).onError(any());
    }

    @Test
    void createQuest_WithInvalidDateFormat_ShouldHandleError() {
        // Arrange - BOUNDARY: Invalid date format
        QuestEntity questEntity = QuestEntity.newBuilder()
                .setTitle("Test Quest")
                .setDescription("Test Description")
                .setStatus("backlog")
                .setStartDate("01-12-2025")  // Invalid format
                .setEndDate("2025-12-31")
                .build();

        CreateQuestRequest request = CreateQuestRequest.newBuilder()
                .setQuest(questEntity)
                .build();

        // Act
        questService.createQuest(request, responseObserver);

        // Assert
        verify(questRepository, never()).save(any(Quest.class));
        verify(responseObserver, never()).onNext(any(QuestEntity.class));
        verify(responseObserver, never()).onCompleted();
        verify(responseObserver, times(1)).onError(any());
    }

    @Test
    void createQuest_WithVeryLongTitle_ShouldHandleError() {
        // Arrange - BOUNDARY: Very long title (>255 chars if DB has limit)
        String veryLongTitle = "A".repeat(500);  // 500 character title

        QuestEntity questEntity = QuestEntity.newBuilder()
                .setTitle(veryLongTitle)
                .setDescription("Test Description")
                .setStatus("backlog")
                .setStartDate("2025-01-01")
                .setEndDate("2025-12-31")
                .build();

        CreateQuestRequest request = CreateQuestRequest.newBuilder()
                .setQuest(questEntity)
                .build();

        // Simulate database constraint violation for too long title
        when(questRepository.save(any(Quest.class)))
                .thenThrow(new RuntimeException("String too long for column"));

        // Act
        questService.createQuest(request, responseObserver);

        // Assert
        verify(questRepository, times(1)).save(any(Quest.class));
        verify(responseObserver, never()).onNext(any(QuestEntity.class));
        verify(responseObserver, never()).onCompleted();
        verify(responseObserver, times(1)).onError(any());
    }

    @Test
    void createQuest_WhenRepositoryFails_ShouldHandleError() {
        // Arrange - EXCEPTION: Database/repository failure
        QuestEntity questEntity = QuestEntity.newBuilder()
                .setTitle("Test Quest")
                .setDescription("Test Description")
                .setStatus("backlog")
                .setStartDate("2025-01-01")
                .setEndDate("2025-12-31")
                .build();

        CreateQuestRequest request = CreateQuestRequest.newBuilder()
                .setQuest(questEntity)
                .build();

        // Simulate database connection failure
        when(questRepository.save(any(Quest.class)))
                .thenThrow(new RuntimeException("Database connection lost"));

        // Act
        questService.createQuest(request, responseObserver);

        // Assert
        verify(questRepository, times(1)).save(any(Quest.class));
        verify(responseObserver, never()).onNext(any(QuestEntity.class));
        verify(responseObserver, never()).onCompleted();
        verify(responseObserver, times(1)).onError(any());
    }
}