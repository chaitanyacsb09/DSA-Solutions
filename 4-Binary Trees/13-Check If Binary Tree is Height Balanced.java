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
    private boolean isBalanced = true;
    private int getDepthAndMarkIsBalanced(TreeNode curr){
        if(curr == null) return 0;
        
        int lDepth = getDepthAndMarkIsBalanced(curr.left);
        int rDepth = getDepthAndMarkIsBalanced(curr.right);

        boolean isSubTreeBalanced = Math.abs(lDepth-rDepth) <= 1;

        isBalanced &= isSubTreeBalanced;

        //+1 for curr node
        return 1 + Math.max(lDepth, rDepth);
    }
    public boolean isBalanced(TreeNode root) {
        getDepthAndMarkIsBalanced(root);
        return isBalanced;
    }
}