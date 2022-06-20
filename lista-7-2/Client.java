import java.io.BufferedReader;
import java.io.Console;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Class responsible for client 
 * @author Jacek Zub
 */
public class Client {

    public static void main(String[] args) {

        Console console = System.console();
        String input = "";
        int port = 666;
        String treeType = "";

        while(treeType.equals("")){ //  Checking which tree type client want to use
            input = console.readLine("Choose tree type you want to use (String/Integer/Double): ");
            if(input.toUpperCase().equals("STRING"))
                treeType = "STRING";
            else if(input.toUpperCase().equals("INTEGER"))
                treeType = "INTEGER";
            else if(input.toUpperCase().equals("DOUBLE"))
                treeType = "DOUBLE";
        }

        try {
            Socket socket = new Socket("localhost",port);
            PrintWriter out = new PrintWriter(socket.getOutputStream(),true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream())); //  Connecting to input/output streams

            System.out.println("<-: "+in.readLine());  // Initial message
            out.println(treeType);  //  Sending treetype to server
            do{
                input = console.readLine("->: ");
                out.println(input);
                System.out.println("<-: "+in.readLine());
            }while(!input.toUpperCase().equals("EXIT"));    //  Communication loop
            socket.close();
        } catch (UnknownHostException ex) {
            System.out.println("Server not found: " + ex.getMessage());
 
        } catch (IOException ex) {
            System.out.println("I/O error: " + ex.getMessage());
        }
    }
}
