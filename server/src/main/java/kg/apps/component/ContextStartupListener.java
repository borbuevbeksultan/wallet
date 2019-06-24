package kg.apps.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class ContextStartupListener implements ApplicationListener<ApplicationStartedEvent> {

    private final GrpcServer grpcServer;

    @Autowired
    public ContextStartupListener(GrpcServer grpcServer) {
        this.grpcServer = grpcServer;
    }

    @Override
    public void onApplicationEvent(ApplicationStartedEvent event) {
        try {
            grpcServer.start();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

}