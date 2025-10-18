/*
class Node{
    int data;
    Node left;
    Node right;
    Node(int data){
        this.data = data;
        left=null;
        right=null;
    }
}
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
    // Function to return a list of nodes visible from the top view
    // from left to right in Binary Tree.
    static private void populatePosMap(Node curr, int currLevel, int verticalPos, Map<Integer, Pair<Integer, Node>> posMap){
        if(curr == null) return;
        
        if(!posMap.containsKey(verticalPos) || posMap.get(verticalPos).first > currLevel){
            posMap.put(verticalPos, new Pair(currLevel, curr));
        }
        populatePosMap(curr.left, currLevel + 1, verticalPos - 1, posMap);
        populatePosMap(curr.right, currLevel + 1, verticalPos + 1, posMap);
    }
    static ArrayList<Integer> topView(Node root) {
        // code here
        Map<Integer, Pair<Integer, Node>> posMap = new TreeMap<>();
        populatePosMap(root, 0, 0, posMap);
        ArrayList<Integer> topViewList = new ArrayList<>();
        for(Pair<Integer, Node> p : posMap.values()){
            topViewList.add(p.second.data);
        }
        return topViewList;
    }
}