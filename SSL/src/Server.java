import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.SSLServerSocketFactory;

public class Server {
    
    static final int port = 5000;

    public static void main(String[] args) throws IOException {
        
        SSLServerSocketFactory sslServerSocketFactory = (SSLServerSocketFactory)SSLServerSocketFactory.getDefault();
        
        ServerSocket sslServerSocket = sslServerSocketFactory.createServerSocket(port);
        System.out.println("SSL ServerSocket started");
        System.out.println(sslServerSocket.toString());
        
        Socket socket = sslServerSocket.accept();
        System.out.println("ServerSocket accepted");
        
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        InputStreamReader isr = new InputStreamReader(socket.getInputStream());
        BufferedReader bufferedReader = new BufferedReader(isr); 
        String line;
        
        while((line = bufferedReader.readLine()) != null){
            System.out.println(line);
            out.println(line);
        }
        
        System.out.println("Closed");
    }
    
}