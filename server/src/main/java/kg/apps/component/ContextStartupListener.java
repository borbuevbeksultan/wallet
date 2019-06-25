package kg.apps.component;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class ContextStartupListener implements ApplicationListener<ApplicationStartedEvent> {

    private final GrpcServer grpcServer;

    @Override
    public void onApplicationEvent(ApplicationStartedEvent event) {
        try {
            grpcServer.start();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

}
