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
class BSTIterator {
    //Max Nodes Stored : O(H)
    private Stack<TreeNode> nodes;
    public BSTIterator(TreeNode root) {
        nodes = new Stack<>();
        //Push all left nodes, sorted inorder
        while(root != null){
            nodes.push(root);
            root = root.left;
        }
    }
    
    public int next() {
        TreeNode top = nodes.pop();
        TreeNode curr = top.right;
        while(curr != null){
            nodes.push(curr);
            curr = curr.left;
        }
        return top.val;
    }
    
    public boolean hasNext() {
        return !nodes.isEmpty();
    }
}

/**
 * Your BSTIterator object will be instantiated and called as such:
 * BSTIterator obj = new BSTIterator(root);
 * int param_1 = obj.next();
 * boolean param_2 = obj.hasNext();
 */