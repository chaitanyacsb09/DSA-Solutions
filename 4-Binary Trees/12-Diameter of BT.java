/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    private int diameter = Integer.MIN_VALUE;
    private int diameterOfBinaryTreeUtil(TreeNode curr){
        if(curr == null) return 0;
        int maxEdgesFromLeftInAPath = diameterOfBinaryTreeUtil(curr.left);
        int maxEdgesFromRightInAPath = diameterOfBinaryTreeUtil(curr.right);

        //Consider curr node as curving point, for the longest path between a node from left and right
        diameter = Math.max(diameter, maxEdgesFromLeftInAPath + maxEdgesFromRightInAPath);

        //But Return Max of (MaxEdges in a path from left or right, so that parent could be considered as curving point)
        return 1 + Math.max(maxEdgesFromLeftInAPath, maxEdgesFromRightInAPath);
    }
    public int diameterOfBinaryTree(TreeNode root) {
        diameterOfBinaryTreeUtil(root);
        return diameter;
    }
}