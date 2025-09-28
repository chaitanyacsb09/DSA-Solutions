class Solution {
    private int timer = 1;

    private void evalBridges(int node, int parent, boolean[] isVisited, int[] timeOfInsertion,
        int[] lowestTimeOfInsertionAmongAdj, List<List<Integer>> bridges, List<List<Integer>> adjG) {

        isVisited[node] = true;
        timeOfInsertion[node] = lowestTimeOfInsertionAmongAdj[node] = timer++;
        for (int adj : adjG.get(node)) {
            if (adj == parent)
                continue;

            if(!isVisited[adj]){
                evalBridges(adj, node, isVisited, timeOfInsertion, lowestTimeOfInsertionAmongAdj, bridges, adjG);
                /* Decide if this edge is a bridge
                the lowest node according to time of insertion to which the adjacent node could reach to, 
                if its greater than the time of insertion of curr node, then removing the edge between adjacent node, and curr node, 
                will make it impossible for the adj node to ever reach to this curr node, hence it will create a graph. */
                if (lowestTimeOfInsertionAmongAdj[adj] > timeOfInsertion[node]) {
                    bridges.add(new ArrayList<>(Arrays.asList(node, adj)));
                }
            }
            lowestTimeOfInsertionAmongAdj[node] = Math.min(
                        lowestTimeOfInsertionAmongAdj[node],
                        lowestTimeOfInsertionAmongAdj[adj] 
                    );

            
        }
    }

    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        List<List<Integer>> adjG = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adjG.add(new ArrayList<>());
        }
        for(List<Integer> connection: connections){
            int serverU = connection.get(0), serverV = connection.get(1);
            adjG.get(serverU).add(serverV);
            adjG.get(serverV).add(serverU);
        }
        boolean[] isVisited = new boolean[n];
        int[] timeOfInsertion = new int[n];
        //Excluding the parent
        int[] lowestTimeOfInsertionAmongAdj = new int[n];
        List<List<Integer>> bridges = new ArrayList<>();
        evalBridges(0, -1, isVisited, timeOfInsertion, lowestTimeOfInsertionAmongAdj, bridges, adjG);
        return bridges;
    }
}
