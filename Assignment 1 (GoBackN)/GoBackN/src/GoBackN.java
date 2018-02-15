import java.util.Random;

public class GoBackN {

	static Random random;
	static final int MAX_WINDOW_SIZE=6;
	static int packetSize = 12;
	
	static int windowSize;
	static int currentPckt;
	static int clock;
	static int congestionFlag;
	
	public static void main(String[] args){
		random = new Random();
		int[] pkt;
		int[] dataSent;
		boolean ack;
		clock = 0;
		while(true){
			pkt = generateRandomPacket(); //generate random packet forever
			currentPckt = 0;
			congestionFlag =0;
			windowSize = MAX_WINDOW_SIZE;
			System.out.println("Packet Generated at clock : " + clock);
			printPacket(pkt);
			System.out.println("-------------------------------------------");
			while(currentPckt < packetSize){ //repeat until whole packet is delivered
				dataSent = sendPackets(pkt); //sender 
				ack = receivePacket(dataSent); //receiver
				if(!ack){ //packet loss (same ack received)
					System.out.println("Packet Loss at clock: " + clock + " :(");
					congestionFlag++;
				}else{
					currentPckt++; //slide the window
				}
				
				if(congestionFlag > 2){
					windowSize = (windowSize > 1) ? windowSize/2 : windowSize;
					congestionFlag = 0;
				}else{
					windowSize = (windowSize < MAX_WINDOW_SIZE ) ? windowSize+1 : windowSize;
				}
				
				clock++;
			}
			System.out.println("-------------------------------------------");
			System.out.println("The following packet is fully recived at clock: " + clock);
			printPacket(pkt);
			System.out.println("---------------END PACKET--------------");
			break;
		}
		
	}

	private static void printPacket(int[] pkt) {
		for(int i = 0; i<pkt.length; i++){
			System.out.print(pkt[i] + " ");
		}
		System.out.println();
		
	}

	private static boolean receivePacket(int[] dataSent) {
		float x = random.nextFloat();
		float range = 0;
		range = (float) ((congestionFlag > 2) ? 0.2 : 0.6);
		if(x < range){
			return false;
		}else{
			System.out.println("The Following Packet Has Been Received: " + dataSent[0]);
			return true;
		}
	}

	private static int[] sendPackets(int[] pkt) {
		int[] data = new int[windowSize];
		int counter = 0;
		for(int i=currentPckt; i<(currentPckt + windowSize) && i<pkt.length; i++){
			System.out.println("This Packet Has Been Sent: " + pkt[i]);
			data[counter] = pkt[i];
			counter++;
		}
		return data;
	}

	private static int[] generateRandomPacket() {
		int[] pkt = new int[packetSize];
		for(int i=0; i<packetSize; i++)
			pkt[i] = random.nextInt(10);
		return pkt;
	}
	
}
