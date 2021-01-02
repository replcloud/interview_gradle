package us.matthey.coco.algorithm.epi.ch8linkedlists;

public class ListNode<T> {
    public T data;
    public ListNode<T> next;

    public ListNode(T data, ListNode<T> next) {
        this.data = data;
        this.next = next;
    }
}
