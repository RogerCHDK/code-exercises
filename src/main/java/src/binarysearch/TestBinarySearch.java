package src.binarysearch;

import java.util.Arrays;

public class TestBinarySearch {
    public static void main(String[] args) {
        System.out.println(search(new int [] {-1}, -9));
    }
    public static int search(int[] nums, int target) {
       int low = 0;
       int high = nums.length -1;
       while (high >= low){
           int midValue = (low + high) / 2;
           if (nums[midValue] == target){
               return midValue;
           }else if (target < nums[midValue]){
               high = midValue - 1;
           }else {
               low = midValue + 1;
           }
       }
       return -1;
    }
}
