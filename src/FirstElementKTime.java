import java.util.HashMap;
import java.util.Map;

public class FirstElementKTime {
    /*
    First element to occur k times

    Given an array of N integers. Find the first element that occurs at least K number of times.

    Example 1:

    Input :
    N = 7, K = 2
    A[] = {1, 7, 4, 3, 4, 8, 7}
    Output :
            4
    Explanation:
    Both 7 and 4 occur 2 times.
            But 4 is first that occurs 2 times
    As at index = 4, 4 has occurred
    atleast 2 times whereas at index = 6,7 has occurred atleast 2 times.*/

    public static int firstElementKTime(int[] a, int n, int k) {

        Map<Integer,Integer> map = new HashMap<Integer,Integer>();
        for(int i=0;i<a.length;i++){
            if(map.containsKey(a[i])){
                map.put(a[i],map.get(a[i])+1);
            }
            else{
                map.put(a[i],1);
            }

            if(map.get(a[i])==k){
                return a[i];
            }
        }
        return -1;
    }
}
