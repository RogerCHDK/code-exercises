package src.twopointers;

import java.util.Arrays;

public class Test {
    public static int maxArea(int[] height) {
        int maxtTotal = 0;
//            Compare height[i] with height[j], where j = height -1
//            take the max value between both value
//            multiple by the total numbers between those values
//            to get the total numbers we can do = height.length - (i+1) (height - j)
//            Store the value in a local variable
//            check if the result value is greater than a variable called maxTotal
        int i = 0;
        int j = height.length -1;
        while(j>i){
            int left = height[i];
            int right = height[j];
            int aux = Math.min(left, right) * (j-i);
            if (aux > maxtTotal){
                maxtTotal = aux;
            }
            if (left > right){
                j--;
            }else{
                i++;
            }
        }
        return maxtTotal;
    }

    public static int trap(int[] height) {
        //once the height is greater than 1 we need to check if the next value is greater or equal to the current element
//        yes: the total water increase n times, where n is the current height
//        no : dont increase, unlees there is a following element wich is greater or equal in n position

//        need to check the right and left items of my current position
//        first check that both values are grater than 0
//        if left < right, increment water in left value
//        else increment water in right value

        //ignores first position
//        once we get the second item, iterate through the array until we found a item
//        that it's greater or equal to my current element
//        during the way need to increase the water
//        doing my initial value - currentValue
//        once an item match the condition that it's greater or equal to my current element
//        i need to change the reference of my current element
//        int firstItem = height[1];
        if (height.length <= 1){
            return 0;
        }
        int i = 0;
        int j =1;
        int total = 0;
        int[] auxArray = Arrays.copyOf(height, height.length);
        Arrays.sort(auxArray);
        int maxValue = auxArray[auxArray.length-1];
        while (i<j){
            if (height[i] == maxValue){
                i++;
                j=i+1;
                continue;
            }
            if (j >= height.length-1 || i >= height.length-1){
                break;
            }
            if (height[i] <= height[j]){
                i = j;
                j = i + 1;
            }else {
                total += height[i] - height[j];
                j++;
            }
        }
        return total;
    }
}
