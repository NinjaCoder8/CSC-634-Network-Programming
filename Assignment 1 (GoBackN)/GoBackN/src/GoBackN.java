import java.util.Random;

public class GoBackN {

	static int packetSize = 12;
	static int windowSize = 6;
	static int currentPckt = 0;
	static Random random;

	public static void main(String[] args){
		random = new Random();
		while(true){
			int[] pkt = generateRandomPacket();
			int[] dataSent = sendPackets(pkt); //sender 
			//receivePacket(dataSent); //receiver
			
		}
		
	}

	private static int[] sendPackets(int[] pkt) {
		int[] data = new int[windowSize];
		int counter = 0;
		for(int i=currentPckt; i<(currentPckt + windowSize); i++){
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