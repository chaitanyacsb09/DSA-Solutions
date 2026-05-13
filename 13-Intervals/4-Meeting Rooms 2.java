//-----------------------------Get Meetings Schedule--------------------
class Solution {
    public int minMeetingRooms(List<Interval> intervals) {
        if (intervals.size() == 0) {
            return 0;
        }

        //Sort the intervals
        Collections.sort(intervals, (a,b) -> Integer.compare(a.start, b.start));

        Map<Integer, List<Interval>> dayToMeetings = new LinkedHashMap<>();
        dayToMeetings
        .computeIfAbsent(1, k -> new ArrayList<>())
        .add(intervals.get(0));

        for (int curr = 1; curr < intervals.size(); curr++) {
            boolean meetingScheduled = false;
            int currMeetingStart = intervals.get(curr).start;

            for (Map.Entry<Integer, List<Interval>> entry : dayToMeetings.entrySet()) {
                int day = entry.getKey();
                List<Interval> meetingsForDay = entry.getValue();

                int endOfLastMeetingInDay = meetingsForDay.get(meetingsForDay.size() - 1).end;

                if (currMeetingStart >= endOfLastMeetingInDay) {
                    dayToMeetings.get(day).add(intervals.get(curr));
                    meetingScheduled = true;
                    break;
                }
            }

            if (meetingScheduled == false) {
                dayToMeetings
                .computeIfAbsent(dayToMeetings.size() + 1, k -> new ArrayList<>())
                .add(intervals.get(curr));
            }
        }

        return dayToMeetings.size();
    }
}

//----------------------------Sweeping Line Algorithm---------------------
class Solution {

    /**
     * Returns the minimum number of meeting rooms required to accommodate
     * all intervals without overlap.
     */
    public int minMeetingRooms(List<Interval> intervals) {

        // Map from time → change in active meetings at that moment.
        TreeMap<Integer, Integer> timeDeltas = new TreeMap<>();

        // Build the sweep-line events: +1 at start, -1 at end.
        for (Interval interval : intervals) {
            timeDeltas.merge(interval.start, 1, Integer::sum); //Meeting Start
            timeDeltas.merge(interval.end, -1, Integer::sum); //Meeting End
        }

        int activeMeetings = 0;
        int maxConcurrentMeetings = 0;

        // Sweep through time in sorted order.
        for (int delta : timeDeltas.values()) {
            activeMeetings += delta; //Either Previous Meeting End, or New concurrent meeting start, hence represents activeMeetings
            maxConcurrentMeetings = Math.max(maxConcurrentMeetings, activeMeetings);
        }

        return maxConcurrentMeetings;
    }
}

//----------------------------Two Pointers-----------------------------------
class Solution {

    public int minMeetingRooms(List<Interval> intervals) {
        int n = intervals.size();
        int[] startTimes = new int[n];
        int[] endTimes = new int[n];

        // Extract and separate start/end times
        for (int i = 0; i < n; i++) {
            Interval interval = intervals.get(i);
            startTimes[i] = interval.start;
            endTimes[i] = interval.end;
        }

        Arrays.sort(startTimes);
        Arrays.sort(endTimes);

        int roomsInUse = 0;
        int endPtr = 0;

        // Sweep through sorted start times
        for (int start : startTimes) {
            // A room gets freed if the earliest meeting ended
            if (start >= endTimes[endPtr]) {
                endPtr++;   // reuse the room
            } else {
                roomsInUse++;  // need a new room
            }
        }

        return roomsInUse;
    }
}

