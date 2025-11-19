package com.db.sep3.gRPC;

import com.db.sep3.DAO.UserRepository;
import com.db.sep3.entities.User;
import com.google.protobuf.Empty;
import com.sep3.data.grpc.*;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
@GrpcService
public class UserServiceImpl extends UserServiceGrpc.UserServiceImplBase {


    @Autowired
    private UserRepository userRepository;

    @Override
    public void createUser(CreateUserRequest request, StreamObserver<UserEntity> responseObserver)
    {
        try{
            System.out.println("=== Creating User ===");
            System.out.println("Email: " + request.getUser().getEmail());
            System.out.println("Display name: " + request.getUser().getDisplayName());
            System.out.println("Password: *********** ");

            //Create JPA entity
            User user = new User();
            user.setEmail(request.getUser().getEmail());
            user.setDisplayName(request.getUser().getDisplayName());
            user.setPassword(request.getUser().getPassword());

            //save to database
            User saved = userRepository.save(user);

            System.out.println("User saved with ID: " + saved.getId());

            //Convert to proto
            UserEntity response = UserEntity.newBuilder()
                    .setId(saved.getId())
                    .setEmail(saved.getEmail())
                    .setDisplayName(saved.getDisplayName())
                    .setPassword(saved.getPassword())
                    .build();

            responseObserver.onNext(response);
            responseObserver.onCompleted();

            System.out.println("=== User Created Successfully ===");
        } catch (org.springframework.dao.DataIntegrityViolationException e) {
            // unique constraint violated -> email already exists
            responseObserver.onError(
                    io.grpc.Status.ALREADY_EXISTS
                            .withDescription("User with this email already exists")
                            .withCause(e)
                            .asRuntimeException()
            );
        } catch (Exception e) {
            System.out.println("Error creating user" + e.getMessage());
            e.printStackTrace();
            responseObserver.onError(e);
        }
    }

    @Override
    public void getUserById(IdRequest request, StreamObserver<UserEntity> responseObserver)
    {
        try {
            System.out.println("=== Getting Users by ID: " + request.getId() + " ===");

            //Get user
            User user = userRepository.findById(request.getId())
                    .orElseThrow(() -> new jakarta.persistence.EntityNotFoundException
                            ("User with id: " + request.getId() + " not found"));


            //conver to proto
            UserEntity response = convertToProto(user);

            //respond
            responseObserver.onNext(response);
            responseObserver.onCompleted();

            System.out.println("=== User Found Successfully ===");
        } catch (jakarta.persistence.EntityNotFoundException e) {
            responseObserver.onError(
                    io.grpc.Status.NOT_FOUND
                            .withDescription(e.getMessage())
                            .withCause(e)
                            .asRuntimeException()
            );
        } catch (Exception e) {
            responseObserver.onError(
                    io.grpc.Status.INTERNAL
                            .withDescription("Failed to get user by id")
                            .withCause(e)
                            .asRuntimeException()
            );
        }


    }

    @Override
    public void getUserByEmail(EmailRequest request, StreamObserver<UserEntity> responseObserver)
    {
        try {
            System.out.println("=== Getting Users by Email: " + request.getEmail() + " ===");

            //Get user
            User user = userRepository.findByEmail(request.getEmail())
                    .orElseThrow(() -> new jakarta.persistence.EntityNotFoundException
                            ("User with email: " + request.getEmail() + " not found"));


            //conver to proto
            UserEntity response = convertToProto(user);

            //respond
            responseObserver.onNext(response);
            responseObserver.onCompleted();

            System.out.println("=== User Found Successfully ===");
        } catch (jakarta.persistence.EntityNotFoundException e) {
            responseObserver.onError(
                    io.grpc.Status.NOT_FOUND
                            .withDescription(e.getMessage())
                            .withCause(e)
                            .asRuntimeException()
            );
        } catch (Exception e) {
            System.out.println("Error getting user" + e.getMessage());
            e.printStackTrace();
            responseObserver.onError(
                    io.grpc.Status.INTERNAL
                            .withDescription("Failed to get user by email")
                            .withCause(e)
                            .asRuntimeException()
            );
        }


    }

    @Override
    public void getAllUsers(Empty request,StreamObserver<UserList>  responseObserver)
    {
        try{
            System.out.println("=== Getting All Users ===");

            //get Users
            UserList.Builder builder = UserList.newBuilder();
            userRepository.findAll().forEach(u -> builder.addUsers(convertToProto(u)));
            UserList users = builder.build();

            //send respond
            responseObserver.onNext(users);
            responseObserver.onCompleted();

        }catch (Exception e)
        {
            System.out.println("Error getting users" + e.getMessage());
            e.printStackTrace();
            // if error send proper error
            responseObserver.onError(
                    io.grpc.Status.INTERNAL
                            .withDescription("Failed to fetch users")
                            .withCause(e)
                            .asRuntimeException()
            );
        }
    }

    //this is more complicated bc of password, bc request dont have password (null)
    //so i want to get the user, than update the email or display name
    //and than save it without touching the password
    @Override
    public void updateUser(UpdateUserRequest request, StreamObserver<UserEntity> responseObserver) {
        try {

            //get user from request id
            User user = userRepository.findById(request.getUser().getId())
                    .orElseThrow(() -> new jakarta.persistence.EntityNotFoundException("User " + request.getUser().getId() + " not found"));


            //check and update email and displayName
            if (request.getUser().getEmail() != null && !request.getUser().getEmail().isEmpty())
                user.setEmail(request.getUser().getEmail());

            if (request.getUser().getDisplayName() != null && !request.getUser().getDisplayName().isEmpty())
                user.setDisplayName(request.getUser().getDisplayName());

            //save the user
            User saved = userRepository.save(user);

            //send respond
            responseObserver.onNext(convertToProto(saved));
            responseObserver.onCompleted();
            System.out.println("=== User Updated Successfully ===");
        } catch (Exception e) {
            responseObserver.onError(io.grpc.Status.INTERNAL.withDescription("Failed to update user").withCause(e).asRuntimeException());
        }
    }


    @Override
    public void deleteUser(DeleteUserRequest request, StreamObserver<Empty> responseObserver) {
        try {
            System.out.println("=== Deleting User ===");

            //check if the user exists
            if (!userRepository.existsById(request.getId())) {
                responseObserver.onError(
                        io.grpc.Status.NOT_FOUND
                                .withDescription("User " + request.getId() + " not found")
                                .asRuntimeException()
                );
                return;
            }

            //if he exists delete it
            userRepository.deleteById(request.getId());

            //send respond of success
            responseObserver.onNext(Empty.newBuilder().build());
            responseObserver.onCompleted();

        } catch (Exception e) {
            responseObserver.onError(
                    io.grpc.Status.INTERNAL
                            .withDescription("Failed to delete user")
                            .withCause(e)
                            .asRuntimeException()
            );
        }
    }


    //util method for converting User to UserEntity
    private UserEntity convertToProto(User user)
    {
        UserEntity userEntity = UserEntity.newBuilder()
                .setId(user.getId())
                .setEmail(user.getEmail())
                .setDisplayName(user.getDisplayName())
                .setPassword(user.getPassword())
                .build();
        return userEntity;
    }



}