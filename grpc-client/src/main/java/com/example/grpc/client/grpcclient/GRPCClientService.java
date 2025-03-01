package com.example.grpc.client.grpcclient;

import com.example.grpc.server.grpcserver.PingRequest;
import com.example.grpc.server.grpcserver.PongResponse;
import com.example.grpc.server.grpcserver.PingPongServiceGrpc;
import com.example.grpc.server.grpcserver.MatrixRequest;
import com.example.grpc.server.grpcserver.MatrixReply;
import com.example.grpc.server.grpcserver.MatrixServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Service;
@Service
public class GRPCClientService {
    public String ping() {
        	ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 9090)
                .usePlaintext()
                .build();        
		PingPongServiceGrpc.PingPongServiceBlockingStub stub
                = PingPongServiceGrpc.newBlockingStub(channel);        
		PongResponse helloResponse = stub.ping(PingRequest.newBuilder()
                .setPing("")
                .build());        
		channel.shutdown();        
		return helloResponse.getPong();
    }
    public String add(){
		ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost",9090)
		.usePlaintext()
		.build();
		MatrixServiceGrpc.MatrixServiceBlockingStub stub
		 = MatrixServiceGrpc.newBlockingStub(channel);
		MatrixReply A=stub.addBlock(MatrixRequest.newBuilder()
			.setA00(1)
			.setA01(2)
			.setA10(5)
			.setA11(6)
			.setB00(1)
			.setB01(2)
			.setB10(5)
			.setB11(6)
			.build());
	    	String resp=A.getC11()+A.getC00()+A.getC01()+A.getC10()+"";
		return resp;
    }
    public String mult(){
		    ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 9090)
		    .usePlaintext()
		    .build();
		    MatrixServiceGrpc.MatrixServiceBlockingStub stub
		     = MatrixServiceGrpc.newBlockingStub(channel);
		    MatrixReply result = stub.multiplyBlock(MatrixRequest.newBuilder()
		        .setA00(1)
		        .setA01(2)
		        .setA10(5)
		        .setA11(6)
		        .setB00(4)
		        .setB01(6)
		        .setB10(12)
		        .setB11(14)
		        .build());
	    	    String response= result.getC00()+result.getC01()+result.getC10()+result.getC11()+"";
		    channel.shutdown();
		    return response;
	}

}
