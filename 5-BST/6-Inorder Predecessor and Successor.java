/* BST Node
class Node {
    int data;
    Node left, right;
    Node(int x) {
        data = x;
        left = right = null;
    }
} */

class Solution {
    private Node findSuccessor(Node curr,  int key){
        if(curr == null) return null;
        Node successor = null;
        while(curr != null){
            if(curr.data >  key){
                successor = curr;
                curr = curr.left;  
            }
            else{
                curr = curr.right;
            }
        }   
        return successor;
    }
    private Node findPredecessor(Node curr,  int key){
        if(curr == null) return null;
        Node predecessor = null;
        while(curr != null){
            if(curr.data < key){
                predecessor = curr;
                curr = curr.right;
            }
            else{
                curr = curr.left;
            }
        }
        return predecessor;
    }
    
    public ArrayList<Node> findPreSuc(Node root, int key) {
        // code here
        Node successor = findSuccessor(root, key);
        Node predecessor = findPredecessor(root, key);
        return new ArrayList<>(Arrays.asList(predecessor, successor));
    }
}