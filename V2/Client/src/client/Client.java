/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

/**
 *
 * @author Ashwin
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
public class Client implements Runnable{
    public JTextField textField, userField;
    public JTextArea textArea;
    public String login="Imed";
    BufferedWriter writer;
    BufferedReader reader;
    public Client(String l){
        login=l;        
        
        
        JFrame f=new JFrame("Linker");
        f.setSize(400,400);        
        
        JPanel p1=new JPanel();
        p1.setLayout(new BorderLayout());
            
        JPanel p2=new JPanel();
        p2.setLayout(new BorderLayout());        
        
        textField=new JTextField();
        p1.add(textField, BorderLayout.SOUTH);
        
        JButton sendMessageButton=new JButton("Send Message Below");
        p1.add(sendMessageButton, BorderLayout.EAST); 
        
        userField=new JTextField();
        p1.add(userField, BorderLayout.NORTH);
        
        JButton usernameButton=new JButton("Enter Username Above");
        p1.add(usernameButton, BorderLayout.WEST); 
        
        textArea=new JTextArea();
        p2.add(textArea, BorderLayout.CENTER);
        p2.add(p1, BorderLayout.SOUTH);
        f.setContentPane(p2);

           
        try{
                 Socket socketClient= new Socket("localhost",5555);
                 writer= new BufferedWriter(new OutputStreamWriter(socketClient.getOutputStream()));
                 
                 reader =new BufferedReader(new InputStreamReader(socketClient.getInputStream()));
                 
		    		
        }catch(Exception e){e.printStackTrace();}
    
    
        sendMessageButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ev){
                
                String s=login+" : "+textField.getText();  
                textField.setText("");
                System.out.println("plain: "+ s);
                s= Encrypted.encrypt(s);
                System.out.println("encrypted: "+ s);
                try{
                    writer.write(s);
                    writer.write("\r\n");
                    writer.flush(); 
                    }catch(Exception e){e.printStackTrace();}
            }
          }
        );
        
        usernameButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ev){
                
                String s=login+" : "+userField.getText();  
                userField.setText("");
                System.out.println("plain: "+ s);
                s= Encrypted.encrypt(s);
                System.out.println("encrypted: "+ s);
                try{
                    writer.write(s);
                    writer.write("\r\n");
                    writer.flush(); 
                    }catch(Exception e){e.printStackTrace();}
            }
          }
        );
        
        f.setVisible(true);    
        
 
    }
    public void run(){
             try{
                String serverMsg=""; 
                while((serverMsg = reader.readLine()) != null){
                    System.out.println("from server: " + serverMsg);
                    textArea.append(serverMsg+"\n");
                }
        }catch(Exception e){e.printStackTrace();}   
    }
}