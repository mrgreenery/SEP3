package com.db.sep3.gRPC;

import com.google.protobuf.Empty;
import com.google.protobuf.Timestamp;
import com.sep3.data.grpc.*;
import io.grpc.stub.StreamObserver;
import com.db.sep3.entities.Task;
import com.db.sep3.entities.User;
import java.sql.Date;
import java.time.LocalDate;
import com.db.sep3.DAO.TaskRepository;
import com.db.sep3.DAO.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
public class TaskServiceImpl extends DataServiceGrpc.DataServiceImplBase
{
    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserRepository userRepository;

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
        try {
            System.out.println("=== Creating Task ===");
            System.out.println("Title: " + request.getTask().getTitle());
            System.out.println("Description: " + request.getTask().getDescription());

            // Create JPA entity
            com.db.sep3.entities.Task task = new com.db.sep3.entities.Task();
            task.setTitle(request.getTask().getTitle());
            task.setDescription(request.getTask().getDescription());
            task.setStatus("TODO");
            task.setCreatedAt(new Date(System.currentTimeMillis()));

            // Save to database
            com.db.sep3.entities.Task saved = taskRepository.save(task);

            System.out.println("Task saved with ID: " + saved.getId());

            // Convert to proto
            Timestamp createdAtTimestamp = Timestamp.newBuilder()
                    .setSeconds(saved.getCreatedAt().getTime() / 1000)
                    .build();

            TaskEntity response = TaskEntity.newBuilder()
                    .setId(saved.getId())
                    .setTitle(saved.getTitle())
                    .setDescription(saved.getDescription())
                    .setStatus(0)
                    .setCreatedAt(createdAtTimestamp)
                    .build();

            responseObserver.onNext(response);
            responseObserver.onCompleted();

            System.out.println("=== Task Created Successfully ===");
        } catch (Exception e) {
            System.err.println("Error creating task: " + e.getMessage());
            e.printStackTrace();
            responseObserver.onError(e);
        }
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
