/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root == null || root == p || root == q) return root;

        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);

        //Both non-null, there one descendants is in L, and one is in R, therefor curr node is LCA
        if(left != null && right != null){
            return root;
        }
        
        //Either L or R, returned Non-null value, therefore either LCA, or P or Q has been found in one of the subtrees
        if(left == null){
            return right;
        }
        return left;
    }
}