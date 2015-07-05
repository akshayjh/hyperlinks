import java.io.*;
import java.net.*;
import java.util.Scanner;
public class Provider{
	public String a;
	public static Scanner reader = new Scanner(System.in);
    ServerSocket providerSocket;
    Socket connection = null;
    ObjectOutputStream out;
    ObjectInputStream in;
    String message;
    Provider(){}
    void run()
    {
        try{
            //1. creating a server socket
            providerSocket = new ServerSocket(2100, 10);
            //2. Wait for connection
            System.out.println("Waiting for connection");
            connection = providerSocket.accept();
            System.out.println("Connection received from " + connection.getInetAddress().getHostName());
            //3. get Input and Output streams
            out = new ObjectOutputStream(connection.getOutputStream());
            out.flush();
            in = new ObjectInputStream(connection.getInputStream());
            sendMessage("Connection successful");
            //4. The two parts communicate via the input and output streams
            do{
                try{
                    message = (String)in.readObject();
                    a = reader.nextLine();
                    message = a;
                    System.out.println("client>" + message);
                    if (message.equals("bye"))
                        sendMessage("bye");
                    else {
                    	sendMessage(a);
                    }
                }
                catch(ClassNotFoundException classnot){
                    System.err.println("Data received in unknown format");
                }
            }while(!message.equals("bye"));
        }
        catch(IOException ioException){
            ioException.printStackTrace();
        }
        finally{
            //4: Closing connection
            try{
                in.close();
                out.close();
                providerSocket.close();
            }
            catch(IOException ioException){
                ioException.printStackTrace();
            }
        }
    }
    void sendMessage(String msg)
    {
        try{
            out.writeObject(msg);
            out.flush();
            System.out.println("server>" + msg);
        }
        catch(IOException ioException){
            ioException.printStackTrace();
        }
    }
    public static void main(String args[])
    {
        Provider server = new Provider();
        while(true){
            server.run();
        }
    }
}
