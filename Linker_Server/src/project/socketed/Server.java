/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.socketed;

//libraries
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 * @author Shylesh Ragunayakam
 */

public class Server {
    private static int portnum;
    private static final int usernum = 20;    //number of users
    static ExecutorService execution = null;
    
    public static void communication(ServerSocket server_socket) throws IOException{
      
      execution = Executors.newFixedThreadPool(usernum);  //program can have #usernum clients
          
      
      
      while (true) {
        Socket client_socket = server_socket.accept();
        Runnable runner = new RequestHandler(client_socket);
        execution.execute(runner);
      
        
      }
             
    }
    
    public static void main(String[] args) throws IOException {
        /**
         * @param args the command line arguments
         */
        System.out.println("Linker launched");
        if (args.length < 1) {
            System.err.println("Usage: java Server <port number>");
            System.exit(1);
        }
        
        portnum = Integer.parseInt(args[0]);
        ExecutorService executor = null;
        
    try (ServerSocket serversocket = new ServerSocket(portnum);) {
        Server.communication(serversocket);
      
    }catch (IOException e) {
       System.out.println("Exception occurs when listing on port/connection: "+portnum);
       System.out.println(e.getMessage());
    } finally{
            if (execution != null) {
                execution.shutdown();
            }
        }
    }
}

