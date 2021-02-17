package us.matthey.coco.algorithm.epi.ch15binarysearchtrees;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BinarySearchTrees {
    public static class BSTNode<T> {
        public T data;
        public BSTNode<T> left, right;

        public BSTNode(T data, BSTNode<T> left, BSTNode<T> right) {
            this.data = data;
            this.left = left;
            this.right = right;
        }
    }

    public static BSTNode<Integer> searchBST(BSTNode<Integer> tree, int key) {
        if (tree == null || tree.data == key) {
            return tree;
        }
        return key < tree.data ? searchBST(tree.left, key) : searchBST(tree.right, key);
    }

    public static boolean isBinaryTreeBSTRecursive(BSTNode<Integer> tree) {
        return areKeysInRange(tree, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    public static boolean areKeysInRange(BSTNode<Integer> tree, Integer lower, Integer upper) {
        if (tree == null) {
            return true;
        } else if (Integer.compare(tree.data, lower) < 0 || Integer.compare(tree.data, upper) > 0) {
            return false;
        }
        return areKeysInRange(tree.left, lower, tree.data) && areKeysInRange(tree.right, tree.data, upper);
    }

    public static class QueueEntry {
        public BSTNode<Integer> treeNode;
        public Integer lowerBound, upperBound;

        public QueueEntry(BSTNode<Integer> treeNode, Integer lowerBound, Integer upperBound) {
            this.treeNode = treeNode;
            this.lowerBound = lowerBound;
            this.upperBound = upperBound;
        }
    }

    public static boolean isBinaryTreeBSTQueue(BSTNode<Integer> tree) {
        Queue<QueueEntry> BFSQueue = new LinkedList<>();
        BFSQueue.add(new QueueEntry(tree, Integer.MIN_VALUE, Integer.MAX_VALUE));
        QueueEntry headEntry;
        while ((headEntry = BFSQueue.poll()) != null) {
            if (headEntry.treeNode != null) {
                if (headEntry.treeNode.data < headEntry.lowerBound || headEntry.treeNode.data > headEntry.upperBound) {
                    return false;
                }
                BFSQueue.add(new QueueEntry(headEntry.treeNode.left, headEntry.lowerBound, headEntry.treeNode.data));
                BFSQueue.add(new QueueEntry(headEntry.treeNode.right, headEntry.treeNode.data, headEntry.upperBound));
            }
        }
        return true;
    }

    public static BSTNode<Integer> findFirstGreaterThanK(BSTNode<Integer> tree, Integer k) {
        BSTNode<Integer> subtree = tree, firstSoFar = null;
        while (subtree != null) {
            if (subtree.data > k) {
                firstSoFar = subtree;
                subtree = subtree.left;
            } else {
                subtree = subtree.right;
            }
        }
        return subtree;
    }

    public static List<Integer> findKLargestInBST(BSTNode<Integer> tree, int k) {
        List<Integer> KLargestElements = new ArrayList<>();
        findKLargestInBSTHelper(tree, k, KLargestElements);
        return KLargestElements;
    }

    private static void findKLargestInBSTHelper(BSTNode<Integer> tree, int k, List<Integer> KLargestElements) {
        if (tree != null && KLargestElements.size() < k) {
            findKLargestInBSTHelper(tree.right, k, KLargestElements);
            if (KLargestElements.size() < k) {
                KLargestElements.add(tree.data);
                findKLargestInBSTHelper(tree.left, k, KLargestElements);
            }
        }
    }

    public static BSTNode<Integer> findLCA(BSTNode<Integer> tree, BSTNode<Integer> s, BSTNode<Integer> b) {
        BSTNode<Integer> p = tree;
        while (p.data < s.data || p.data > b.data) {
            while (p.data < s.data) p = p.right;
            while (p.data > b.data) p = p.left;
        }
        return p;
    }

    public static BSTNode<Integer> rebuildBSTFromPreorderN2(List<Integer> preorderSequence) {
        return rebuildBSTFromPreorderHelper(preorderSequence, 0, preorderSequence.size());
    }

    private static BSTNode<Integer> rebuildBSTFromPreorderHelper(List<Integer> preorderSequence, int start, int end) {
        if (start >= end) return null;
        int transit = start + 1;
        while (transit < end && Integer.compare(preorderSequence.get(transit), preorderSequence.get(start)) < 0) {
            ++transit;
        }
        return new BSTNode<>(preorderSequence.get(start),
                rebuildBSTFromPreorderHelper(preorderSequence, start + 1, transit),
                rebuildBSTFromPreorderHelper(preorderSequence, transit, end));
    }
    
    private static Integer rootIdx;

    public static BSTNode<Integer> rebuildBSTFromPreorderN(List<Integer> preorderSequence) {
        rootIdx = 0;
        return rebuildBSTFromPreorderOnValueRange(preorderSequence, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    private static BSTNode<Integer> rebuildBSTFromPreorderOnValueRange(List<Integer> preorderSequence, int lowerBound, int upperBound) {
        if (rootIdx == preorderSequence.size()) return null;
        Integer root = preorderSequence.get(rootIdx);
        if (rootIdx < lowerBound || rootIdx > upperBound) return null;
        ++rootIdx;
        BSTNode<Integer> leftSubtree = rebuildBSTFromPreorderOnValueRange(preorderSequence, lowerBound, root);
        BSTNode<Integer> rightSubtree = rebuildBSTFromPreorderOnValueRange(preorderSequence, root, upperBound);
        return new BSTNode<>(root, leftSubtree, rightSubtree);
    }

}
