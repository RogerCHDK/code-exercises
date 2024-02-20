package src.linkendList;

public class TestLinkedList {
    public static void main(String[] args) {
        reverseList(createList(1, 5));
    }
    public static ListNode reverseList(ListNode head) {
    /*
    *Iterate the tree starting with the next value
    * While iterating the tree:
    * Store the next value in a temp variable
    * the current value it's going to point the root
    * the root it's going to be the next value*/
        ListNode aux = head;
        while (head.next != null){
            head.next.next = head;
        }
        return head;
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
}
