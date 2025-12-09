package com.db.sep3.gRPC;

import com.db.sep3.DAO.UserRepository;
import com.db.sep3.entities.User;
import com.db.sep3.util.PasswordUtil;
import com.google.protobuf.Empty;
import com.sep3.data.grpc.CreateUserRequest;
import com.sep3.data.grpc.DeleteUserRequest;
import com.sep3.data.grpc.GetUserByIdRequest;
import com.sep3.data.grpc.LoginUserRequest;
import com.sep3.data.grpc.UpdateUserNameRequest;
import com.sep3.data.grpc.UserEntity;
import com.sep3.data.grpc.UserList;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private StreamObserver<UserEntity> userResponseObserver;

    @Mock
    private StreamObserver<UserList> userListResponseObserver;

    @Mock
    private StreamObserver<Empty> emptyResponseObserver;

    @InjectMocks
    private UserServiceImpl userService;


    @Test
    void createUser_WithValidData_ShouldCreateUser() {
        // Arrange
        CreateUserRequest request = CreateUserRequest.newBuilder()
                .setEmail("test@example.com")
                .setDisplayName("Test User")
                .setPassword("Password123!")
                .build();

        User saved = new User();
        saved.setId(1L);
        saved.setEmail("test@example.com");
        saved.setDisplayName("Test User");
        saved.setPassword("hashed");

        when(userRepository.save(any(User.class))).thenReturn(saved);

        // Act
        userService.createUser(request, userResponseObserver);

        // Assert
        verify(userRepository, times(1)).save(any(User.class));
        verify(userResponseObserver, times(1)).onNext(any(UserEntity.class));
        verify(userResponseObserver, times(1)).onCompleted();
        verify(userResponseObserver, never()).onError(any());
    }

    @Test
    void createUser_WhenEmailAlreadyExists_ShouldReturnError() {
        // Arrange
        CreateUserRequest request = CreateUserRequest.newBuilder()
                .setEmail("duplicate@example.com")
                .setDisplayName("Dup User")
                .setPassword("Password123!")
                .build();

        when(userRepository.save(any(User.class)))
                .thenThrow(new org.springframework.dao.DataIntegrityViolationException("duplicate"));

        // Act
        userService.createUser(request, userResponseObserver);

        // Assert
        verify(userRepository, times(1)).save(any(User.class));
        verify(userResponseObserver, never()).onNext(any());
        verify(userResponseObserver, never()).onCompleted();
        verify(userResponseObserver, times(1)).onError(any());
    }

    // ---------- getUserById ----------

    @Test
    void getUserById_WithExistingUser_ShouldReturnUser() {
        // Arrange
        long userId = 1L;
        GetUserByIdRequest request = GetUserByIdRequest.newBuilder()
                .setUserId(userId)
                .build();

        User user = new User();
        user.setId(userId);
        user.setEmail("test@example.com");
        user.setDisplayName("Test User");
        user.setPassword("hashed");

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        // Act
        userService.getUserById(request, userResponseObserver);

        // Assert
        verify(userRepository, times(1)).findById(userId);
        verify(userResponseObserver, times(1)).onNext(any(UserEntity.class));
        verify(userResponseObserver, times(1)).onCompleted();
        verify(userResponseObserver, never()).onError(any());
    }

    @Test
    void getUserById_WithNonExistingUser_ShouldReturnNotFoundError() {
        // Arrange
        long userId = 999L;
        GetUserByIdRequest request = GetUserByIdRequest.newBuilder()
                .setUserId(userId)
                .build();

        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        // Act
        userService.getUserById(request, userResponseObserver);

        // Assert
        verify(userRepository, times(1)).findById(userId);
        verify(userResponseObserver, never()).onNext(any());
        verify(userResponseObserver, never()).onCompleted();
        verify(userResponseObserver, times(1)).onError(any());
    }

    // ---------- logUser (login) ----------

    @Test
    void logUser_WithValidCredentials_ShouldReturnUser() {
        // Arrange
        String email = "login@example.com";
        String rawPassword = "Password123!";
        String hashedPassword = PasswordUtil.hashPassword(rawPassword);

        LoginUserRequest request = LoginUserRequest.newBuilder()
                .setEmail(email)
                .setPassword(rawPassword)
                .build();

        User userFromDb = new User();
        userFromDb.setId(1L);
        userFromDb.setEmail(email);
        userFromDb.setDisplayName("Login User");
        userFromDb.setPassword(hashedPassword);

        when(userRepository.findByEmail(email)).thenReturn(Optional.of(userFromDb));

        // Act
        userService.logUser(request, userResponseObserver);

        // Assert
        verify(userRepository, times(1)).findByEmail(email);
        verify(userResponseObserver, times(1)).onNext(any(UserEntity.class));
        verify(userResponseObserver, times(1)).onCompleted();
        verify(userResponseObserver, never()).onError(any());
    }

    @Test
    void logUser_WithWrongPassword_ShouldReturnNullUserEntity() {
        String email = "login@example.com";

        LoginUserRequest request = LoginUserRequest.newBuilder()
                .setEmail(email)
                .setPassword("wrong-password")
                .build();

        // User in DB with some (different) password
        User userFromDb = new User();
        userFromDb.setId(1L);
        userFromDb.setEmail(email);
        userFromDb.setDisplayName("Login User");
        userFromDb.setPassword(PasswordUtil.hashPassword("correct-password"));

        when(userRepository.findByEmail(email)).thenReturn(Optional.of(userFromDb));

        // Act
        userService.logUser(request, userResponseObserver);

        // Assert
        verify(userRepository, times(1)).findByEmail(email);
        verify(userResponseObserver, times(1)).onNext(isNull());
        verify(userResponseObserver, times(1)).onCompleted();
        verify(userResponseObserver, never()).onError(any());
    }

    // ---------- updateUserName ----------

    @Test
    void updateUserName_WithExistingUser_ShouldUpdateDisplayName() {
        long userId = 1L;
        UpdateUserNameRequest request = UpdateUserNameRequest.newBuilder()
                .setUserId(userId)
                .setDisplayName("New Name")
                .build();

        User existing = new User();
        existing.setId(userId);
        existing.setEmail("user@example.com");
        existing.setDisplayName("Old Name");
        existing.setPassword("hashed");

        User saved = new User();
        saved.setId(userId);
        saved.setEmail("user@example.com");
        saved.setDisplayName("New Name");
        saved.setPassword("hashed");

        when(userRepository.findById(userId)).thenReturn(Optional.of(existing));
        when(userRepository.save(any(User.class))).thenReturn(saved);

        // Act
        userService.updateUserName(request, userResponseObserver);

        // Assert
        verify(userRepository, times(1)).findById(userId);
        verify(userRepository, times(1)).save(any(User.class));
        verify(userResponseObserver, times(1)).onNext(any(UserEntity.class));
        verify(userResponseObserver, times(1)).onCompleted();
        verify(userResponseObserver, never()).onError(any());
    }

    // ---------- getAllUsers ----------

    @Test
    void getAllUsers_ShouldReturnUserList() {
        User u1 = new User();
        u1.setId(1L);
        u1.setEmail("one@example.com");
        u1.setDisplayName("One");
        u1.setPassword("p1");

        User u2 = new User();
        u2.setId(2L);
        u2.setEmail("two@example.com");
        u2.setDisplayName("Two");
        u2.setPassword("p2");

        when(userRepository.findAll()).thenReturn(List.of(u1, u2));

        // Act
        userService.getAllUsers(Empty.newBuilder().build(), userListResponseObserver);

        // Assert
        verify(userRepository, times(1)).findAll();
        verify(userListResponseObserver, times(1)).onNext(any(UserList.class));
        verify(userListResponseObserver, times(1)).onCompleted();
        verify(userListResponseObserver, never()).onError(any());
    }

    // ---------- deleteUser ----------

    @Test
    void deleteUser_WithExistingUser_ShouldDeleteAndReturnEmpty() {
        long userId = 1L;
        DeleteUserRequest request = DeleteUserRequest.newBuilder()
                .setUserId(userId)
                .build();

        User user = new User();
        user.setId(userId);
        user.setEmail("delete@example.com");
        user.setDisplayName("Delete Me");
        user.setPassword("hashed");

        when(userRepository.existsById(userId)).thenReturn(true);
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        // Act
        userService.deleteUser(request, emptyResponseObserver);

        // Assert
        verify(userRepository, times(1)).existsById(userId);
        verify(userRepository, times(1)).findById(userId);
        verify(userRepository, times(1)).deleteById(userId);

        verify(emptyResponseObserver, times(1)).onNext(any(Empty.class));
        verify(emptyResponseObserver, times(1)).onCompleted();
        verify(emptyResponseObserver, never()).onError(any());
    }

    @Test
    void deleteUser_WithNonExistingUser_ShouldReturnNotFoundError() {
        long userId = 999L;
        DeleteUserRequest request = DeleteUserRequest.newBuilder()
                .setUserId(userId)
                .build();

        when(userRepository.existsById(userId)).thenReturn(false);
        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        // Act
        userService.deleteUser(request, emptyResponseObserver);

        // Assert
        verify(userRepository, times(1)).existsById(userId);
        verify(userRepository, times(1)).findById(userId);
        verify(emptyResponseObserver, never()).onNext(any());
        verify(emptyResponseObserver, never()).onCompleted();
        verify(emptyResponseObserver, atLeastOnce()).onError(any());
    }
}
