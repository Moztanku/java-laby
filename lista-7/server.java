import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class server {
    public static void main(String[] args) {
        try(ServerSocket serverSocket = new ServerSocket(4444)){
            System.out.println("Server is listening on port 4444...");
            while(true){
                Socket socket = serverSocket.accept();
                System.out.println("New client connected.");

                new MultiThread(socket).start();
            }
        }catch(IOException ex){
            System.err.println(ex);
            ex.printStackTrace();
        }
    }
}
