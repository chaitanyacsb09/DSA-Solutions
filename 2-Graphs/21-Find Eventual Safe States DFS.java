class Solution {
    boolean markSafeNodes(int node, int[] visited, int[][] graph) {
        if (visited[node] == 1) return false; // in current path, cycle detected
        if (visited[node] == 2) return true;  // already processed and safe

        visited[node] = 1; // mark as visiting
        for (int nbr : graph[node]) {
            if (!markSafeNodes(nbr, visited, graph)) return false; // propagate unsafety
        }
        visited[node] = 2; // safe node
        return true;
    }

    public List<Integer> eventualSafeNodes(int[][] graph) {
        int numNodes = graph.length;
        int[] visited = new int[numNodes];
        for(int node = 0; node < numNodes; node++){
            if(visited[node] == 0){
                 markSafeNodes(node, visited, graph);
            }
        }
        List<Integer> safeStates = new ArrayList<>();
        for(int i = 0; i < numNodes; i++){
            if(visited[i] == 2){
                safeStates.add(i);
            }
        }
        return safeStates;
    }
}