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
    private int maxPathSumVal = Integer.MIN_VALUE;
    private int maxPathSumUtil(TreeNode curr){
        if(curr == null) return 0;

        //-ve sums will only decrease the value, therefore skip them
        int left = Math.max(0, maxPathSumUtil(curr.left));
        int right = Math.max(0, maxPathSumUtil(curr.right));

        //Considering curr node as curving point
        maxPathSumVal = Math.max(maxPathSumVal, left + curr.val + right);

        return curr.val + Math.max(left, right);
    }
    public int maxPathSum(TreeNode root) {
        maxPathSumUtil(root);
        return maxPathSumVal;
    }
}