import java.io.IOException;
import java.net.UnknownHostException;

public class Part1 {

	private static final int peersTotal = 5;
	public static Peer peers[];
	
	public static void main(String[] args) throws UnknownHostException, IOException{
		Peer p;
		peers = new Peer[peersTotal];
		for(int i=1; i<=peersTotal; i++){
		      JThread thread = new JThread( "Thread-" + i, i);
		      thread.start();
		}
		
	}
}
