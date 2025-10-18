//TC: o(NlogK) SC: O(K)
class Solution {
    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        for(int num: nums){
            minHeap.offer(num);
            if(minHeap.size() > k){
                minHeap.poll();
            }
        }
        return minHeap.poll();
    }
}


//----------TC: O(2N) | SC: O(N)
class Solution {
    public int findKthLargest(int[] nums, int k) {
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        for(int num : nums){
            max = Math.max(max, num);
            min = Math.min(min, num);
        }
        //All the numbers in the array will be between the inclusive range of min-max
        //The freq array will store count of each distinct number
        int[] freq = new int[max - min + 1];
        for(int num: nums){
            freq[num - min]++;
        }

        /*
        Now for array: [3,2,3,1,2,4,5,5,6]
        The freq array will be: [1,2,2,1,2,1] : Count of Distinct Numbers : 1(Min), 2, 3, 4, 5, 6(Max)
        Sorted Array: [1,2,2,3,3,4,5,5,6] : Now the Kth Largest El is 4, and we will find this using freq array
        As for 6 -> count 1, the largest | idx = 5
        For 5 -> count 2, second and third largest | idx = 4
        For 4 -> 1, Fourth or Kth Largest in this case | idx = 3, Min is 1, return min + idx, as the idx is based on min
         */
        for(int idx = freq.length - 1; idx >= 0; idx--){
            k = k - freq[idx];
            if(k <= 0){
                return idx + min;
            }
        }
        return -1;
    }
}