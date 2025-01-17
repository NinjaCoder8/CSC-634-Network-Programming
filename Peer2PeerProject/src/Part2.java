import java.io.IOException;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Part2 {

	static Peer[][] dht;
	static int maxPeers = 10;
	
	public static void main(String[] args) throws UnknownHostException, IOException{
		Scanner scan = new Scanner(System.in);
		dht = new Peer[maxPeers][maxPeers];
		for(int i=0; i<maxPeers; i++)
			dht[i] = null;
		
		System.out.println("Adding a new node:");
		System.out.print("Please enter the Peer IP Address: ");
		String ip = scan.next();
		System.out.print("Please enter the Peer Port Numnber (if empty, will be added to know port): ");
		String port = scan.next();

	}
	
	public int getSuccessor(int index){
		for(int i = index+1; i< dht.length; i++)
			if(dht[i] != null)
				return i;
		
		for(int i = 0; i< index; i++)
			if(dht[i] != null)
				return i;
		
		return -1;
		
	}
	
	public int getPredecessor(int index){
		for(int i = index-1; i > 0; i--)
			if(dht[i] != null)
				return i;
		
		for(int i = dht.length-1; i > index; i--)
			if(dht[i] != null)
				return i;
		
		return -1;
		
	}
}
