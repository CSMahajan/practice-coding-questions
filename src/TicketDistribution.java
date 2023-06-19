/*
Ticket Counter

N people from 1 to N are standing in the queue at a movie ticket counter. It is a weird counter, as it distributes tickets to the first K people and then the last K people and again first K people and so on, once a person gets a ticket moves out of the queue. The task is to find the last person to get the ticket.

Example 1

Input:
N = 9
K = 3
Output:
6
Explanation:
Starting queue will like {1, 2, 3, 4, 5, 6, 7, 8, 9}. After the first distribution queue will look like {4, 5, 6, 7, 8, 9}. And after the second distribution queue will look like {4, 5, 6}. The last person to get the ticket will be 6.
Example 2

Input:
N = 5
K = 1
Output:
3
Explanation:
Queue start as {1, 2, 3, 4, 5} -> {2, 3, 4, 5} -> {2, 3, 4} -> {3, 4} -> {3}
Last person to get ticket will be 3.
 */
public class TicketDistribution {

    //T.C: O(N),
    //S.C: O(1)
    //Two pointer approach
    public static int distributeTicket(int N, int K) {
        int left = 1, right = N, lastPerson = 0;
        boolean flag = true;
        while (left <= right) {
            if (flag) {
                left += K;
                lastPerson = right;
            } else {
                right -= K;
                lastPerson = left;
            }
            flag = !flag;  //flag is toggled to operate on both left and right side distributions of tickets
        }
        return lastPerson;
    }

    //T.C: O(1),
    //S.C: O(1)
    //Optimal approach
    //Finding number of steps left and right sides and try to find the formula for last person
    public static int distributeTicketOptimal(int N, int K) {
        int step = N / K; // steps means total number of times left (start) and right (end) side distribution of tickets are happening
        int left = step / 2; // left is distribution from start like 1,2,3,4...K people
        int right = left; // right is distribution from end like 10,9,8,...N-K people
        if (step % 2 == 1) {
            // steps are odd means from left side one last times distribution is going to be done as
            // we are starting to distribute from left side only
            //that's why increasing left side count
            left++;
        }
        int leftSideTicketGotLastPersonIndex = K * left;
        int rightSideTicketGotLastPersonIndex = N + 1 - K * right;
        if (step % 2 == 1) {
            if (N % K == 0) {
                return leftSideTicketGotLastPersonIndex;
            }
            return leftSideTicketGotLastPersonIndex + 1;
        }
        if (N % K == 0) {
            return rightSideTicketGotLastPersonIndex;
        }
        return rightSideTicketGotLastPersonIndex - 1;
    }

    public static void main(String[] args) {
        //System.out.println(distributeTicket(10,6));
        int N = 9; // Total number of people
        int K = 3; // Number of people to get tickets each time

        int lastPerson = distributeTicket(N, K);
        System.out.println("Last person to get the ticket: " + lastPerson);
    }
}
