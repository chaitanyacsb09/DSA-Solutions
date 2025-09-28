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
            if(!isVisited[i] && detectCycleBfs(i, V, isVisited, adjMap)){
                return true;
            }
        }
        return false
    }
    
    private boolean detectCycleBfs(int source, int V, boolean[]isVisited, Map<Integer, List<Integer>> adjMap){
        
        Queue<Pair> q = new LinkedList<>();
        q.add(new Pair(source, -1));
        isVisited[source]=true;
        while(!q.isEmpty()){
            int node = q.peek().first;
            int par = q.peek().second;
            q.remove();
            
            for(int nbr: adjMap.getOrDefault(node, new ArrayList<Integer>())){
                if(!isVisited[nbr]){
                    q.add(new Pair(nbr, node));
                    isVisited[nbr] = true;
                }
                else if(nbr != par){
                    return true;
                }
            }
            
        }
        
        return false;
    }
}