class Solution {
    public int[][] insert(int[][] intervals, int[] newInterval) {
        int totalIntervals = intervals.length;
        int currInterval = 0;
        List<int[]> newIntervals = new ArrayList<>();

        //First find out the index at which new interval needs to be inserted
        while (currInterval < totalIntervals && intervals[currInterval][1] < newInterval[0]) {
            newIntervals.add(intervals[currInterval]);
            currInterval++;
        }
        //Insert the intervals, but keep in mind to merge with any overlapping interval
        while (currInterval < totalIntervals && newInterval[1] >= intervals[currInterval][0]) {
            newInterval[0] = Math.min(newInterval[0], intervals[currInterval][0]);
            newInterval[1] = Math.max(newInterval[1], intervals[currInterval][1]);
            currInterval++;
        }
        newIntervals.add(newInterval);

        while (currInterval < totalIntervals) {
            newIntervals.add(intervals[currInterval]);
            currInterval++;
        }

        return newIntervals.toArray(new int[newIntervals.size()][]);
    }
}

class Solution {
    public int[][] insert(int[][] intervals, int[] newInterval) {
        int totalIntervals = intervals.length;
        int currInterval = 0;
        List<int[]> newIntervals = new ArrayList<>();

        boolean newIntervalInserted = false;
        for (int[] interval: intervals) {
            int currIntervalStart = interval[0];
            int currIntervalEnd = interval[1];
            
            if (newIntervalInserted || currIntervalEnd < newInterval[0]) {
                newIntervals.add(interval);
            } 
            else if (currIntervalStart > newInterval[1]) {
                newIntervals.add(newInterval);
                newIntervals.add(interval);
                newIntervalInserted = true;
            } 
            else {
                //CurrInterval overlaps with new interval
                newInterval[0] = Math.min(currIntervalStart, newInterval[0]);
                newInterval[1] = Math.max(currIntervalEnd, newInterval[1]);
            }
        }

        if (newIntervalInserted == false) {
            newIntervals.add(newInterval);
        }
        return newIntervals.toArray(new int[newIntervals.size()][]);
    }
}