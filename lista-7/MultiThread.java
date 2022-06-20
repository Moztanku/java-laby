import java.io.BufferedReader;
import java.io.Console;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class MultiThread extends Thread{
    private enum TreeType{
        INTEGER,STRING,DOUBLE,UNSET
    };

    private static int count;
    private Socket socket;
    private String clientId;
    private TreeType treeType = TreeType.UNSET;
    private Boolean clientExit = false;

    public MultiThread(Socket socket){
        this.socket = socket;
        this.clientId = "Client"+(++count);
    }

    @Override
    public void run() {
        try {
            InputStream input = socket.getInputStream();
            BufferedReader in = new BufferedReader(new InputStreamReader(input));
   
            OutputStream output = socket.getOutputStream();
            PrintWriter out = new PrintWriter(output, true);
   
            String inputLine = "";
            String outputLine = "";

            out.println("<-: Connected succesfully, your id: "+clientId);
            out.println("<-: Choose Tree Type (String,Integer,Double)");

            Tree<> ts = null;
            Tree<Integer> ti = null;
            Tree<Double> td = null;

            while(treeType == TreeType.UNSET){
                inputLine = in.readLine();

                switch(inputLine){
                    case "String":
                        treeType = TreeType.STRING;
                        ts = new Tree<String>();
                        outputLine = "Tree type set to String.";
                        break;
                    case "Integer":
                        treeType = TreeType.INTEGER;
                        ti = new Tree<Integer>();
                        outputLine = "Tree type set to Integer.";
                        break;
                    case "Double":
                        treeType = TreeType.DOUBLE;
                        td = new Tree<Double>();
                        outputLine = "Tree type set to Double.";
                        break;
                    default:
                        outputLine = "Choose either String, Integer or Double!";
                        break;
                }
                System.out.println(clientId+"->\""+inputLine+"\"");
                out.println("<-: "+outputLine);
            }
            while(!clientExit){
                inputLine = in.readLine();
                String[] inputArr = inputLine.split(" ");

                switch(inputArr[0].toLowerCase()){
                    case "help":
                        outputLine = "help / draw / search x / insert x / remove x / exit.";
                        break;
                    case "draw":
                        switch(treeType){
                            case STRING:
                                try{
                                    outputLine = ts.draw();
                                }catch(Exception e){
                                    outputLine = e.getMessage();
                                    System.out.println(clientId+"<-\""+e.getMessage()+"\"");
                                }
                                break;
                            case INTEGER:
                                try{
                                    outputLine = ti.draw();
                                }catch(Exception e){
                                    outputLine = e.getMessage();
                                    System.out.println(clientId+"<-\""+e.getMessage()+"\"");
                                }
                                break;
                            case DOUBLE:
                                try{
                                    outputLine = td.draw();
                                }catch(Exception e){
                                    outputLine = e.getMessage();
                                    System.out.println(clientId+"<-\""+e.getMessage()+"\"");
                                }
                                break;
                            default:
                                break;
                        }
                        break;
                    case "search":
                        switch(treeType){
                            case STRING:
                                try {
                                    outputLine = ts.search(inputArr[1])?inputArr[1]+" found on tree.":inputArr[1]+" not found on tree.";
                                } catch (Exception e) {
                                    outputLine = e.getMessage();
                                    System.out.println(clientId+"<-\""+e.getMessage()+"\"");
                                }
                                break;
                            case INTEGER:
                                try{
                                    Integer temp = Integer.parseInt(inputArr[1]);
                                    outputLine = ti.search(temp)?inputArr[1]+" found on tree.":inputArr[1]+" not found on tree.";
                                }catch(Exception e){
                                    outputLine = e.getMessage();
                                    System.out.println(clientId+"<-\""+e.getMessage()+"\"");
                                }
                                break;
                            case DOUBLE:
                                try{
                                    Double temp = Double.parseDouble(inputArr[1]);
                                    outputLine = td.search(temp)?inputArr[1]+" found on tree.":inputArr[1]+" not found on tree.";
                                }catch(Exception e){
                                    outputLine = e.getMessage();
                                    System.out.println(clientId+"<-\""+e.getMessage()+"\"");
                                }
                                break;
                            default:
                                break;
                        }
                        break;
                    case "insert":
                        switch(treeType){
                            case STRING:
                                try{
                                    ts.insert(inputArr[1]);
                                    outputLine = ts.draw();
                                }catch(Exception e){
                                    outputLine = e.getMessage();
                                    System.out.println(clientId+"<-\""+e.getMessage()+"\"");
                                }
                                break;
                            case INTEGER:
                                try{
                                    Integer temp = Integer.parseInt(inputArr[1]);
                                    ti.insert(temp);
                                    outputLine = ti.draw();
                                }catch(Exception e){
                                    outputLine = e.getMessage();
                                    System.out.println(clientId+"<-\""+e.getMessage()+"\"");
                                }
                                break;
                            case DOUBLE:
                                try{
                                    Double temp = Double.parseDouble(inputArr[1]);
                                    td.insert(temp);
                                    outputLine = td.draw();
                                }catch(Exception e){
                                    outputLine = e.getMessage();
                                    System.out.println(clientId+"<-\""+e.getMessage()+"\"");
                                }
                                break;
                            default:
                                break;
                        }
                        break;
                    case "remove":
                        switch(treeType){
                            case STRING:
                                try{
                                    ts.delete(inputArr[1]);
                                    outputLine = ts.draw();
                                }catch(Exception e){
                                    outputLine = e.getMessage();
                                    System.out.println(clientId+"<-\""+e.getMessage()+"\"");
                                }
                                break;
                            case INTEGER:
                                try{
                                    Integer temp = Integer.parseInt(inputArr[1]);
                                    ti.delete(temp);
                                    outputLine = ti.draw();
                                }catch(Exception e){
                                    outputLine = e.getMessage();
                                    System.out.println(clientId+"<-\""+e.getMessage()+"\"");
                                }
                                break;
                            case DOUBLE:
                                try{
                                    Double temp = Double.parseDouble(inputArr[1]);
                                    td.delete(temp);
                                    outputLine = td.draw();
                                }catch(Exception e){
                                    outputLine = e.getMessage();
                                    System.out.println(clientId+"<-\""+e.getMessage()+"\"");
                                }
                                break;
                            default:
                                break;
                        }
                        break;
                    case "exit":
                        clientExit = true;
                        outputLine = "Goodbye";
                        break;
                    default:
                        outputLine = "Invalid option, type help for help.";
                        break;
                }

                System.out.println(clientId+"->\""+inputLine+"\"");
                out.println("<-: "+outputLine);
            }
            socket.close();
       } catch (IOException ex) {
           System.out.println("Server exception: " + ex.getMessage());
           ex.printStackTrace();
       }
    }
}
