class Solution {
    public static ArrayList<Integer> topoSort(int V, int[][] edges) {
        // code here
        Map<Integer, List<Integer>> adjMap = new HashMap<>();
        for(int[] edge : edges){
            int from = edge[0], to = edge[1];
            adjMap.computeIfAbsent(from, k -> new ArrayList<Integer>()).add(to);
        }
        
        boolean[] isVisited = new boolean[V];
        //Given a topoSort order, how would you check its correctness
        ArrayList<Integer> topoSort = new ArrayList<Integer>();
        for(int i = 0; i < V; i++){
            if(isVisited[i]){
                continue;
            }
            dfsTopo(i, isVisited, topoSort, adjMap);
        }
        Collections.reverse(topoSort);
        return topoSort;
    }
    
    private static void dfsTopo(int node, boolean[] isVisited, ArrayList<Integer> topoSort, Map<Integer, List<Integer>> adjMap){
        isVisited[node] = true;
        for(int nbr: adjMap.getOrDefault(node, new ArrayList<>())){
            if(!isVisited[nbr]){
                dfsTopo(nbr, isVisited, topoSort, adjMap);
            }
        }
        topoSort.add(node);
        return;
     }
}