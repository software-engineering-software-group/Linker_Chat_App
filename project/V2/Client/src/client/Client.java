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

public class Client implements Runnable {

    public JTextField textField;
    public JTextArea textArea;
    public JScrollPane scrollBar;
    public String login = Thread.currentThread().getName();
    public String message;
    public JButton sendMessageButton,usernameButton;
    public int i = 0;
    BufferedWriter writer;
    BufferedReader reader;

    public Client(String l) {

        JFrame clientGUI = new JFrame("Linker");
        clientGUI.setSize(533, 300);

        JPanel p1 = new JPanel();
        p1.setLayout(new BorderLayout());

        JPanel p2 = new JPanel();
        p2.setLayout(new BorderLayout());

        textField = new JTextField();
        p1.add(textField, BorderLayout.CENTER);
        JScrollBar scrollBar2 = new JScrollBar(JScrollBar.HORIZONTAL);
        BoundedRangeModel hsize = textField.getHorizontalVisibility();
        scrollBar2.setModel(hsize);
        p1.add(scrollBar2, BorderLayout.SOUTH);

        sendMessageButton = new JButton("Send");
        p1.add(sendMessageButton, BorderLayout.EAST);

        usernameButton = new JButton("Change Username");
        p2.add(usernameButton, BorderLayout.NORTH);

        textArea = new JTextArea();
        textArea.setWrapStyleWord(true);
        textArea.setLineWrap(true);
        textArea.setEditable(false);
        scrollBar = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        p2.add(scrollBar);
        p2.add(p1, BorderLayout.SOUTH);
        clientGUI.setContentPane(p2);

        clientGUI.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        try {
            Socket socketClient = new Socket("localhost", 5555);
            writer = new BufferedWriter(new OutputStreamWriter(socketClient.getOutputStream()));
            reader = new BufferedReader(new InputStreamReader(socketClient.getInputStream()));
            writer.flush();

        } catch (Exception e) {
            e.printStackTrace();
        }
        KeyAdapter Enter = new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    sendMessageButton.doClick();
                }
            }
        };
        textField.addKeyListener(Enter);
        sendMessageButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                if (textField.getText().isEmpty() == false) {
                    String s = login + " : " + textField.getText();
                    textField.setText("");
                    System.out.println("" + textField);
                    System.out.println("plain: " + s);
                    s = clientCipher.encrypt(s);
                    System.out.println("encrypted: " + s);
                    try {
                        writer.write(s);
                        writer.write("\r\n");
                        writer.flush();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            }
        }
        );

        usernameButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                String key, k;
                if (i == 0) {

                    message = "Welcome to Linker!\nPlease enter a username.";
                    i++;
                    k = JOptionPane.showInputDialog(null, message, "Linker", JOptionPane.PLAIN_MESSAGE);
                    key = JOptionPane.showInputDialog(null, "Please enter server Key.", "Linker - Server Key", JOptionPane.PLAIN_MESSAGE);
                    clientCipher.changekey(key);
                } else {
                    message = "Please enter a new username.";
                    k = JOptionPane.showInputDialog(null, message, "Linker", JOptionPane.PLAIN_MESSAGE);
                }
                String s = login + " has changed their name to: ";


                if(k==null){
                    clientGUI.setTitle("Linker - " + login);
                    s += login;
                    Thread.currentThread().setName(login);
                    s = clientCipher.encrypt(s);
                }
                else{
                    clientGUI.setTitle("Linker - " + k);
                    s += k;
                    Thread.currentThread().setName(k);
                    s = clientCipher.encrypt(s);
                    login = k;
                }
                try {
                    writer.write(s);
                    writer.write("\r\n");
                    writer.flush();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        );
        clientGUI.setVisible(true);
        usernameButton.doClick();

        
    }

    

    public void run() {
        JScrollBar vertical = scrollBar.getVerticalScrollBar();
        try {
            String serverMsg = "";
            while ((serverMsg = reader.readLine()) != null) {
                serverMsg = clientCipher.decrypt(serverMsg);
                System.out.println("from server: " + serverMsg);
                textArea.append(serverMsg + "\n");
                vertical.setValue(vertical.getMaximum());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
    private void setComponentsNames(){
        /*
            naming gui components for testing
        */
        sendMessageButton.setName("sendbutton");
        usernameButton.setName("unbutton");
        textField.setName("input");
        textArea.setName("area");
    }
}
