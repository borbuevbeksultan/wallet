package kg.apps.component;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import kg.apps.WalletServiceGrpc;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;

@Component
@Configuration
@RequiredArgsConstructor
public class GrpcServer {

    private final WalletServiceGrpc.WalletServiceImplBase walletServiceImplBase;

    private Server grpcServer;

    @PostConstruct
    public void initGrpcServer() {
        grpcServer = ServerBuilder
                .forPort(8888)
                .addService(walletServiceImplBase)
                .build();
    }

    public void start() throws IOException, InterruptedException {
        grpcServer.start();
        grpcServer.awaitTermination();
    }

}
