package GreedyAlgorithms;

import java.util.Arrays;

/*
Minimum Platforms

Given arrival and departure times of all trains that reach a railway station.
Find the minimum number of platforms required for the railway station so that no train is kept waiting.
Consider that all the trains arrive at the same day and leave on the same day.
Arrival and departure time can never be the same for a train,
but we can have arrival time of one train equal to departure time of the other.
At any given instance of time, same platform can not be used for both departure of a train and arrival of another train.
In such cases, we need different platforms.
Example 1:
Input: n = 6
arr[] = {0900, 0940, 0950, 1100, 1500, 1800}
dep[] = {0910, 1200, 1120, 1130, 1900, 2000}
Output: 3
Explanation:
Minimum 3 platforms are required to safely arrive and depart all trains.
Example 2:
Input: n = 3
arr[] = {0900, 1100, 1235}
dep[] = {1000, 1200, 1240}
Output: 1
Explanation: Only 1 platform is required to safely manage the arrival and departure of all trains.
*/
public class MinimumPlatforms {

    //Time Complexity: O(n*logn)
    //Sorting takes O(n*logn) and traversal of arrays takes O(n) so overall time complexity is O(n*logn).
    //Space complexity: O(1)   (No extra space used).
    public static int findPlatform(int[] arr, int[] dep, int n) {
        // add your code here
        //sorting arrival and departure timings of trains in ascending order
        Arrays.sort(arr);
        Arrays.sort(dep);
        int currentPlatformNeeded = 1;
        int maximumPlatformNeeded = 1;
        //we are starting i with 1 and j with 0 because we are using 2 pointer approach
        //where i points to arrival of trains and j points to departure of trains
        //so, initially we require 1 platform,so arrival of trains from 1st index(2nd train), we will start the loop
        int i = 1, j = 0;
        while (i < n && j < n) {
            if (arr[i] <= dep[j]) {
                currentPlatformNeeded++;
                i++;
            } else if (arr[i] > dep[j]) {
                currentPlatformNeeded--;
                j++;
            }
            maximumPlatformNeeded = Math.max(maximumPlatformNeeded, currentPlatformNeeded);
        }
        return maximumPlatformNeeded;
    }

    public static void main(String[] args) {
        int n = 6;
        int[] arr = {900, 940, 950, 1100, 1500, 1800};
        int[] dep = {910, 1200, 1120, 1130, 1900, 2000};
        System.out.println(findPlatform(arr, dep, n));
    }
}