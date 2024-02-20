package src.linkendList;

public class Solution {
    public static ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        if(list1 == null && list2 != null){
            return list2;
        }else if (list2 == null && list1 != null){
            return list1;
        } else if (list2 == null && list1 == null) {
            return null;
        }
        ListNode output = null;
        if (list1.val < list2.val){
            output = aux(list1, list2);
        }else {
            output = aux(list2, list1);
        }
        return output;
    }
    public static ListNode aux(ListNode list1, ListNode list2){
        ListNode list1Head = list1;
        while (list2 != null){
            if (list1.next == null){
                list1.next = new ListNode(list2.val);
                list2 = list2.next;
            }else if (list2.val >= list1.val && list2.val <= list1.next.val){
                ListNode auxNext = list1.next;
                list1.next = new ListNode(list2.val);
                list1.next.next = auxNext;
                list2 = list2.next;
            }else {
                list1 = list1.next;
            }
        }
        return list1Head;
    }

    public static ListNode createList(int initialValue, int maxValue){
        if (initialValue <=  maxValue){
            return new ListNode(initialValue, createList(initialValue + 1, maxValue));
        }
        return null;
    }

    public static void printLinkedList(ListNode head){
        while (head != null){
            System.out.println(head.val);
            head = head.next;
        }
    }

    public static void reorderList(ListNode head) {
        ListNode auxiliar = head;
        ListNode firstNode = head;

        while (auxiliar != null) {
            if (auxiliar.next == null) {
                if (firstNode.next.val != auxiliar.val) {
                    auxiliar.next = firstNode.next;
                    firstNode.next = auxiliar;
                    firstNode = firstNode.next;
                }
            }
            printLinkedList(head);
            auxiliar = auxiliar.next;
        }

    }
}
