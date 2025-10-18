/*
class Node
{
    int data;
    Node left, right;

    Node(int item)
    {
        data = item;
        left = right = null;
    }
}*/
/*---------------ITERATIVE--------------------------------
TC: O(N) | SC : O(N)
*/
class Solution {
    ArrayList<Integer> leftView(Node root) {
        // code here
        if(root == null) return new ArrayList<>();
        Queue<Node> q = new LinkedList<>();
        q.add(root);
        ArrayList<Integer> leftView = new ArrayList<>();
        
        while(!q.isEmpty()){
            int size = q.size();
            boolean isLeftMost = true;
            while(size > 0){
                Node curr = q.poll();
                if(isLeftMost){
                    leftView.add(curr.data);
                    isLeftMost = false;
                }
                if(curr.left != null) q.add(curr.left);
                if(curr.right != null) q.add(curr.right);
                size--;
            }
            
        }
        return leftView;
    }
}
/*---------------RECURSIVE--------------------------------
TC: O(N) | SC : O(H) -> Which can be in worst case O(N) for skewed tree, log(N) for balanced binary trees
*/
class Solution {
    private void computeLeftView(Node curr, ArrayList<Integer> leftView, int level){
        if(curr == null) return;
        if(level == leftView.size()){
            leftView.add(curr.data);
        }
        computeLeftView(curr.left, leftView, level+1);
        computeLeftView(curr.right, leftView, level+1);
    }
    ArrayList<Integer> leftView(Node root) {
        // code here
        ArrayList<Integer> leftViewList = new ArrayList<>();
        computeLeftView(root, leftViewList, 0);
        return leftViewList;
    }
}