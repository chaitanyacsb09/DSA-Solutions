//TC: O(K * (2N)) SC: O(N)
class Solution {
    public double minMaxDist(int[] stations, int K) {
        // code here
        if(stations.length == 1){
            return 0;
        }
        boolean debugEnabled = false;
        double d = 1e6 + 1;
        int numStations = stations.length;
        int numSections = numStations - 1;
        int[] numGasStationsPlacedInSection = new int[numSections];
        //Where to place k_i gas station
        for(int gasStation = 1; gasStation <= K; gasStation++){
            
            double maxSectionLen = -1;
            int maxSectionInd = -1;
            if(debugEnabled) System.out.println("Placing Gas Station: " + gasStation);
            //Find max section
            for(int i = 0; i < numSections ; i++){
                double currSectionLen = (stations[i + 1] - stations[i]) / (double) (numGasStationsPlacedInSection[i] + 1);
                if(currSectionLen > maxSectionLen){
                    maxSectionLen = currSectionLen;
                    maxSectionInd = i;
                }
            }
            
            if(debugEnabled){ 
                System.out.println("\tCurrent Max Section is between Stations : " + maxSectionInd + " and " + (maxSectionInd + 1));
                System.out.println("\tNumber of Gas Stations Placed currently in this section: " + numGasStationsPlacedInSection[maxSectionInd]);
            }
            //Add a gas station in the current largest section
            numGasStationsPlacedInSection[maxSectionInd]++;
            
            //Find max section after optimally placing new gas station
            maxSectionLen = -1;
            for(int i = 0; i < numSections ; i++){
                double currSectionLen = (stations[i + 1] - stations[i]) / (double) (numGasStationsPlacedInSection[i] + 1);
                maxSectionLen = Math.max(maxSectionLen, currSectionLen);
            }
            
            //Update the minimum, max distance between adjacent gas stations, after placing the k_i gas station
            d = Math.min(d, maxSectionLen);
            if(debugEnabled){
                System.out.println("\tMax Section Length After Placing Current Gas Station: " + maxSectionLen);
                System.out.println("\tValue of d, after placing current gas station: " + d);
            }
            
        }
        
        return d;
    }
}

//HEAP OPTIMIAZATION TC: O(N LOG N) + O(K * (2Log(N))) SC: O(2*(N-1)) 
class SectionLenIdxPair{
    double len;
    int idx;
    SectionLenIdxPair(double len, int idx){
        this.len = len;
        this.idx = idx;
    }
}
class Solution {
    public double minMaxDist(int[] stations, int K) {
        // code here
        if(stations.length == 1){
            return 0;
        }
        boolean debugEnabled = false;
        double d = 1e6 + 1;
        int numStations = stations.length;
        int numSections = numStations - 1;
        int[] numGasStationsPlacedInSection = new int[numSections];
        
        PriorityQueue<SectionLenIdxPair> maxHeap = new PriorityQueue<>((a,b) -> Double.compare(b.len, a.len));
        
        for(int i = 0; i < numSections; i++){
            double currSectionLen = stations[i + 1] - stations[i];
            maxHeap.offer(new SectionLenIdxPair(currSectionLen, i));
        }
        //Where to place k_i gas station
        for(int gasStation = 1; gasStation <= K; gasStation++){
            
            SectionLenIdxPair currLargestSection = maxHeap.poll();
            int maxSectionInd = currLargestSection.idx; 
            
            if(debugEnabled){
                System.out.println("Placing Gas Station: " + gasStation);
                System.out.println("\tCurrent Max Section is between Stations : " + maxSectionInd + " and " + (maxSectionInd + 1));
                System.out.println("\tNumber of Gas Stations Placed currently in this section: " + numGasStationsPlacedInSection[maxSectionInd]);
            }
            
            //Add a gas station in the current largest section, and insert new section len
            numGasStationsPlacedInSection[maxSectionInd]++;
            double newSectionLen = (stations[maxSectionInd + 1] - stations[maxSectionInd]) / (double) (numGasStationsPlacedInSection[maxSectionInd] + 1);
            currLargestSection.len = newSectionLen;
            maxHeap.offer(currLargestSection);
            
            //Find max section after optimally placing new gas station
            //Update the minimum, max distance between adjacent gas stations, after placing the k_i gas station
            double maxSectionLenAfterPlacingCurrGasStation = maxHeap.peek().len;
            d = Math.min(d, maxSectionLenAfterPlacingCurrGasStation);
            
            if(debugEnabled){
                System.out.println("\tMax Section Length After Placing Current Gas Station: " + maxSectionLenAfterPlacingCurrGasStation);
                System.out.println("\tValue of d, after placing current gas station: " + d);
            }
            
        }
        
        return d;
    }
}

//Optimal Approach: Binary Search => TC: O(N Log(Range)) [0,1e6] Max Range Possible according to the values provided, SC: O(1)
//TC: O(N Log(Range(0,1e6))) SC: O(1)
class Solution {
    public double minMaxDist(int[] stations, int K) {
        // code here
        int numStations = stations.length;
        if(numStations == 1){
            return 0;
        }
        int numSections = numStations - 1;
        boolean debugEnabled = true;
        
        //find MaxSectionLen
        double maxSectionLen = -1;
        for(int i = 0; i < numSections; i++){
            double currSectionLen = stations[i + 1] - stations[i];
            maxSectionLen = Math.max(maxSectionLen, currSectionLen);
        }
        
        //Search Space, either d could be 0(All stations at same pos), or maxSectionLen(If we add new stations outside the boundary of current ones)[1e6 at max here, [0,1e6]]
        double low = 0, high = maxSectionLen;
        double d = maxSectionLen;
        double precision = 1e-3; //Asked upto 2 Decimal places correctly, but GFG Expected rounded Up Values, rather than truncating, therefore one level extra
        while(high - low > precision){
            double dist = low + (high - low) / 2;
            int numStationsRequired = evalNumStationsForDist(dist, stations);
            
            if(numStationsRequired > K){
                //If more stations are required than the allowed/provided, then the current distance is too small, hence eliminate left side of search spac
                low = dist;
            }
            else{
                d = dist;
                high = dist;
            }
        }
        
        return d;
    }
    
    //Fn to evaluate number of stations that needs to be added, in order to keep the max adj distance between the stations to 'dist'
    private int evalNumStationsForDist(double dist, int[] stations){
        int numStationsRequired = 0;
        int numSections = stations.length - 1;
        
        for(int i = 0; i < numSections; i++){
            int currSectionLen = stations[i + 1] - stations[i]; 
            int numStationsRequiredForCurrSection = (int)(currSectionLen/dist);
            
            if(currSectionLen % dist == 0){
                //if len = 8, and dist = 2, therefor 8/2 = 4, one extra, only 3 required to minimize the adj section len to 2
                numStationsRequiredForCurrSection--;
            }
            numStationsRequired += numStationsRequiredForCurrSection;
        }
        
        return numStationsRequired;
    }
}



