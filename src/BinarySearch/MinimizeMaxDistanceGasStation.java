package BinarySearch;

import java.util.Comparator;
import java.util.PriorityQueue;

/*
Minimize Max Distance to Gas Station

We have a horizontal number line. On that number line,
we have gas stations at positions stations[0], stations[1], ..., stations[N-1],
where N = size of the stations array. Now, we add K more gas stations so that D,
the maximum distance between adjacent gas stations, is minimized.
We have to find the smallest possible value of D. Find the answer exactly to 2 decimal places.
Example 1:
Input:
N = 10
stations = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]
K = 9
Output: 0.50
Explanation: Each of the 9 stations can be added mid way between all the existing adjacent stations.
Example 2:
Input:
N = 10
stations = [3,6,12,19,33,44,67,72,89,95]
K = 2
Output: 14.00
Explanation: Construction of gas stations at 86 locations
*/
public class MinimizeMaxDistanceGasStation {

    //Brute Force Solution
    //TC: N*K + N
    //SC: O(N - 1)
    public static double findSmallestMaxDistLinearSearch(int[] stations, int K) {
        // code here
        int n = stations.length;
        int[] howManyGasStations = new int[n - 1];
        for (int gasStations = 1; gasStations <= K; gasStations++) {
            double maxSection = -1;
            int maxSectionIndex = -1;
            for (int i = 0; i < n - 1; i++) {
                double difference = stations[i + 1] - stations[i];
                double sectionLength = difference / (double) (howManyGasStations[i] + 1);
                if (maxSection < sectionLength) {
                    maxSection = sectionLength;
                    maxSectionIndex = i;
                }
            }
            howManyGasStations[maxSectionIndex]++;
        }
        double maxGasStationDistance = -1;
        for (int i = 0; i < n - 1; i++) {
            double difference = stations[i + 1] - stations[i];
            double sectionLength = difference / (double) (howManyGasStations[i] + 1);
            maxGasStationDistance = Math.max(maxGasStationDistance, sectionLength);
        }
        return maxGasStationDistance;
    }

    //Better Solution
    //TC: N * logN + K * logN
    //SC: O(N - 1)
    public static double findSmallestMaxDistPriorityQueue(int[] stations, int K) {
        // code here
        int n = stations.length;
        int[] howManyGasStations = new int[n - 1];
        PriorityQueue<Pair> priorityQueue = new PriorityQueue<>(new SectionLengthComparator());
        for (int i = 0; i < n - 1; i++) {
            double difference = stations[i + 1] - stations[i];
            priorityQueue.add(new Pair(difference, i));
        }
        for (int gasStations = 1; gasStations <= K; gasStations++) {
            Pair pair = priorityQueue.peek();
            priorityQueue.remove();
            int sectionIndex = pair.sectionIndex;
            howManyGasStations[sectionIndex]++;
            double initialDifference = stations[sectionIndex + 1] - stations[sectionIndex];
            double sectionLength = initialDifference / (double) (howManyGasStations[sectionIndex] + 1);
            priorityQueue.add(new Pair(sectionLength, sectionIndex));
        }
        return priorityQueue.peek().sectionLength;
    }

    static class SectionLengthComparator implements Comparator<Pair> {
        @Override
        public int compare(Pair o1, Pair o2) {
            if (o1.sectionLength < o2.sectionLength) {
                return 1;
            } else if (o1.sectionLength > o2.sectionLength) {
                return -1;
            } else return 0;
        }
    }

    static class Pair {
        double sectionLength;
        int sectionIndex;
        public Pair(double sectionLength, int sectionIndex) {
            this.sectionLength = sectionLength;
            this.sectionIndex = sectionIndex;
        }
    }

    //Optimal Solution
    //TC: N * logN + N
    //SC: O(1)
    public static double findSmallestMaxDistBinarySearch(int[] stations, int K) {
        // code here
        int n = stations.length;
        double low = 0;
        double high = 0;
        for (int i = 0; i < n - 1; i++) {
            double difference = stations[i + 1] - stations[i];
            high = Math.max(difference, high);
        }
        double differenceMinRequired = Math.pow(10, -6);
        while (high - low > differenceMinRequired) {
            double mid = (low + high) / 2.0;
            int noOfGasStationsRequired = totalNumberOfGasStationsRequired(mid, stations);
            if (noOfGasStationsRequired > K) {
                low = mid;
            } else {
                high = mid;
            }
        }
        return high;
    }

    private static int totalNumberOfGasStationsRequired(double distance, int[] stations) {
        int totalGasStationsRequired = 0;
        for (int i = 1; i < stations.length; i++) {
            int numberInBetween = (int) ((stations[i] - stations[i - 1]) / distance);
            if ((stations[i] - stations[i - 1]) == numberInBetween * distance) {
                numberInBetween--;
            }
            totalGasStationsRequired += numberInBetween;
        }
        return totalGasStationsRequired;
    }

    public static void main(String[] args) {
        int[] stations = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int K = 9;
        System.out.println(findSmallestMaxDistBinarySearch(stations, K));
        int[] stations2 = {3, 6, 12, 19, 33, 44, 67, 72, 89, 95};
        int K2 = 2;
        System.out.println(findSmallestMaxDistPriorityQueue(stations2, K2));
    }
}
