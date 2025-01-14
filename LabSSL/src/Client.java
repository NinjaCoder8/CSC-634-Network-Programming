import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import javax.net.ssl.SSLServerSocketFactory;
import javax.net.ssl.SSLSocketFactory;

public class Client {

	static final int port=8000;
	
	public static void main(String[] args) throws UnknownHostException, IOException{
		
		SSLSocketFactory sf = (SSLSocketFactory) SSLSocketFactory.getDefault();
		Socket socket = sf.createSocket("localhost", port);
		
		PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
		InputStreamReader isr = new InputStreamReader(socket.getInputStream());
		BufferedReader br = new BufferedReader(isr);
		
		Scanner scanner = new Scanner(System.in);
		
		while(true){
			System.out.println("Enter Something:");
			String inputLine = scanner.nextLine();
			out.println(inputLine);
			System.out.println(br.readLine());
		}
		
	}
}
