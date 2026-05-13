class Solution {
    public int[][] merge(int[][] intervals) {
        //sort the array
        Arrays.sort(intervals, (a,b) -> Integer.compare(a[0],b[0]));

        //create a new merged intervals
        List<int[]> mergedIntervals = new ArrayList<>();

        //iterate over intervals, and merge overlapping intervals
        int curr = 0;
        while (curr < intervals.length) {
            int start = intervals[curr][0];
            int end = intervals[curr][1];

            while (curr < intervals.length - 1 && end >= intervals[curr + 1][0]) {
                end = Math.max(end, intervals[curr + 1][1]);
                curr++;
            }

            mergedIntervals.add(new int[]{
                start, end
            });

            curr++;
        }

        //return answer
        return mergedIntervals.toArray(new int[mergedIntervals.size()][]);
    }
}


public class Solution {
    public int[][] merge(int[][] intervals) {
        int max = 0;
        for (int i = 0; i < intervals.length; i++) {
            max = Math.max(intervals[i][0], max);
        }

        int[] mp = new int[max + 1];
        //Could get away without setting all -1, store end + 1, and later while merging intervals -1
        Arrays.fill(mp, - 1);
        for (int i = 0; i < intervals.length; i++) {
            int start = intervals[i][0];
            int end = intervals[i][1];
            mp[start] = Math.max(end, mp[start]);
        }

        int r = 0;
        int intervalStart = -1;
        int intervalEnd = -1;
        
        for (int i = 0; i < mp.length; i++) {
            if (mp[i] != -1) {
                if (intervalStart == -1) intervalStart = i;
                intervalEnd = Math.max(mp[i], intervalEnd);
            }
            if (intervalEnd == i) {
                intervals[r++] = new int[] { intervalStart, intervalEnd };
                intervalEnd = -1;
                intervalStart = -1;
            }
        }

        if (intervalStart != -1) {
            intervals[r++] = new int[] { intervalStart, intervalEnd };
        }
        if (intervals.length == r) {
            return intervals;
        }

        int[][] res = new int[r][];
        for (int i = 0; i < r; i++) {
            res[i] = intervals[i];
        }

        return res;
    }
}