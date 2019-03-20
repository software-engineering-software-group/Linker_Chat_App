/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.socketed;

//libraries
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 *
 * @author Shylesh Ragunayakam
 */

public class Client{
    private static String hostname;
    private static int hostnum;
    Socket socketclient;
    
    
    
  public static void communication(Socket client_socket, PrintWriter output1, BufferedReader input_1, BufferedReader input_2) throws IOException{
      String uinput,encrypt_in;
      
      
      
      
      while ((uinput = input_2.readLine()) != null) {
          encrypt_in = Encrypted.encrypt(uinput);
          //System.out.println(""+encrypt_in);
        output1.println(encrypt_in);
	System.out.println("" + input_1.readLine());
      }
  }
  
  public static void main(String[] args) throws IOException {
      String username;
    /**
     * @param args the command line arguments
     */
    if (args.length != 2) {
      System.err.println("Usage: java Client <host name> <port number>");
      System.exit(1);
    }
    
    hostname = args[0];
    hostnum = Integer.parseInt(args[1]);
    
    try (Socket clientsocket = new Socket(hostname, hostnum);
        PrintWriter output = new PrintWriter(clientsocket.getOutputStream(), true);
        BufferedReader input1 = new BufferedReader(new InputStreamReader(clientsocket.getInputStream()));
        BufferedReader input2 = new BufferedReader(new InputStreamReader(System.in))) {
      
        System.out.print("Enter username: ");
        username = input2.readLine();
        Thread.currentThread().setName(username);   //username changed
        output.println(username);   //sending username to server
        
        Client.communication(clientsocket,output,input1,input2);
        
      
      
    }catch (UnknownHostException e) {
       System.err.println("Don't know about host " + hostname);
       System.exit(1);
    }catch (IOException e) {
       System.err.println("Couldn't get I/O for the connection to " + hostname);
       System.exit(1);
    } 
  }
}

