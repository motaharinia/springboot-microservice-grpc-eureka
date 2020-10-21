package ir.micser.geo.business.service.cityplace.stub;

import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.15.0)",
    comments = "Source: CityPlaceMicro.proto")
public final class CityPlaceGrpc {

  private CityPlaceGrpc() {}

  public static final String SERVICE_NAME = "CityPlace";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<ir.micser.geo.business.service.cityplace.stub.CityPlaceMicro.CreateRequestModel,
      ir.micser.geo.business.service.cityplace.stub.CityPlaceMicro.CreateResponseModel> getGrpcCreateMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "grpcCreate",
      requestType = ir.micser.geo.business.service.cityplace.stub.CityPlaceMicro.CreateRequestModel.class,
      responseType = ir.micser.geo.business.service.cityplace.stub.CityPlaceMicro.CreateResponseModel.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<ir.micser.geo.business.service.cityplace.stub.CityPlaceMicro.CreateRequestModel,
      ir.micser.geo.business.service.cityplace.stub.CityPlaceMicro.CreateResponseModel> getGrpcCreateMethod() {
    io.grpc.MethodDescriptor<ir.micser.geo.business.service.cityplace.stub.CityPlaceMicro.CreateRequestModel, ir.micser.geo.business.service.cityplace.stub.CityPlaceMicro.CreateResponseModel> getGrpcCreateMethod;
    if ((getGrpcCreateMethod = CityPlaceGrpc.getGrpcCreateMethod) == null) {
      synchronized (CityPlaceGrpc.class) {
        if ((getGrpcCreateMethod = CityPlaceGrpc.getGrpcCreateMethod) == null) {
          CityPlaceGrpc.getGrpcCreateMethod = getGrpcCreateMethod = 
              io.grpc.MethodDescriptor.<ir.micser.geo.business.service.cityplace.stub.CityPlaceMicro.CreateRequestModel, ir.micser.geo.business.service.cityplace.stub.CityPlaceMicro.CreateResponseModel>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "CityPlace", "grpcCreate"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  ir.micser.geo.business.service.cityplace.stub.CityPlaceMicro.CreateRequestModel.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  ir.micser.geo.business.service.cityplace.stub.CityPlaceMicro.CreateResponseModel.getDefaultInstance()))
                  .setSchemaDescriptor(new CityPlaceMethodDescriptorSupplier("grpcCreate"))
                  .build();
          }
        }
     }
     return getGrpcCreateMethod;
  }

  private static volatile io.grpc.MethodDescriptor<ir.micser.geo.business.service.cityplace.stub.CityPlaceMicro.TransactionModel,
      ir.micser.geo.business.service.cityplace.stub.CityPlaceMicro.Empty> getGrpcCommitMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "grpcCommit",
      requestType = ir.micser.geo.business.service.cityplace.stub.CityPlaceMicro.TransactionModel.class,
      responseType = ir.micser.geo.business.service.cityplace.stub.CityPlaceMicro.Empty.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<ir.micser.geo.business.service.cityplace.stub.CityPlaceMicro.TransactionModel,
      ir.micser.geo.business.service.cityplace.stub.CityPlaceMicro.Empty> getGrpcCommitMethod() {
    io.grpc.MethodDescriptor<ir.micser.geo.business.service.cityplace.stub.CityPlaceMicro.TransactionModel, ir.micser.geo.business.service.cityplace.stub.CityPlaceMicro.Empty> getGrpcCommitMethod;
    if ((getGrpcCommitMethod = CityPlaceGrpc.getGrpcCommitMethod) == null) {
      synchronized (CityPlaceGrpc.class) {
        if ((getGrpcCommitMethod = CityPlaceGrpc.getGrpcCommitMethod) == null) {
          CityPlaceGrpc.getGrpcCommitMethod = getGrpcCommitMethod = 
              io.grpc.MethodDescriptor.<ir.micser.geo.business.service.cityplace.stub.CityPlaceMicro.TransactionModel, ir.micser.geo.business.service.cityplace.stub.CityPlaceMicro.Empty>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "CityPlace", "grpcCommit"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  ir.micser.geo.business.service.cityplace.stub.CityPlaceMicro.TransactionModel.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  ir.micser.geo.business.service.cityplace.stub.CityPlaceMicro.Empty.getDefaultInstance()))
                  .setSchemaDescriptor(new CityPlaceMethodDescriptorSupplier("grpcCommit"))
                  .build();
          }
        }
     }
     return getGrpcCommitMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static CityPlaceStub newStub(io.grpc.Channel channel) {
    return new CityPlaceStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static CityPlaceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new CityPlaceBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static CityPlaceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new CityPlaceFutureStub(channel);
  }

  /**
   */
  public static abstract class CityPlaceImplBase implements io.grpc.BindableService {

    /**
     */
    public void grpcCreate(ir.micser.geo.business.service.cityplace.stub.CityPlaceMicro.CreateRequestModel request,
        io.grpc.stub.StreamObserver<ir.micser.geo.business.service.cityplace.stub.CityPlaceMicro.CreateResponseModel> responseObserver) {
      asyncUnimplementedUnaryCall(getGrpcCreateMethod(), responseObserver);
    }

    /**
     */
    public void grpcCommit(ir.micser.geo.business.service.cityplace.stub.CityPlaceMicro.TransactionModel request,
        io.grpc.stub.StreamObserver<ir.micser.geo.business.service.cityplace.stub.CityPlaceMicro.Empty> responseObserver) {
      asyncUnimplementedUnaryCall(getGrpcCommitMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getGrpcCreateMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                ir.micser.geo.business.service.cityplace.stub.CityPlaceMicro.CreateRequestModel,
                ir.micser.geo.business.service.cityplace.stub.CityPlaceMicro.CreateResponseModel>(
                  this, METHODID_GRPC_CREATE)))
          .addMethod(
            getGrpcCommitMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                ir.micser.geo.business.service.cityplace.stub.CityPlaceMicro.TransactionModel,
                ir.micser.geo.business.service.cityplace.stub.CityPlaceMicro.Empty>(
                  this, METHODID_GRPC_COMMIT)))
          .build();
    }
  }

  /**
   */
  public static final class CityPlaceStub extends io.grpc.stub.AbstractStub<CityPlaceStub> {
    private CityPlaceStub(io.grpc.Channel channel) {
      super(channel);
    }

    private CityPlaceStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected CityPlaceStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new CityPlaceStub(channel, callOptions);
    }

    /**
     */
    public void grpcCreate(ir.micser.geo.business.service.cityplace.stub.CityPlaceMicro.CreateRequestModel request,
        io.grpc.stub.StreamObserver<ir.micser.geo.business.service.cityplace.stub.CityPlaceMicro.CreateResponseModel> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGrpcCreateMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void grpcCommit(ir.micser.geo.business.service.cityplace.stub.CityPlaceMicro.TransactionModel request,
        io.grpc.stub.StreamObserver<ir.micser.geo.business.service.cityplace.stub.CityPlaceMicro.Empty> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGrpcCommitMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class CityPlaceBlockingStub extends io.grpc.stub.AbstractStub<CityPlaceBlockingStub> {
    private CityPlaceBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private CityPlaceBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected CityPlaceBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new CityPlaceBlockingStub(channel, callOptions);
    }

    /**
     */
    public ir.micser.geo.business.service.cityplace.stub.CityPlaceMicro.CreateResponseModel grpcCreate(ir.micser.geo.business.service.cityplace.stub.CityPlaceMicro.CreateRequestModel request) {
      return blockingUnaryCall(
          getChannel(), getGrpcCreateMethod(), getCallOptions(), request);
    }

    /**
     */
    public ir.micser.geo.business.service.cityplace.stub.CityPlaceMicro.Empty grpcCommit(ir.micser.geo.business.service.cityplace.stub.CityPlaceMicro.TransactionModel request) {
      return blockingUnaryCall(
          getChannel(), getGrpcCommitMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class CityPlaceFutureStub extends io.grpc.stub.AbstractStub<CityPlaceFutureStub> {
    private CityPlaceFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private CityPlaceFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected CityPlaceFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new CityPlaceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<ir.micser.geo.business.service.cityplace.stub.CityPlaceMicro.CreateResponseModel> grpcCreate(
        ir.micser.geo.business.service.cityplace.stub.CityPlaceMicro.CreateRequestModel request) {
      return futureUnaryCall(
          getChannel().newCall(getGrpcCreateMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<ir.micser.geo.business.service.cityplace.stub.CityPlaceMicro.Empty> grpcCommit(
        ir.micser.geo.business.service.cityplace.stub.CityPlaceMicro.TransactionModel request) {
      return futureUnaryCall(
          getChannel().newCall(getGrpcCommitMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_GRPC_CREATE = 0;
  private static final int METHODID_GRPC_COMMIT = 1;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final CityPlaceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(CityPlaceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_GRPC_CREATE:
          serviceImpl.grpcCreate((ir.micser.geo.business.service.cityplace.stub.CityPlaceMicro.CreateRequestModel) request,
              (io.grpc.stub.StreamObserver<ir.micser.geo.business.service.cityplace.stub.CityPlaceMicro.CreateResponseModel>) responseObserver);
          break;
        case METHODID_GRPC_COMMIT:
          serviceImpl.grpcCommit((ir.micser.geo.business.service.cityplace.stub.CityPlaceMicro.TransactionModel) request,
              (io.grpc.stub.StreamObserver<ir.micser.geo.business.service.cityplace.stub.CityPlaceMicro.Empty>) responseObserver);
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

  private static abstract class CityPlaceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    CityPlaceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return ir.micser.geo.business.service.cityplace.stub.CityPlaceMicro.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("CityPlace");
    }
  }

  private static final class CityPlaceFileDescriptorSupplier
      extends CityPlaceBaseDescriptorSupplier {
    CityPlaceFileDescriptorSupplier() {}
  }

  private static final class CityPlaceMethodDescriptorSupplier
      extends CityPlaceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    CityPlaceMethodDescriptorSupplier(String methodName) {
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
      synchronized (CityPlaceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new CityPlaceFileDescriptorSupplier())
              .addMethod(getGrpcCreateMethod())
              .addMethod(getGrpcCommitMethod())
              .build();
        }
      }
    }
    return result;
  }
}
