package com.db.sep3.gRPC;

import com.google.protobuf.Empty;
import com.google.protobuf.Timestamp;
import com.sep3.data.grpc.*;
import io.grpc.stub.StreamObserver;

import java.sql.Date;

import com.db.sep3.DAO.QuestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import net.devh.boot.grpc.server.service.GrpcService;
import com.db.sep3.entities.Quest;

@GrpcService
public class QuestServiceImpl extends DataServiceGrpc.DataServiceImplBase
{
    @Autowired
    private QuestRepository questRepository;

//    @Autowired
//    private UserRepository userRepository;

//    // ===== User Methods =====
//    @Override
//    public void createUser(UserEntity request, StreamObserver<UserEntity> responseObserver) {
//        // Example stub
//        UserEntity user = UserEntity.newBuilder()
//                .setId(1)
//                .setEmail(request.getEmail())
//                .setPassword(request.getPassword())
//                .setDisplayName(request.getDisplayName())
//                .build();
//        responseObserver.onNext(user);
//        responseObserver.onCompleted();
//    }
//
//    @Override
//    public void getUserById(IdRequest request, StreamObserver<UserEntity> responseObserver) {
//        UserEntity user = UserEntity.newBuilder()
//                .setId(request.getId())
//                .setEmail("test@example.com")
//                .setPassword("secret")
//                .setDisplayName("Example")
//                .build();
//        responseObserver.onNext(user);
//        responseObserver.onCompleted();
//    }
//
//    @Override
//    public void getAllUsers(Empty request, StreamObserver<UserList> responseObserver) {
//        UserList users = UserList.newBuilder()
//                .addUsers(UserEntity.newBuilder()
//                        .setId(1)
//                        .setEmail("a@b.com")
//                        .setPassword("p")
//                        .setDisplayName("Test User")
//                        .build())
//                .build();
//        responseObserver.onNext(users);
//        responseObserver.onCompleted();
//    }
//
//    @Override
//    public void updateUser(IdRequest request, StreamObserver<UserEntity> responseObserver) {
//        UserEntity user = UserEntity.newBuilder()
//                .setId(request.getId())
//                .setEmail("updated@example.com")
//                .setPassword("newpass")
//                .setDisplayName("Updated User")
//                .build();
//        responseObserver.onNext(user);
//        responseObserver.onCompleted();
//    }
//
//    @Override
//    public void deleteUser(IdRequest request, StreamObserver<Empty> responseObserver) {
//        responseObserver.onNext(Empty.getDefaultInstance());
//        responseObserver.onCompleted();
//    }

    // ===== Quest Methods =====
    @Override
    public void createQuest(CreateQuestRequest request, StreamObserver<QuestEntity> responseObserver) {
        try {
            System.out.println("=== Creating Quest ===");
            System.out.println("Title: " + request.getQuest().getTitle());
            System.out.println("Description: " + request.getQuest().getDescription());

            // Create JPA entity
            Quest quest = new Quest();
            quest.setTitle(request.getQuest().getTitle());
            quest.setDescription(request.getQuest().getDescription());
            quest.setStatus("TODO");
            quest.setCreatedAt(new Date(System.currentTimeMillis()));

            // Save to database
            Quest saved = questRepository.save(quest);

            System.out.println("Quest saved with ID: " + saved.getId());

            // Convert to proto
            Timestamp createdAtTimestamp = Timestamp.newBuilder()
                    .setSeconds(saved.getCreatedAt().getTime() / 1000)
                    .build();

            QuestEntity response = QuestEntity.newBuilder()
                    .setId(saved.getId())
                    .setTitle(saved.getTitle())
                    .setDescription(saved.getDescription())
                    .setStatus(0)
                    .setCreatedAt(createdAtTimestamp)
                    .build();

            responseObserver.onNext(response);
            responseObserver.onCompleted();

            System.out.println("=== Quest Created Successfully ===");
        } catch (Exception e) {
            System.err.println("Error creating quest: " + e.getMessage());
            e.printStackTrace();
            responseObserver.onError(e);
        }
    }

    @Override
    public void getQuestsById(IdRequest request, StreamObserver<QuestEntity> responseObserver) {
        QuestEntity quest = QuestEntity.newBuilder()
                .setId(request.getId())
                .setTitle("Example Quest")
                .setDescription("Demo description")
                .setStatus(1)
                .build();
        responseObserver.onNext(quest);
        responseObserver.onCompleted();
    }

    @Override
    public void getAllQuests(Empty request, StreamObserver<QuestList> responseObserver) {
        QuestList list = QuestList.newBuilder()
                .addQuests(QuestEntity.newBuilder()
                        .setId(1)
                        .setTitle("Sample")
                        .setDescription("Sample description")
                        .setStatus(0)
                        .build())
                .build();
        responseObserver.onNext(list);
        responseObserver.onCompleted();
    }

    @Override
    public void updateQuest(UpdateQuestRequest request, StreamObserver<QuestEntity> responseObserver) {
        QuestEntity updated = request.getQuest().toBuilder()
                .setTitle(request.getQuest().getTitle() + " (updated)")
                .build();
        responseObserver.onNext(updated);
        responseObserver.onCompleted();
    }

    @Override
    public void deleteQuest(IdRequest request, StreamObserver<Empty> responseObserver) {
        responseObserver.onNext(Empty.getDefaultInstance());
        responseObserver.onCompleted();
    }
}
