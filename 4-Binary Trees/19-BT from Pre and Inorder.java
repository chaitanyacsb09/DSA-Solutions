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
    private TreeNode buildTreeUtil(int preStart, int preEnd, int inStart, int inEnd, int[] preorder, int[] inorder, Map<Integer, Integer> invInorderMap){
        if(preStart > preEnd || inStart > inEnd){
            return null;
        }
        TreeNode curr = new TreeNode(preorder[preStart]);
        int idxInInorder = invInorderMap.get(curr.val);
        int leftSubTreeSize = idxInInorder - inStart;

        curr.left = buildTreeUtil(
            preStart + 1, preStart + leftSubTreeSize, 
            inStart, idxInInorder - 1, 
            preorder, inorder, invInorderMap
            );
        curr.right = buildTreeUtil(
            preStart + leftSubTreeSize + 1, preEnd, 
            idxInInorder + 1, inEnd, 
            preorder, inorder, invInorderMap);

        return curr;
    }
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        Map<Integer, Integer> invInorderMap = new HashMap<>();
        for(int i = 0; i < inorder.length; i++){
            invInorderMap.put(inorder[i], i);
        }
        return buildTreeUtil(0, preorder.length-1, 0, inorder.length-1, preorder, inorder, invInorderMap);
    }
}