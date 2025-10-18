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
class NodeInfo{
    int val, row, col;
    NodeInfo(int val, int row, int col){
        this.val = val;
        this.row = row;
        this.col = col;
    }
}
class Solution {
    private void populateNodeInfoList(TreeNode curr, int rowIdx, int colIdx, List<NodeInfo> nodeInfoList){
        if(curr == null) return;
        nodeInfoList.add(
           new NodeInfo(curr.val, rowIdx, colIdx)
        );
        populateNodeInfoList(curr.left, rowIdx + 1, colIdx - 1, nodeInfoList);
        populateNodeInfoList(curr.right, rowIdx + 1, colIdx + 1, nodeInfoList);
    }
    public List<List<Integer>> verticalTraversal(TreeNode root) {
        List<NodeInfo> nodeInfoList = new ArrayList<>();
        populateNodeInfoList(root, 0, 0, nodeInfoList);

        nodeInfoList.sort((a,b) -> {
            if(a.col != b.col) return Integer.compare(a.col, b.col);
            else if(a.row != b.row) return Integer.compare(a.row, b.row);
            else return Integer.compare(a.val, b.val);
        });

        List<List<Integer>> verticalOrder = new ArrayList<>();
        int currCol = -1001;
        List<Integer> currColNodes = new ArrayList<>();
        
        for(NodeInfo n : nodeInfoList){
            if(currCol != n.col){
                if(!currColNodes.isEmpty())
                verticalOrder.add(currColNodes);
                currColNodes = new ArrayList<>();
                currCol = n.col;
            }
            currColNodes.add(n.val);
        }
        verticalOrder.add(currColNodes);
        return verticalOrder;        
    }
}