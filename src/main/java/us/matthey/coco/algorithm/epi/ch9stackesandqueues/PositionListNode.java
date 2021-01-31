package us.matthey.coco.algorithm.epi.ch9stackesandqueues;

public class PositionListNode<T> {
    public T data;
    public PositionListNode<T> next;
    public PositionListNode<T> jump;
    public int order;

    public PositionListNode(T data, PositionListNode<T> next, PositionListNode<T> jump) {
        this.data = data;
        this.next = next;
        this.next = jump;
        this.order = -1;
    }
}
