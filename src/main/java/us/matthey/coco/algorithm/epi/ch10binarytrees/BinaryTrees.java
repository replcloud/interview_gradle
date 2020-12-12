package us.matthey.coco.algorithm.epi.ch10binarytrees;

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
}
