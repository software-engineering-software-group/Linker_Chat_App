/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

/**
 *
 * @author Ashwin
 */
import java.io.*;
import java.net.*;
import java.util.*;
import java.text.SimpleDateFormat;
import java.util.Date;
        
class Server implements Runnable{
            Socket connectionSocket;     
            public static Vector clients=new Vector();
            public Server(Socket s){
                    try{
                            System.out.println("Client Got Connected  " );
                            connectionSocket=s;
                    }catch(Exception e){e.printStackTrace();}
            }     
            public void run(){
                    try{
                            BufferedReader reader =
                                            new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
                            BufferedWriter writer= 
                                            new BufferedWriter(new OutputStreamWriter(connectionSocket.getOutputStream()));
                            
                            clients.add(writer); 
                            
                        while(true){
                            String userInput = reader.readLine().trim();
                            //System.out.println("Encrypted: "+data1);      
                            userInput = Decrypted.decrypt(userInput);
                            System.out.println("Received : "+userInput);      
                            
                            for (int i=0;i<clients.size();i++){
                               try{
                                    BufferedWriter bw= (BufferedWriter)clients.get(i);
                                    
                                    SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");  
                                    Date timeonly = new Date();  
                                    bw.write(formatter.format(timeonly) + " " + userInput);
                                    bw.write("\r\n");
                                    bw.flush();
                                }catch(Exception e){e.printStackTrace();}
                            }
                        }
                    }catch(Exception e){e.printStackTrace();}
        }
        public static void main(String argv[]) throws Exception{
            System.out.println("Linker Server is Running. Waiting for Clients." );
            ServerSocket mysocket = new ServerSocket(5555);
                while(true){
                Socket sock = mysocket.accept();
                Server server=new Server(sock);
                Thread serverThread=new Thread(server);
                serverThread.start();
                }
            }
}