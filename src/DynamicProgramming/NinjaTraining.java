package DynamicProgramming;

import java.util.Arrays;

/*
Geek's Training

Geek is going for n days training program,
he can perform any one of these three activities Running, Fighting, and Learning Practice.
Each activity has some point on each day. as Geek wants to improve all his skills,
he can't do the same activity on two consecutive days,
help Geek to maximize his merit points as we were given a 2D array of n*3 points corresponding to each day and activity.
Example:
Input:
n = 3
point= [[1,2,5],[3,1,1],[3,3,3]]
Output:
11
Explanation:
Geek will learn a new move and earn 5 point then on second
day he will do running and earn 3 point and on third day
he will do fighting and earn 3 points so, maximum point is 11.
*/
public class NinjaTraining {

    public int maximumPointsTopDown(int[][] points, int N) {
        //code here
        int[][] dp = new int[N][4];
        for (int[] row : dp) {
            Arrays.fill(row, -1);
        }
        return maximumPointsTopDown(N - 1, 3, points, dp);
    }

    //Time Complexity: O(N*4*3)
    //Reason: There are N*4 states and for every state, we are running a for loop iterating three times.
    //Space Complexity: O(N) + O(N*4)
    //Reason: We are using a recursion stack space(O(N)) and a 2D array (again O(N*4)).
    //Therefore total space complexity will be O(N) + O(N) ≈ O(N)
    private int maximumPointsTopDown(int day, int last, int[][] points, int[][] dp) {
        if (dp[day][last] != -1) {
            return dp[day][last];
        }
        if (day == 0) {
            int maximum = 0;
            for (int i = 0; i < 3; i++) {
                if (i != last) {
                    maximum = Math.max(maximum, points[0][i]);
                }
            }
            return dp[day][last] = maximum;
        }
        int maximum = 0;
        for (int i = 0; i < 3; i++) {
            if (i != last) {
                int currentPoints = points[day][i] + maximumPointsTopDown(day - 1, i, points, dp);
                maximum = Math.max(maximum, currentPoints);
            }
        }
        return dp[day][last] = maximum;
    }

    //Time Complexity: O(N*4*3), for three nested loops
    //Space Complexity: O(N*4), for an external dp array of size N*4
    public int maximumPointsBottomUp(int[][] points, int N) {
        //code here
        int[][] dp = new int[N][4];
        /*
        To consider the points for 0th day(at the start of tabulation bottom up, at start if any task dp position carries
        the maximum of points of other two activities, where
        0-Running activity
        1-Fighting activity
        2-Learning activity
        3-No activity performed on that day
        */
        dp[0][0] = Math.max(points[0][1], points[0][2]);
        dp[0][1] = Math.max(points[0][0], points[0][2]);
        dp[0][2] = Math.max(points[0][0], points[0][1]);
        dp[0][3] = Math.max(points[0][0], Math.max(points[0][1], points[0][2]));

        /*
        for every day we need to calculate the last day's performed activity so first for loop runs for days from 1 to N-1
        as for 0th day we have calculated as there is nothing previous to consider before 0th day
        the second loop of last runs for all 4 activities(0,1,2,3) because we need last day maximum collected points, to calculate future points
        and to let us know the last activity performed(for that innermost for loop has if condition)
        the innermost for loop checks the points that can be maximum possible for the day and we will store them in dp array,
        which adds current days task's points and gets the previous points (excluding current task as last days task) using dp array
        */
        for (int day = 1; day < N; day++) {
            for (int last = 0; last < 4; last++) {
                dp[day][last] = 0;
                for (int task = 0; task < 3; task++) {
                    if(task != last) {
                        int activity = points[day][task] + dp[day - 1][task];
                        dp[day][last] = Math.max(activity, dp[day][last]);
                    }
                }
            }
        }
        //when all the loop ends our answer is stored in dp[N-1][3]
        return dp[N-1][3];
    }

    //Time Complexity: O(N*4*3), for three nested loops
    //Space Complexity: O(4), for an external previous array of size 4 to store only one (previous) row
    public int maximumPointsSpaceOptimised(int[][] points, int N) {
        //code here
        int[] previous = new int[4];
        /*
        Previous day signifies the points collected on previous day of the current day.
        We have pre-computed the points on 0th day when index in previous array represents the task performed
        0-Running activity
        1-Fighting activity
        2-Learning activity
        3-No activity performed on that day
        */
        previous[0] = Math.max(points[0][1], points[0][2]);
        previous[1] = Math.max(points[0][0], points[0][2]);
        previous[2] = Math.max(points[0][0], points[0][1]);
        previous[3] = Math.max(points[0][0], Math.max(points[0][1], points[0][2]));

        /*
        for every day we need to calculate the last day's performed activity so first for loop runs for days from 1 to N-1
        as for 0th day we have calculated as there is nothing previous to consider before 0th day,
        we will create the temp array of size 4 for each day from first day onwards.
        the second loop of last runs for all 4 activities(0,1,2,3) because we need last day maximum collected points, to calculate future points
        and to let us know the last activity performed(for that innermost for loop has if condition)
        the innermost for loop checks the points that can be maximum possible for the day and we will store them in temp array,
        which adds current days task's points and gets the previous points (excluding current task as last days task) using temp array
        */
        for (int day = 1; day < N; day++) {
            int[] temp = new int[4];
            for (int last = 0; last < 4; last++) {
                temp[last] = 0;
                for (int task = 0; task < 3; task++) {
                    if(task != last) {
                        int activity = points[day][task] + previous[task];
                        temp[last] = Math.max(activity, temp[last]);
                    }
                }
            }
            //for our next day, we will require the points collected for each activity in previous array,so previous stores the temp
            //because we don't require any backward records for current calculation except the immediate previous day
            previous = temp;
        }
        //when all the loop ends our answer is stored in previous[3]
        return previous[3];
    }

    public static void main(String[] args) {
        NinjaTraining nj = new NinjaTraining();
        int[][] points = {{10, 40, 70},
                {20, 50, 80},
                {30, 60, 90}};
        int n = points.length;
        System.out.println(nj.maximumPointsTopDown(points, n));
        System.out.println(nj.maximumPointsBottomUp(points, n));
        System.out.println(nj.maximumPointsSpaceOptimised(points, n));
    }
}
