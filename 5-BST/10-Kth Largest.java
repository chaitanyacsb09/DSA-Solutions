/*
class Node
{
    int data;
    Node left;
    Node right;
    Node(int data)
    {
        this.data = data;
        left=null;
        right=null;
    }
}
*/
class Solution {
    private int count = 0;
    // return the Kth largest element in the given BST rooted at 'root'
    public int kthLargest(Node root, int k) {
        // Your code here
        if(root == null){
            return -1;
        }
        int right = kthLargest(root.right, k); 
        if(right != -1){
            return right; 
        }
        if(++count == k){
            return root.data; 
        }
        return kthLargest(root.left, k);
    }
}