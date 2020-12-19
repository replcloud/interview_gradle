package us.matthey.coco.algorithm.epi.ch9stackesandqueues;

import java.util.Deque;
import java.util.LinkedList;

public class Stack {
    /* O(1) in time complexity and O(n) in space complexity */
    private static class ElementWithCachedMax {
        public Integer element;
        public Integer max;

        public ElementWithCachedMax(Integer element, Integer max) {
            this.element = element;
            this.max = max;
        }
    }

    private Deque<ElementWithCachedMax> elementWithCachedMax = new LinkedList<>();

    public boolean empty() {
        return elementWithCachedMax.isEmpty();
    }

    public Integer max() {
        if (empty()) {
            throw new IllegalStateException("max(): empty stack");
        }
        return elementWithCachedMax.peekFirst().max;
    }

    public Integer pop() {
        if (empty()) {
            throw new IllegalStateException("pop(): empty stack");
        }
        return elementWithCachedMax.removeFirst().element;
    }

    public void push(Integer x) {
        elementWithCachedMax.addFirst(new ElementWithCachedMax(x, Math.max(x, empty() ? x : max())));
    }

    /* Improve the  space */
    private static class MaxWithCount {
        public Integer max;
        public Integer count;

        public MaxWithCount(Integer max, Integer count) {
            this.max = max;
            this.count = count;
        }
    }

    public static class StackImprovedSpace {
        private Deque<Integer> element = new LinkedList<>();
        private Deque<MaxWithCount> cachedMaxWithCount = new LinkedList<>();

        public boolean empty() {
            return element.isEmpty();
        }

        public Integer max() {
            if (empty()) {
                throw new IllegalStateException("max(): empty stack");
            }
            return cachedMaxWithCount.peekFirst().max;
        }

        public Integer pop() {
            if (empty()) {
                throw new IllegalStateException("pop(): empty stack");
            }
            Integer popElement = element.removeFirst();
            if (popElement.equals(max())) {
                cachedMaxWithCount.peekFirst().count = cachedMaxWithCount.peekFirst().count - 1;
                if (cachedMaxWithCount.peekFirst().count.equals(0)) {
                    cachedMaxWithCount.removeFirst();
                }
            }
            return popElement;
        }

        public void push(Integer x) {
            element.addFirst(x);
            if (!cachedMaxWithCount.isEmpty()) {
                if (Integer.compare(x, cachedMaxWithCount.peekFirst().max) == 0) {
                    cachedMaxWithCount.peekFirst().count = cachedMaxWithCount.peekFirst().count + 1;
                } else if (Integer.compare(x, cachedMaxWithCount.peekFirst().max) > 0) {
                    cachedMaxWithCount.addFirst(new MaxWithCount(x, 1));
                }
            } else {
                cachedMaxWithCount.addFirst(new MaxWithCount(x, 1));
            }
        }
    }
}
