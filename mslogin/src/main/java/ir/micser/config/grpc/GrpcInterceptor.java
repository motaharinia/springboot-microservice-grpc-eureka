package ir.micser.config.grpc;

import io.grpc.Metadata;
import io.grpc.ServerCall;
import io.grpc.ServerCallHandler;
import io.grpc.ServerInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * User: https://github.com/motaharinia<br>
 * Date: 2020-07-01<br>
 * Time: 16:47:12<br>
 * Description: بازبینی کننده فعالیتهای مایکروسرویس
 */
public class GrpcInterceptor implements ServerInterceptor {

    private static final Logger log = LoggerFactory.getLogger(GrpcInterceptor.class);

    @Override
    public <ReqT, RespT> ServerCall.Listener<ReqT> interceptCall(ServerCall<ReqT, RespT> serverCall, Metadata metadata, ServerCallHandler<ReqT, RespT> serverCallHandler) {
        log.info(serverCall.getMethodDescriptor().getFullMethodName());
        return serverCallHandler.startCall(serverCall, metadata);
    }

}
