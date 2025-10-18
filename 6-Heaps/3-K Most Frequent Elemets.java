class NumFreqPair{
    int num;
    int freq;
    NumFreqPair(int num, int freq){
        this.num = num;
        this.freq = freq;
    }
}
class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> numFreq = new HashMap<>();
        for(int num : nums){
            numFreq.merge(num, 1, Integer::sum);
        }
        PriorityQueue<NumFreqPair> minHeap = new PriorityQueue<>((a,b) -> Integer.compare(a.freq, b.freq));
        for(Map.Entry<Integer, Integer> kv: numFreq.entrySet()){
            minHeap.offer(new NumFreqPair(kv.getKey(), kv.getValue()));
            if(minHeap.size() > k){
                minHeap.poll();
            }
        }
        int[] mostFrequentK = new int[minHeap.size()];
        int idx = 0;
        while(!minHeap.isEmpty()){
            mostFrequentK[idx++] = minHeap.poll().num;
        }
        return mostFrequentK;
    }
}