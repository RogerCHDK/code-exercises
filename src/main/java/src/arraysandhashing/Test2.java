package src.arraysandhashing;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class Test2 {
    public static void main(String[] args) {
        System.out.println(containsDuplicate(new int[]{1,2,3,1}));
    }
    public static boolean containsDuplicate(int[] nums) {
//        link: https://leetcode.com/problems/contains-duplicate/
//        1. se puede ordenar el array y ver si array[i] es igual a array[i-1], empezar en la posicion 1 del array
//        2. se puede usar hash map
//        3. la mejor solucion es usar hash set
        HashSet<Integer> numsWithoutDuplicate = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            if (numsWithoutDuplicate.contains(nums[i]))
                return true;
            numsWithoutDuplicate.add(nums[i]);
        }
        return false;
    }

    public boolean isAnagram(String s, String t) {
        char [] sToCharArray = s.toCharArray();
        char [] tToCharArray = t.toCharArray();
        Arrays.sort(sToCharArray);
        Arrays.sort(tToCharArray);
        return Arrays.equals(sToCharArray, tToCharArray);
    }

    public int[] twoSum(int[] nums, int target) {
        HashMap <Integer, Integer> map = new HashMap<>();
        for(int i = 0; i < nums.length; i++){
            int valueTarget = target - nums[i];
            if(map.containsKey(valueTarget)){
                return new int []{map.get(valueTarget), i};
            }
            map.put(nums[i],i);
        }
        return new int []{};
    }
}
