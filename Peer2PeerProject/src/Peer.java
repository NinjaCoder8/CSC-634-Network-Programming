import java.io.IOException;
import java.net.ServerSocket;
import java.net.UnknownHostException;

public class Peer {

	ServerSocket socket;
	String ipv4Address;
	int port;
	int connections;
	int max;
	int[] connectedPorts;
	
	public Peer(String host, int port) throws UnknownHostException, IOException{
		this.ipv4Address = host;
		this.port = port;
		this.socket = new ServerSocket(port);
		this.connectedPorts = new int[5];
		this.connections = 0;
		this.max = 3;
	}
	
	public void incrementConnections(int portID, int max){
		if(max != -1)
			this.max = max;
		
		connectedPorts[connections] = portID;
		this.connections++;
	}
	
	public int getConnections(){
		return this.connections;
	}
	
	public int getMaximumConnections(){
		return this.max;
	}
	
	public boolean canEstablishConnections(){
		return this.connections < this.max;
	}
	
	
}
