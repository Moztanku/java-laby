import java.io.BufferedReader;
import java.io.Console;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 *  Thread handling client
 * @author Jacek Zub
 */
public class ClientHandlingThread extends Thread {
    private Socket socket;
    private String treeType;
    
    private Tree<String> ts = null;
    private Tree<Integer> ti = null;
    private Tree<Double> td = null;

    ClientHandlingThread(Socket socket){
        this.socket = socket;
    };


    @Override
    public void run() {
        try{

            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(),true);   //  Setting input/output streams

            String[] input = {""};
            String output = "You've connected succesfully.";
            

            out.println(output);    //  Sending initial message
            treeType = in.readLine();   //  Getting treetype from client
            
            if(treeType.equals("STRING"))
                ts = new Tree<String>();
            else if(treeType.equals("INTEGER"))
                ti = new Tree<Integer>();
            else 
                td = new Tree<Double>();    //  Setting treetype to one client selected


            do{
                input = in.readLine().split(" ");
                output = clientAction(input);   //    Handling client input
                out.println(output);
            }while(!input[0].toUpperCase().equals("EXIT"));
            socket.close();
        } catch (IOException ex) {
           System.out.println("("+socket.getLocalPort()+") Server exception: " + ex.getMessage());
           //ex.printStackTrace();
       }
    }

    private String clientAction(String[] input){
        switch(input[0].toLowerCase()){
            case "search":
                if(input.length<=1)
                    return "Parameter is required!";
                else
                    return searchAction(input[1]);
            case "insert":
                if(input.length<=1)
                    return "Parameter is required!";
                else
                    return insertAction(input[1]);
            case "delete":
                if(input.length<=1)
                    return "Parameter is required!";
                else
                    return deleteAction(input[1]);
            case "draw":
                    return drawAction();
            case "exit":
                    return "Goodbye.";
            default:
                    return "Available options: search x\\insert x\\delete x\\draw\\exit";
        }
    };

    private String searchAction(String input){  //  If client selected search
        switch(treeType){
            case "STRING":
                return ts.search(input)?"Element found!":"Element not found!";
            case "INTEGER":
                try{
                    Integer x = Integer.parseInt(input);
                    return ti.search(x)?"Element found!":"Element not found!";
                }catch(NumberFormatException ex){
                    return "Invalid parameter!";
                }
            case "DOUBLE":
                try{
                    Double x = Double.parseDouble(input);
                    return td.search(x)?"Element found!":"Element not found!";
                }catch(NumberFormatException ex){
                    return "Invalid parameter!";
                }
            default:
                return "Unexpected behaviour";
        }
    }

    private String insertAction(String input) { //  If client selected insert
        switch(treeType){
            case "STRING":
                ts.insert(input);
                break;
            case "INTEGER":
                try{
                    Integer x = Integer.parseInt(input);
                    ti.insert(x);
                }catch(NumberFormatException ex){
                    return "Invalid parameter!";
                }
                break;
            case "DOUBLE":
                try{
                    Double x = Double.parseDouble(input);
                    td.insert(x);
                }catch(NumberFormatException ex){
                    return "Invalid parameter!";
                }
                break;
            default:
                return "Unexpected behaviour";
        }
        return drawAction();
    }
    private String deleteAction(String input) { //  If client selected delete
        switch(treeType){
            case "STRING":
                ts.delete(input);
                break;
            case "INTEGER":
                try{
                    Integer x = Integer.parseInt(input);
                    ti.delete(x);
                }catch(NumberFormatException ex){
                    return "Invalid parameter!";
                }
                break;
            case "DOUBLE":
                try{
                    Double x = Double.parseDouble(input);
                    td.delete(x);
                }catch(NumberFormatException ex){
                    return "Invalid parameter!";
                }
                break;
            default:
                return "Unexpected behaviour";
        }
        return drawAction();
    }
    private String drawAction() {   //  If client selected draw
        switch(treeType){
            case "STRING":
                return ts.draw();
            case "INTEGER":
                return ti.draw();
            case "DOUBLE":
                return td.draw();
            default:
                return "Unexpected behaviour";
        }
    }
}
