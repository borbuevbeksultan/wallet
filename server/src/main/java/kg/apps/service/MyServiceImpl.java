package kg.apps.service;

import kg.apps.MyServiceGrpc;
import kg.apps.Wallet;
import org.springframework.stereotype.Service;

@Service
public class MyServiceImpl extends MyServiceGrpc.MyServiceImplBase {

    @Override
    public void myMethod(Wallet.MyMessage request,
                         io.grpc.stub.StreamObserver<Wallet.MyMessage> responseObserver) {
        System.out.println(request.getBody());
        responseObserver.onNext(null);
        responseObserver.onCompleted();
    }

}
