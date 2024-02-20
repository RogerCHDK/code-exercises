package src.twopointers;

import java.util.ArrayList;
import java.util.List;

public class Test2 {
    public static void main(String[] args) {
        System.out.println(isPalindrome("r, a, c, e, a, c, a, r"));
    }
    public static boolean isPalindrome(String s) {
        /*
        * Remove spaces
        * Remove special characters
        * Put a punter at the beginner of the String
        * Put another punter at the end of the String
        * If both punters are same, move the punter to the next position
        * Else return false
        * While i < j*/
        if (s.isBlank()){
            return true;
        }
        char[] characterList = s.toLowerCase().trim().toCharArray();
        List <Character> input = new ArrayList<>();
        for (Character character: characterList){
            if (Character.isLetterOrDigit(character)){
                input.add(character);
            }
        }
        int i = 0;
        int j = input.size()-1;
        while (i<j){
            if (input.get(i).equals(input.get(j))){
                i++;
                j--;
            }else {
                return false;
            }
        }
        return true;
    }
}
