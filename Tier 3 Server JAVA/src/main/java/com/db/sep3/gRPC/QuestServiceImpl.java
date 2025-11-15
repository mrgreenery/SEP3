package com.db.sep3.gRPC;

import com.google.protobuf.Empty;
import com.sep3.data.grpc.*;
import io.grpc.stub.StreamObserver;

import java.sql.Date;

import com.db.sep3.DAO.QuestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import net.devh.boot.grpc.server.service.GrpcService;
import com.db.sep3.entities.Quest;
import org.springframework.transaction.annotation.Transactional;

@GrpcService
public class QuestServiceImpl extends QuestServiceGrpc.QuestServiceImplBase {
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
            quest.setStatus(request.getQuest().getStatus());
            quest.setCreatedAt(new Date(System.currentTimeMillis()));
            quest.setStartDate(Date.valueOf(request.getQuest().getStartDate()));
            quest.setEndDate(Date.valueOf(request.getQuest().getEndDate()));
            //need to check how to add createdBy idk if it should be linked from userREpo

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

            //find the quest that we want to update
            Quest quest = questRepository.findById(request.getQuest().getId())
                    .orElseThrow(() -> new jakarta.persistence.EntityNotFoundException(
                            "Quest " + request.getQuest().getId() + " not found"));

            //change the variables which we want to change
            QuestEntity incoming = request.getQuest();
            if (!incoming.getTitle().isEmpty()) quest.setTitle(incoming.getTitle());
            if (!incoming.getDescription().isEmpty()) quest.setDescription(incoming.getDescription());
            if (!incoming.getStatus().isEmpty()) quest.setStatus(incoming.getStatus());

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
                .setStatus(quest.getStatus())
                .setCreatedAt(quest.getCreatedAt().toString());

        if (quest.getStartDate() != null) builder.setStartDate(quest.getStartDate().toString());
        if (quest.getEndDate() != null) builder.setEndDate(quest.getEndDate().toString());


        return builder.build();


    }
}
