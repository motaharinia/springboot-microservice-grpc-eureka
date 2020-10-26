package ir.micser.login.business.service.authorization;


import com.google.protobuf.Int32Value;
import com.motaharinia.msutility.customexception.BusinessException;
import com.motaharinia.msutility.grpc.GrpcExceptionHandler;
import io.grpc.stub.StreamObserver;
import ir.micser.login.business.service.authorization.stub.AuthorizationGrpc;
import ir.micser.login.business.service.authorization.stub.AuthorizationMicro.*;
import net.devh.boot.grpc.server.service.GrpcService;

import java.util.Date;
import java.util.Optional;

/**
 * User: https://github.com/motaharinia<br>
 * Date: 2020-07-01<br>
 * Time: 14:02:31<br>
 * Description:<br>
 */
@GrpcService(interceptors = { GrpcExceptionHandler.class })
public class AuthorizationServiceImpl extends AuthorizationGrpc.AuthorizationImplBase implements AuthorizationService {
    /**
     * @param checkAccessRequestModel
     * @param responseObserver
     */
    @Override
    public void checkAccess(CheckAccessRequestModel checkAccessRequestModel, StreamObserver<CheckAccessResponseModel> responseObserver) {
        if (checkAccessRequestModel.getUrl().isBlank()) {
            throw new BusinessException(getClass(), AuthorizationBusinessExceptionKeyEnum.URL_IS_EMPTY, "url");
        }
        Integer resultCode=null;
        CheckAccessResponseModel.Builder builder = CheckAccessResponseModel.newBuilder();
        Optional.ofNullable(new Date().getTime()).ifPresent(builder::setCheckDate);
        Optional.ofNullable(resultCode).ifPresent(builder::setResultCode);
        Optional.ofNullable(true).ifPresent(builder::setResult);
        responseObserver.onNext(builder.build());
        responseObserver.onCompleted();
    }
}
