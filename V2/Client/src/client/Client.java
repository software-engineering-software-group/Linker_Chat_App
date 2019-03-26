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
    public JScrollPane scrollBar;
    //public String login="User";
    public String login=Thread.currentThread().getName();
    public String elogin;
    BufferedWriter writer;
    BufferedReader reader;
    public Client(String l){

        
        JFrame clientGUI=new JFrame("Linker");
        clientGUI.setSize(400,300);        
        
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
        scrollBar = new JScrollPane(textArea,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        p2.add(scrollBar);
        p2.add(p1, BorderLayout.SOUTH);
        clientGUI.setContentPane(p2);
        
        clientGUI.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);


           
        try{
                 Socket socketClient= new Socket("localhost",5555);
                 writer= new BufferedWriter(new OutputStreamWriter(socketClient.getOutputStream()));
                 
                 reader =new BufferedReader(new InputStreamReader(socketClient.getInputStream()));
                 
                 elogin = Encrypted.encrypt(login);
                 writer.write(elogin);
		  
        }catch(Exception e){e.printStackTrace();}
    
    
        sendMessageButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ev){
                
                String s=login+" : "+textField.getText();  
                textField.setText("");
                System.out.println(""+textField);
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
                String s = login + " has changed their name to: ";
                login = userField.getText();  
                userField.setText("");
                s += login;
                
                Thread.currentThread().setName(login); 
                
                s= Encrypted.encrypt(s);
                
                try{
                    writer.write(s);
                    writer.write("\r\n");
                    writer.flush(); 
                    }catch(Exception e){e.printStackTrace();}
            }
          }
        ); 
        clientGUI.setVisible(true);    
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