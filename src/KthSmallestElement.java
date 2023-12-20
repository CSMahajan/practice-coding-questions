// find the kth smallest element in an array
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

public class KthSmallestElement {
    public static int kthSmallest(int[] arr, int k)
    {
        //Your code here
        Set<Integer> s = new TreeSet<>();
        for(int n:arr){
            s.add(n);
        }
        k--;
        Iterator<Integer> i = s.iterator();
        while(k > 0){
            i.next();
            k--;
        }
        return i.next();
        //Arrays.sort(arr);
        //return arr[k-1];
    }

    public static void main(String[] args) {
        int[] arr = {7,10,4,3,20,15};
        System.out.println(kthSmallest(arr, 3));
    }
}