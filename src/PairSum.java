import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

public class PairSum {

    /*
    Find all pairs with a given sum

    Given two unsorted arrays A of size N and B of size M of distinct elements,
    the task is to find all pairs from both arrays whose sum is equal to X.

    Note: All pairs should be printed in increasing order of u.
    For eg. for two pairs (u1,v1) and (u2,v2), if u1 < u2 then
    (u1,v1) should be printed first else second.

    Example 1:

    Input:
    A[] = {1, 2, 4, 5, 7}
    B[] = {5, 6, 3, 4, 8}
    X = 9
    Output:
            1 8
            4 5
            5 4
    Explanation:
            (1, 8), (4, 5), (5, 4) are the pairs which sum to 9.*/

    class pair  {
        long first, second;
        public pair(long first, long second)
        {
            this.first = first;
            this.second = second;
        }

        @Override
        public String toString(){
            return "("+first+","+second+")";
        }
    }
    public pair[] allPairs( long A[], long B[], long N, long M, long X) {
        // Your code goes here
        Arrays.sort(A);
        Set<Long> bArraySet = new LinkedHashSet<>();
        ArrayList<pair> pairList = new ArrayList<>();
        for( long number : B){
            bArraySet.add(number);
        }
        for( long number : A){
            if(bArraySet.contains(X-number)){
                pairList.add(new pair(number, X-number));
            }
        }
        int counter=0;
        pair[] pairArray = new pair[pairList.size()];
        for(pair pairObject: pairList){
            pairArray[counter++]=pairObject;
        }
        return pairArray;
    }
}
