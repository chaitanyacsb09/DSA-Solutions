class Solution {
    public List<Integer> eventualSafeNodes(int[][] graph) {
        int numNodes = graph.length;
        int[] inDegree = new int[numNodes];
        boolean[] isSafe = new boolean[numNodes];
        List<Integer>[] revGraph = new ArrayList[numNodes];
        for(int i = 0; i < numNodes; i++){
            revGraph[i] = new ArrayList<Integer>();
        }
        for(int i = 0; i < numNodes; i++){
            for(int adj: graph[i]){
                revGraph[adj].add(i);
                inDegree[i]++;
            }
        }
        
        Queue<Integer> q = new LinkedList<>();
        for(int i = 0; i < numNodes; i++){
            if(inDegree[i] == 0) q.add(i);
        }
        while(!q.isEmpty()){
            int currSafeNode = q.poll();
            isSafe[currSafeNode] = true;
            for(int adj : revGraph[currSafeNode]){
                if(--inDegree[adj] == 0){
                    q.add(adj);
                }
            } 
        }

        List<Integer> safeStates = new ArrayList<>();
        for(int i = 0; i < numNodes; i++){
            if(isSafe[i] == true){
                safeStates.add(i);
            }
        }
        return safeStates;
    }
}