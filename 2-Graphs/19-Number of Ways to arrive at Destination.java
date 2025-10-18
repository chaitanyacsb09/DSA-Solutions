class Pair<T,V>{
    T first;
    V second;
    public Pair(T _f, V _s){
        first = _f;
        second = _s;
    }
}
class Solution {
    public int countPaths(int n, int[][] roads) {
        final int MOD = (int)1e9 + 7; 
        List<List<Pair<Integer, Integer>>> graph = new ArrayList<>();
        //According to constraints this should be long, one test case was failing 
        //Due to initialization of this to int[]                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                              
        long[] shortestTimes = new long[n];
        int[] numWays = new int[n];
        for(int i = 0; i < n; i++){
            shortestTimes[i] = Long.MAX_VALUE;
            graph.add(new ArrayList<>());
        }
        
        for(int[] road: roads){
            int startNode = road[0], endNode = road[1];
            int roadTime = road[2];
            graph.get(startNode).add(new Pair(endNode, roadTime));
            graph.get(endNode).add(new Pair(startNode, roadTime));
        }
        
        PriorityQueue<Pair<Long,Integer>> pq = new PriorityQueue<>((x, y) -> Long.compare(x.first, y.first));
        
        pq.add(new Pair(0L, 0));
        shortestTimes[0] = 0L;
        numWays[0] = 1;

        while(!pq.isEmpty()){
            Pair<Long,Integer> top = pq.poll();
            long currTime = top.first; int currNode = top.second;
            //As this statement is placed, therefore while processing nbrs, we could use shortestTimes[currNode] in place of currTime
            //All outdated times would not be processed further, therefore no risk of incorrect updation
            if (currTime > shortestTimes[currNode]) continue; 
            
            for(Pair<Integer,Integer> nbrP : graph.get(currNode)){
                int nbr = nbrP.first, nbrRoadTime = nbrP.second;
                /*shortestTimes[currNode] + nbrDist < shortestTimes[nbr] : Using this is incorrect, as it might have been modified, 
                before processing this entry, therefore always consider the dist popped from queue, and similarly take care in the else if node */
                if(currTime + nbrRoadTime < shortestTimes[nbr]){
                    shortestTimes[nbr] = currTime + nbrRoadTime;
                    pq.add(new Pair(shortestTimes[nbr], nbr));
                    numWays[nbr] = numWays[currNode];
                }
                else if(currTime + nbrRoadTime == shortestTimes[nbr]){
                    numWays[nbr] = (numWays[nbr] + numWays[currNode]) % MOD;
                }
            }
        }
        
        return numWays[n-1];
    }
}