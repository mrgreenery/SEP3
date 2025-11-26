package com.db.sep3.gRPC;

import com.db.sep3.DAO.UserRepository;
import com.db.sep3.entities.User;
import com.db.sep3.gRPC.mapper.QuestStatusMapper;
import com.db.sep3.gRPC.mapper.TimeMapper;
import com.google.protobuf.Empty;
import com.sep3.data.grpc.*;
import io.grpc.stub.StreamObserver;

import java.sql.Date;
import java.time.Instant;

import com.db.sep3.DAO.QuestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import net.devh.boot.grpc.server.service.GrpcService;
import com.db.sep3.entities.Quest;
import org.springframework.transaction.annotation.Transactional;

@GrpcService
public class QuestServiceImpl extends QuestServiceGrpc.QuestServiceImplBase {
    @Autowired
    private QuestRepository questRepository;
    @Autowired
    private UserRepository userRepository;

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
            System.out.println("Title: " + request.getTitle());

            // Create JPA entity
            Quest quest = new Quest();
            quest.setTitle(request.getTitle());
            quest.setDescription(request.getDescription());
            quest.setStatus(QuestStatusMapper.toEntity(request.getStatus()));
            quest.setCreatedDate(Instant.now());
            quest.setCreatedBy(userRepository.getReferenceById(request.getCreatedBy()));
            quest.setStartDate(TimeMapper.fromTimestamp(request.getStartDate()));
            quest.setDeadline(TimeMapper.fromTimestamp(request.getDeadline()));
            quest.setDeadline(TimeMapper.fromTimestamp(request.getDeadline()));
            quest.setFinishedDate(TimeMapper.fromTimestamp(request.getFinishedDate()));

            // Save to database
            Quest saved = questRepository.save(quest);

            System.out.println("Quest saved with ID: " + saved.getId());

            //here i did change so instead now converting the saved back to QuestEntity
            // its just calling the util class to convert it

            responseObserver.onNext(convertToProto(saved));
            responseObserver.onCompleted();

            System.out.println("=== Quest Created Successfully ===");


        } catch (Exception e) { // this is apparently better error handling than just sending 'e' but idk
            responseObserver.onError(io.grpc.Status.INTERNAL
                    .withDescription("Failed to create quest")
                    .withCause(e)
                    .asRuntimeException());
        }
    }

    @Override
    public void getQuestsById(IdRequest request, StreamObserver<QuestEntity> responseObserver) {


        try {
            System.out.println("=== Getting Quest by ID: " + request.getId() + " ===");

            //get quest
            Quest quest = questRepository.findById(request.getId())
                    .orElseThrow(() -> new jakarta.persistence.EntityNotFoundException
                            ("Quest with id: " + request.getId() + " not found"));

            // and send it converted
            responseObserver.onNext(convertToProto(quest));
            responseObserver.onCompleted();

        } catch (Exception e) {
            responseObserver.onError(io.grpc.Status.INTERNAL
                    .withDescription("Failed to get quest")
                    .withCause(e)
                    .asRuntimeException());
        }
    }



    @Override
    public void getAllQuests(Empty request, StreamObserver<QuestList> responseObserver) {
        try {
            // create a QuestLIst and than go through all quests and add them to the list
            QuestList.Builder builder = QuestList.newBuilder();
            questRepository.findAll().forEach(q -> builder.addQuests(convertToProto(q)));

            //Send the list
            responseObserver.onNext(builder.build());
            responseObserver.onCompleted();


        } catch (Exception e) {
            responseObserver.onError(io.grpc.Status.INTERNAL
                    .withDescription("Failed to fetch quests")
                    .withCause(e)
                    .asRuntimeException());
        }
    }

    @Transactional
    @Override
    public void updateQuest(UpdateQuestRequest request, StreamObserver<QuestEntity> responseObserver) {
        try {

            QuestEntity questEntity = request.getQuest();

            // 1. Load existing quest
            Quest quest = questRepository.findById(questEntity.getId())
                    .orElseThrow(() -> new jakarta.persistence.EntityNotFoundException(
                            "Quest " + questEntity.getId() + " not found"));

            // 2. Apply changes from request
            quest.setTitle(questEntity.getTitle());
            quest.setDescription(questEntity.getDescription());
            quest.setStatus(QuestStatusMapper.toEntity(questEntity.getStatus()));
            quest.setStartDate(TimeMapper.fromTimestamp(questEntity.getStartDate()));
            quest.setDeadline(TimeMapper.fromTimestamp(questEntity.getDeadline()));
            quest.setFinishedDate(TimeMapper.fromTimestamp(questEntity.getFinishedDate()));

            if (questEntity.hasAssignee()) {
                User assignee = userRepository.findById(questEntity.getAssignee())
                        .orElseThrow(() -> new jakarta.persistence.EntityNotFoundException(
                                "Assignee " + questEntity.getAssignee() + " not found"));
                quest.setAssignee(assignee);
            } else {
                quest.setAssignee(null);
            }

            // and save it back to db
            Quest saved = questRepository.save(quest);

            //send respond
            responseObserver.onNext(convertToProto(saved));
            responseObserver.onCompleted();


        } catch (Exception e) {
            responseObserver.onError(io.grpc.Status.INTERNAL
                    .withDescription("Failed to update quest")
                    .withCause(e)
                    .asRuntimeException());
        }
    }

    @Transactional
    @Override
    public void deleteQuest(IdRequest request, StreamObserver<Empty> responseObserver) {
        try {
            // check if quest exesits
            if (!questRepository.existsById(request.getId())) {
                responseObserver.onError(io.grpc.Status.NOT_FOUND
                        .withDescription("Quest " + request.getId() + " not found")
                        .asRuntimeException());
                return;
            }

            //if it exists delete it
            questRepository.deleteById(request.getId());

            //send respond of success
            responseObserver.onNext(Empty.newBuilder().build());
            responseObserver.onCompleted();
        } catch (Exception e) {
            responseObserver.onError(io.grpc.Status.INTERNAL
                    .withDescription("Failed to delete quest")
                    .withCause(e)
                    .asRuntimeException());
        }
    }


    // util class for converting quest to QuestEntity
    private QuestEntity convertToProto(Quest quest) {
        QuestEntity.Builder builder = QuestEntity.newBuilder()
                .setId(quest.getId())
                .setTitle(quest.getTitle())
                .setDescription(quest.getDescription())
                .setStatus(QuestStatusMapper.toProto(quest.getStatus()))
                .setCreatedDate(TimeMapper.toTimestamp(quest.getCreatedDate()))
                .setCreatedBy(quest.getCreatedBy().getId())
                .setAssignee(quest.getAssignee().getId())
                .setStartDate(TimeMapper.toTimestamp(quest.getStartDate()))
                .setDeadline(TimeMapper.toTimestamp(quest.getDeadline()))
                .setFinishedDate(TimeMapper.toTimestamp(quest.getFinishedDate()));

        return builder.build();
    }
}
