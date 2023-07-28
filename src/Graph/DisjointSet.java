package Graph;

import java.util.ArrayList;
import java.util.List;

public class DisjointSet {

    List<Integer> rank = new ArrayList<>();
    List<Integer> parent = new ArrayList<>();
    List<Integer> size = new ArrayList<>();

    public DisjointSet(int n) {
        for (int i = 0; i <= n; i++) {
            rank.add(0);
            parent.add(i);
            size.add(1);
        }
    }

    public int findUltimateParent(int node) {
        if (node == parent.get(node)) {
            return node;
        } else {
            int parentOfNode = findUltimateParent(parent.get(node));
            parent.set(node, parentOfNode);
            return parent.get(node);
        }
    }

    //Time Complexity:  The time complexity is O(4) which is very small and close to 1.
    //So, we can consider 4 as a constant.
    public void unionByRank(int u, int v) {
        int ultimateParentOf_U = findUltimateParent(u);
        int ultimateParentOf_V = findUltimateParent(v);
        if (ultimateParentOf_U == ultimateParentOf_V) {
            return;
        }
        if (rank.get(ultimateParentOf_U) < rank.get(ultimateParentOf_V)) {
            parent.set(ultimateParentOf_U, ultimateParentOf_V);
        } else if (rank.get(ultimateParentOf_U) > rank.get(ultimateParentOf_V)) {
            parent.set(ultimateParentOf_V, ultimateParentOf_U);
        } else {
            parent.set(ultimateParentOf_V, ultimateParentOf_U);
            int rankOfUltimateParentOfU = rank.get(ultimateParentOf_U);
            rank.set(ultimateParentOf_U, rankOfUltimateParentOfU + 1);
        }
    }

    //Time Complexity:  The time complexity is O(4) which is very small and close to 1.
    //So, we can consider 4 as a constant.
    public void unionBySize(int u, int v) {
        int ultimateParentOf_U = findUltimateParent(u);
        int ultimateParentOf_V = findUltimateParent(v);
        if (ultimateParentOf_U == ultimateParentOf_V) {
            return;
        }
        int sizeOfUltimateParentOf_U = size.get(ultimateParentOf_U);
        int sizeOfUltimateParentOf_V = size.get(ultimateParentOf_V);
        if (size.get(ultimateParentOf_U) < size.get(ultimateParentOf_V)) {
            parent.set(ultimateParentOf_U, ultimateParentOf_V);
            size.set(ultimateParentOf_V, sizeOfUltimateParentOf_U + sizeOfUltimateParentOf_V);
        } else {
            parent.set(ultimateParentOf_V, ultimateParentOf_U);
            size.set(ultimateParentOf_U, sizeOfUltimateParentOf_U + sizeOfUltimateParentOf_V);
        }
    }

    public static void main(String[] args) {
        System.out.println("Creating DisjointSet via unionByRank...............");
        DisjointSet ds = new DisjointSet(7);
        ds.unionByRank(1,2);
        ds.unionByRank(2,3);
        ds.unionByRank(4,5);
        ds.unionByRank(6,7);
        ds.unionByRank(5,6);
        //let's check if 3 and 7 nodes are connected or not
        if(ds.findUltimateParent(3)== ds.findUltimateParent(7)) {
            System.out.println("Same component");
        } else  {
            System.out.println("Not same component");
        }
        ds.unionByRank(3,7);
        if(ds.findUltimateParent(3)== ds.findUltimateParent(7)) {
            System.out.println("Same component");
        } else  {
            System.out.println("Not same component");
        }

        //Here for only understanding purpose, we are doing unionBySize
        //In actual implementation use either unionBySize or unionByRank, but not both
        //as doing so, we cause different logical implementations to connect components will cause problems in formation of graph

        System.out.println("Creating DisjointSet via unionBySize...............");
        DisjointSet ds1 = new DisjointSet(7);
        ds1.unionBySize(1,2);
        ds1.unionBySize(2,3);
        ds1.unionBySize(4,5);
        ds1.unionBySize(6,7);
        ds1.unionBySize(5,6);
        //let's check if 3 and 7 nodes are connected or not
        if(ds1.findUltimateParent(3)== ds1.findUltimateParent(7)) {
            System.out.println("Same component");
        } else  {
            System.out.println("Not same component");
        }
        ds1.unionBySize(3,7);
        if(ds1.findUltimateParent(3)== ds1.findUltimateParent(7)) {
            System.out.println("Same component");
        } else  {
            System.out.println("Not same component");
        }
    }
}