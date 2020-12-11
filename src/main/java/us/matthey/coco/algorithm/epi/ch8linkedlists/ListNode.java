package us.matthey.coco.algorithm.epi.ch8linkedlists;

public class ListNode<T> {
    public T data;
    public us.matthey.coco.algorithm.epi.ch8linkedlists.ListNode<T> next;

    public ListNode(T data, us.matthey.coco.algorithm.epi.ch8linkedlists.ListNode<T> next) {
        this.data = data;
        this.next = next;
    }
}
