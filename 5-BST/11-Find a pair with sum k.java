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
    private Set<Integer> dataSet = new HashSet<>();
    public boolean findTarget(TreeNode root, int k) {
        if(root == null) return false;
        if(dataSet.contains(k - root.val)){
            return true;
        }
        dataSet.add(root.val);
        boolean left = findTarget(root.left, k);
        return left == true ? left : findTarget(root.right, k);        
    }
}

//WITH BST ITERATOR: O(H) SPACE if tree is not skewed
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
    public boolean findTarget(TreeNode root, int k) {
        if(root == null) return false;
        BSTIterator iterator = new BSTIterator(root, false);
        BSTIterator reverseIterator = new BSTIterator(root, true); //Reverse Inorder
        TreeNode left = iterator.next();
        TreeNode right = reverseIterator.next();

        //Failing this condition means, we have crossed the mid, therfore explored all nodes, but didn't find a pair
        while(left != null && right != null && left.val < right.val){
            int currSum = left.val + right.val;

            if(currSum == k){
                return true;
            }
            else if(currSum < k){
                left = iterator.next();
            }
            else{
                right = reverseIterator.next();
            }
        }
        return false;
    }
}

class BSTIterator {
    //Max Nodes Stored : O(H)
    private Stack<TreeNode> nodes;
    private boolean isReversed;
    public BSTIterator(TreeNode root, boolean isReversed) {
        nodes = new Stack<>();
        this.isReversed = isReversed;
        //Push all Left/Right nodes depending on isReversed, sorted inorder
        while(root != null){
            nodes.push(root);
            root = isReversed ? root.right : root.left;
        }
    }
    
    public TreeNode next() {
        if(!hasNext()){
            return null;
        }
        TreeNode top = nodes.pop();
        TreeNode curr = isReversed ? top.left : top.right;
        while(curr != null){
            nodes.push(curr);
            curr = isReversed ? curr.right : curr.left;
        }
        return top;
    }

    public boolean hasNext() {
        return !nodes.isEmpty();
    }
}