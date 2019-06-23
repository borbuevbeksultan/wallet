package kg.apps.component;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import kg.apps.service.MyServiceImpl;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;

@Component
@Configuration
public class GrpcServer {

    private Server grpcServer;

    private final MyServiceImpl myService;

    public GrpcServer(MyServiceImpl myService) {
        this.myService = myService;
    }

    @PostConstruct
    public void initGrpcServer() {
        grpcServer = ServerBuilder
                .forPort(8888)
                .addService(myService)
                .build();
    }

    public void start() throws IOException, InterruptedException {
        grpcServer.start();
        grpcServer.awaitTermination();
    }

}
