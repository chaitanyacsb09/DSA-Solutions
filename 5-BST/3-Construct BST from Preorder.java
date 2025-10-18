//O(N)
class Solution {
    public TreeNode bstFromPreorder(int[] preorder) {
        int[] i = new int[]{0};
        return buildBST(preorder, 10001, i);
    }
    //The following function will only handle/create node for values less bound
    private TreeNode buildBST(int[] preorder, int bound, int[] i){
        if(i[0] == preorder.length || preorder[i[0]] > bound){
            //Either invalid idx, or curr.val is greater than bound
            return null;
        }
        TreeNode curr = new TreeNode(preorder[i[0]++]);
        //The nodes should only be created with values less curr, in LST
        curr.left = buildBST(preorder, curr.val, i);

        //The values possible for LST, are already over, and i is now pointing to the element, which should exist in RST
        curr.right = buildBST(preorder, bound, i);

        return curr;
    }
}
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
    private TreeNode buildBST(int[] preorder, int preStart, int preEnd, int[] inorder,int inStart, int inEnd, Map<Integer, Integer> invMap){
        if(preStart > preEnd || inStart > inEnd){
            return null;
        }
        TreeNode curr = new TreeNode(preorder[preStart]);
        int idxInInorder = invMap.get(preorder[preStart]);
        int lstSize = idxInInorder - inStart; //Not Including curr

        curr.left = buildBST(preorder, preStart + 1, preStart + lstSize,
                            inorder, inStart, idxInInorder - 1, invMap);
        
        curr.right = buildBST(preorder, preStart + lstSize + 1, preEnd, 
                              inorder, idxInInorder + 1, inEnd, invMap);
        
        return curr;
    }
    public TreeNode bstFromPreorder(int[] preorder) {
        int[] inorder = Arrays.copyOf(preorder, preorder.length);
        Arrays.sort(inorder);
        Map<Integer, Integer> invMap = new HashMap<>();
        for(int i = 0; i < preorder.length; i++){
            invMap.put(inorder[i], i);
        }
        return buildBST(preorder, 0, preorder.length - 1 , inorder, 0, inorder.length - 1, invMap);
    }
}