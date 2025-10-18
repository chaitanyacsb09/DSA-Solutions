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
       public boolean isValidBST(TreeNode root) {
        if(root==null)return true;
        
        //The Value of a node, should always be in the exclusive range of min,max
        return isValidBSTUtil(root,Long.MIN_VALUE,Long.MAX_VALUE);
    }
    //The Value of a node, should always be in the exclusive range of min,max
    private boolean isValidBSTUtil(TreeNode curr, long min, long max){
        if(curr == null) return true;

        //If Curr Node Value is not in the exclusive range of min and max, then BST Property Fails, Return False
        if(!(min < curr.val && curr.val < max)){
            return false;
        }

        //IN LST all elements should be less than curr.val and RST all elements should be greater than curr.val
        return isValidBSTUtil(curr.left, min, curr.val) && isValidBSTUtil(curr.right, curr.val, max);
    }
}   