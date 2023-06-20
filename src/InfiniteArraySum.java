import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/*
* We have array A of N integers and array B which is infinite times concatenation of A
* We also have Q queries and for each query we have L and R start and end index
* Find the sum of subarray from L to R for each query
* Example:
* Input: A= {5,2,6,9}
* Q = 3
* List of Q queries = [[1,5],[10,13],[7,11]]
* here L = 1, R = 5 for first query and so on
* Output : [27,22,28]
* Explanation: for first query, L = 1, R = 5, for sum of array from A[1] to A[5] (1-based indexing) which gives
* 5+2+6+9+5 = 27, for second query, 2+6+9+5=22 and so on..
* */
public class InfiniteArraySum {

    //below code does not work for very large sum
    public static List<Integer> sumInRanges(int[] arr, int n, List<List<Long>> queries, int q) {
        // Write your code here!
        int mod = 1000000007;
        ArrayList<Integer> list = new ArrayList<>();
        for(int i = 0; i < q; i++){
            Integer sum = 0;
            List<Long> query = queries.get(i);
            Long L = query.get(0);
            Long R = query.get(1);
            for(Long j = L; j <= R; j++) {
                sum += arr[(int)(j%mod-1)%n]%mod;
            }
            list.add(sum);
        }
        return list;
    }

    public static void main(String[] args) {
        int arr[] = {13288,59470,76228,76097,7207,23723,90900,92571,84929,69554,64330,98202,23297,8406,22442,10585,78245,72156,21483,27979,32061,27487,83545,5010,61999,1006,23924,17384,54646,36076,49968,86073,54335,66160,22932,58311,47498,35107,29403,4165,96879,34756,19605,12733,58198,38279,16839,31882,22430,32145,47943,82526,55159,15678,99873,27582,3368,97596,61045,95649,70665,73389,44566,53364,67626,4329,51948,71992,82539,67866,87443,42083,77410,29821,99002,26139,83992,53320,38981,74142,32844,66974,80556,96253,92110,69949,68057,41276,55781,72945,48974,39796,58154,81502,73149,34290,77387,81466,27054,15317,23401,7599,75132};
        int n = arr.length;
        int q = 10;
        Scanner input = new Scanner(System.in);
        List<List<Long>> queries = new ArrayList<>();
        for(int i = 0; i < q; i++) {
            System.out.println("Enter start and end index for " + i + "th query");
            ArrayList<Long> list = new ArrayList<>();
            Long num1 = input.nextLong();
            Long num2 = input.nextLong();
            list.add(num1);
            list.add(num2);
            queries.add(list);
        }
        System.out.println(sumInRanges(arr,n,queries,q));
    }
}
