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
        TreeNode curr = root;
        while(curr != null){
            if(p.val < curr.val && q.val < curr.val){
                curr = curr.left;
            }
            else if(p.val > curr.val && q.val > curr.val){
                curr = curr.right;
            }
            else{
                return curr; //LCA Found
            }
        }
        return null;
    }
}

class Solution {
    public TreeNode lowestCommonAncestor(TreeNode curr, TreeNode p, TreeNode q) {
        if(curr == null || curr == p || curr == q){
            return curr;
        }
        
        //Either P in LST/CURR and Q in RST/CURR
        //OR Q in LST/CURR and P in RST/CURR
        if((p.val <= curr.val && curr.val <= q.val) || (q.val <= curr.val && curr.val <= p.val)){
            return curr;
        }
        TreeNode left = null, right = null;
        if(curr.val > p.val || curr.val > q.val){
            left = lowestCommonAncestor(curr.left, p, q);
        }
        if(curr.val < p.val || curr.val < q.val){
            right = lowestCommonAncestor(curr.right, p, q);
        }

        //Found one in lst and other in rst, therefore curr is LCA
        return left == null ? right : left;
    }
}
//Better Implementation:
class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || p == null || q == null) {
            return null;
        }

        // Both nodes in left subtree
        if (p.val < root.val && q.val < root.val) {
            return lowestCommonAncestor(root.left, p, q);
        }

        // Both nodes in right subtree
        if (p.val > root.val && q.val > root.val) {
            return lowestCommonAncestor(root.right, p, q);
        }

        // Split point, current root is LCA
        return root;
    }
}