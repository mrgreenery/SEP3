package com.db.sep3.gRPC;

import com.db.sep3.DAO.UserRepository;
import com.db.sep3.entities.User;
import com.db.sep3.gRPC.mapper.ToProto;
import com.google.protobuf.Empty;
import com.sep3.data.grpc.*;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.factory.annotation.Autowired;

@GrpcService
public class UserServiceImpl extends UserServiceGrpc.UserServiceImplBase {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void createUser(CreateUserRequest request, StreamObserver<UserEntity> responseObserver)
    {
        try{
            System.out.println("=== Creating User ===");
            System.out.println("Email: " + request.getEmail());
            System.out.println("Display name: " + request.getDisplayName());
            System.out.println("Password: *********** ");

            //Create JPA entity
            User user = new User();
            user.setEmail(request.getEmail());
            user.setDisplayName(request.getDisplayName());
            user.setPassword(request.getPassword());

            //save to database
            User saved = userRepository.save(user);

            System.out.println("User saved with ID: " + saved.getId());

            //Convert to proto
            UserEntity response = ToProto.UserToProto(saved);

            responseObserver.onNext(response);
            responseObserver.onCompleted();

            System.out.println("=== User Created Successfully ===");
        } catch (org.springframework.dao.DataIntegrityViolationException e) {
            // error thrown by database - when email already exists (unique constraint)
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
            UserEntity response = ToProto.UserToProto(user);

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
    public void logUser(LoginUserRequest request, StreamObserver<UserEntity> responseObserver){
        try{
            String email = request.getEmail();

            System.out.println("=== Getting User by Email: " + email + " ===");

            //Get user
            User userFromDb = userRepository.findByEmail(email)
                    .orElseThrow(() -> new jakarta.persistence.EntityNotFoundException
                            ("User with email: " + email + " not found"));

            UserEntity response = null;

            // check if password matches
            if(userFromDb.getPassword().equals(request.getPassword())){
                System.out.println("=== User Credentials Checked Successfully ===");

                //password matches, so set new UserDto as a response
                 response = ToProto.UserToProto(userFromDb);
            }
                //respond
                responseObserver.onNext(response);
                responseObserver.onCompleted();

        }catch (jakarta.persistence.EntityNotFoundException e) {
            responseObserver.onError(
                    io.grpc.Status.NOT_FOUND
                            .withDescription(e.getMessage())
                            .withCause(e)
                            .asRuntimeException()
            );
        } catch (Exception e){
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

    @Override
    public void getAllUsers(Empty request,StreamObserver<UserList>  responseObserver)
    {
        try{
            System.out.println("=== Getting All Users ===");

            //get Users
            UserList.Builder builder = UserList.newBuilder();
            userRepository.findAll().forEach(u -> builder.addUsers(ToProto.UserToProto(u)));
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


    @Override
    public void updateUserName(UpdateUserNameRequest request, StreamObserver<UserEntity> responseObserver) {
        try {

            //get user from request id
            User user = userRepository.findById(request.getUserId())
                    .orElseThrow(() ->
                            new jakarta.persistence.EntityNotFoundException(
                                    "User with id: " + request.getUserId() + " not found"));


            //update displayName and save updated User
            user.setDisplayName(request.getDisplayName());
            User saved = userRepository.save(user);

            //send response
            responseObserver.onNext(ToProto.UserToProto(saved));
            responseObserver.onCompleted();
            System.out.println("=== User Updated Successfully ===");
        }catch (jakarta.persistence.EntityNotFoundException e) {
            responseObserver.onError(
                    io.grpc.Status.NOT_FOUND
                            .withDescription(e.getMessage())
                            .withCause(e)
                            .asRuntimeException()
            );
        }
        catch (Exception e) {
            responseObserver.onError(io.grpc.Status.INTERNAL.withDescription("Failed to update user").withCause(e).asRuntimeException());
        }
    }


    @Override
    public void updateUserEmail(UpdateUserEmailRequest request, StreamObserver<UserEntity> responseObserver) {
        try {

            //get user from request id
            User user = userRepository.findById(request.getUserId())
                    .orElseThrow(() ->
                            new jakarta.persistence.EntityNotFoundException(
                                    "User with id: " + request.getUserId() + " not found"));


            //update displayName and save updated User
            user.setDisplayName(request.getEmail());
            User saved = userRepository.save(user);

            //send response
            responseObserver.onNext(ToProto.UserToProto(saved));
            responseObserver.onCompleted();
            System.out.println("=== User Updated Successfully ===");
        }catch (jakarta.persistence.EntityNotFoundException e) {
            responseObserver.onError(
                    io.grpc.Status.NOT_FOUND
                            .withDescription(e.getMessage())
                            .withCause(e)
                            .asRuntimeException()
            );
        }
        catch (Exception e) {
            responseObserver.onError(io.grpc.Status.INTERNAL.withDescription("Failed to update user").withCause(e).asRuntimeException());
        }
    }


    @Override
    public void updateUserPassword(UpdateUserPasswordRequest request, StreamObserver<UserEntity> responseObserver) {
        try {

            //get user from request id
            User user = userRepository.findById(request.getUserId())
                    .orElseThrow(() ->
                            new jakarta.persistence.EntityNotFoundException(
                                    "User with id: " + request.getUserId() + " not found"));


            //update displayName and save updated User
            user.setDisplayName(request.getPassword());
            User saved = userRepository.save(user);

            //send response
            responseObserver.onNext(ToProto.UserToProto(saved));
            responseObserver.onCompleted();
            System.out.println("=== User Updated Successfully ===");
        }catch (jakarta.persistence.EntityNotFoundException e) {
            responseObserver.onError(
                    io.grpc.Status.NOT_FOUND
                            .withDescription(e.getMessage())
                            .withCause(e)
                            .asRuntimeException()
            );
        }
        catch (Exception e) {
            responseObserver.onError(io.grpc.Status.INTERNAL.withDescription("Failed to update user").withCause(e).asRuntimeException());
        }
    }


    @Override
    public void deleteUser(DeleteUserRequest request, StreamObserver<Empty> responseObserver) {
        try {
            System.out.println("=== Deleting User ===");

            //check if the user exists
            if (!userRepository.existsById(request.getUserId())) {
                responseObserver.onError(
                        io.grpc.Status.NOT_FOUND
                                .withDescription("User " + request.getUserId() + " not found")
                                .asRuntimeException()
                );
            }

            User user = userRepository.findById(request.getUserId()).
                    orElseThrow(() -> new jakarta.persistence.EntityNotFoundException(
                            "User " + request.getUserId() + " not found"));

            //if he exists delete it
            userRepository.deleteById(user.getId());

            //send respond of success
            responseObserver.onNext(Empty.newBuilder().build());
            responseObserver.onCompleted();

        }catch (jakarta.persistence.EntityNotFoundException e) {
            responseObserver.onError(
                    io.grpc.Status.NOT_FOUND
                            .withDescription(e.getMessage())
                            .withCause(e)
                            .asRuntimeException()
            );
        } catch (Exception e) {
            responseObserver.onError(
                    io.grpc.Status.INTERNAL
                            .withDescription("Failed to delete user")
                            .withCause(e)
                            .asRuntimeException()
            );
        }
    }






}