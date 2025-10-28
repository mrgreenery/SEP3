package com.sep3.data.grpc;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 * <pre>
 * ===== Database service =====
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.66.0)",
    comments = "Source: kanban.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class DataServiceGrpc {

  private DataServiceGrpc() {}

  public static final java.lang.String SERVICE_NAME = "data.DataService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.sep3.data.grpc.UserEntity,
      com.sep3.data.grpc.UserEntity> getCreateUserMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "CreateUser",
      requestType = com.sep3.data.grpc.UserEntity.class,
      responseType = com.sep3.data.grpc.UserEntity.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.sep3.data.grpc.UserEntity,
      com.sep3.data.grpc.UserEntity> getCreateUserMethod() {
    io.grpc.MethodDescriptor<com.sep3.data.grpc.UserEntity, com.sep3.data.grpc.UserEntity> getCreateUserMethod;
    if ((getCreateUserMethod = DataServiceGrpc.getCreateUserMethod) == null) {
      synchronized (DataServiceGrpc.class) {
        if ((getCreateUserMethod = DataServiceGrpc.getCreateUserMethod) == null) {
          DataServiceGrpc.getCreateUserMethod = getCreateUserMethod =
              io.grpc.MethodDescriptor.<com.sep3.data.grpc.UserEntity, com.sep3.data.grpc.UserEntity>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "CreateUser"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.sep3.data.grpc.UserEntity.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.sep3.data.grpc.UserEntity.getDefaultInstance()))
              .setSchemaDescriptor(new DataServiceMethodDescriptorSupplier("CreateUser"))
              .build();
        }
      }
    }
    return getCreateUserMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.sep3.data.grpc.IdRequest,
      com.sep3.data.grpc.UserEntity> getGetUserByIdMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetUserById",
      requestType = com.sep3.data.grpc.IdRequest.class,
      responseType = com.sep3.data.grpc.UserEntity.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.sep3.data.grpc.IdRequest,
      com.sep3.data.grpc.UserEntity> getGetUserByIdMethod() {
    io.grpc.MethodDescriptor<com.sep3.data.grpc.IdRequest, com.sep3.data.grpc.UserEntity> getGetUserByIdMethod;
    if ((getGetUserByIdMethod = DataServiceGrpc.getGetUserByIdMethod) == null) {
      synchronized (DataServiceGrpc.class) {
        if ((getGetUserByIdMethod = DataServiceGrpc.getGetUserByIdMethod) == null) {
          DataServiceGrpc.getGetUserByIdMethod = getGetUserByIdMethod =
              io.grpc.MethodDescriptor.<com.sep3.data.grpc.IdRequest, com.sep3.data.grpc.UserEntity>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetUserById"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.sep3.data.grpc.IdRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.sep3.data.grpc.UserEntity.getDefaultInstance()))
              .setSchemaDescriptor(new DataServiceMethodDescriptorSupplier("GetUserById"))
              .build();
        }
      }
    }
    return getGetUserByIdMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.google.protobuf.Empty,
      com.sep3.data.grpc.UserList> getGetAllUsersMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetAllUsers",
      requestType = com.google.protobuf.Empty.class,
      responseType = com.sep3.data.grpc.UserList.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.google.protobuf.Empty,
      com.sep3.data.grpc.UserList> getGetAllUsersMethod() {
    io.grpc.MethodDescriptor<com.google.protobuf.Empty, com.sep3.data.grpc.UserList> getGetAllUsersMethod;
    if ((getGetAllUsersMethod = DataServiceGrpc.getGetAllUsersMethod) == null) {
      synchronized (DataServiceGrpc.class) {
        if ((getGetAllUsersMethod = DataServiceGrpc.getGetAllUsersMethod) == null) {
          DataServiceGrpc.getGetAllUsersMethod = getGetAllUsersMethod =
              io.grpc.MethodDescriptor.<com.google.protobuf.Empty, com.sep3.data.grpc.UserList>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetAllUsers"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.google.protobuf.Empty.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.sep3.data.grpc.UserList.getDefaultInstance()))
              .setSchemaDescriptor(new DataServiceMethodDescriptorSupplier("GetAllUsers"))
              .build();
        }
      }
    }
    return getGetAllUsersMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.sep3.data.grpc.IdRequest,
      com.sep3.data.grpc.UserEntity> getUpdateUserMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "UpdateUser",
      requestType = com.sep3.data.grpc.IdRequest.class,
      responseType = com.sep3.data.grpc.UserEntity.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.sep3.data.grpc.IdRequest,
      com.sep3.data.grpc.UserEntity> getUpdateUserMethod() {
    io.grpc.MethodDescriptor<com.sep3.data.grpc.IdRequest, com.sep3.data.grpc.UserEntity> getUpdateUserMethod;
    if ((getUpdateUserMethod = DataServiceGrpc.getUpdateUserMethod) == null) {
      synchronized (DataServiceGrpc.class) {
        if ((getUpdateUserMethod = DataServiceGrpc.getUpdateUserMethod) == null) {
          DataServiceGrpc.getUpdateUserMethod = getUpdateUserMethod =
              io.grpc.MethodDescriptor.<com.sep3.data.grpc.IdRequest, com.sep3.data.grpc.UserEntity>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "UpdateUser"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.sep3.data.grpc.IdRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.sep3.data.grpc.UserEntity.getDefaultInstance()))
              .setSchemaDescriptor(new DataServiceMethodDescriptorSupplier("UpdateUser"))
              .build();
        }
      }
    }
    return getUpdateUserMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.sep3.data.grpc.IdRequest,
      com.google.protobuf.Empty> getDeleteUserMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "DeleteUser",
      requestType = com.sep3.data.grpc.IdRequest.class,
      responseType = com.google.protobuf.Empty.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.sep3.data.grpc.IdRequest,
      com.google.protobuf.Empty> getDeleteUserMethod() {
    io.grpc.MethodDescriptor<com.sep3.data.grpc.IdRequest, com.google.protobuf.Empty> getDeleteUserMethod;
    if ((getDeleteUserMethod = DataServiceGrpc.getDeleteUserMethod) == null) {
      synchronized (DataServiceGrpc.class) {
        if ((getDeleteUserMethod = DataServiceGrpc.getDeleteUserMethod) == null) {
          DataServiceGrpc.getDeleteUserMethod = getDeleteUserMethod =
              io.grpc.MethodDescriptor.<com.sep3.data.grpc.IdRequest, com.google.protobuf.Empty>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "DeleteUser"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.sep3.data.grpc.IdRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.google.protobuf.Empty.getDefaultInstance()))
              .setSchemaDescriptor(new DataServiceMethodDescriptorSupplier("DeleteUser"))
              .build();
        }
      }
    }
    return getDeleteUserMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.sep3.data.grpc.CreateTaskRequest,
      com.sep3.data.grpc.TaskEntity> getCreateTaskMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "CreateTask",
      requestType = com.sep3.data.grpc.CreateTaskRequest.class,
      responseType = com.sep3.data.grpc.TaskEntity.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.sep3.data.grpc.CreateTaskRequest,
      com.sep3.data.grpc.TaskEntity> getCreateTaskMethod() {
    io.grpc.MethodDescriptor<com.sep3.data.grpc.CreateTaskRequest, com.sep3.data.grpc.TaskEntity> getCreateTaskMethod;
    if ((getCreateTaskMethod = DataServiceGrpc.getCreateTaskMethod) == null) {
      synchronized (DataServiceGrpc.class) {
        if ((getCreateTaskMethod = DataServiceGrpc.getCreateTaskMethod) == null) {
          DataServiceGrpc.getCreateTaskMethod = getCreateTaskMethod =
              io.grpc.MethodDescriptor.<com.sep3.data.grpc.CreateTaskRequest, com.sep3.data.grpc.TaskEntity>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "CreateTask"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.sep3.data.grpc.CreateTaskRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.sep3.data.grpc.TaskEntity.getDefaultInstance()))
              .setSchemaDescriptor(new DataServiceMethodDescriptorSupplier("CreateTask"))
              .build();
        }
      }
    }
    return getCreateTaskMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.sep3.data.grpc.IdRequest,
      com.sep3.data.grpc.TaskEntity> getGetTasksByIdMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetTasksById",
      requestType = com.sep3.data.grpc.IdRequest.class,
      responseType = com.sep3.data.grpc.TaskEntity.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.sep3.data.grpc.IdRequest,
      com.sep3.data.grpc.TaskEntity> getGetTasksByIdMethod() {
    io.grpc.MethodDescriptor<com.sep3.data.grpc.IdRequest, com.sep3.data.grpc.TaskEntity> getGetTasksByIdMethod;
    if ((getGetTasksByIdMethod = DataServiceGrpc.getGetTasksByIdMethod) == null) {
      synchronized (DataServiceGrpc.class) {
        if ((getGetTasksByIdMethod = DataServiceGrpc.getGetTasksByIdMethod) == null) {
          DataServiceGrpc.getGetTasksByIdMethod = getGetTasksByIdMethod =
              io.grpc.MethodDescriptor.<com.sep3.data.grpc.IdRequest, com.sep3.data.grpc.TaskEntity>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetTasksById"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.sep3.data.grpc.IdRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.sep3.data.grpc.TaskEntity.getDefaultInstance()))
              .setSchemaDescriptor(new DataServiceMethodDescriptorSupplier("GetTasksById"))
              .build();
        }
      }
    }
    return getGetTasksByIdMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.google.protobuf.Empty,
      com.sep3.data.grpc.TaskList> getGetAllTasksMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetAllTasks",
      requestType = com.google.protobuf.Empty.class,
      responseType = com.sep3.data.grpc.TaskList.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.google.protobuf.Empty,
      com.sep3.data.grpc.TaskList> getGetAllTasksMethod() {
    io.grpc.MethodDescriptor<com.google.protobuf.Empty, com.sep3.data.grpc.TaskList> getGetAllTasksMethod;
    if ((getGetAllTasksMethod = DataServiceGrpc.getGetAllTasksMethod) == null) {
      synchronized (DataServiceGrpc.class) {
        if ((getGetAllTasksMethod = DataServiceGrpc.getGetAllTasksMethod) == null) {
          DataServiceGrpc.getGetAllTasksMethod = getGetAllTasksMethod =
              io.grpc.MethodDescriptor.<com.google.protobuf.Empty, com.sep3.data.grpc.TaskList>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetAllTasks"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.google.protobuf.Empty.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.sep3.data.grpc.TaskList.getDefaultInstance()))
              .setSchemaDescriptor(new DataServiceMethodDescriptorSupplier("GetAllTasks"))
              .build();
        }
      }
    }
    return getGetAllTasksMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.sep3.data.grpc.UpdateTaskRequest,
      com.sep3.data.grpc.TaskEntity> getUpdateTaskMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "UpdateTask",
      requestType = com.sep3.data.grpc.UpdateTaskRequest.class,
      responseType = com.sep3.data.grpc.TaskEntity.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.sep3.data.grpc.UpdateTaskRequest,
      com.sep3.data.grpc.TaskEntity> getUpdateTaskMethod() {
    io.grpc.MethodDescriptor<com.sep3.data.grpc.UpdateTaskRequest, com.sep3.data.grpc.TaskEntity> getUpdateTaskMethod;
    if ((getUpdateTaskMethod = DataServiceGrpc.getUpdateTaskMethod) == null) {
      synchronized (DataServiceGrpc.class) {
        if ((getUpdateTaskMethod = DataServiceGrpc.getUpdateTaskMethod) == null) {
          DataServiceGrpc.getUpdateTaskMethod = getUpdateTaskMethod =
              io.grpc.MethodDescriptor.<com.sep3.data.grpc.UpdateTaskRequest, com.sep3.data.grpc.TaskEntity>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "UpdateTask"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.sep3.data.grpc.UpdateTaskRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.sep3.data.grpc.TaskEntity.getDefaultInstance()))
              .setSchemaDescriptor(new DataServiceMethodDescriptorSupplier("UpdateTask"))
              .build();
        }
      }
    }
    return getUpdateTaskMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.sep3.data.grpc.IdRequest,
      com.google.protobuf.Empty> getDeleteTaskMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "DeleteTask",
      requestType = com.sep3.data.grpc.IdRequest.class,
      responseType = com.google.protobuf.Empty.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.sep3.data.grpc.IdRequest,
      com.google.protobuf.Empty> getDeleteTaskMethod() {
    io.grpc.MethodDescriptor<com.sep3.data.grpc.IdRequest, com.google.protobuf.Empty> getDeleteTaskMethod;
    if ((getDeleteTaskMethod = DataServiceGrpc.getDeleteTaskMethod) == null) {
      synchronized (DataServiceGrpc.class) {
        if ((getDeleteTaskMethod = DataServiceGrpc.getDeleteTaskMethod) == null) {
          DataServiceGrpc.getDeleteTaskMethod = getDeleteTaskMethod =
              io.grpc.MethodDescriptor.<com.sep3.data.grpc.IdRequest, com.google.protobuf.Empty>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "DeleteTask"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.sep3.data.grpc.IdRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.google.protobuf.Empty.getDefaultInstance()))
              .setSchemaDescriptor(new DataServiceMethodDescriptorSupplier("DeleteTask"))
              .build();
        }
      }
    }
    return getDeleteTaskMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static DataServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<DataServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<DataServiceStub>() {
        @java.lang.Override
        public DataServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new DataServiceStub(channel, callOptions);
        }
      };
    return DataServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static DataServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<DataServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<DataServiceBlockingStub>() {
        @java.lang.Override
        public DataServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new DataServiceBlockingStub(channel, callOptions);
        }
      };
    return DataServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static DataServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<DataServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<DataServiceFutureStub>() {
        @java.lang.Override
        public DataServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new DataServiceFutureStub(channel, callOptions);
        }
      };
    return DataServiceFutureStub.newStub(factory, channel);
  }

  /**
   * <pre>
   * ===== Database service =====
   * </pre>
   */
  public interface AsyncService {

    /**
     */
    default void createUser(com.sep3.data.grpc.UserEntity request,
        io.grpc.stub.StreamObserver<com.sep3.data.grpc.UserEntity> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getCreateUserMethod(), responseObserver);
    }

    /**
     */
    default void getUserById(com.sep3.data.grpc.IdRequest request,
        io.grpc.stub.StreamObserver<com.sep3.data.grpc.UserEntity> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetUserByIdMethod(), responseObserver);
    }

    /**
     */
    default void getAllUsers(com.google.protobuf.Empty request,
        io.grpc.stub.StreamObserver<com.sep3.data.grpc.UserList> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetAllUsersMethod(), responseObserver);
    }

    /**
     */
    default void updateUser(com.sep3.data.grpc.IdRequest request,
        io.grpc.stub.StreamObserver<com.sep3.data.grpc.UserEntity> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getUpdateUserMethod(), responseObserver);
    }

    /**
     */
    default void deleteUser(com.sep3.data.grpc.IdRequest request,
        io.grpc.stub.StreamObserver<com.google.protobuf.Empty> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getDeleteUserMethod(), responseObserver);
    }

    /**
     */
    default void createTask(com.sep3.data.grpc.CreateTaskRequest request,
        io.grpc.stub.StreamObserver<com.sep3.data.grpc.TaskEntity> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getCreateTaskMethod(), responseObserver);
    }

    /**
     */
    default void getTasksById(com.sep3.data.grpc.IdRequest request,
        io.grpc.stub.StreamObserver<com.sep3.data.grpc.TaskEntity> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetTasksByIdMethod(), responseObserver);
    }

    /**
     */
    default void getAllTasks(com.google.protobuf.Empty request,
        io.grpc.stub.StreamObserver<com.sep3.data.grpc.TaskList> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetAllTasksMethod(), responseObserver);
    }

    /**
     */
    default void updateTask(com.sep3.data.grpc.UpdateTaskRequest request,
        io.grpc.stub.StreamObserver<com.sep3.data.grpc.TaskEntity> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getUpdateTaskMethod(), responseObserver);
    }

    /**
     */
    default void deleteTask(com.sep3.data.grpc.IdRequest request,
        io.grpc.stub.StreamObserver<com.google.protobuf.Empty> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getDeleteTaskMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service DataService.
   * <pre>
   * ===== Database service =====
   * </pre>
   */
  public static abstract class DataServiceImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return DataServiceGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service DataService.
   * <pre>
   * ===== Database service =====
   * </pre>
   */
  public static final class DataServiceStub
      extends io.grpc.stub.AbstractAsyncStub<DataServiceStub> {
    private DataServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected DataServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new DataServiceStub(channel, callOptions);
    }

    /**
     */
    public void createUser(com.sep3.data.grpc.UserEntity request,
        io.grpc.stub.StreamObserver<com.sep3.data.grpc.UserEntity> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getCreateUserMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getUserById(com.sep3.data.grpc.IdRequest request,
        io.grpc.stub.StreamObserver<com.sep3.data.grpc.UserEntity> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetUserByIdMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getAllUsers(com.google.protobuf.Empty request,
        io.grpc.stub.StreamObserver<com.sep3.data.grpc.UserList> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetAllUsersMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void updateUser(com.sep3.data.grpc.IdRequest request,
        io.grpc.stub.StreamObserver<com.sep3.data.grpc.UserEntity> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getUpdateUserMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void deleteUser(com.sep3.data.grpc.IdRequest request,
        io.grpc.stub.StreamObserver<com.google.protobuf.Empty> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getDeleteUserMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void createTask(com.sep3.data.grpc.CreateTaskRequest request,
        io.grpc.stub.StreamObserver<com.sep3.data.grpc.TaskEntity> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getCreateTaskMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getTasksById(com.sep3.data.grpc.IdRequest request,
        io.grpc.stub.StreamObserver<com.sep3.data.grpc.TaskEntity> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetTasksByIdMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getAllTasks(com.google.protobuf.Empty request,
        io.grpc.stub.StreamObserver<com.sep3.data.grpc.TaskList> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetAllTasksMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void updateTask(com.sep3.data.grpc.UpdateTaskRequest request,
        io.grpc.stub.StreamObserver<com.sep3.data.grpc.TaskEntity> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getUpdateTaskMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void deleteTask(com.sep3.data.grpc.IdRequest request,
        io.grpc.stub.StreamObserver<com.google.protobuf.Empty> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getDeleteTaskMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service DataService.
   * <pre>
   * ===== Database service =====
   * </pre>
   */
  public static final class DataServiceBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<DataServiceBlockingStub> {
    private DataServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected DataServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new DataServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public com.sep3.data.grpc.UserEntity createUser(com.sep3.data.grpc.UserEntity request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getCreateUserMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.sep3.data.grpc.UserEntity getUserById(com.sep3.data.grpc.IdRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetUserByIdMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.sep3.data.grpc.UserList getAllUsers(com.google.protobuf.Empty request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetAllUsersMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.sep3.data.grpc.UserEntity updateUser(com.sep3.data.grpc.IdRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getUpdateUserMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.google.protobuf.Empty deleteUser(com.sep3.data.grpc.IdRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getDeleteUserMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.sep3.data.grpc.TaskEntity createTask(com.sep3.data.grpc.CreateTaskRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getCreateTaskMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.sep3.data.grpc.TaskEntity getTasksById(com.sep3.data.grpc.IdRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetTasksByIdMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.sep3.data.grpc.TaskList getAllTasks(com.google.protobuf.Empty request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetAllTasksMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.sep3.data.grpc.TaskEntity updateTask(com.sep3.data.grpc.UpdateTaskRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getUpdateTaskMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.google.protobuf.Empty deleteTask(com.sep3.data.grpc.IdRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getDeleteTaskMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service DataService.
   * <pre>
   * ===== Database service =====
   * </pre>
   */
  public static final class DataServiceFutureStub
      extends io.grpc.stub.AbstractFutureStub<DataServiceFutureStub> {
    private DataServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected DataServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new DataServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.sep3.data.grpc.UserEntity> createUser(
        com.sep3.data.grpc.UserEntity request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getCreateUserMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.sep3.data.grpc.UserEntity> getUserById(
        com.sep3.data.grpc.IdRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetUserByIdMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.sep3.data.grpc.UserList> getAllUsers(
        com.google.protobuf.Empty request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetAllUsersMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.sep3.data.grpc.UserEntity> updateUser(
        com.sep3.data.grpc.IdRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getUpdateUserMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.google.protobuf.Empty> deleteUser(
        com.sep3.data.grpc.IdRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getDeleteUserMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.sep3.data.grpc.TaskEntity> createTask(
        com.sep3.data.grpc.CreateTaskRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getCreateTaskMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.sep3.data.grpc.TaskEntity> getTasksById(
        com.sep3.data.grpc.IdRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetTasksByIdMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.sep3.data.grpc.TaskList> getAllTasks(
        com.google.protobuf.Empty request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetAllTasksMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.sep3.data.grpc.TaskEntity> updateTask(
        com.sep3.data.grpc.UpdateTaskRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getUpdateTaskMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.google.protobuf.Empty> deleteTask(
        com.sep3.data.grpc.IdRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getDeleteTaskMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_CREATE_USER = 0;
  private static final int METHODID_GET_USER_BY_ID = 1;
  private static final int METHODID_GET_ALL_USERS = 2;
  private static final int METHODID_UPDATE_USER = 3;
  private static final int METHODID_DELETE_USER = 4;
  private static final int METHODID_CREATE_TASK = 5;
  private static final int METHODID_GET_TASKS_BY_ID = 6;
  private static final int METHODID_GET_ALL_TASKS = 7;
  private static final int METHODID_UPDATE_TASK = 8;
  private static final int METHODID_DELETE_TASK = 9;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final AsyncService serviceImpl;
    private final int methodId;

    MethodHandlers(AsyncService serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_CREATE_USER:
          serviceImpl.createUser((com.sep3.data.grpc.UserEntity) request,
              (io.grpc.stub.StreamObserver<com.sep3.data.grpc.UserEntity>) responseObserver);
          break;
        case METHODID_GET_USER_BY_ID:
          serviceImpl.getUserById((com.sep3.data.grpc.IdRequest) request,
              (io.grpc.stub.StreamObserver<com.sep3.data.grpc.UserEntity>) responseObserver);
          break;
        case METHODID_GET_ALL_USERS:
          serviceImpl.getAllUsers((com.google.protobuf.Empty) request,
              (io.grpc.stub.StreamObserver<com.sep3.data.grpc.UserList>) responseObserver);
          break;
        case METHODID_UPDATE_USER:
          serviceImpl.updateUser((com.sep3.data.grpc.IdRequest) request,
              (io.grpc.stub.StreamObserver<com.sep3.data.grpc.UserEntity>) responseObserver);
          break;
        case METHODID_DELETE_USER:
          serviceImpl.deleteUser((com.sep3.data.grpc.IdRequest) request,
              (io.grpc.stub.StreamObserver<com.google.protobuf.Empty>) responseObserver);
          break;
        case METHODID_CREATE_TASK:
          serviceImpl.createTask((com.sep3.data.grpc.CreateTaskRequest) request,
              (io.grpc.stub.StreamObserver<com.sep3.data.grpc.TaskEntity>) responseObserver);
          break;
        case METHODID_GET_TASKS_BY_ID:
          serviceImpl.getTasksById((com.sep3.data.grpc.IdRequest) request,
              (io.grpc.stub.StreamObserver<com.sep3.data.grpc.TaskEntity>) responseObserver);
          break;
        case METHODID_GET_ALL_TASKS:
          serviceImpl.getAllTasks((com.google.protobuf.Empty) request,
              (io.grpc.stub.StreamObserver<com.sep3.data.grpc.TaskList>) responseObserver);
          break;
        case METHODID_UPDATE_TASK:
          serviceImpl.updateTask((com.sep3.data.grpc.UpdateTaskRequest) request,
              (io.grpc.stub.StreamObserver<com.sep3.data.grpc.TaskEntity>) responseObserver);
          break;
        case METHODID_DELETE_TASK:
          serviceImpl.deleteTask((com.sep3.data.grpc.IdRequest) request,
              (io.grpc.stub.StreamObserver<com.google.protobuf.Empty>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  public static final io.grpc.ServerServiceDefinition bindService(AsyncService service) {
    return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
        .addMethod(
          getCreateUserMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.sep3.data.grpc.UserEntity,
              com.sep3.data.grpc.UserEntity>(
                service, METHODID_CREATE_USER)))
        .addMethod(
          getGetUserByIdMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.sep3.data.grpc.IdRequest,
              com.sep3.data.grpc.UserEntity>(
                service, METHODID_GET_USER_BY_ID)))
        .addMethod(
          getGetAllUsersMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.google.protobuf.Empty,
              com.sep3.data.grpc.UserList>(
                service, METHODID_GET_ALL_USERS)))
        .addMethod(
          getUpdateUserMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.sep3.data.grpc.IdRequest,
              com.sep3.data.grpc.UserEntity>(
                service, METHODID_UPDATE_USER)))
        .addMethod(
          getDeleteUserMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.sep3.data.grpc.IdRequest,
              com.google.protobuf.Empty>(
                service, METHODID_DELETE_USER)))
        .addMethod(
          getCreateTaskMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.sep3.data.grpc.CreateTaskRequest,
              com.sep3.data.grpc.TaskEntity>(
                service, METHODID_CREATE_TASK)))
        .addMethod(
          getGetTasksByIdMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.sep3.data.grpc.IdRequest,
              com.sep3.data.grpc.TaskEntity>(
                service, METHODID_GET_TASKS_BY_ID)))
        .addMethod(
          getGetAllTasksMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.google.protobuf.Empty,
              com.sep3.data.grpc.TaskList>(
                service, METHODID_GET_ALL_TASKS)))
        .addMethod(
          getUpdateTaskMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.sep3.data.grpc.UpdateTaskRequest,
              com.sep3.data.grpc.TaskEntity>(
                service, METHODID_UPDATE_TASK)))
        .addMethod(
          getDeleteTaskMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.sep3.data.grpc.IdRequest,
              com.google.protobuf.Empty>(
                service, METHODID_DELETE_TASK)))
        .build();
  }

  private static abstract class DataServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    DataServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.sep3.data.grpc.DataProto.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("DataService");
    }
  }

  private static final class DataServiceFileDescriptorSupplier
      extends DataServiceBaseDescriptorSupplier {
    DataServiceFileDescriptorSupplier() {}
  }

  private static final class DataServiceMethodDescriptorSupplier
      extends DataServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final java.lang.String methodName;

    DataServiceMethodDescriptorSupplier(java.lang.String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (DataServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new DataServiceFileDescriptorSupplier())
              .addMethod(getCreateUserMethod())
              .addMethod(getGetUserByIdMethod())
              .addMethod(getGetAllUsersMethod())
              .addMethod(getUpdateUserMethod())
              .addMethod(getDeleteUserMethod())
              .addMethod(getCreateTaskMethod())
              .addMethod(getGetTasksByIdMethod())
              .addMethod(getGetAllTasksMethod())
              .addMethod(getUpdateTaskMethod())
              .addMethod(getDeleteTaskMethod())
              .build();
        }
      }
    }
    return result;
  }
}
