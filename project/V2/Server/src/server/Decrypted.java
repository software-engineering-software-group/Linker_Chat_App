/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

/**
 *
 * @author kings
 */
final public class Decrypted {
    private static final String key = "LINKERKEY";
    private static final String lowkey = "linkerkey";
    private Decrypted(){
      /*Private Constructor will prevent the instantiation of this class directly*/
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
