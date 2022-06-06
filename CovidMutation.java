/*
Name: Anthony Nunez
ID: A14448615
Email: ann114@ucsd.edu
Sources Used: Zybooks, Piazza
*/
import java.util.Scanner;

/*
This class takes a string of letters as one input and an integer k to reverse
the sequence of the string after each ith iteration of the kth element of the
string.
*/
public class CovidMutation{
  public static void main(String[] args){
    Scanner scnr = new Scanner(System.in);
    String covidMutation;
    int k;
    int end = -1;// initialize at -1 to get the 0th element of the string
    String outputSequence;
    String remainderSequence;
    int i;// integer that is going to be used for the for loop
    int j;// integer that is going to be used for the for loop
    int l;
    boolean valid = true;// assume the input is valid

    covidMutation = scnr.next();
    k = scnr.nextInt();


    outputSequence = "";
    remainderSequence = "";

    /*
    The for loop checks to ensure that the characters in the input string are
    valid nucloebases for the genetic sequence and k is greater than 1
    */
    for (l = 0; l < covidMutation.length(); l++){
      if ((covidMutation.charAt(l) != 'A' && covidMutation.charAt(l) != 'T' &&
          covidMutation.charAt(l) != 'C' && covidMutation.charAt(l) != 'G') ||
          (k <= 0)){
        valid = false;
        break;
      }
    }

    /*
    if the input string is valid the for loop goes through the the string
    until the last divisible (by k) element of the string. If there are multiple
    elements divisible by k it takes each segment and reverses it until it
    reaches the end. As it goes it adds the element to outputSequence. If the
    input is invalid the output gets the input as is.
    */
    if (valid == true){
      for (i = 0; i < covidMutation.length() - (covidMutation.length() % k);
      i++){
        if ((i + 1) % k == 0){
          for(j = i; j > end; j --){
            outputSequence = outputSequence + covidMutation.charAt(j);
          }
        /*
        update end with the current value of i in order to run through each
        ith segment of k elements
        */
          end = i;
        }
      }
      /*
      the remainder sequence is anything in the input string that is not
      divisible by the integer input. Reverse the elements and add them to
      output
      */
      remainderSequence = covidMutation.substring(covidMutation.length() -
                          (covidMutation.length() % k));
      for (i = remainderSequence.length() - 1; i >= 0; i--){
        outputSequence = outputSequence + remainderSequence.charAt(i);
      }
    }else if (valid == false){
      outputSequence = covidMutation;
    }



    System.out.println(outputSequence);
  }
}
