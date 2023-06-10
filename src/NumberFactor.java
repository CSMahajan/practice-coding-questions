/*
Find the number of ways possible to represent a given number as a sum of 1, 3, 4
Example: N=5
Output: 6
Explanation: {1,1,1,1,1},{1,3,1},{3,1,1},{1,1,3},{1,4},{4,1}
             are such 6 ways which number 5 can be represented
*/
public class NumberFactor {

    //Recursive way
    public static int waysToGetNumber(int n) {
        if(n==0 || n==1 || n==2) return 1;
        if(n==3) return 2;
        int sub1 = waysToGetNumber(n-1);
        int sub2 = waysToGetNumber(n-3);
        int sub3 = waysToGetNumber(n-4);
        return sub1 + sub2 + sub3;
    }

    public static void main(String[] args) {
        System.out.println(waysToGetNumber(5));
    }
}
