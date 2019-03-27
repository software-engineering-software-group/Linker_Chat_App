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
    public String elogin, message;
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
        p1.add(textField);
        JScrollBar scrollBar2 = new JScrollBar(JScrollBar.HORIZONTAL);
        BoundedRangeModel hsize = textField.getHorizontalVisibility();
        scrollBar2.setModel(hsize);
        p1.add(scrollBar2, BorderLayout.SOUTH);

        JButton sendMessageButton = new JButton("Send");
        p1.add(sendMessageButton, BorderLayout.EAST);

        JButton usernameButton = new JButton("Change Username");
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
                    s = Encrypted.encrypt(s);
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

                if (i == 0) {
                    message = "Welcome to Linker!\nPlease enter a username.";
                    i++;
                } else {
                    message = "Please enter a new username.";
                }
                String s = login + " has changed their name to: ";
                String k;
                k = JOptionPane.showInputDialog(null, message, "Linker", JOptionPane.PLAIN_MESSAGE);
                if(k==null){
                    clientGUI.setTitle("Linker - " + login);
                    s += login;
                    Thread.currentThread().setName(login);
                    s = Encrypted.encrypt(s);
                }
                else{
                    clientGUI.setTitle("Linker - " + k);
                    s += k;
                    Thread.currentThread().setName(k);
                    s = Encrypted.encrypt(s);
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
                System.out.println("from server: " + serverMsg);
                textArea.append(serverMsg + "\n");
                vertical.setValue(vertical.getMaximum());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
