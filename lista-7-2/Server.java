import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Server class
 * @author Jacek Zub
 */
public class Server {
    final int port = 666;

    public static void main(String[] args) {
        ServerThread serverT = new ServerThread(666);
        serverT.start();
    }
}

/**
 * Used to handle connected client, used in {@link}Server
 * @author Jacek Zub
 */
class ServerThread extends Thread{
    final int port;
    final int number;
    static int count;

    ServerThread(int port){
        this.port = port;
        this.number = ++count;
    }

    @Override
    public void run() {
        try(ServerSocket serverSocket = new ServerSocket(port)){
            System.out.println("ServerThread"+"#"+number+" is listening on port "+port+"...");

            while(true){
                Socket socket = serverSocket.accept();
                System.out.println("("+port+") New client connected!");
                new ClientHandlingThread(socket).start();
            }
        }catch(IOException ex){
            System.out.println("Server exception: "+ex.getMessage());
            ex.printStackTrace();
        }
    }

}
