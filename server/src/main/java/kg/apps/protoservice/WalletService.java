package kg.apps.protoservice;

import io.grpc.stub.StreamObserver;
import kg.apps.Wallet;
import kg.apps.WalletServiceGrpc;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class WalletService extends WalletServiceGrpc.WalletServiceImplBase {

    @Override
    public void deposit(Wallet.Deposit request, StreamObserver<Wallet.Response> responseObserver) {
        log.info("Deposit: userId = {}, amount = {}, currency = {}", request.getUserId(), request.getAmount(), request.getCurrency());
        responseObserver.onNext(Wallet.Response.newBuilder().build());
        responseObserver.onCompleted();
    }

    @Override
    public void withdraw(Wallet.Withdraw request, StreamObserver<Wallet.Response> responseObserver) {
        log.info("Withdraw: userId = {}, amount = {}, currency = {}", request.getUserId(), request.getAmount(), request.getCurrency());
        responseObserver.onNext(Wallet.Response.newBuilder().build());
        responseObserver.onCompleted();
    }

}
