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
    private void postorderTraversalUtil(TreeNode curr, List<Integer> postorder){
        if(curr == null) return;
        postorderTraversalUtil(curr.left, postorder);
        postorderTraversalUtil(curr.right, postorder);
        postorder.add(curr.val);
    }
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> postorder = new ArrayList<>();
        postorderTraversalUtil(root, postorder);
        return postorder;
    }
}