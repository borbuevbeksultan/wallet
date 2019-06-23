package kg.apps;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;

import static kg.apps.MyServiceGrpc.newBlockingStub;

public class App {
    public static void main(String[] args) {

        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 8888)
                .usePlaintext().build();

        MyServiceGrpc.MyServiceBlockingStub myServiceBlockingStub = newBlockingStub(channel);

        Wallet.MyMessage myMessage = Wallet.MyMessage.newBuilder().setBody("body").build();

        try {
            Wallet.MyMessage myMessage1 = myServiceBlockingStub.myMethod(myMessage);
            System.out.println(myMessage1.getBody());
        } catch (StatusRuntimeException e) {
            System.out.println("RPC failed: " + e.getStatus());
        }

    }
}
