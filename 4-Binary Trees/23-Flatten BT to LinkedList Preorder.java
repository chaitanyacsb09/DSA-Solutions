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
    //Think how to flatten postorder
    //Also Do a Dry Run
    public void flatten(TreeNode root) {
        if(root == null) return;
        TreeNode curr = root;
        while(curr != null){
            if(curr.left != null){
                //Find the rightmost node in LST:
                TreeNode rightMost = curr.left;
                while(rightMost.right != null){
                    rightMost = rightMost.right;
                }

                //Connect the rightMost node to the Right ST
                rightMost.right = curr.right;

                //Move the LST to right and Set Left to null, for flattening
                curr.right = curr.left;
                curr.left = null;
            }
            //Move to right, either process currently shifted LST, or move to right Part
            curr = curr.right;
        }
        return;
    }
}