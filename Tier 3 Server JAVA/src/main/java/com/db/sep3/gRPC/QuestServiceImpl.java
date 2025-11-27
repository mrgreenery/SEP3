package com.db.sep3.gRPC;

import com.db.sep3.DAO.UserRepository;
import com.db.sep3.entities.User;
import com.db.sep3.gRPC.mapper.QuestStatusMapper;
import com.db.sep3.gRPC.mapper.TimeMapper;
import com.db.sep3.gRPC.mapper.ToProto;
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

    // ===== Quest Methods =====
    @Override
    public void createQuest(CreateQuestRequest request, StreamObserver<QuestEntity> responseObserver) {
        try {
            System.out.println("=== Creating Quest ===");
            System.out.println("Title: " + request.getTitle());

            // load creator (required)
            User creator = userRepository.findById(request.getCreatedBy())
                    .orElseThrow(() -> new jakarta.persistence.EntityNotFoundException(
                            "User " + request.getCreatedBy() + " not found"));

            // optional assignee
            User assignee = null;
            if (request.hasAssigneeId()) {
                assignee = userRepository.findById(request.getAssigneeId())
                        .orElseThrow(() -> new jakarta.persistence.EntityNotFoundException(
                                "Assignee " + request.getAssigneeId() + " not found"));
            }

            // Create JPA entity
            Quest quest = new Quest();
            quest.setTitle(request.getTitle());
            quest.setDescription(request.hasDescription() ? request.getDescription() : null);
            quest.setStatus(QuestStatusMapper.toEntity(request.getStatus()));
            quest.setCreatedDate(Instant.now());
            quest.setCreatedBy(creator);
            quest.setAssignee(assignee);

            //check if dates are not null
            if (request.hasStartDate()) {
                quest.setStartDate(TimeMapper.fromTimestamp(request.getStartDate()));
            }
            if (request.hasDeadline()) {
                quest.setDeadline(TimeMapper.fromTimestamp(request.getDeadline()));
            }
            if (request.hasFinishedDate()) {
                quest.setFinishedDate(TimeMapper.fromTimestamp(request.getFinishedDate()));
            }

            // Save to database
            Quest saved = questRepository.save(quest);

            System.out.println("Quest saved with ID: " + saved.getId());

            responseObserver.onNext(ToProto.QuestToProto(saved));
            responseObserver.onCompleted();

            System.out.println("=== Quest Created Successfully ===");


        } catch (jakarta.persistence.EntityNotFoundException e) {
            //Throw not_found error when creator/assignee with provided id does not exist
            responseObserver.onError(
                    io.grpc.Status.NOT_FOUND
                            .withDescription(e.getMessage())
                            .asRuntimeException());
        }catch (Exception e) { // this is apparently better error handling than just sending 'e' but idk
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
            responseObserver.onNext(ToProto.QuestToProto(quest));
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
            questRepository.findAll().forEach(q -> builder.addQuests(ToProto.QuestToProto(q)));

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
            // Get quest by id from daatabase
            Quest quest = questRepository.findById(request.getQuestId())
                    .orElseThrow(() -> new jakarta.persistence.EntityNotFoundException(
                            "Quest " + request.getQuestId() + " not found"));


            //Overwrite fields - except id, createdDate and createdBy
            quest.setTitle(request.getTitle());
            quest.setDescription(request.hasDescription() ? request.getDescription() : null);
            quest.setStatus(QuestStatusMapper.toEntity(request.getStatus()));

            if (request.hasStartDate()) {
                quest.setStartDate(TimeMapper.fromTimestamp(request.getStartDate()));
            } else {
                quest.setStartDate(null);
            }

            if (request.hasDeadline()) {
                quest.setDeadline(TimeMapper.fromTimestamp(request.getDeadline()));
            } else {
                quest.setDeadline(null);
            }

            if (request.hasFinishedDate()) {
                quest.setFinishedDate(TimeMapper.fromTimestamp(request.getFinishedDate()));
            } else {
                quest.setFinishedDate(null);
            }

            if (request.hasAssigneeId()) {
                User assignee = userRepository.findById(request.getAssigneeId())
                        .orElseThrow(() -> new jakarta.persistence.EntityNotFoundException(
                                "Assignee " + request.getAssigneeId() + " not found"));
                quest.setAssignee(assignee);
            } else {
                quest.setAssignee(null);
            }

            // and save it back to db
            Quest saved = questRepository.save(quest);

            //send respond
            responseObserver.onNext(ToProto.QuestToProto(saved));
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
}
