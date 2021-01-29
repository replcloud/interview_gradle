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

    public static ListNode<Integer> reverseList(ListNode<Integer> L) {
        ListNode<Integer> newList = null;
        while (L != null) {
            ListNode<Integer> next = L.next;
            L.next = newList;
            newList = L;
            L = next;
        }
        return newList;
    }

    private static ListNode<Integer> generateList(int n) {
        ListNode<Integer> next = null;
        for (int i = n; i > 0; i--) {
            ListNode<Integer> curr = new ListNode<Integer>(i, next);
            next = curr;
        }
        return next;
    }

    public static ListNode<Integer> reverseSublist(ListNode<Integer> L, int start, int finish) {
        if (start == finish) {
            return L;
        }
        ListNode dummyHead = new ListNode<>(0, L);
        ListNode<Integer> sublistHead = dummyHead;
        int k = 1;
        while (k < start) {
            sublistHead = sublistHead.next;
        }

        ListNode<Integer> sublistIter = sublistHead.next;
        while (start++ < finish) {
            ListNode<Integer> temp = sublistIter.next;
            sublistIter.next = temp.next;
            temp.next = sublistHead.next;
            sublistHead.next = temp;
        }
        return dummyHead.next;
    }

    public static ListNode<Integer> hasCycle(ListNode<Integer> head) {
        ListNode<Integer> fast = head, slow = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                int cycleLne = 0;
                do {
                    ++cycleLne;
                    fast = fast.next;
                } while (fast != slow);
                ListNode<Integer> cycleLenAdvancedIter = head;
                while (cycleLne-- > 0) {
                    cycleLenAdvancedIter = cycleLenAdvancedIter.next;
                }
                ListNode<Integer> iter = head;
                while (iter != cycleLenAdvancedIter) {
                    iter = iter.next;
                    cycleLenAdvancedIter = cycleLenAdvancedIter.next;
                }
                return iter;
            }
        }
        return null;
    }

    public static ListNode<Integer> overlappingNoCycleLists(ListNode<Integer> L1, ListNode<Integer> L2) {
        int L1Length = length(L1), L2Length = length(L2);
        if (L1Length > L2Length) {
            L1 = advanceListByK(L1Length - L2Length, L1);
        } else {
            L2 = advanceListByK(L2Length - L1Length, L2);
        }
        while (L1 != null && L2 != null && L1 != L2) {
            L1 = L1.next;
            L2 = L2.next;
        }
        return L1;
    }

    private static ListNode<Integer> advanceListByK(int k, ListNode<Integer> L) {
        while (k-- > 0) {
            L = L.next;
        }
        return L;
    }

    private static int length(ListNode<Integer> L) {
        int len = 0;
        while (L != null) {
            len++;
            L = L.next;
        }
        return len;
    }

    public static void deletionFromList(ListNode<Integer> nodeToDelete) {
        nodeToDelete.data = nodeToDelete.next.data;
        nodeToDelete.next = nodeToDelete.next.next;
    }

    public static ListNode<Integer> removeKthLast(ListNode<Integer> L, int k) {
        ListNode<Integer> dummyHead = new ListNode<>(0, L);
        ListNode<Integer> first = dummyHead.next;
        while (k-- > 0) {
            first = first.next;
        }
        ListNode<Integer> second = dummyHead;
        while (first != null) {
            first = first.next;
            second = second.next;
        }
        second.next = second.next.next;
        return dummyHead.next;
    }

    public static void main(String[] args) {
        ListNode<Integer> head = generateList(5);
        System.out.println(head.data);
        ListNode<Integer> tail = reverseList(head);
        System.out.println(tail.data);
    }
}
