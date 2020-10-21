package com.motaharinia.msutility.grpc;

import com.motaharinia.msutility.customexception.CustomException;
import io.grpc.*;

/**
 * User: https://github.com/motaharinia<br>
 * Date: 2020-07-11<br>
 * Time: 13:04:06<br>
 * Description:<br>
 */
public class GrpcExceptionHandler implements ServerInterceptor {

    @Override
    public <ReqT, RespT> ServerCall.Listener<ReqT> interceptCall(ServerCall<ReqT, RespT> serverCall, Metadata metadata, ServerCallHandler<ReqT, RespT> serverCallHandler) {
        ServerCall.Listener<ReqT> listener = serverCallHandler.startCall(serverCall, metadata);
        return new ExceptionHandlingServerCallListener<>(listener, serverCall, metadata);
    }

    private class ExceptionHandlingServerCallListener<ReqT, RespT> extends ForwardingServerCallListener.SimpleForwardingServerCallListener<ReqT> {
        private ServerCall<ReqT, RespT> serverCall;
        private Metadata metadata;

        ExceptionHandlingServerCallListener(ServerCall.Listener<ReqT> listener, ServerCall<ReqT, RespT> serverCall, Metadata metadata) {
            super(listener);
            this.serverCall = serverCall;
            this.metadata = metadata;
        }

        @Override
        public void onHalfClose() {
            try {
                super.onHalfClose();
            } catch (RuntimeException ex) {
                handleException(ex, serverCall, metadata);
                throw ex;
            }
        }

        @Override
        public void onReady() {
            try {
                super.onReady();
            } catch (RuntimeException ex) {
                handleException(ex, serverCall, metadata);
                throw ex;
            }
        }

        private void handleException(RuntimeException exception, ServerCall<ReqT, RespT> serverCall, Metadata metadata) {
            if (exception instanceof CustomException) {
                CustomException customException = (CustomException) exception;
//                System.out.println("exception.getMessage(): "+customException.getMessage());
//                System.out.println("exception.getClass(): "+customException.getClass());
//                System.out.println("exception.getLocalizedMessage(): "+customException.getLocalizedMessage());
//                System.out.println("exception.getCause(): "+customException.getCause());
//                System.out.println("metadata.toString(): "+metadata.toString());
                serverCall.close(Status.UNKNOWN.withDescription(customException.getExceptionMessage() + ":::" + customException.getExceptionDescription() + ":::" + customException.getStackTrace()[0].toString()), metadata);
            } else {
                serverCall.close(Status.UNKNOWN, metadata);
            }
        }



    }
}