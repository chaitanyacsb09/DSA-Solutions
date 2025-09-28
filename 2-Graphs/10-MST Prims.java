class Pair{
    int first, second;
    public Pair(int f, int s){
        first = f;
        second = s;
    }
}

class Triple{
    int first, second, third;
    public Triple(int f, int s, int t){
        first = f;
        second = s;
        third = t;
    }
}
class Solution {
    public int spanningTree(int V, int[][] edges) {
        // code here
        List<List<Pair>> adjG = new ArrayList<>();
        for(int i = 0; i < V; i++){
            adjG.add(new ArrayList<>());
        }
        
        //Prepare Adj Graph
        for(int[] edge: edges){
            int u = edge[0], v = edge[1], edgWt = edge[2];
            adjG.get(u).add(new Pair(v, edgWt));
            adjG.get(v).add(new Pair(u, edgWt));
        }
        
        PriorityQueue<Triple> pq = new PriorityQueue<>((x,y) -> x.first - y.first);
        pq.add(new Triple(0,0,-1));
        int mstSum = 0;
        //Optional for finding the mst edges
        List<Pair> mst = new ArrayList<>();
        boolean[] isVisited = new boolean[V];
        while(!pq.isEmpty()){
            Triple t = pq.poll();
            int wt = t.first, node = t.second, parent = t.third;
            
            if(isVisited[node]){
                continue;
            }

            isVisited[node] = true;
            mstSum += wt;
            //Optional for finding the mst edges
            mst.add(new Pair(node, parent));
            for(Pair nbr: adjG.get(node)){
                pq.add(new Triple(nbr.second, nbr.first, node));
            }

        }
        
        return mstSum;
    }
}
