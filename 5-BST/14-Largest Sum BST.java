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
class SubTreeMetadata{
    int sum;
    int minVal, maxVal;
    boolean isBST;
    public SubTreeMetadata(int sum, int minVal, int maxVal, boolean isBST){
        this.sum = sum;
        this.minVal = minVal;
        this.maxVal = maxVal;
        this.isBST = isBST;
    }
} 
class Solution {
    private int maxSum = 0;
    private void log(String message){
        boolean log = false;
        if(!log) return;
        System.out.println(message);
    }
    public int maxSumBST(TreeNode root) {
        maxSumBSTUtil(root);
        return maxSum;
    }
    private SubTreeMetadata maxSumBSTUtil(TreeNode curr){
        if(curr == null){
            return new SubTreeMetadata(0, Integer.MAX_VALUE, Integer.MIN_VALUE, true);
        }
        SubTreeMetadata left = maxSumBSTUtil(curr.left);
        SubTreeMetadata right = maxSumBSTUtil(curr.right);
        // log("LMaxVal: " + left.maxVal + " CurrVal: "+ curr.val +  " RMinVal: " + right.minVal);

        if((left.isBST && right.isBST) && (left.maxVal < curr.val && curr.val < right.minVal)){
            // log("\tBST CONDITION PASSED");   
            int sum = left.sum + curr.val + right.sum;
            int min = Math.min(curr.val, left.minVal);
            int max = Math.max(curr.val, right.maxVal);
            maxSum = Math.max(maxSum, left.sum + curr.val + right.sum);
            // log("\t\tReturning: {" + sum + ", " +  min + ", " + max + "}, true"); 
            return new SubTreeMetadata(sum, min, max, true);
        }
        // log("\tBST CONDITION FAILED");
        // log("\t\tReturning: {" + 0 + ", 0, 0, false}");
        return new SubTreeMetadata(0,0,0,false);
    }
}


//TC: o(N^2) for skewed tree
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
    int maxSum = 0;
    private TreeNode getPredecessor(TreeNode root){
        TreeNode pred = root.left;
        while(pred.right != null){
            pred = pred.right;
        }
        return pred;
    }
    private TreeNode getSuccessor(TreeNode root){
        TreeNode succ = root.right;
        while(succ.left != null){
            succ = succ.left;
        }
        return succ;
    }
    private int helper(TreeNode curr){
        if(curr == null) return 0;
        int left = helper(curr.left), right = helper(curr.right);
        if(left == Integer.MIN_VALUE || right == Integer.MIN_VALUE){
            return Integer.MIN_VALUE;
        }
        if(curr.left != null){
            TreeNode pred = getPredecessor(curr);
            if(pred.val >= curr.val){
                return Integer.MIN_VALUE;
            }
        }
        if(curr.right != null){
            TreeNode succ = getSuccessor(curr);
            if(succ.val <= curr.val){
                return Integer.MIN_VALUE;
            }
        }
        int sum = left + curr.val + right;
        maxSum = Math.max(maxSum, sum);
        return sum;
    }
    public int maxSumBST(TreeNode root) {
        helper(root);
        return maxSum;
    }
}