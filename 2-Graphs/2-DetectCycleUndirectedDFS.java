class Pair{
    public int first;
    public int second;
    Pair(int _first, int _second){
        first = _first;
        second = _second;
    }
}
class Solution {
    public boolean isCycle(int V, int[][] edges) {
        // Code here
        Map<Integer, List<Integer>> adjMap = new HashMap<>();
        for(int[] edge: edges){
            int from = edge[0], to = edge[1];
            adjMap.computeIfAbsent(from, k -> new ArrayList<Integer>()).add(to);
            adjMap.computeIfAbsent(to, k -> new ArrayList<Integer>()).add(from);
        }
        boolean[] isVisited = new boolean[V];
        for(int i = 0; i < V; i++){
            if(!isVisited[i] && detectCycleDfs(i, -1, isVisited, adjMap)){
                return true;
            }
        }
        return false;
    }
    
    private boolean detectCycleDfs(int node, int par, boolean[]isVisited, Map<Integer, List<Integer>> adjMap){
        isVisited[node] = true;
        for(int nbr: adjMap.getOrDefault(node, new ArrayList<Integer>())){
            if(!isVisited[nbr]){
                if(detectCycleDfs(nbr, node, isVisited, adjMap))return true;
            }
            else if(nbr != par){
                return true;
            }
        }
        return false;
    }
}