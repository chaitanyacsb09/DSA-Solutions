// User function Template for Java

class Solution {
    public static int floor(Node root, int x) {
        // Code here
        Node curr = root;
        Node floor = null;
        while(curr != null){
            if(curr.data > x){
                curr = curr.left;
            }
            else if(curr.data == x){
                return curr.data;
            }
            else{
                floor = curr;
                curr = curr.right;
            }
        }
        return floor != null ? floor.data : -1;
    }
}