/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

/**
 *
 * @author kings
 */
final public class Encrypted {
    private static final String key = "LINKERKEY";
   
    private Encrypted(){
      /*Private Constructor will prevent the instantiation of this class directly*/
    }
    
   public static String encrypt(String message){
        String encodedMessage = "";
        char mess[] = new char[message.length()];
        
        //message = message.toUpperCase();

        for (int x = 0, y = 0; x < message.length(); x++) {
            char c = message.charAt(x);
            if (c == ',' || c == ' ') {
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
   
   
}
