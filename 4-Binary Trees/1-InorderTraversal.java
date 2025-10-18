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
    private void inorderTraversalUtil(TreeNode curr, List<Integer> inorder){
        if(curr == null) return;
        inorderTraversalUtil(curr.left, inorder);
        inorder.add(curr.val);
        inorderTraversalUtil(curr.right, inorder);
    }
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> inorder = new ArrayList<>();
        inorderTraversalUtil(root, inorder);
        return inorder;
    }
}