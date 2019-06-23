package kg.apps.service;

import kg.apps.MyServiceGrpc;
import kg.apps.Wallet;

public class MyServiceImpl extends MyServiceGrpc.MyServiceImplBase {

    @Override
    public void myMethod(Wallet.MyMessage request, io.grpc.stub.StreamObserver<Wallet.MyMessage> responseObserver) {
        super.myMethod(request, responseObserver);
    }

}
