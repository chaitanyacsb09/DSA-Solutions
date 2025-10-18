/*
class Node {
    int data;
    Node left, right;

    public Node(int d) {
        data = d;
        left = right = null;
    }
}
*/

class Solution {
    private boolean isLeaf(Node n){
        return n.left == null && n.right == null;
    }
    //Not The Left View
    private void addLeftBoundaryNodes(Node root, ArrayList<Integer> boundaryNodes){
        Node curr = root.left;
        while(curr != null){
            if(!isLeaf(curr)){
                boundaryNodes.add(curr.data);
            }
            //If left child avl, then move to that, otherwise to the right child
            if(curr.left != null){
                curr = curr.left;
            }
            else
                curr = curr.right;
        }
        return;
    }
    //Not The Right View
    private void addRightBoundaryNodes(Node root, ArrayList<Integer> boundaryNodes){
        Node curr = root.right;
        ArrayList<Integer> temp = new ArrayList<>();
        while(curr != null){
            if(!isLeaf(curr)){
                temp.add(curr.data);
            }
            //If right child avl, then move to that, otherwise to the left child
            if(curr.right != null){
                curr = curr.right;
            }
            else
                curr = curr.left;
        }
        for(int i = temp.size() - 1; i >= 0; i--){
            boundaryNodes.add(temp.get(i));
        }
        return;
    }
    //Not the Bottom View
    private void addLeafNodes(Node root, ArrayList<Integer> boundaryNodes){
        if(root == null) return;
        if(isLeaf(root)){
            boundaryNodes.add(root.data);
            return;
        }
        addLeafNodes(root.left, boundaryNodes);
        addLeafNodes(root.right, boundaryNodes);
    }
    ArrayList<Integer> boundaryTraversal(Node root) {
        // code here
        ArrayList<Integer> boundaryOrder = new ArrayList<>();
        if(root == null) return boundaryOrder;
        
        if(!isLeaf(root))
            boundaryOrder.add(root.data);
        
        addLeftBoundaryNodes(root, boundaryOrder);
        addLeafNodes(root, boundaryOrder);
        addRightBoundaryNodes(root, boundaryOrder);
        return boundaryOrder;
    }
}