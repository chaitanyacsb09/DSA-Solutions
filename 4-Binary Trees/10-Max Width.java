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
class Pair<A,B>{
    A first;
    B second;
    Pair(A first, B second){
        this.first = first;
        this.second = second;
    }
}
class Solution {
    public int widthOfBinaryTree(TreeNode root) {
        Queue<Pair<Integer, TreeNode>> q = new LinkedList<>();
        q.add(new Pair(0, root));
        int maxWidth = Integer.MIN_VALUE;
        int leftMostIdx = -1, rightMostIdx = -1;
        while(!q.isEmpty()){
            int size = q.size();
            leftMostIdx = q.peek().first;
            while(size > 0){
                int idx = q.peek().first;
                TreeNode node = q.peek().second;
                q.poll();
                rightMostIdx = idx;
                if(node.left != null){
                    q.add(new Pair(idx * 2 + 1, node.left));
                } 
                if(node.right != null){
                    q.add(new Pair(idx * 2 + 2, node.right));
                }
                size--;
            }
            // System.out.println("R: " + rightMostIdx + " L: " + leftMostIdx);
            maxWidth = Math.max(maxWidth, rightMostIdx - leftMostIdx + 1);
        }
        return maxWidth;
    }
}