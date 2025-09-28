class Pair{
    int first;
    int second;
    public Pair(int _f, int _s){
        first = _f;
        second = _s;
    }
}
class Solution {
    public int[] dijkstra(int V, int[][] edges, int src) {
        // code here
        List<List<Pair>> adj = new ArrayList<>();
        int[] distances = new int[V];
        for(int i = 0; i < V; i++){
            distances[i] = (int)1e9;
            adj.add(new ArrayList<>());
        }
        
        for(int[] edge: edges){
            int u = edge[0], v = edge[1];
            int weight = edge[2];
            adj.get(u).add(new Pair(v, weight));
            adj.get(v).add(new Pair(u, weight));
        }
        
        PriorityQueue<Pair> pq = new PriorityQueue<>((x, y) -> x.first - y.first);
        
        pq.add(new Pair(0, src));
        distances[src] = 0;
        
        while(!pq.isEmpty()){
            Pair p = pq.poll();
            int dist = p.first, node = p.second;
            for(Pair nbrP : adj.get(node)){
                int nbr = nbrP.first, nbrDist = nbrP.second;
                if(distances[node] + nbrDist < distances[nbr]){
                    distances[nbr] = distances[node] + nbrDist;
                    pq.add(new Pair(distances[nbr], nbr));
                }
            }
        }
        
        return distances[V-1] == 1e9 ? new int[]{-1} : distances;
    }
}