import java.util.ArrayList;

public class StockBuySell {

    /*
    Stock buy and sell

    The cost of stock on each day is given in an array A[] of size N. Find all the segments of days on which
    you buy and sell the stock so that in between those days  for which profit can be generated.
    Note: Since there can be multiple solutions, the driver code will print 1 if your answer is correct,
    otherwise, it will return 0.
    In case there's no profit the driver code will print the string "No Profit" for a correct solution.

    Example 1:

    Input:
    N = 7
    A[] = {100,180,260,310,40,535,695}
    Output:
            1
    Explanation:
    One possible solution is (0 3) (4 6)
    We can buy stock on day 0, and sell it on 3rd day, which will give us
    maximum profit.Now, we buy stock on day 4 and sell it on day 6.*/

    public static ArrayList<ArrayList<Integer>> stockBuySell(int A[], int n) {
        // code here
        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();

        int buy = 0; //assuming we buy on day 1
        int sell = 1; // and selling on day 2

        while (sell < n) {
            if (A[buy] > A[sell]) { //if stock value is less on sell we update the buy and sell
                buy = sell;
                sell++;
            } else {
                while (sell + 1 < n && A[sell + 1] >= A[sell]) { //else we will find the maximum sell value
                    sell++;
                }
                ArrayList<Integer> li = new ArrayList<>();
                li.add(buy);
                li.add(sell);
                ans.add(li); // and add the buy and sell in our array
                buy = sell + 1; //don't forget to update the buy and sell again.
                sell = buy + 1;
                /*
                buy=sell+1; sell=buy+1;
                these two steps are done in order to find next pair of buy and sell value possible in the array,
                so as we would start looking from new indexes
                */
            }
        }
        return ans;
    }

    /* other possible simple solution */
    public static ArrayList<ArrayList<Integer>> stockBuySell1(int A[], int n) {
        ArrayList<ArrayList<Integer>> ans = new ArrayList<ArrayList<Integer>>();
        for (int i = 1; i < n; i++) {
            if (A[i] > A[i - 1]) {
                ArrayList<Integer> list = new ArrayList<>();
                list.add(i - 1);
                list.add(i);
                ans.add(list);
            }
        }
        return ans;
    }
}