class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        //Max will always be at the front of dq
        Deque<Integer> dq = new ArrayDeque<>();
        int[] maxFromWindow = new int[nums.length - k + 1]; //Num Windows
        for(int i = 0; i < nums.length; i++){
            int currEl = nums[i];
            //Remove the elements outside of curr window
            while(!dq.isEmpty() && dq.peekFirst() <= i - k){
                dq.pollFirst();
            }

            //All the elements less than the current el, can anyway be not the max, as long as currEl is in window, hence remove
            while(!dq.isEmpty() && nums[dq.peekLast()] <= currEl){
                dq.pollLast();
            }

            //Push the current el at rear
            dq.offerLast(i);

            if(i >= k - 1){
                maxFromWindow[i - (k - 1)] = nums[dq.peekFirst()];
            }
            // System.out.println("First: " + dq.peekFirst() + ", Last: " + dq.peekLast());
        }
        return maxFromWindow;
    }
}