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
//DO A DRY RUN FOR POST ORDER CREATION
class Solution {
    private TreeNode buildTreeUtil(int postStart, int postEnd, int inStart, int inEnd, int[] postorder, int[] inorder, Map<Integer, Integer> invInorderMap){
        if(postStart > postEnd || inStart > inEnd){
            return null;
        }

        TreeNode curr = new TreeNode(postorder[postEnd]);
        int idxInInorder = invInorderMap.get(curr.val);
        int leftSubTreeSize = idxInInorder - inStart;

        curr.left = buildTreeUtil(
            postStart, postStart + leftSubTreeSize - 1, 
            inStart, idxInInorder - 1, 
            postorder, inorder, invInorderMap
            );

        curr.right = buildTreeUtil(
            postStart + leftSubTreeSize, postEnd - 1, 
            idxInInorder + 1, inEnd, 
            postorder, inorder, invInorderMap);

        return curr;
    }
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        Map<Integer, Integer> invInorderMap = new HashMap<>();
        for(int i = 0; i < inorder.length; i++){
            invInorderMap.put(inorder[i], i);
        }
        return buildTreeUtil(0, postorder.length-1, 0, inorder.length-1, postorder, inorder, invInorderMap);
    }
}