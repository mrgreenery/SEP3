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

  private static volatile io.grpc.MethodDescriptor<com.sep3.data.grpc.CreateQuestRequest,
      com.sep3.data.grpc.QuestEntity> getCreateQuestMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "CreateQuest",
      requestType = com.sep3.data.grpc.CreateQuestRequest.class,
      responseType = com.sep3.data.grpc.QuestEntity.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.sep3.data.grpc.CreateQuestRequest,
      com.sep3.data.grpc.QuestEntity> getCreateQuestMethod() {
    io.grpc.MethodDescriptor<com.sep3.data.grpc.CreateQuestRequest, com.sep3.data.grpc.QuestEntity> getCreateQuestMethod;
    if ((getCreateQuestMethod = DataServiceGrpc.getCreateQuestMethod) == null) {
      synchronized (DataServiceGrpc.class) {
        if ((getCreateQuestMethod = DataServiceGrpc.getCreateQuestMethod) == null) {
          DataServiceGrpc.getCreateQuestMethod = getCreateQuestMethod =
              io.grpc.MethodDescriptor.<com.sep3.data.grpc.CreateQuestRequest, com.sep3.data.grpc.QuestEntity>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "CreateQuest"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.sep3.data.grpc.CreateQuestRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.sep3.data.grpc.QuestEntity.getDefaultInstance()))
              .setSchemaDescriptor(new DataServiceMethodDescriptorSupplier("CreateQuest"))
              .build();
        }
      }
    }
    return getCreateQuestMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.sep3.data.grpc.IdRequest,
      com.sep3.data.grpc.QuestEntity> getGetQuestsByIdMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetQuestsById",
      requestType = com.sep3.data.grpc.IdRequest.class,
      responseType = com.sep3.data.grpc.QuestEntity.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.sep3.data.grpc.IdRequest,
      com.sep3.data.grpc.QuestEntity> getGetQuestsByIdMethod() {
    io.grpc.MethodDescriptor<com.sep3.data.grpc.IdRequest, com.sep3.data.grpc.QuestEntity> getGetQuestsByIdMethod;
    if ((getGetQuestsByIdMethod = DataServiceGrpc.getGetQuestsByIdMethod) == null) {
      synchronized (DataServiceGrpc.class) {
        if ((getGetQuestsByIdMethod = DataServiceGrpc.getGetQuestsByIdMethod) == null) {
          DataServiceGrpc.getGetQuestsByIdMethod = getGetQuestsByIdMethod =
              io.grpc.MethodDescriptor.<com.sep3.data.grpc.IdRequest, com.sep3.data.grpc.QuestEntity>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetQuestsById"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.sep3.data.grpc.IdRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.sep3.data.grpc.QuestEntity.getDefaultInstance()))
              .setSchemaDescriptor(new DataServiceMethodDescriptorSupplier("GetQuestsById"))
              .build();
        }
      }
    }
    return getGetQuestsByIdMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.google.protobuf.Empty,
      com.sep3.data.grpc.QuestList> getGetAllQuestsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetAllQuests",
      requestType = com.google.protobuf.Empty.class,
      responseType = com.sep3.data.grpc.QuestList.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.google.protobuf.Empty,
      com.sep3.data.grpc.QuestList> getGetAllQuestsMethod() {
    io.grpc.MethodDescriptor<com.google.protobuf.Empty, com.sep3.data.grpc.QuestList> getGetAllQuestsMethod;
    if ((getGetAllQuestsMethod = DataServiceGrpc.getGetAllQuestsMethod) == null) {
      synchronized (DataServiceGrpc.class) {
        if ((getGetAllQuestsMethod = DataServiceGrpc.getGetAllQuestsMethod) == null) {
          DataServiceGrpc.getGetAllQuestsMethod = getGetAllQuestsMethod =
              io.grpc.MethodDescriptor.<com.google.protobuf.Empty, com.sep3.data.grpc.QuestList>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetAllQuests"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.google.protobuf.Empty.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.sep3.data.grpc.QuestList.getDefaultInstance()))
              .setSchemaDescriptor(new DataServiceMethodDescriptorSupplier("GetAllQuests"))
              .build();
        }
      }
    }
    return getGetAllQuestsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.sep3.data.grpc.UpdateQuestRequest,
      com.sep3.data.grpc.QuestEntity> getUpdateQuestMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "UpdateQuest",
      requestType = com.sep3.data.grpc.UpdateQuestRequest.class,
      responseType = com.sep3.data.grpc.QuestEntity.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.sep3.data.grpc.UpdateQuestRequest,
      com.sep3.data.grpc.QuestEntity> getUpdateQuestMethod() {
    io.grpc.MethodDescriptor<com.sep3.data.grpc.UpdateQuestRequest, com.sep3.data.grpc.QuestEntity> getUpdateQuestMethod;
    if ((getUpdateQuestMethod = DataServiceGrpc.getUpdateQuestMethod) == null) {
      synchronized (DataServiceGrpc.class) {
        if ((getUpdateQuestMethod = DataServiceGrpc.getUpdateQuestMethod) == null) {
          DataServiceGrpc.getUpdateQuestMethod = getUpdateQuestMethod =
              io.grpc.MethodDescriptor.<com.sep3.data.grpc.UpdateQuestRequest, com.sep3.data.grpc.QuestEntity>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "UpdateQuest"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.sep3.data.grpc.UpdateQuestRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.sep3.data.grpc.QuestEntity.getDefaultInstance()))
              .setSchemaDescriptor(new DataServiceMethodDescriptorSupplier("UpdateQuest"))
              .build();
        }
      }
    }
    return getUpdateQuestMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.sep3.data.grpc.IdRequest,
      com.google.protobuf.Empty> getDeleteQuestMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "DeleteQuest",
      requestType = com.sep3.data.grpc.IdRequest.class,
      responseType = com.google.protobuf.Empty.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.sep3.data.grpc.IdRequest,
      com.google.protobuf.Empty> getDeleteQuestMethod() {
    io.grpc.MethodDescriptor<com.sep3.data.grpc.IdRequest, com.google.protobuf.Empty> getDeleteQuestMethod;
    if ((getDeleteQuestMethod = DataServiceGrpc.getDeleteQuestMethod) == null) {
      synchronized (DataServiceGrpc.class) {
        if ((getDeleteQuestMethod = DataServiceGrpc.getDeleteQuestMethod) == null) {
          DataServiceGrpc.getDeleteQuestMethod = getDeleteQuestMethod =
              io.grpc.MethodDescriptor.<com.sep3.data.grpc.IdRequest, com.google.protobuf.Empty>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "DeleteQuest"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.sep3.data.grpc.IdRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.google.protobuf.Empty.getDefaultInstance()))
              .setSchemaDescriptor(new DataServiceMethodDescriptorSupplier("DeleteQuest"))
              .build();
        }
      }
    }
    return getDeleteQuestMethod;
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
    default void createQuest(com.sep3.data.grpc.CreateQuestRequest request,
        io.grpc.stub.StreamObserver<com.sep3.data.grpc.QuestEntity> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getCreateQuestMethod(), responseObserver);
    }

    /**
     */
    default void getQuestsById(com.sep3.data.grpc.IdRequest request,
        io.grpc.stub.StreamObserver<com.sep3.data.grpc.QuestEntity> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetQuestsByIdMethod(), responseObserver);
    }

    /**
     */
    default void getAllQuests(com.google.protobuf.Empty request,
        io.grpc.stub.StreamObserver<com.sep3.data.grpc.QuestList> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetAllQuestsMethod(), responseObserver);
    }

    /**
     */
    default void updateQuest(com.sep3.data.grpc.UpdateQuestRequest request,
        io.grpc.stub.StreamObserver<com.sep3.data.grpc.QuestEntity> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getUpdateQuestMethod(), responseObserver);
    }

    /**
     */
    default void deleteQuest(com.sep3.data.grpc.IdRequest request,
        io.grpc.stub.StreamObserver<com.google.protobuf.Empty> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getDeleteQuestMethod(), responseObserver);
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
    public void createQuest(com.sep3.data.grpc.CreateQuestRequest request,
        io.grpc.stub.StreamObserver<com.sep3.data.grpc.QuestEntity> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getCreateQuestMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getQuestsById(com.sep3.data.grpc.IdRequest request,
        io.grpc.stub.StreamObserver<com.sep3.data.grpc.QuestEntity> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetQuestsByIdMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getAllQuests(com.google.protobuf.Empty request,
        io.grpc.stub.StreamObserver<com.sep3.data.grpc.QuestList> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetAllQuestsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void updateQuest(com.sep3.data.grpc.UpdateQuestRequest request,
        io.grpc.stub.StreamObserver<com.sep3.data.grpc.QuestEntity> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getUpdateQuestMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void deleteQuest(com.sep3.data.grpc.IdRequest request,
        io.grpc.stub.StreamObserver<com.google.protobuf.Empty> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getDeleteQuestMethod(), getCallOptions()), request, responseObserver);
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
    public com.sep3.data.grpc.QuestEntity createQuest(com.sep3.data.grpc.CreateQuestRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getCreateQuestMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.sep3.data.grpc.QuestEntity getQuestsById(com.sep3.data.grpc.IdRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetQuestsByIdMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.sep3.data.grpc.QuestList getAllQuests(com.google.protobuf.Empty request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetAllQuestsMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.sep3.data.grpc.QuestEntity updateQuest(com.sep3.data.grpc.UpdateQuestRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getUpdateQuestMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.google.protobuf.Empty deleteQuest(com.sep3.data.grpc.IdRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getDeleteQuestMethod(), getCallOptions(), request);
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
    public com.google.common.util.concurrent.ListenableFuture<com.sep3.data.grpc.QuestEntity> createQuest(
        com.sep3.data.grpc.CreateQuestRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getCreateQuestMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.sep3.data.grpc.QuestEntity> getQuestsById(
        com.sep3.data.grpc.IdRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetQuestsByIdMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.sep3.data.grpc.QuestList> getAllQuests(
        com.google.protobuf.Empty request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetAllQuestsMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.sep3.data.grpc.QuestEntity> updateQuest(
        com.sep3.data.grpc.UpdateQuestRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getUpdateQuestMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.google.protobuf.Empty> deleteQuest(
        com.sep3.data.grpc.IdRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getDeleteQuestMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_CREATE_USER = 0;
  private static final int METHODID_GET_USER_BY_ID = 1;
  private static final int METHODID_GET_ALL_USERS = 2;
  private static final int METHODID_UPDATE_USER = 3;
  private static final int METHODID_DELETE_USER = 4;
  private static final int METHODID_CREATE_QUEST = 5;
  private static final int METHODID_GET_QUESTS_BY_ID = 6;
  private static final int METHODID_GET_ALL_QUESTS = 7;
  private static final int METHODID_UPDATE_QUEST = 8;
  private static final int METHODID_DELETE_QUEST = 9;

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
        case METHODID_CREATE_QUEST:
          serviceImpl.createQuest((com.sep3.data.grpc.CreateQuestRequest) request,
              (io.grpc.stub.StreamObserver<com.sep3.data.grpc.QuestEntity>) responseObserver);
          break;
        case METHODID_GET_QUESTS_BY_ID:
          serviceImpl.getQuestsById((com.sep3.data.grpc.IdRequest) request,
              (io.grpc.stub.StreamObserver<com.sep3.data.grpc.QuestEntity>) responseObserver);
          break;
        case METHODID_GET_ALL_QUESTS:
          serviceImpl.getAllQuests((com.google.protobuf.Empty) request,
              (io.grpc.stub.StreamObserver<com.sep3.data.grpc.QuestList>) responseObserver);
          break;
        case METHODID_UPDATE_QUEST:
          serviceImpl.updateQuest((com.sep3.data.grpc.UpdateQuestRequest) request,
              (io.grpc.stub.StreamObserver<com.sep3.data.grpc.QuestEntity>) responseObserver);
          break;
        case METHODID_DELETE_QUEST:
          serviceImpl.deleteQuest((com.sep3.data.grpc.IdRequest) request,
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
          getCreateQuestMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.sep3.data.grpc.CreateQuestRequest,
              com.sep3.data.grpc.QuestEntity>(
                service, METHODID_CREATE_QUEST)))
        .addMethod(
          getGetQuestsByIdMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.sep3.data.grpc.IdRequest,
              com.sep3.data.grpc.QuestEntity>(
                service, METHODID_GET_QUESTS_BY_ID)))
        .addMethod(
          getGetAllQuestsMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.google.protobuf.Empty,
              com.sep3.data.grpc.QuestList>(
                service, METHODID_GET_ALL_QUESTS)))
        .addMethod(
          getUpdateQuestMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.sep3.data.grpc.UpdateQuestRequest,
              com.sep3.data.grpc.QuestEntity>(
                service, METHODID_UPDATE_QUEST)))
        .addMethod(
          getDeleteQuestMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.sep3.data.grpc.IdRequest,
              com.google.protobuf.Empty>(
                service, METHODID_DELETE_QUEST)))
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
              .addMethod(getCreateQuestMethod())
              .addMethod(getGetQuestsByIdMethod())
              .addMethod(getGetAllQuestsMethod())
              .addMethod(getUpdateQuestMethod())
              .addMethod(getDeleteQuestMethod())
              .build();
        }
      }
    }
    return result;
  }
}
