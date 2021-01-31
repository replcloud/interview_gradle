package us.matthey.coco.algorithm.epi.ch10binarytrees;

import us.matthey.coco.algorithm.epi.ch15binarysearchtrees.BinarySearchTrees;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class BinaryTrees {
    public static void treetraversal(BinaryTreeNode<Integer> root) {
        if (root != null) {
            // Preorder
            System.out.print("Preorder: " + root.data);
            treetraversal(root.left);
            System.out.print("Inorder: " + root.data);
            treetraversal(root.right);
            System.out.print("Postorder: " + root.data);
        }
    }

    /* Test if a binary tree is height-balanced */
    private static class BalanceStatusWithHeight {
        public boolean balanced;
        public int height;

        public BalanceStatusWithHeight(boolean balanced, int height) {
            this.balanced = balanced;
            this.height = height;
        }
    }

    public static boolean isBalanced(BinaryTreeNode<Integer> tree) {
        return checkBalanced(tree).balanced;
    }

    private static BalanceStatusWithHeight checkBalanced(BinaryTreeNode<Integer> tree) {
        if (tree == null) {
            return new BalanceStatusWithHeight(true, -1);
        }
        BalanceStatusWithHeight leftResult = checkBalanced(tree.left);
        if (!leftResult.balanced) {
            return leftResult;
        }
        BalanceStatusWithHeight rightResult = checkBalanced(tree.right);
        if (!rightResult.balanced) {
            return rightResult;
        }
        boolean isBalanced = Math.abs(leftResult.height - rightResult.height) <= 1;
        int height = Math.max(leftResult.height, rightResult.height) + 1;
        return new BalanceStatusWithHeight(isBalanced, height);
    }

    public static int sumRootToLeaf(BinaryTreeNode<Integer> tree) {
        return sumRootToLeafHelper(tree, 0);
    }

    public static class Status {
        public int numTargetNodes;
        public BinaryTreeNode<Integer> ancestor;

        public Status(int numTargetNodes, BinaryTreeNode<Integer> node) {
            this.numTargetNodes = numTargetNodes;
            this.ancestor = node;
        }
    }

    public static BinaryTreeNode<Integer> LCA(BinaryTreeNode<Integer> tree, BinaryTreeNode<Integer> node0, BinaryTreeNode<Integer> node1) {
        return LCAHelper(tree, node0, node1).ancestor;
    }

    private static Status LCAHelper(BinaryTreeNode<Integer> tree, BinaryTreeNode<Integer> node0, BinaryTreeNode<Integer> node1) {
        if (tree == null) return new Status(0, null);
        Status leftResult = LCAHelper(tree.left, node0, node1);
        if (leftResult.numTargetNodes == 2) return leftResult;
        Status rightResult = LCAHelper(tree.left, node0, node1);
        if (rightResult.numTargetNodes == 2) return rightResult;
        int numTargetNodes = leftResult.numTargetNodes + rightResult.numTargetNodes + (tree == node0 ? 1 : 0) + (tree == node1 ? 1 : 0);
        return new Status(numTargetNodes, numTargetNodes == 2 ? tree : null);
    }

    public static BinaryTree<Integer> LCAParentPointer(BinaryTree<Integer> node0, BinaryTree<Integer> node1) {
        int depth0 = getDepth(node0);
        int depth1 = getDepth(node1);

        if (depth1 > depth0) {
            BinaryTree<Integer> temp = node0;
            node0 = node1;
            node1 = temp;
        }

        int depthDiff = Math.abs(depth0 - depth1);
        while (depthDiff-- > 0) {
            node0 = node0.parent;
            node1 = node1.parent;
        }

        while (node0 != node1) {
            node0 = node0.parent;
            node1 = node1.parent;
        }
        return node0;
    }

    private static int getDepth(BinaryTree<Integer> node) {
        int depth = 0;
        while (node.parent != null) {
            depth++;
            node = node.parent;
        }
        return depth;
    }

    private static int sumRootToLeafHelper(BinaryTreeNode<Integer> tree, int partialPathSum) {
        if (tree == null) return 0;
        partialPathSum = 2 * partialPathSum + tree.data;
        if (tree.left == null && tree.right == null) return partialPathSum;
        return sumRootToLeafHelper(tree.left, partialPathSum) +
                sumRootToLeafHelper(tree.right, partialPathSum);

    }

    public static boolean hasPathSum(BinaryTreeNode<Integer> tree, int targetSum) {
        return hasPathSumHelper(tree, 0, targetSum);
    }

    private static boolean hasPathSumHelper(BinaryTreeNode<Integer> node, int partialPathSum, int targetSum) {
        if (node == null) return false;
        partialPathSum += node.data;
        if (node.left == null && node.right == null) return partialPathSum == targetSum;
        return hasPathSumHelper(node.left, partialPathSum, targetSum) || hasPathSumHelper(node.right, partialPathSum, targetSum);
    }

    public static List<Integer> BSTInSortedOrder(BinarySearchTrees.BSTNode<Integer> tree) {
        Deque<BinarySearchTrees.BSTNode<Integer>> s = new LinkedList<>();
        BinarySearchTrees.BSTNode<Integer> curr = tree;
        List<Integer> result = new ArrayList<>();

        while (!s.isEmpty() || curr != null) {
            if (curr != null) {
                s.addFirst(curr);
                curr = curr.left;
            } else {
                curr = s.removeFirst();
                result.add(curr.data);
                curr = curr.right;
            }
        }
        return result;
    }

    public static List<Integer> preOrderTraversal(BinaryTreeNode<Integer> tree) {
        Deque<BinaryTreeNode<Integer>> path = new LinkedList<>();
        path.add(tree);
        List<Integer> result = new ArrayList<>();
        while (!path.isEmpty()) {
            BinaryTreeNode<Integer> curr = path.removeFirst();
            if (curr != null) {
                result.add(curr.data);
                path.addFirst(curr.right);
                path.addFirst(curr.left);
            }
        }
        return result;
    }

    public static class KthBinaryTreeNode<T> {
        T data;
        int size;
        KthBinaryTreeNode left, right;
    }

    public static KthBinaryTreeNode<Integer> findKthNodeBinaryTree(KthBinaryTreeNode<Integer> tree, int k) {
        KthBinaryTreeNode<Integer> iter = tree;
        while (iter != null) {
            int leftSize = iter.left != null ? iter.left.size : 0;
            if (leftSize + 1 < k) {
                k -= leftSize + 1;
                iter = iter.right;
            } else if (leftSize == k - 1) {
                return iter;
            } else {
                iter = iter.left;
            }
        }
        return null;
    }
}
