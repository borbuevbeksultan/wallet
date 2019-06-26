package kg.apps.protoservice;

import io.grpc.stub.StreamObserver;
import kg.apps.Wallet;
import kg.apps.WalletServiceGrpc;
import kg.apps.endpoint.WalletEndpoint;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class WalletProtoService extends WalletServiceGrpc.WalletServiceImplBase {

    private final WalletEndpoint walletEndpoint;

    @Override
    public void deposit(Wallet.Deposit request, StreamObserver<Wallet.Response> responseObserver) {
        walletEndpoint.deposit(request);
        responseObserver.onNext(Wallet.Response.newBuilder().build());
        responseObserver.onCompleted();
    }

    @Override
    public void withdraw(Wallet.Withdraw request, StreamObserver<Wallet.Response> responseObserver) {
        walletEndpoint.withdraw(request);
        responseObserver.onNext(Wallet.Response.newBuilder().build());
        responseObserver.onCompleted();
    }

    @Override
    public void balance(Wallet.User request, StreamObserver<Wallet.Balances> responseObserver) {
        Wallet.Balances balances = walletEndpoint.balance(request);
        responseObserver.onNext(balances);
        responseObserver.onCompleted();
    }

}
