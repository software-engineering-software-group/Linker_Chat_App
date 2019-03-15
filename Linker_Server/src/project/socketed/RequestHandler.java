/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.socketed;

//libraries
import java.net.ServerSocket;
import java.net.Socket;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;



public class RequestHandler implements Runnable {
    private Socket client;

  
    public RequestHandler(Socket client) {
      this.client = client;
    }

    @Override
    public void run() {
        String input;
        
      try ( BufferedReader in_client = new BufferedReader(new InputStreamReader(client.getInputStream()));
	BufferedWriter write_client = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));) {
          
        Thread.currentThread().setName(in_client.readLine());       //changing username
	System.out.println("" + Thread.currentThread().getName() + " Connected");
            
	while ((input = in_client.readLine()) != null) {
	  input = input.replaceAll("[^A-Za-z0-9 ]", "");
          System.out.println("" + Thread.currentThread().getName() + " : " + input);
	  write_client.write("Sending : " + input);
	  write_client.newLine();
	  write_client.flush();
	}
      } catch (IOException e) {
           System.out.println("I/O exception: " + e);
        } catch (Exception ex) {
	   System.out.println("Exception in Thread Run. Exception : " + ex);
	  }
    }

}


