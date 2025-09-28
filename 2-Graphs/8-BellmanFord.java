// User function Template for Java

class Solution {
    public int[] bellmanFord(int V, int[][] edges, int src) {
        // code here
        int[] dist = new int[V];
        Arrays.fill(dist, (int)1e8);
        dist[src] = 0;
        //N-1 Relaxatinos
        for(int i = 1; i <= V-1; i++){
            for(int[] edge: edges){
                int u = edge[0], v = edge[1], edgW = edge[2];
                
                if(dist[u] != 1e8 && dist[u] + edgW < dist[v]){
                    dist[v] = dist[u] + edgW;
                }
            }
        }
        
        //-ve cycle detection
        for(int[] edge: edges){
            int u = edge[0], v = edge[1], edgW = edge[2];
            
            if(dist[u] != 1e8 && dist[u] + edgW < dist[v]){
                return new int[]{-1};
            }
        }
        
        return dist;
    }
}
