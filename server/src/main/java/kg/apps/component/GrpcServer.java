package kg.apps.component;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import kg.apps.service.MyServiceImpl;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;

@Component
public class GrpcServer {

    private Server grpcServer;

    @PostConstruct
    public void initGrpcServer() throws InterruptedException, IOException {
        grpcServer = ServerBuilder
                .forPort(8888)
                .addService(new MyServiceImpl())
                .build();
        grpcServer.start();
        grpcServer.awaitTermination();
    }

}
