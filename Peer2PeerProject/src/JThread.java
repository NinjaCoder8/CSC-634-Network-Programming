import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class JThread implements Runnable {
   private Thread t;
   private int threadID;
   private Peer peer;
   private String threadName;
   
   public JThread(String name, int ID) {
      threadName = name;
      threadID = ID;
      System.out.println("Creating " +  threadName );
   }
   
   public void run(){
      System.out.println("Running " +  threadName );
		try {
			Peer p = new Peer("localhost", threadID);
			peer = p;
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
	}
      try {
         while(true){
            //System.out.println("Thread: " + threadName + ", " + i);

            Thread.sleep(50);
         }
      } catch (InterruptedException e) {
         System.out.println("Thread " +  threadName + " interrupted.");
      }
      System.out.println("Thread " +  threadName + " exiting.");
   }
   
   public void start () throws UnknownHostException, IOException {
      System.out.println("Starting " +  threadName );
      if (t == null) {
         t = new Thread (this, threadName);
         t.start ();
      }
   }

	public void connectPeer(int port, int max) throws UnknownHostException, IOException {
		 if(this.peer.canEstablishConnections()){
	         InetAddress address = InetAddress.getByName("localhost");
	         Socket socket = new Socket(address, port);
	         this.peer.incrementConnections(port, max);
	         System.out.println("Connection established between port " + threadID + " and port " + port);
		 }else{
	         System.out.println("Maximum Connections Reached For Peer on Port " + threadID);
		 }
	}
	
	public Peer getPeer(){
		return this.peer;
	}
	
	public int getPeerID(){
		return this.threadID;
	}
}