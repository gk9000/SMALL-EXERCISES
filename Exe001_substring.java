/**
 *       Have the function minWindowSubstring(strArr) take the array of strings stored in strArr, which will
 *       contain only two strings, the first parameter being the string N and the second parameter being a string K
 *       of some characters, and your goal is to determine the smallest substring of N that contains
 *       all the characters in K. For example: if strArr is ["aaabaaddae", "aed"] then the smallest
 *       substring of N that contains the characters a, e, and d is "dae" located at the end of the string.
 *       So for this example your program should return the string dae.
 *
 *       Another example: if strArr is ["aabdccdbcacd", "aad"] then the smallest substring of N that contains
 *       all of the characters in K is "aabd" which is located at the beginning of the string.
 *       Both parameters will be strings ranging in length from 1 to 50 characters and all of K's
 *       characters will exist somewhere in the string N. Both strings will only contains
 *       lowercase alphabetic characters.
*/


import java.util.ArrayList;
import java.util.Arrays;

class Exe001_substring {
    public static String minWindowSubstring(String[] strArr) {

        String result = strArr[0];
        String a = strArr[0];
        String b = strArr[1];

        int counter=0;

        for(int i=0; i < a.length()-b.length(); i++){
            for(int j=a.length(); j>=i+b.length(); j--){

                   String temp1=a.substring(i,j);
                   String temp2=b.substring(0,b.length());

                   ArrayList <String> al1 = new ArrayList<>(Arrays.asList(temp1.split("")));
                   ArrayList <String> al2 = new ArrayList<>(Arrays.asList(temp2.split("")));

                    ONE: for(int k=0; k<temp2.length();k++){
                       for(int n=0; n<temp1.length();n++){
                           if(al2.get(k).equals(al1.get(n))){
                               al1.set(n," ");
                               counter++;
                               continue ONE;
                           }
                       }//close for
                   }//close for

                if(counter==temp2.length() && result.length()>temp1.length()){
                        result=String.join("",a.substring(i,j));
                }//close if
                counter=0;
                System.out.println(temp1 + "   " + j);
            }//close for
        }//close for

        System.out.println("Result: " + result);
        return result;
    }//close method

    public static void main (String[] args) {
        String[] strArr = {"ahffaksfajeeubsne","jefaa"};
        minWindowSubstring(strArr);
    }//close main

}//close  class
