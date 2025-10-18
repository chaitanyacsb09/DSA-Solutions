//Leetcode Solution Link: https://leetcode.com/problems/task-scheduler/solutions/7263712/maxheap-self-explanatory-java-solution-b-g50y/
class Solution {
    public int leastInterval(char[] tasks, int n) {
        int[] charCount = new int[26];
        for(char task: tasks){
            charCount[task - 'A']++;
        }
        //MaxHeap: task with Max Freq to be scheduled first on priority, to get min idle slots | Greedy Approach
        //Not Storing the Task along with its frequency, as its not a requirement to be returned, the actual scheduled order
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());

        for(int i = 0; i < 26; i++){
            if(charCount[i] == 0) continue;
            maxHeap.add(charCount[i]);
        }
        
        int cpuIntervals = 0;

        while(!maxHeap.isEmpty()){
            int slotsInACycle = n + 1; //As a task cannot be repeated before n intervals, therefore one slot cycle must be of n+ 1 length
            int scheduledSlots = 0;
            List<Integer> remainingTasks = new ArrayList<>(); //To store the tasks, if it can occur in upcoming cycles
            while(slotsInACycle > scheduledSlots && !maxHeap.isEmpty()){
                int maxFreqOfTask = maxHeap.poll();
                if(maxFreqOfTask > 1){
                    remainingTasks.add(maxFreqOfTask - 1); //As one is scheduled in this cycle
                }
                scheduledSlots++;
            }
            cpuIntervals += scheduledSlots;
            for(int remainingTaskFreq: remainingTasks){
                maxHeap.add(remainingTaskFreq);
            }
            int idleSlots = slotsInACycle - scheduledSlots;
            if(!maxHeap.isEmpty()){
                cpuIntervals += idleSlots;
            }
        }
        return cpuIntervals;
    }
}