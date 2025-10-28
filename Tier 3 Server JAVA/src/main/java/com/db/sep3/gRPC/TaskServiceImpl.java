package com.db.sep3.gRPC;

import com.google.protobuf.Empty;
import com.sep3.data.grpc.*;
import io.grpc.stub.StreamObserver;
import com.sep3.*;

public class TaskServiceImpl extends DataServiceGrpc.DataServiceImplBase
{


    // ===== User Methods =====
    @Override
    public void createUser(UserEntity request, StreamObserver<UserEntity> responseObserver) {
        // Example stub
        UserEntity user = UserEntity.newBuilder()
                .setId(1)
                .setEmail(request.getEmail())
                .setPassword(request.getPassword())
                .setDisplayName(request.getDisplayName())
                .build();
        responseObserver.onNext(user);
        responseObserver.onCompleted();
    }

    @Override
    public void getUserById(IdRequest request, StreamObserver<UserEntity> responseObserver) {
        UserEntity user = UserEntity.newBuilder()
                .setId(request.getId())
                .setEmail("test@example.com")
                .setPassword("secret")
                .setDisplayName("Example")
                .build();
        responseObserver.onNext(user);
        responseObserver.onCompleted();
    }

    @Override
    public void getAllUsers(Empty request, StreamObserver<UserList> responseObserver) {
        UserList users = UserList.newBuilder()
                .addUsers(UserEntity.newBuilder()
                        .setId(1)
                        .setEmail("a@b.com")
                        .setPassword("p")
                        .setDisplayName("Test User")
                        .build())
                .build();
        responseObserver.onNext(users);
        responseObserver.onCompleted();
    }

    @Override
    public void updateUser(IdRequest request, StreamObserver<UserEntity> responseObserver) {
        UserEntity user = UserEntity.newBuilder()
                .setId(request.getId())
                .setEmail("updated@example.com")
                .setPassword("newpass")
                .setDisplayName("Updated User")
                .build();
        responseObserver.onNext(user);
        responseObserver.onCompleted();
    }

    @Override
    public void deleteUser(IdRequest request, StreamObserver<Empty> responseObserver) {
        responseObserver.onNext(Empty.getDefaultInstance());
        responseObserver.onCompleted();
    }

    // ===== Task Methods =====
    @Override
    public void createTask(CreateTaskRequest request, StreamObserver<TaskEntity> responseObserver) {
        TaskEntity saved = request.getTask().toBuilder()
                .setId(1)
                .build();
        responseObserver.onNext(saved);
        responseObserver.onCompleted();
    }

    @Override
    public void getTasksById(IdRequest request, StreamObserver<TaskEntity> responseObserver) {
        TaskEntity task = TaskEntity.newBuilder()
                .setId(request.getId())
                .setTitle("Example Task")
                .setDescription("Demo description")
                .setStatus(1)
                .build();
        responseObserver.onNext(task);
        responseObserver.onCompleted();
    }

    @Override
    public void getAllTasks(Empty request, StreamObserver<TaskList> responseObserver) {
        TaskList list = TaskList.newBuilder()
                .addTasks(TaskEntity.newBuilder()
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
    public void updateTask(UpdateTaskRequest request, StreamObserver<TaskEntity> responseObserver) {
        TaskEntity updated = request.getTask().toBuilder()
                .setTitle(request.getTask().getTitle() + " (updated)")
                .build();
        responseObserver.onNext(updated);
        responseObserver.onCompleted();
    }

    @Override
    public void deleteTask(IdRequest request, StreamObserver<Empty> responseObserver) {
        responseObserver.onNext(Empty.getDefaultInstance());
        responseObserver.onCompleted();
    }
}
