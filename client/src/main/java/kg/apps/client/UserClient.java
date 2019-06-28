package kg.apps.client;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import kg.apps.UserServiceGrpc;
import kg.apps.Wallet;

import java.util.UUID;

public class UserClient {

    private ManagedChannel channel;
    private UserServiceGrpc.UserServiceBlockingStub blockingStub;

    public UserClient() {
        channel = ManagedChannelBuilder.forAddress("localhost", 8888)
                .usePlaintext().build();
        blockingStub = UserServiceGrpc.newBlockingStub(channel);
    }

    public Wallet.UserCreationResponse create() {
        Wallet.UserCreationRequest userCreationRequest = Wallet.UserCreationRequest
                .newBuilder()
                .setEmail(UUID.randomUUID().toString())
                .build();

        return blockingStub.create(userCreationRequest);
    }

}
