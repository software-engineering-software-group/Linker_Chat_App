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
public class ClientGUI{
     
        public static void main(String [] args){
            	   try{
                            System.out.println("Hello ");        	        
                            Client c=new Client("User");   
                            Thread t1=new Thread(c);
                            t1.start();
    	   }                catch(Exception e){e.printStackTrace();}
            
        }
    }