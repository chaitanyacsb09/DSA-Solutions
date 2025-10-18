class Pair{
    int first, second;
    public Pair(int _f, int _s){
        first = _f;
        second = _s;
    }
}
class Solution {
    
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        List<Pair>[] graph = new ArrayList[n];
        for(int i = 0; i < n; i++){
            graph[i] = new ArrayList<>();
        }
        for(int[] flight: flights){
            int from = flight[0], to = flight[1], price = flight[2];
            graph[from].add(new Pair(to, price)); 
        }

        //Stops increasing monotonically, +1, therefore no need of priorityQueue
        Queue<int[]> q = new LinkedList<>();
        int[] price = new int[n];
        Arrays.fill(price, (int)1e7);
        q.offer(new int[]{0,0,src});
        price[src] = 0;

        while(!q.isEmpty()){
            int[] front = q.poll();
            int stopsRequired = front[0], priceToReachCurr = front[1], stop = front[2];
            
            if(stopsRequired > k) continue;

            //Check with nbrs
            for(Pair p : graph[stop]){
                int adjStop = p.first, priceToReachAdj = p.second;
                if(priceToReachCurr + priceToReachAdj < price[adjStop]){
                    price[adjStop] = priceToReachCurr + priceToReachAdj;
                    q.offer(new int[]{stopsRequired + 1, price[adjStop], adjStop});
                } 
            }
        }
        return price[dst] == 1e7 ? -1 : price[dst];
    }
}