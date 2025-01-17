import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Client {

    private static Socket socket;

    public static void main(String args[]) {
    	
        try{
            String host = "localhost";
            int port = 19999;
            InetAddress address = InetAddress.getByName(host);
            socket = new Socket(address, port);

            OutputStream os = socket.getOutputStream();
            OutputStreamWriter osw = new OutputStreamWriter(os);
            BufferedWriter bw = new BufferedWriter(osw);
            do{
            	System.out.println("hi");
	            String version = "1";
	            String opcode = "1";
	            String offset = "0000";
	            String fileID = "1100";
	            String filename = toBinary("file.txt");
	            String checksum = complement(version+opcode+offset+fileID+filename);
	
	            String request = version + opcode + checksum + fileID + offset + filename;
	            
	            String sendMessage = request + "\n";
	            bw.write(sendMessage);
	            bw.flush();
	            System.out.println("Message sent to the server !");
	
	            InputStream is = socket.getInputStream();
	            InputStreamReader isr = new InputStreamReader(is);
	            BufferedReader br = new BufferedReader(isr);
	            String line;
	            
	            while ((line = br.readLine() )!= null) {    
	            	System.out.println("Message received from the server : " +line);
	            	break;
	            }
            }while(true);
        }catch (Exception exception){
            exception.printStackTrace();
        }
        finally{
            try{
                socket.close();
            }
            catch(Exception e){
                e.printStackTrace();
            }
        }
    }
    
    public static String toBinary(String s){
    	  byte[] bytes = s.getBytes();
    	  StringBuilder binary = new StringBuilder();
    	  for (byte b : bytes)
    	  {
    	     int val = b;
    	     for (int i = 0; i < 8; i++)
    	     {
    	        binary.append((val & 128) == 0 ? 0 : 1);
    	        val <<= 1;
    	     }
    	  }
    	  
    	  return binary.toString();
    }
    
    public static String complement(String s){
    	String ans = "";
		for(int i=0; i< s.length(); i++){
			if(s.charAt(i) == '0')
				ans += "1";
			else 
				ans += 0;
		}
  	  
  	  	return ans;
  }
}