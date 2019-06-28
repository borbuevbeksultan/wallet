package kg.apps.protoservice;

import io.grpc.stub.StreamObserver;
import kg.apps.UserServiceGrpc;
import kg.apps.Wallet;
import kg.apps.model.User;
import kg.apps.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserProtoService extends UserServiceGrpc.UserServiceImplBase {

    private final UserService userService;

    @Override
    public void create(Wallet.UserCreationRequest request, StreamObserver<Wallet.UserCreationResponse> responseObserver) {
        User createdUser = userService.createWholeUser(request.getEmail());
        responseObserver.onNext(Wallet.UserCreationResponse
                .newBuilder()
                .setUserId(createdUser.getId())
                .setEmail(createdUser.getEmail())
                .build());
        responseObserver.onCompleted();
    }

}
