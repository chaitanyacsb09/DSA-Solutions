// https://leetcode.com/problems/maximum-profit-in-job-scheduling/description/

class Job{
    int startTime;
    int endTime;
    int profit;
    public Job(int startTime, int endTime, int profit) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.profit = profit;
    }
    public static final Comparator<Job> sortByStartTime = Comparator.comparingInt(j -> j.startTime);

    // Lower bound method based on endTime
    public static int lowerBound(Job[] jobs, int targetStartTime) {
        //Initialize with jobs.length, so that when no jobs have startTime >= targetStartTime, then n is returned, invalid postition and hence the recursion is stopped further
        int left = 0, right = jobs.length;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (jobs[mid].startTime >= targetStartTime) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }
}
class Solution {
    private int jobSchedulingUtil(int idx, Job[] jobs, int memo[]){
        if(idx == jobs.length){
            return 0;
        }
        if(memo[idx] != -1){
            return memo[idx];
        }

        int take = 0, notTake = 0;
        int nextValidIdx = Job.lowerBound(jobs, jobs[idx].endTime);
        take = jobs[idx].profit + jobSchedulingUtil(nextValidIdx,jobs, memo);
        notTake = jobSchedulingUtil(idx+1, jobs, memo);
        return memo[idx] = Math.max(take,notTake);
    }
    public int jobScheduling(int[] startTime, int[] endTime, int[] profit) {
        // Your code goes here
        int n = startTime.length;
        Job[] jobs = new Job[n];
        for(int i = 0; i < n; i++){
            jobs[i] = new Job(startTime[i], endTime[i], profit[i]);
        }
        Arrays.sort(jobs, Job.sortByStartTime);
        int[] memo = new int[n];
        Arrays.fill(memo,-1);
        return jobSchedulingUtil(0, jobs, memo);
    }
}