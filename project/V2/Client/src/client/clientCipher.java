/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import java.util.Locale;

/**
 *
 * @author kings
 */
final public class clientCipher {
    private static final String key = "LINKERKEY";
    private static final String lowkey = key.toLowerCase();
   
    private clientCipher(){
      /*Private Constructor will prevent the instantiation of this class directly*/
    }
    
   public static String encrypt(String message){
        String encodedMessage = "";
        char mess[] = new char[message.length()];
        
        //message = message.toUpperCase();

        for (int x = 0, y = 0; x < message.length(); x++) {
            char c = message.charAt(x);
            //if (c == ',' || c == ' ') {
            if (((int)c >= 32 && (int)c <= 64) || ((int)c >= 91 && (int)c <= 96) || ((int)c >= 123 && ((int)c <= 177))){    
                encodedMessage += c;
            } else {
                if (c >= 'A' && c <= 'Z') {
                    encodedMessage += (char) ((c + key.charAt(y)) % 26 + 'A');
                    y = ++y % key.length();
                }
                else if (c >= 'a' && c <= 'z'){
                    c = Character.toUpperCase(c);
                    mess[x] = (char) ((c + key.charAt(y)) % 26 + 'A');
                    encodedMessage += Character.toLowerCase(mess[x]);
                    
                    y = ++y % key.length();
                }
            }
        }
        return encodedMessage;
   }
   
   public static String decrypt(String encodedMessage){

   String decodedMessage = "";
        //encodedMessage = encodedMessage.toUpperCase();
        for (int x = 0, y = 0; x < encodedMessage.length(); x++) {
            char c = encodedMessage.charAt(x);
            //if (c == ',' || c == ' ') {
            if (((int)c >= 32 && (int)c <= 64) || ((int)c >= 91 && (int)c <= 96) || ((int)c >= 123 && ((int)c <= 177))){
                decodedMessage += c;
            } else {
                if (c >= 'A' && c <= 'Z') {
                    decodedMessage += (char) ((c - key.charAt(y) + 26) % 26 + 'A');
                    y = ++y % key.length();
                }
                if (c >= 'a' && c <= 'z'){
                    decodedMessage += (char) ((c - lowkey.charAt(y) + 26) % 26 + 'a');
                    y = ++y % lowkey.length();
                }
            }
        }
        return decodedMessage;
   }
}
