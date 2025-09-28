class Solution {
    public static ArrayList<Integer> topoSort(int V, int[][] edges) {
        // code here
        Map<Integer, List<Integer>> adjMap = new HashMap<>();
        int[] inDegree = new int[V];
        for(int[] edge : edges){
            int from = edge[0], to = edge[1];
            adjMap.computeIfAbsent(from, k -> new ArrayList<Integer>()).add(to);
            inDegree[to]++;
        }
        Queue<Integer> q = new LinkedList<>();
        // boolean[] isVisited = new boolean[V];
        ArrayList<Integer> topoSort = new ArrayList<Integer>();
        for(int i = 0; i < V; i++){
            if(inDegree[i] == 0){
                q.add(i);
                topoSort.add(i);
                
            }
        }
        while(!q.isEmpty()){
            int node = q.poll();
            for(int nbr : adjMap.getOrDefault(node, new ArrayList<>())){
                if(--inDegree[nbr] == 0){
                    q.add(nbr);
                    topoSort.add(nbr);
                }
            }
        }
        return topoSort;
    }
}