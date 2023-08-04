package GreedyAlgorithm;

import java.util.Arrays;

/*
Job Sequencing Problem

Given a set of N jobs where each jobi has a deadline and profit associated with it.
Each job takes 1 unit of time to complete and only one job can be scheduled at a time.
We earn the profit associated with job if and only if the job is completed by its deadline.
Find the number of jobs done and the maximum profit.
Note: Jobs will be given in the form (Jobid, Deadline, Profit) associated with that Job.
Example 1:
Input:
N = 4
Jobs = {(1,4,20),(2,1,10),(3,1,40),(4,1,30)}
Output:
2 60
Explanation:
Job1 and Job3 can be done with
maximum profit of 60 (20+40).
Example 2:
Input:
N = 5
Jobs = {(1,2,100),(2,1,19),(3,2,27),
        (4,1,25),(5,1,15)}
Output:
2 127
Explanation:
2 jobs can be done with maximum profit of 127 (100+27).
*/
public class JobSequencing {

    static class Job {
        int id, profit, deadline;

        Job(int id, int deadline, int profit) {
            this.id = id;
            this.deadline = deadline;
            this.profit = profit;
        }
    }

    //Time Complexity: O(N log N) + O(N*M).
    //O(N log N ) for sorting the jobs in decreasing order of profit.
    //O(N*M) since we are iterating through all N jobs and for every job we are checking from the last deadline,
    //say M deadlines in the worst case.
    //Space Complexity: O(M) for an array that keeps track of which day job is performed
    //if M is the maximum deadline available.
    public int[] JobScheduling(Job[] arr, int n) {
        // Your code here
        //sorting job array based on descending order of profit
        Arrays.sort(arr, (job1, job2) -> job2.profit - job1.profit);
        int noOfJobs = 0;
        int totalProfitEarned = 0;
        int maximumDeadline = 0;
        for (int i = 0; i < n; i++) {
            //finding the maximum possible deadline
            maximumDeadline = Math.max(maximumDeadline, arr[i].deadline);
        }
        //our logic states that schedule the job as late as possible (possibly on its deadline) to schedule maximum job
        //jobSequencing array denotes the jobs occupied as their values
        int[] jobSequencing = new int[maximumDeadline + 1];
        Arrays.fill(jobSequencing, -1);
        jobSequencing[0] = 0;
        //we require the index from 1 to n-1 with initialized value of -1, so marking 0th element as 0
        //initially all as -1 means all are empty slots
        for (int i = 0; i < n; i++) {
            for (int j = arr[i].deadline; j > 0; j--) {
                //-1 indicates free slot found
                if (jobSequencing[j] == -1) {
                    //marking the index in jobSequencing to i as we can even retrieve the jobId of the jobs that we can schedule
                    jobSequencing[j] = i;
                    noOfJobs++;
                    totalProfitEarned += arr[i].profit;
                    break;
                }
            }
        }
        return new int[]{noOfJobs, totalProfitEarned};
    }

    public static void main(String[] args) {
        int N = 4;
        Job[] jobs = new Job[N];
        jobs[0] = new Job(1, 4, 20);
        jobs[1] = new Job(2, 1, 10);
        jobs[2] = new Job(3, 1, 40);
        jobs[3] = new Job(4, 1, 30);
        JobSequencing js = new JobSequencing();
        System.out.println(Arrays.toString(js.JobScheduling(jobs, N)));
    }
}