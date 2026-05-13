/**
 * Definition of Interval:
 * public class Interval {
 *     public int start, end;
 *     public Interval(int start, int end) {
 *         this.start = start;
 *         this.end = end;
 *     }
 * }
 */

class Solution {
    public boolean canAttendMeetings(List<Interval> intervals) {

        //Sort the intervals
        Collections.sort(intervals, (a,b) -> Integer.compare(a.start, b.start));

        for (int curr = 0; curr < intervals.size() - 1; curr++) {
            int currMeetingEnd = intervals.get(curr).end;
            int nextMeetingStart = intervals.get(curr + 1).start;

            if (currMeetingEnd > nextMeetingStart) {
                return false;
            }
        }

        return true;
    }
}
