/*
class Node
{
    int data; //data of the node
    int hd; //horizontal distance of the node
    Node left, right; //left and right references

    // Constructor of tree node
    public Node(int key)
    {
        data = key;
        hd = Integer.MAX_VALUE;
        left = right = null;
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
    private void populatePosMap(Node curr, int currLevel, int verticalPos, Map<Integer, Pair<Integer, Node>> posMap){
        if(curr == null) return;
        
        // <= : because in question it was asked to consider the later element for the clashing nodes at same vertical level
        if(!posMap.containsKey(verticalPos) || posMap.get(verticalPos).first <= currLevel){
            posMap.put(verticalPos, new Pair(currLevel, curr));
        }
        populatePosMap(curr.left, currLevel + 1, verticalPos - 1, posMap);
        populatePosMap(curr.right, currLevel + 1, verticalPos + 1, posMap);
    }
    
    public ArrayList<Integer> bottomView(Node root) {
        // Code here
        Map<Integer, Pair<Integer, Node>> posMap = new TreeMap<>();
        populatePosMap(root, 0, 0, posMap);
        ArrayList<Integer> bottomViewList = new ArrayList<>();
        for(Pair<Integer, Node> p : posMap.values()){
            bottomViewList.add(p.second.data);
        }
        return bottomViewList;
    }
}