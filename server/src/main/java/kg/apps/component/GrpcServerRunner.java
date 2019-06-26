package kg.apps.component;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import kg.apps.WalletServiceGrpc;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@Profile("!test")
@RequiredArgsConstructor
public class GrpcServerRunner implements ApplicationRunner {

    private final WalletServiceGrpc.WalletServiceImplBase walletServiceImplBase;

    private Server grpcServer;

    @PostConstruct
    public void initGrpcServer() {
        grpcServer = ServerBuilder
                .forPort(8888)
                .addService(walletServiceImplBase)
                .build();
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        grpcServer.start();
        grpcServer.awaitTermination();
    }

}
