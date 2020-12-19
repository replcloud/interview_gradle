package us.matthey.coco.algorithm.epi.ch9stackesandqueues;

import us.matthey.coco.algorithm.epi.ch8linkedlists.ListNode;

import java.util.Deque;

public class PrintLinkedListInReverse {
    public static void printLinkedListInReverse(ListNode<Integer> head) {
        Deque<Integer> nodes = new java.util.LinkedList<>();
        while (head != null) {
            nodes.addFirst(head.data);
            head = head.next;
        }
        while (!nodes.isEmpty()) {
            System.out.print(nodes.poll());
        }
    }
}