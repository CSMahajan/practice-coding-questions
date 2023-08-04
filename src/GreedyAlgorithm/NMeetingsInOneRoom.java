package GreedyAlgorithm;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/*
N meetings in one room

There is one meeting room in a firm. There are N meetings in the form of (start[i], end[i])
where start[i] is start time of meeting i and end[i] is finish time of meeting i.
What is the maximum number of meetings that can be accommodated in the meeting room when
only one meeting can be held in the meeting room at a particular time?
Note: Start time of one chosen meeting can't be equal to the end time of the other chosen meeting.
Example 1:
Input:
N = 6
start[] = {1,3,0,5,8,5}
end[] =  {2,4,6,7,9,9}
Output: 4
Explanation:
Maximum four meetings can be held with given start and end timings.
The meetings are - (1, 2),(3, 4), (5,7) and (8,9)
Example 2:
Input:
N = 3
start[] = {10, 12, 20}
end[] = {20, 25, 30}
Output: 1
Explanation:
Only one meetings can be held with given start and end timings.
*/
public class NMeetingsInOneRoom {

    static class Meeting {
        int start;
        int end;
        int index;


        public Meeting(int start, int end, int index) {
            this.start = start;
            this.end = end;
            this.index = index;
        }
    }

    static class MeetingComparator implements Comparator<Meeting> {

        @Override
        public int compare(Meeting o1, Meeting o2) {
            if (o1.end < o2.end) {
                return -1;
            } else if (o1.end > o2.end) {
                return 1;
            } else if (o1.index < o2.index) {
                return -1;
            } else {
                return 1;
            }
        }
    }

    //Time Complexity: O(n) +O(n log n) + O(n) ~O(n log n)
    //O(n) to iterate through every position and insert them in a data structure.
    //O(n log n)  to sort the data structure in ascending order of end time.
    //O(n)  to iterate through the positions and check which meeting can be performed.
    //Space Complexity: O(n)
    //since we used an additional data structure for storing the start time, end time, and meeting no.
    public static int maxMeetings(int[] start, int[] end, int n) {
        // add your code here
        List<Meeting> meetings = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            meetings.add(new Meeting(start[i], end[i], i + 1));
        }
        //sorting the given meetings according to the ascending order of ending time of the meetings,
        //if they match then based on the ascending order of the index of the meetings
        meetings.sort(new MeetingComparator());
        List<Integer> finalMeetings = new ArrayList<>();
        finalMeetings.add(meetings.get(0).index);
        int lastMeetingEndingTime = meetings.get(0).end;
        for (int i = 1; i < n; i++) {
            if (lastMeetingEndingTime < meetings.get(i).start) {
                //start time of current meeting is more(later) than ending time of the last meeting
                //so this meeting can be taken in the room
                //updating the ending time in lastMeetingEndingTime
                lastMeetingEndingTime = meetings.get(i).end;
                finalMeetings.add(meetings.get(i).index);
            }
        }
        return finalMeetings.size();
    }

    public static void main(String[] args) {
        int N = 6;
        int[] start = {1, 3, 0, 5, 8, 5};
        int[] end = {2, 4, 6, 7, 9, 9};
        System.out.println(maxMeetings(start, end, N));
    }
}