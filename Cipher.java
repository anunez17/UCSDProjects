/**
 * Name: Anthony Nunez
 * ID: A14448615
 * Email: ann114@ucsd.edu
 * Sources Used: Zybooks
 */
import java.util.Scanner;

/**
 * The Cipher class consists of six different methods. The first two check to
 * ensure the validity of the input. Then we have the Caesar Shift encoder and
 * decoder. Finally the last two methods are the Vigenére Cipher encoder and
 * decoder.
 */

public class Cipher{
  //set magic number for empty string
  private static final String EMPTYSTRING = "";
  //set magic number for empty char
  private static final char EMPTYCHAR = '\0';

  /**
   * isLowerCase checks to see if the input is a lowercase letter. If it is true
   * it returns true if it is not a lowecase it returns false
   *
   * @param letter character that is checked to ensure it is a lowercase
   * @return a true or false value
   */
  public static boolean isLowerCase(char letter){
    if (letter >= 'a' && letter <= 'z'){
      return true;
    }else{
      return false;
    }

  }

  /**
   * isLowerCase checks to see if the input string is all lowercase letter. It
   * calls on isLowerCase for each character that is in the input string. If all
   * characters are lower case it returns true if not it returns false.
   *
   * @param str the string we will see if it is lower case
   * @return true or false value
   */
  public static boolean isLowerCase(String str){
    boolean valid = true;// initialize as true

    if(str == null){
      valid = false;
    }
    else if(str == ""){
      valid = true;// an empty string is still a valid input
    }
    else if(str != null){
      for (int i = 0; i < str.length(); i++){
        //call on isLowerCase method to check the validity of all characters
        if (isLowerCase(str.charAt(i)) == false){
          valid = false;
          break;//stop checking the string if one character is invalid
        }
      }
    }
    return valid;
  }

  /**
   * caesarShiftEncode takes in a letter and a key and. After checking the
   * validity of the inputs, it endcodes the character based on the shift of
   * the alphabet starting at the key.
   *
   * @param plaintext character to be encoded
   * @param key charcter to begin the shift of the alphabet
   * @return the encoded character
   */
  public static char caesarShiftEncode(char plaintext, char key){
    char[] alpha = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k',
                    'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v',
                    'w', 'x', 'y', 'z'};
    char charOut = EMPTYCHAR; // initiate as an empty char
    int charVal = 0;
    int keyVal = 0;
    int placeVal = 0;

    //check to see if the inputs are valid by calling on isLowerCase method
    if ((isLowerCase(plaintext) == false) || (isLowerCase(key) == false)){
      charOut = plaintext;//output original plaintext if inputs are invalid
    }
    else if ((isLowerCase(plaintext) == true) && (isLowerCase(key) == true)){
      for (int i = 0; i < alpha.length; i++){
        if(alpha[i] == plaintext){
          charVal = i;//index value of plaintext character
        }
        if (alpha[i] == key){
          keyVal = i;//index value of key character
        }
      }
      placeVal = (charVal + keyVal) % 26;//calculate index of coded character
      charOut = alpha[placeVal];
    }

    return charOut;
  }

  /**
   * caesarShiftDecode takes in an encrypted character and a key and first
   * checks the validity of the inputs. After, it uses the key to decode the
   * encrypted character into the real character.
   *
   * @param ciphertext the encoded character to be decoded
   * @param key the key character to begin the shift of the alphabet
   * @return returns the decrypted character
   */
  public static char caesarShiftDecode(char ciphertext, char key){
    char[] alpha = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k',
                    'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v',
                    'w', 'x', 'y', 'z'};
    char charOut = EMPTYCHAR;
    int charVal = 0;
    int keyVal = 0;
    int placeVal = 0;

    //check the validity of the inputs by calling on isLowerCase method
    if ((isLowerCase(ciphertext) == false) || (isLowerCase(key) == false)){
      charOut = ciphertext;//output original ciphertext if inputs are invalid
    }
    else if ((isLowerCase(ciphertext)) == true && (isLowerCase(key) == true)){
      for (int i = 0; i < alpha.length; i++){
        if(alpha[i] == ciphertext){
          charVal = i;//index value of the ciphertext character
        }
        if (alpha[i] == key){
          keyVal = i;//index value of the key character
        }
      }
      placeVal = (charVal - keyVal);//obtain the initial shift value
      if (placeVal < 0){
        charOut = alpha[((placeVal + 26) % 26)];//add 26 for the wrap around
      }else{
        charOut = alpha[(placeVal % 26)];
      }
    }
    return charOut;
  }

  /**
   * vigenereEncode takes an input string and a key string and first checks its
   * validity. Then, for each character in the input string and key string it
   * encrypts it by using calling on the caesarshiftEncode method.
   *
   * @param plaintext string that will be encoded
   * @param key string that will be used for the shift of the alphabet
   * @return returns the encoded string
   */
  public static String vigenereEncode(String plaintext, String key){
    char[] alpha = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k',
                    'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v',
                    'w', 'x', 'y', 'z'};
    String strOut = EMPTYSTRING;
    String keyHold = key;
    int textVal = 0;//initialize to 0
    int keyVal = 0;//initialize to 0

    if(isLowerCase(plaintext) == false || key == EMPTYSTRING){
      strOut = null;//output null if the inputs are invalid
    }
    else if(isLowerCase(plaintext) == true && key != EMPTYSTRING){
      //ensure that plaintext and the key are the same length
      while(plaintext.length() != keyHold.length()){
        for(int i = 0; i < key.length(); i++){//keep looping through key chars
          if (plaintext.length() != keyHold.length()){
            keyHold += (key.charAt(i));//add wrap around of the key characters
          }else{
            break;//if plaintext and key length are the same then break
          }
        }
      }

      for(int j = 0; j < plaintext.length(); j++){
        for(int k = 0; k < alpha.length; k++){
          if (alpha[k] == plaintext.charAt(j)){
            textVal = k;//index value for plaintext character
          }
          if (alpha[k] == keyHold.charAt(j)){
            keyVal = k;//index value for key character
          }
        }
        //call on caesarshiftEncode for each character and add to strOut
        strOut += (caesarShiftEncode(alpha[textVal], alpha[keyVal]));
      }
    }
    return strOut;
  }

  /**
   * vigenereDecode takes an encoded string and a key string and first checks
   * its validity. Then, it goes through each character in the encoded string
   * and the key string and calls on the caesarShiftDecode method to decode the
   * string.
   *
   * @param ciphertext the encoded string that will be decoded
   * @param key the key string that will be used for the alphabet shift
   * @return the decoded string
   */
  public static String vigenereDecode(String ciphertext, String key){
    char[] alpha = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k',
                    'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v',
                    'w', 'x', 'y', 'z'}; //alphabet array
    String strOut = EMPTYSTRING;
    String keyHold = key;
    int textVal = 0;//initialize to 0
    int keyVal = 0;//initialize to 0

    if(isLowerCase(ciphertext) == false || key == EMPTYSTRING){
      strOut = null; //output a null if the inputs are invalid
    }
    else if(isLowerCase(ciphertext) == true && key != EMPTYSTRING){
      //ensure that the key and the ciphertext are the same length
      while(ciphertext.length() != keyHold.length()){
        for(int i = 0; i < key.length(); i++){ // keep looping through key chars
          if (ciphertext.length() != keyHold.length()){
            keyHold += (key.charAt(i));//add wrap around of the key characters
          }else{
            break;// if ciphertext and key are the same length break
          }
        }
      }

      for(int j = 0; j < ciphertext.length(); j++){
        for(int k = 0; k < alpha.length; k++){
          if (alpha[k] == ciphertext.charAt(j)){
            textVal = k; //index value for the ciphertext character
          }
          if (alpha[k] == keyHold.charAt(j)){
            keyVal = k;//index value for the key character
          }
        }
        //call on caesarShiftDecode for each character and add to strOut
        strOut += (caesarShiftDecode(alpha[textVal], alpha[keyVal]));
      }
    }
    return strOut;
  }
  public static void main(String[] args){
    Scanner scnr = new Scanner(System.in);
    String plaintext;
    String key;
    String  output = "";

    plaintext = scnr.next();
    key = scnr.next();

    output = vigenereDecode(plaintext, key);
    System.out.println(output);
  }
}
