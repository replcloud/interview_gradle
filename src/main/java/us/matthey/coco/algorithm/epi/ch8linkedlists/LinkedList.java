package us.matthey.coco.algorithm.epi.ch8linkedlists;

public class LinkedList {
    public static ListNode<Integer> search(ListNode<Integer> L, int key) {
        while (L != null && L.data != key) {
            L = L.next;
        }
        return L;

    }

    public static void insertAfter(ListNode<Integer> node, ListNode<Integer> newNode) {
        newNode.next = node.next;
        node.next = newNode;
    }

    public static void deleteList(ListNode<Integer> aNode) {
        if (aNode != null && aNode.next != null) {
            aNode.next = aNode.next.next;
        }
    }

    public static ListNode<Integer> mergeTwoSortedLists(ListNode<Integer> L1, ListNode<Integer> L2) {
        ListNode<Integer> dummyHead = new ListNode<Integer>(0, null);
        ListNode<Integer> current = dummyHead;
        ListNode<Integer> p1 = L1, p2 = L2;

        while (p1 != null && p2 != null) {
            if (p1.data <= p2.data) {
                current.next = p1;
                p1 = p1.next;
            } else {
                current.next = p2;
                p2 = p2.next;
            }
            current = current.next;
        }
        current.next = p1 != null ? p1 : p2;
        return dummyHead.next;
    }
}
