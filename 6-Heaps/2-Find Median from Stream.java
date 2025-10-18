class MedianFinder {
    //To Store the first half
    private PriorityQueue<Integer> maxHeap;
    //To Store the remaining
    private PriorityQueue<Integer> minHeap;
    
    public MedianFinder() {
        maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        minHeap = new PriorityQueue<>();
    }
    
    public void addNum(int num) {
        //Add to first part
        maxHeap.offer(num);

        //Move the largest from the first part to the second
        minHeap.offer(maxHeap.poll());

        //If the second part's size is greater, then move the min El, from second to first
        //Because intention is to maintain, their size equal or if not, then the maxHeap should have middle el
        //Vice-Versa could also be done
        if(minHeap.size() > maxHeap.size()){
            maxHeap.offer(minHeap.poll());
        }
    }
    
    public double findMedian() {
        //Currently odd number of elements
        if(maxHeap.size() > minHeap.size()){
            return maxHeap.peek();
        }
        return (minHeap.peek() + maxHeap.peek()) / 2.0;
    }
}

/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */