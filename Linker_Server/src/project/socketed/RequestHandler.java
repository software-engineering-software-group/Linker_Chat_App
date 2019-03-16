
package project.socketed;

//libraries
import java.net.ServerSocket;
import java.net.Socket;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

@SuppressWarnings("unused")
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
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");  
        Date timeonly = new Date();  
        Thread.currentThread().setName(in_client.readLine());       //changing username
	System.out.println( formatter.format(timeonly) + " Welcome " + Thread.currentThread().getName()); 
            
	while ((input = in_client.readLine()) != null) {
	  input = input.replaceAll("[^A-Za-z0-9 ]", "");
         SimpleDateFormat X = new SimpleDateFormat("HH:mm:ss");  
          Date Y = new Date(); 
          System.out.println(X.format(Y) + " " + Thread.currentThread().getName() + ": " + input);
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


