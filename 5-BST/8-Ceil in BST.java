/* class Node {
    int data;
    Node left, right;

    Node(int data) {
        this.data = data;
        left = right = null;
    }
} */

class Solution {
    int findCeil(Node root, int x) {
        // code here
        Node curr = root;
        Node ceil = null;
        while(curr != null){
            if(curr.data < x){
                curr = curr.right;
            }
            else if(curr.data == x){
                return curr.data;
            }
            else{
                ceil = curr;
                curr = curr.left;
            }
        }
        return ceil != null ? ceil.data : -1;
    }
}