import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.Collectors;

/*
Subset Sums

Given a list arr of N integers, print sums of all subsets in it.



Example 1:

Input:
N = 2
arr[] = {2, 3}
Output:
0 2 3 5
Explanation:
When no elements is taken then Sum = 0.
When only 2 is taken then Sum = 2.
When only 3 is taken then Sum = 3.
When element 2 and 3 are taken then
Sum = 2+3 = 5.
Example 2:

Input:
N = 3
arr = {5, 2, 1}
Output:
0 1 2 3 5 6 7 8
*/
public class SubsetSum {

    //TC: O(2^N + 2^N * log(2^N))
    //Here in time complexity, first 2^N is due to recursive calls for all possible combinations
    //i.e.for N elements there are 2^N possibilities
    //Second 2^n * log(2^N) is used for sorting the elements as we are sorting 2^N sum elements
    //in list derived from N elements of array
    //SC: O(2^N)
    //Space required to store 2^N elements
    public static ArrayList<Integer> subsetSums(ArrayList<Integer> arr, int N) {
        // code here
        ArrayList<Integer> sumList = new ArrayList<>();
        getAllSubsetSums(0, 0, arr, N, sumList);
        //Sorting due to increasing order requirement in output
        Collections.sort(sumList);
        return sumList;
    }

    private static void getAllSubsetSums(int index, int sum, ArrayList<Integer> arr, int n, ArrayList<Integer> sumList) {
        if (index == n) {
            //If index reaches out of given array/arraylist size then add the calculated sum till now
            //and return to other recursive calls
            sumList.add(sum);
            return;
        }
        //Case for picking up current index element and moving to next element, sum also gets increased due to picking up current element
        getAllSubsetSums(index + 1, sum + arr.get(index), arr, n, sumList);
        //Case for not picking up current index element and moving to next element, sum remains as it is due to not picking up current element
        getAllSubsetSums(index + 1, sum, arr, n, sumList);
    }

    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(2);
        list.add(3);
        int n = 2;
        System.out.println(subsetSums(list, n));
    }
}
