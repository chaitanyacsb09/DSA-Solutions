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
    private TreeNode buildBST(int[] nums, int startIdx, int endIdx){
        if(startIdx > endIdx){
            return null;
        }
        int mid = (startIdx + endIdx) / 2; //Integer Overflow could happen
        TreeNode curr = new TreeNode(nums[mid]);
        curr.left = buildBST(nums, startIdx, mid - 1);
        curr.right = buildBST(nums, mid + 1, endIdx);
        return curr;
    }
    public TreeNode sortedArrayToBST(int[] nums) {
        return buildBST(nums, 0, nums.length - 1);
    }
}