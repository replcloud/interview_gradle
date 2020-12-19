package us.matthey.coco.algorithm.epi.ch15binarysearchtrees;

import java.util.LinkedList;
import java.util.Queue;

public class BinarySearchTrees {
    public static class BSTNode<T> {
        public T data;
        public BSTNode<T> left, right;
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
}
