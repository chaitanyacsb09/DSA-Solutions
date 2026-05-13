class DistanceNodePair{
    int distanceToReach;
    int node;
    public DistanceNodePair(int distanceToReach, int node){
        this.distanceToReach = distanceToReach;
        this.node = node;
    }
}
class Solution {
    public int[] dijkstra(int V, int[][] edges, int src) {
        // code here
        List<List<DistanceNodePair>> adj = new ArrayList<>();
        int[] distances = new int[V];
        for(int i = 0; i < V; i++){
            distances[i] = (int)1e9;
            adj.add(new ArrayList<>());
        }
        
        for(int[] edge: edges){
            int nodeA = edge[0], nodeB = edge[1];
            int distance = edge[2];
            adj.get(nodeA).add(new DistanceNodePair(distance, nodeB));
            adj.get(nodeB).add(new DistanceNodePair(distance, nodeA));
        }
        
        PriorityQueue<DistanceNodePair> pq = new PriorityQueue<>((x, y) -> x.distanceToReach - y.distanceToReach);
        
        pq.add(new DistanceNodePair(0, src));
        distances[src] = 0;
        
        /*
        Input: V = 3, edges[][] = [[0, 1, 1], [1, 2, 3], [0, 2, 6]], src = 2
                     0   1   2
        distances = [1e9, 1e9, 1e9] | pq =  
        0: (1,1) (6,2)
        1: (1,0) (3,2)
        2:  (3,1) (6,0)
        -------------------------------
    
        */
        while(!pq.isEmpty()){
            DistanceNodePair currentDistanceNodePair = pq.poll();
            int dist = currentDistanceNodePair.distanceToReach, node = currentDistanceNodePair.node;
            
            for(DistanceNodePair nbrP : adj.get(node)){
                int nbr = nbrP.node, nbrDist = nbrP.distanceToReach;
                if(distances[node] + nbrDist < distances[nbr]){
                    distances[nbr] = distances[node] + nbrDist;
                    pq.add(new DistanceNodePair(distances[nbr], nbr));
                }
            }
        }
        
        return distances[V-1] == 1e9 ? new int[]{-1} : distances;
    }
}