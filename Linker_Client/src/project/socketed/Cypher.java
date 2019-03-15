package project.socketed;

/**
 *
 * @author Linker Team
 */
final public class Cypher {
    private Cypher(){
      /*Private Constructor will prevent the instantiation of this class directly*/
    }
    
   public static String encrypt(String message, String key){
        String encodedMessage = "";
        message = message.toUpperCase();
        for (int x = 0, y = 0; x < message.length(); x++) {
            char c = message.charAt(x);
            if (c == ',' || c == ' ') {
                encodedMessage += c;
            } else {
                if (c >= 'A' || c <= 'Z') {
                    encodedMessage += (char) ((c + key.charAt(y)) % 26 + 'A');
                    y = ++y % key.length();
                }
            }
        }
        return encodedMessage;
   }
   
   public static String decrypt(String encodedMessage, String key){

   String decodedMessage = "";
        encodedMessage = encodedMessage.toUpperCase();
        for (int x = 0, y = 0; x < encodedMessage.length(); x++) {
            char c = encodedMessage.charAt(x);
            if (c == ',' || c == ' ') {
                decodedMessage += c;
            } else {
                if (c >= 'A' || c <= 'Z') {
                    decodedMessage += (char) ((c - key.charAt(y) + 26) % 26 + 'A');
                    y = ++y % key.length();
                }
            }
        }
        return decodedMessage;
   }
}
