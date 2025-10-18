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
    //Morris Inorder Traversal
    public List<Integer> inorderTraversal(TreeNode root) {
        TreeNode curr = root;
        List<Integer> inorder = new ArrayList<>();
        while(curr != null){
            if(curr.left == null){
                inorder.add(curr.val);
                curr = curr.right;
                continue;
            }
        
            //LST Exists, therefore find the RightMost El
            TreeNode rightMost = curr.left;
            //Either the rightMost should be null or rightMost should be pointing at curr
            while(rightMost.right != null && rightMost.right != curr){
                rightMost = rightMost.right;
            }

            //rightMost not null and points to curr, only possible if this link was created by this program in earlier steps
            //In which case it will be pointing to the curr, and LST is explored, therefore add node, and move right
            if(rightMost.right == curr){
                inorder.add(curr.val);
                rightMost.right = null;
                curr = curr.right;
            }
            else{
                //If rightMost == null, then LST is unExplored, create link with RST, and move to LST
                rightMost.right = curr;
                curr = curr.left;
            }           
        }
        return inorder;
    }
}