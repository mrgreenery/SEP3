package com.db.sep3.gRPC;

import com.db.sep3.DAO.QuestRepository;
import com.db.sep3.entities.Quest;
import com.sep3.data.grpc.CreateQuestRequest;
import com.sep3.data.grpc.QuestEntity;
import io.grpc.stub.StreamObserver;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
@ActiveProfiles("test")
class QuestServiceImplIntegrationTest {

    @Autowired
    private QuestRepository questRepository;

    @Autowired
    private QuestServiceImpl questService;

    @AfterEach
    void cleanUp() {
        // Clean database after each test
        questRepository.deleteAll();
    }

    @Test
    void createQuest_ShouldPersistToDatabase() {
        // Arrange
        QuestEntity questEntity = QuestEntity.newBuilder()
                .setTitle("Integration Test Quest")
                .setDescription("This should be saved to H2")
                .setStatus("backlog")
                .setStartDate("2025-01-01")
                .setEndDate("2025-12-31")
                .build();

        CreateQuestRequest request = CreateQuestRequest.newBuilder()
                .setQuest(questEntity)
                .build();

        StreamObserver<QuestEntity> responseObserver = mock(StreamObserver.class);

        // Act
        questService.createQuest(request, responseObserver);

        // Debug: Print what's in the database
        System.out.println("=== Database Contents ===");
        System.out.println("Total quests in DB: " + questRepository.count());
        questRepository.findAll().forEach(q -> {
            System.out.println("Quest ID: " + q.getId());
            System.out.println("Title: " + q.getTitle());
            System.out.println("Description: " + q.getDescription());
            System.out.println("Status: " + q.getStatus());
        });
        System.out.println("========================");

        // Assert - Verify quest was saved to database
        assertEquals(1, questRepository.count(), "Should have exactly 1 quest in database");

        Optional<Quest> savedQuest = questRepository.findAll().stream().findFirst();
        assertTrue(savedQuest.isPresent(), "Quest should exist in database");
        assertEquals("Integration Test Quest", savedQuest.get().getTitle());
        assertEquals("This should be saved to H2", savedQuest.get().getDescription());
        assertEquals("backlog", savedQuest.get().getStatus());

        // Verify gRPC response was sent
        verify(responseObserver, times(1)).onNext(any(QuestEntity.class));
        verify(responseObserver, times(1)).onCompleted();
    }


}