/*
class Node{
    int data;
    Node left,right;

    Node(int key)
    {
        data = key;
        left = right = null;
    }
}
*/
class Solution {
    private boolean isLeaf(Node curr){
        return curr.left == null && curr.right == null;
    }
    private boolean isChildrenSumPropertyFollowed = true;
    private int checkChildrenSumUtil(Node curr){
        if(curr == null) return 0;
        
        if(isLeaf(curr)){
            return curr.data;
        }
        
        int left = checkChildrenSumUtil(curr.left);
        int right = checkChildrenSumUtil(curr.right);
        
        if(curr.data != left + right){
            // System.out.println("CD: " + curr.data + " L: " + left + " R: " + right);
            isChildrenSumPropertyFollowed = false;
        }

        return curr.data;
    }
    public boolean isSumProperty(Node root) {
        // Read Que Properly, it was only mentioned to check sum of curr node, with its children only, not the entire L an R subtrees sum
        checkChildrenSumUtil(root);
        return isChildrenSumPropertyFollowed;
    }
}