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
    public List<Integer> rightSideView(TreeNode root) {
        if(root == null) return new ArrayList<>();
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        List<Integer> rightView = new ArrayList<>();
        
        while(!q.isEmpty()){
            int size = q.size();
            while(size > 0){
                TreeNode curr = q.poll();
                if(size == 1){
                    rightView.add(curr.val);
                }
                if(curr.left != null) q.add(curr.left);
                if(curr.right != null) q.add(curr.right);
                size--;
            }
            
        }
        return rightView;
    }
}

//------RECURSIVE
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
    private void computeRightView(TreeNode curr, List<Integer> rightView, int level){
        if(curr == null) return;
        if(level == rightView.size()){
            rightView.add(curr.val);
        }
        computeRightView(curr.right, rightView, level+1);
        computeRightView(curr.left, rightView, level+1);
    }
    public List<Integer> rightSideView(TreeNode root) {
        
        List<Integer> rightView = new ArrayList<>();
        computeRightView(root, rightView, 0);
        return rightView;
    }
}-