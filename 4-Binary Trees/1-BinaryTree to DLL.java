/*
class Node {
    int data;
    Node left, right;

    Node() {
        this.data = 0;
        this.left = this.right = null;
    }

    Node(int data) {
        this.data = data;
        this.left = this.right = null;
    }
}
*/

//TC : O(2N) SC: O(N) + O(logn)
class Solution {
    private void computeInorder(Node curr, List<Node> inorder){
        if(curr == null){
            return;
        }
        computeInorder(curr.left, inorder);
        inorder.add(curr);
        computeInorder(curr.right, inorder);
    }
    Node bToDLL(Node root) {
        // code here
        if(root == null){
            return null;
        }
        List<Node> inorder = new ArrayList<>();
        computeInorder(root, inorder);
        
        Node prev = null;
        for(Node curr : inorder){
            // System.out.println(curr.data);
            curr.left = prev;
            curr.right = null;
            if(prev != null){
                prev.right = curr;
            }
            prev = curr;
        }
        return inorder.get(0);
    }
}

//TC:O(n^2) [Skewed Tree] ?? SC: O(LOG N)
class Solution {
    private Node getRightMost(Node curr){
        while(curr.right != null){
            curr = curr.right;
        }
        return curr;
    }
    private Node getLeftMost(Node curr){
        while(curr.left != null){
            curr = curr.left;
        }
        return curr;
    }
    
    private Node btToDLLUtil(Node curr){
        if(curr == null){
            return null;
        }
        
        if(curr.left != null){
            Node left = btToDLLUtil(curr.left);
            Node inorderPredecessor = getRightMost(left);
            curr.left = inorderPredecessor;
            inorderPredecessor.right = curr;
        }
        if(curr.right != null){
            Node right = btToDLLUtil(curr.right);
            Node inorderSuccessor = getLeftMost(right);
            curr.right = inorderSuccessor;
            inorderSuccessor.left = curr;
        }
        return curr;
    }
    Node bToDLL(Node root) {
        // code here
        if(root == null){
            return null;
        }
        btToDLLUtil(root);
        while(root.left != null){
            root = root.left;
        }
        return root;
    }
}

//TC:O(N) SC: O(LOG N)
class Solution {
    private Node prev = null;
    private Node head = null;
    void convertBTToDLL(Node curr){
        if(curr == null){
            return;
        }
        
        convertBTToDLL(curr.left);
        if(prev == null){
            head = curr;
        }
        else{
            curr.left = prev;
            prev.right = curr;
        }
        prev = curr;
        convertBTToDLL(curr.right);
    }
    Node bToDLL(Node root) {
        // code here
        if(root == null){
            return null;
        }
        convertBTToDLL(root);
        return head;
    }
}