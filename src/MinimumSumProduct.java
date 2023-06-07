import java.util.Arrays;

public class MinimumSumProduct {

    /*
    Minimize the sum of product

    You are given two arrays, A and B, of equal size N.
    The task is to find the minimum value of A[0] * B[0] + A[1] * B[1] + .... + A[N-1] * B[N-1],
    where shuffling of elements of arrays A and B is allowed.
    Example 1:

    Input:
    N = 3
    A[] = {3, 1, 1}
    B[] = {6, 5, 4}
    Output:
            23
    Explanation:
            1*6+1*5+3*4 = 6+5+12 = 23 is the minimum sum*/

    public long minValue(long a[], long b[], long n)
    {
        // Your code goes here
        Arrays.sort(a);
        Arrays.sort(b);
        long minimumSumOfProduct = 0;
        for(long i=0;i<n;i++)
        {
            minimumSumOfProduct += a[(int)i]*b[(int)(n-i-1)];
        }
        return minimumSumOfProduct;
    }

}
