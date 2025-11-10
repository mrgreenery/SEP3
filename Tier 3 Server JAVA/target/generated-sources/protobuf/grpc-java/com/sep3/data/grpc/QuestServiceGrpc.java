package com.sep3.data.grpc;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.66.0)",
    comments = "Source: kanban.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class QuestServiceGrpc {

  private QuestServiceGrpc() {}

  public static final java.lang.String SERVICE_NAME = "data.QuestService";

  // Static method descriptors that strictly reflect the proto.
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
    if ((getCreateQuestMethod = QuestServiceGrpc.getCreateQuestMethod) == null) {
      synchronized (QuestServiceGrpc.class) {
        if ((getCreateQuestMethod = QuestServiceGrpc.getCreateQuestMethod) == null) {
          QuestServiceGrpc.getCreateQuestMethod = getCreateQuestMethod =
              io.grpc.MethodDescriptor.<com.sep3.data.grpc.CreateQuestRequest, com.sep3.data.grpc.QuestEntity>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "CreateQuest"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.sep3.data.grpc.CreateQuestRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.sep3.data.grpc.QuestEntity.getDefaultInstance()))
              .setSchemaDescriptor(new QuestServiceMethodDescriptorSupplier("CreateQuest"))
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
    if ((getGetQuestsByIdMethod = QuestServiceGrpc.getGetQuestsByIdMethod) == null) {
      synchronized (QuestServiceGrpc.class) {
        if ((getGetQuestsByIdMethod = QuestServiceGrpc.getGetQuestsByIdMethod) == null) {
          QuestServiceGrpc.getGetQuestsByIdMethod = getGetQuestsByIdMethod =
              io.grpc.MethodDescriptor.<com.sep3.data.grpc.IdRequest, com.sep3.data.grpc.QuestEntity>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetQuestsById"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.sep3.data.grpc.IdRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.sep3.data.grpc.QuestEntity.getDefaultInstance()))
              .setSchemaDescriptor(new QuestServiceMethodDescriptorSupplier("GetQuestsById"))
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
    if ((getGetAllQuestsMethod = QuestServiceGrpc.getGetAllQuestsMethod) == null) {
      synchronized (QuestServiceGrpc.class) {
        if ((getGetAllQuestsMethod = QuestServiceGrpc.getGetAllQuestsMethod) == null) {
          QuestServiceGrpc.getGetAllQuestsMethod = getGetAllQuestsMethod =
              io.grpc.MethodDescriptor.<com.google.protobuf.Empty, com.sep3.data.grpc.QuestList>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetAllQuests"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.google.protobuf.Empty.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.sep3.data.grpc.QuestList.getDefaultInstance()))
              .setSchemaDescriptor(new QuestServiceMethodDescriptorSupplier("GetAllQuests"))
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
    if ((getUpdateQuestMethod = QuestServiceGrpc.getUpdateQuestMethod) == null) {
      synchronized (QuestServiceGrpc.class) {
        if ((getUpdateQuestMethod = QuestServiceGrpc.getUpdateQuestMethod) == null) {
          QuestServiceGrpc.getUpdateQuestMethod = getUpdateQuestMethod =
              io.grpc.MethodDescriptor.<com.sep3.data.grpc.UpdateQuestRequest, com.sep3.data.grpc.QuestEntity>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "UpdateQuest"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.sep3.data.grpc.UpdateQuestRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.sep3.data.grpc.QuestEntity.getDefaultInstance()))
              .setSchemaDescriptor(new QuestServiceMethodDescriptorSupplier("UpdateQuest"))
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
    if ((getDeleteQuestMethod = QuestServiceGrpc.getDeleteQuestMethod) == null) {
      synchronized (QuestServiceGrpc.class) {
        if ((getDeleteQuestMethod = QuestServiceGrpc.getDeleteQuestMethod) == null) {
          QuestServiceGrpc.getDeleteQuestMethod = getDeleteQuestMethod =
              io.grpc.MethodDescriptor.<com.sep3.data.grpc.IdRequest, com.google.protobuf.Empty>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "DeleteQuest"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.sep3.data.grpc.IdRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.google.protobuf.Empty.getDefaultInstance()))
              .setSchemaDescriptor(new QuestServiceMethodDescriptorSupplier("DeleteQuest"))
              .build();
        }
      }
    }
    return getDeleteQuestMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static QuestServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<QuestServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<QuestServiceStub>() {
        @java.lang.Override
        public QuestServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new QuestServiceStub(channel, callOptions);
        }
      };
    return QuestServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static QuestServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<QuestServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<QuestServiceBlockingStub>() {
        @java.lang.Override
        public QuestServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new QuestServiceBlockingStub(channel, callOptions);
        }
      };
    return QuestServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static QuestServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<QuestServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<QuestServiceFutureStub>() {
        @java.lang.Override
        public QuestServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new QuestServiceFutureStub(channel, callOptions);
        }
      };
    return QuestServiceFutureStub.newStub(factory, channel);
  }

  /**
   */
  public interface AsyncService {

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
   * Base class for the server implementation of the service QuestService.
   */
  public static abstract class QuestServiceImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return QuestServiceGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service QuestService.
   */
  public static final class QuestServiceStub
      extends io.grpc.stub.AbstractAsyncStub<QuestServiceStub> {
    private QuestServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected QuestServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new QuestServiceStub(channel, callOptions);
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
   * A stub to allow clients to do synchronous rpc calls to service QuestService.
   */
  public static final class QuestServiceBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<QuestServiceBlockingStub> {
    private QuestServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected QuestServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new QuestServiceBlockingStub(channel, callOptions);
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
   * A stub to allow clients to do ListenableFuture-style rpc calls to service QuestService.
   */
  public static final class QuestServiceFutureStub
      extends io.grpc.stub.AbstractFutureStub<QuestServiceFutureStub> {
    private QuestServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected QuestServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new QuestServiceFutureStub(channel, callOptions);
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

  private static final int METHODID_CREATE_QUEST = 0;
  private static final int METHODID_GET_QUESTS_BY_ID = 1;
  private static final int METHODID_GET_ALL_QUESTS = 2;
  private static final int METHODID_UPDATE_QUEST = 3;
  private static final int METHODID_DELETE_QUEST = 4;

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

  private static abstract class QuestServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    QuestServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.sep3.data.grpc.DataProto.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("QuestService");
    }
  }

  private static final class QuestServiceFileDescriptorSupplier
      extends QuestServiceBaseDescriptorSupplier {
    QuestServiceFileDescriptorSupplier() {}
  }

  private static final class QuestServiceMethodDescriptorSupplier
      extends QuestServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final java.lang.String methodName;

    QuestServiceMethodDescriptorSupplier(java.lang.String methodName) {
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
      synchronized (QuestServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new QuestServiceFileDescriptorSupplier())
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
