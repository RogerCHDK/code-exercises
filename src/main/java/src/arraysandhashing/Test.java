package src.arraysandhashing;

import java.util.*;
import java.util.stream.Stream;

public class Test {
    public static boolean containsDuplicate(int[] nums) { //Time limit exception
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j <= i; j++) {
                if (nums[i] == nums[j])
                    count++;
                if (count > 1)
                    return true;
            }
            count = 0;
        }
        return false;
    }
    public static boolean containsDuplicateSecondSolution(int[] nums) {//21 ms
        Arrays.sort(nums);
        for (int i = 0; i < nums.length -1; i++) {
            if (nums[i] == nums[i+1])
                return true;
        }
        return false;
    }

    public static boolean containsDuplicateThirdSolution(int[] nums) { //13 ms
        HashMap<Integer, Integer> numsWithoutDuplicate = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (numsWithoutDuplicate.containsKey(nums[i]))
                return true;
            numsWithoutDuplicate.put(nums[i], 1);
        }
        return false;
    }

    public static boolean containsDuplicateFourthSolution(int[] nums) { //Best Solution 11ms
        HashSet<Integer> numsWithoutDuplicate = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            if (numsWithoutDuplicate.contains(nums[i]))
                return true;
            numsWithoutDuplicate.add(nums[i]);
        }
        return false;
    }

    public boolean containsNearbyDuplicate(int[] nums, int k) {
        HashMap<Integer, Integer> numsWithoutDuplicate = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (numsWithoutDuplicate.containsKey(nums[i]) && (Math.abs(numsWithoutDuplicate.get(nums[i]) - i) <= k))
                return true;
            numsWithoutDuplicate.put(nums[i], i);
        }
        return false;
    }

    public boolean containsNearbyAlmostDuplicate(int[] nums, int indexDiff, int valueDiff) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(i,nums[i]);
            for (Map.Entry<Integer, Integer> set :
                    map.entrySet()) {
                if ((set.getKey() != i) && (Math.abs(set.getKey() - i) <= indexDiff) && (Math.abs(set.getValue() - nums[i]) <= valueDiff))
                    return true;
            }
        }
        return false;
    }

    public boolean isAnagram(String s, String t) {
        char [] array1 = s.toCharArray();
        char [] array2 = t.toCharArray();
        Arrays.sort(array1);
        Arrays.sort(array2);
        return Arrays.equals(array1, array2);
    }

    public int[] twoSum(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i+1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target)
                    return new int [] {i,j};
            }
        }
        return new int [] {0};
    }

    public int[] twoSumSecondSolution(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int complement  = target - nums[i];
            if (map.containsKey(complement ))
                return new int [] {map.get(complement ), i};
            map.put(nums[i],i);
        }
        return new int [] {0};
    }

    public static List<List<String>> groupAnagrams(String[] strs) {
        if (strs.length == 0) {
            return new ArrayList<>();
        }
        List<List<String>> anagrams = new ArrayList<>();
        Map<String, List<String>> map = new HashMap<>();

        for (int i = 0; i < strs.length; i++) {
            char[] element = strs[i].toCharArray();
            Arrays.sort(element);
            if (map.containsKey(Arrays.toString(element))) {
                map.get(Arrays.toString(element)).add(strs[i]);
            } else {
                List<String> opt = new ArrayList<>();
                opt.add(strs[i]);
                map.put(Arrays.toString(element), opt);
            }
        }
        map.forEach((key, value) -> anagrams.add(value));
        return anagrams;
    }

    public static List<List<String>> groupAnagramsSecondSolution(String[] strs) {
        if (strs.length == 0) {
            return new ArrayList<>();
        }
        Map<String, List<String>> map = new HashMap<>();

        for (String i: strs) {
            char[] element = i.toCharArray();
            Arrays.sort(element);
            String sortedStr = String.valueOf(element);
            if (!map.containsKey(sortedStr)) {
                map.put(sortedStr, new ArrayList<>());
            }
            map.get(sortedStr).add(i);
        }
        return new ArrayList<>(map.values());
    }

    public static int[] topKFrequent(int[] nums, int k) {
        if (nums.length < 2){
            return nums;
        }
        Map<Integer, Integer> map = new HashMap<>();
        List<Integer> list = new ArrayList<>();
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i]))
                map.put(nums[i], map.get(nums[i]) + 1);
            else
                map.put(nums[i], 1);
        }
        int [] aux = map.values().stream().mapToInt(i->i).toArray();
        Arrays.sort(aux);
        for (int i = aux.length-1; i > (aux.length-1) - k; i--){
            list.add(aux[i]);
        }
        map.forEach((key, value) -> {if(list.contains(value)) result.add(key);});
        return result.stream().mapToInt(i->i).toArray();
    }

    public static int[] topKFrequentSecondSolution(int[] nums, int k) {
        if (nums.length < 2){
            return nums;
        }
        int [] output = new int[k];
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            if (map.containsKey(num))
                map.put(num, map.get(num) + 1);
            else
                map.put(num, 1);
        }
        List<Map.Entry<Integer, Integer>> list = new LinkedList<>(map.entrySet());
        list.sort(Map.Entry.comparingByValue(Comparator.reverseOrder()));

        List<Integer> topKKeys = list.stream().map(Map.Entry::getKey).limit(k).toList();
        for (int i = 0; i < k; i++) {
            output[i] = topKKeys.get(i);
        }
        return output;
    }

    public static int[] productExceptSelf(int[] nums) {
        int[] answerArray = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            int answer = 1;
            for (int j = 0; j < nums.length; j++) {
                if (j!=i){
                    answer *= nums[j];
                }
            }
            answerArray[i] = answer;
        }
        return answerArray;
    }

    public static int[] productExceptSelf2(int[] nums) {
//        HashMap<Integer, Integer> map = new HashMap<>();
        int total = 1;
        int totalInCaseOfCero = 0;
        boolean flag;
        int[] answerArray = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            flag = true;
            if (nums[i] == 0){
                totalInCaseOfCero = total;
                flag = false;
            }
            total *= nums[i];
            if (totalInCaseOfCero != 0 && flag){
                totalInCaseOfCero *= nums[i];
            }
        }
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0){
                answerArray[i] = total / nums[i];
            }else {
                answerArray[i] = totalInCaseOfCero;
            }

        }
        return answerArray;
    }

    public int[] productExceptSel3(int[] nums) {
        //Approach: Using prefix and postfix
        //Idea: Just find prefix and postfix product and keep them in the ans array

        int ans[] = new int[nums.length];
        int pre = 1, post = 1;

        //find pre product
        for(int i=0;i<nums.length;i++){
            ans[i] = pre;
            pre*=nums[i];
        }

        //find post product
        for(int i=nums.length-1;i>=0;i--){
            ans[i]*=post;
            post*=nums[i];
        }

        return ans;
    }

    public static boolean isValidSudoku(char[][] board) {
        boolean isValid = true;
        HashMap<Character, Integer> mapBase = new HashMap<>();
        mapBase.put('1',1);
        mapBase.put('2',1);
        mapBase.put('3',1);
        mapBase.put('4',1);
        mapBase.put('5',1);
        mapBase.put('6',1);
        mapBase.put('7',1);
        mapBase.put('8',1);
        mapBase.put('9',1);
        HashMap<Character, Integer> map1 = new HashMap<>(mapBase);
        HashMap<Character, Integer> map2 = new HashMap<>(mapBase);
        HashMap<Character, Integer> map3 = new HashMap<>(mapBase);
        HashMap<Integer, HashMap<Character, Integer>> mapRow = new HashMap<>();
        mapRow.put(0,new HashMap<>(mapBase));
        mapRow.put(1,new HashMap<>(mapBase));
        mapRow.put(2,new HashMap<>(mapBase));
        mapRow.put(3,new HashMap<>(mapBase));
        mapRow.put(4,new HashMap<>(mapBase));
        mapRow.put(5,new HashMap<>(mapBase));
        mapRow.put(6,new HashMap<>(mapBase));
        mapRow.put(7,new HashMap<>(mapBase));
        mapRow.put(8,new HashMap<>(mapBase));
        for (int i = 0; i < board.length; i++) {
            HashMap<Character, Integer> map = new HashMap<>(mapBase);
            if (i == 3 || i == 6){
                map1.clear();
                map2.clear();
                map3.clear();
                map1.putAll(mapBase);
                map2.putAll(mapBase);
                map3.putAll(mapBase);
            }
            for (int j = 0; j < board[i].length; j++) {
                System.out.println(board[i][j]);
                if (board[i][j] ==  '.'){
                    continue;
                }
                if (mapRow.get(j).containsKey(board[i][j])){
                    mapRow.get(j).remove(board[i][j]);
                }else {
                    isValid = false;
                    break;
                }

                if (map.containsKey(board[i][j])) {
                    map.remove(board[i][j]);
                } else {
                    isValid = false;
                    break;
                }
                if (j<3){
                    if (map1.containsKey(board[i][j])) {
                        map1.remove(board[i][j]);
                    } else {
                        isValid = false;
                        break;
                    }
                }
                if (j >= 3 && j < 6){
                    if (map2.containsKey(board[i][j])) {
                        map2.remove(board[i][j]);
                    } else {
                        isValid = false;
                        break;
                    }
                }
                if (j >= 6){
                    if (map3.containsKey(board[i][j])) {
                        map3.remove(board[i][j]);
                    } else {
                        isValid = false;
                        break;
                    }
                }
            }
        }
        return isValid;
    }

    public static int longestConsecutive(int[] nums) {
        if (nums.length == 0){
            return 0;
        }
        Set<Integer> set = new TreeSet<>();
        Integer aux = null;
        int resp = 1;
        List<Integer> max = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            set.add(nums[i]);
        }
        for (Integer value : set) {
            if(aux == null){
                aux = value;
                continue;
            }
            if ((aux + 1) == value){
                resp++;
                aux=value;
            }else {
                max.add(resp);
                aux = value;
                resp = 1;
            }
            System.out.println(value);
        }
        max.add(resp);
        return Collections.max(max);
    }

    public static boolean isPalindrome(String s) {
        List<Character> charList = s.chars().mapToObj(c -> (char) c).toList();
        List<Character> filter = charList.stream()
                .map(Character::toLowerCase)
                .filter(character -> (character >= 97 && character <= 122) || (character >= 48 && character <= 57))
                .toList();
        for (int i = 0; i < filter.size(); i++) {
            if (filter.get(i) != filter.get((filter.size() - 1) - i)){
                return false;
            }
        }
        return true;
    }

    public static int[] twoSum2(int[] numbers, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < numbers.length; i++) {
            int complement  = target - numbers[i];
            if (map.containsKey(complement ))
                return new int [] {map.get(complement), i+1};
            map.put(numbers[i],i+1);
        }
        return new int [] {};
    }

    public static List<List<Integer>> threeSum(int[] nums) {
        //This works, but didn't pass the performance test
        HashMap<Integer, Integer> map = new HashMap<>();
        Set<List<Integer>> result = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(i, nums[i]);
        }
        for (int i = 0; i <= nums.length-2; i++) {
            map.remove(i);
            for (int j = i+1; j <= nums.length-2; j++) {
                map.remove(j);
                int sum = nums[i] + nums[j];
                if (sum > 0 && map.containsValue(Math.negateExact(sum))){
                    result.add(Stream.of(nums[i], nums[j],Math.negateExact(sum)).sorted().toList());
                } else if (sum < 0 && map.containsValue(Math.abs(sum))) {
                    result.add(Stream.of(nums[i], nums[j],Math.abs(sum)).sorted().toList());
                } else if (sum == 0 && map.containsValue(0)) {
                    result.add(Stream.of(nums[i], nums[j],0).sorted().toList());
                }
                map.put(j, nums[j]);
            }
        }
        return result.stream().toList();
    }

    public List<List<Integer>> threeSumRealSolution(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();

        // Sort the array
        Arrays.sort(nums);

        for (int i = 0; i < nums.length - 2; i++) {
            // Skip duplicate elements for i
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }

            int j = i + 1;
            int k = nums.length - 1;

            while (j < k) {
                int sum = nums[i] + nums[j] + nums[k];

                if (sum == 0) {
                    // Found a triplet with zero sum
                    ans.add(Arrays.asList(nums[i], nums[j], nums[k]));

                    // Skip duplicate elements for j
                    while (j < k && nums[j] == nums[j + 1]) {
                        j++;
                    }

                    // Skip duplicate elements for k
                    while (j < k && nums[k] == nums[k - 1]) {
                        k--;
                    }

                    // Move the pointers
                    j++;
                    k--;
                } else if (sum < 0) {
                    // Sum is less than zero, increment j to increase the sum
                    j++;
                } else {
                    // Sum is greater than zero, decrement k to decrease the sum
                    k--;
                }
            }
        }
        return ans;
    }
    
}
