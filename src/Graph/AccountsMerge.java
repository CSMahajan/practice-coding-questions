package Graph;

import java.util.*;

/*
Accounts Merge

Given a list of accounts where each element accounts[i] is a list of strings,
where the first element accounts[i][0] is a name,
and the rest of the elements are emails representing emails of the account.
Now, we would like to merge these accounts.
Two accounts definitely belong to the same person if there is some common email to both accounts.
Note that even if two accounts have the same name, they may belong to different people as people could have the same name.
A person can have any number of accounts initially, but all of their accounts definitely have the same name.
After merging the accounts, return the accounts in the following format: the first element of each account is the name,
and the rest of the elements are emails in sorted order.
The accounts themselves can be returned in any order.
Example 1:
Input:
accounts = [["John","johnsmith@mail.com","john_newyork@mail.com"],["John","johnsmith@mail.com","john00@mail.com"],
["Mary","mary@mail.com"],["John","johnnybravo@mail.com"]]
Output:
[["John","john00@mail.com","john_newyork@mail.com","johnsmith@mail.com"],["Mary","mary@mail.com"],
["John","johnnybravo@mail.com"]]
Explanation:
The first and second John's are the same person as they have the common email "johnsmith@mail.com".
The third John and Mary are different people as none of their email addresses are used by other accounts.
We could return these lists in any order, for example the answer [['Mary', 'mary@mail.com'], ['John', 'johnnybravo@mail.com'],
['John', 'john00@mail.com', 'john_newyork@mail.com', 'johnsmith@mail.com']] would still be accepted.
Example 2:
Input:
accounts = [["Gabe","Gabe0@m.co","Gabe3@m.co","Gabe1@m.co"],["Kevin","Kevin3@m.co","Kevin5@m.co","Kevin0@m.co"],
["Ethan","Ethan5@m.co","Ethan4@m.co","Ethan0@m.co"],["Hanzo","Hanzo3@m.co","Hanzo1@m.co","Hanzo0@m.co"],
["Fern","Fern5@m.co","Fern1@m.co","Fern0@m.co"]]
Output:
[["Ethan","Ethan0@m.co","Ethan4@m.co","Ethan5@m.co"],["Gabe","Gabe0@m.co","Gabe1@m.co","Gabe3@m.co"],
["Hanzo","Hanzo0@m.co","Hanzo1@m.co","Hanzo3@m.co"],["Kevin","Kevin0@m.co","Kevin3@m.co","Kevin5@m.co"],
["Fern","Fern0@m.co","Fern1@m.co","Fern5@m.co"]]
*/
public class AccountsMerge {

    static class DisjointSet {
        List<Integer> parent = new ArrayList<>();
        List<Integer> size = new ArrayList<>();

        public DisjointSet(int n) {
            for (int i = 0; i <= n; i++) {
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
    }

    //Time Complexity: O(N+E) + O(E*4*alpha) + O(N*(E*log(E) + E)) where N = no. of indices or nodes and E = no. of emails.
    //The first term is for visiting all the emails. The second term is for merging the accounts.
    //And the third term is for sorting the emails and storing them in the answer array.
    //Space Complexity: O(N)+ O(N) +O(2N) ~ O(N) where N = no. of nodes/indices.
    //The first and second space is for the ‘mergedMail’ and the 'ans' array.
    //The last term is for the parent and size array used inside the Disjoint set data structure.
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        int n = accounts.size();
        DisjointSet ds = new DisjointSet(n);
        HashMap<String, Integer> mailNodeMap = new HashMap<>();
        for (int i = 0; i < n; i++) {
            //starting j loop from 1 as first value in every list contains the name and not the email address
            for (int j = 1; j < accounts.get(i).size(); j++) {
                String mail = accounts.get(i).get(j);
                if (!mailNodeMap.containsKey(mail)) {
                    //Mail is new, so need to bind it to the i-th node
                    mailNodeMap.put(mail, i);
                } else {
                    //Mail is already present (already mapped to other node), need to merge this node with previously present node
                    ds.unionBySize(mailNodeMap.get(mail), i);
                }
            }
        }
        List<List<String>> mergedMail = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            mergedMail.add(new ArrayList<>());
        }
        //below map for loop is used for adding all the email to the ultimate parent of their present nodes instead of present nodes(which was till now)
        //map entry tuples contain mail as the key and node as the value
        for (Map.Entry<String, Integer> mailEntry : mailNodeMap.entrySet()) {
            String mail = mailEntry.getKey();
            //find the ultimate parent of the node of the current mail using the map entries that we had created
            int node = ds.findUltimateParent(mailEntry.getValue());
            mergedMail.get(node).add(mail);
        }
        //Below for loop is used for creating output in sorted list starting with name in every mail at first position of every list
        List<List<String>> sortedMailAccounts = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            List<String> listOfMails = mergedMail.get(i);
            if (listOfMails.isEmpty()) {
                //this means this node email has been merged with other node, so this node became empty
                continue;
            }
            //sorting list in ascending order
            Collections.sort(listOfMails);
            List<String> temp = new ArrayList<>();
            //getting the name of the email address from the given mail accounts list of string
            //basically appending the name before for the sake of output returning
            temp.add(accounts.get(i).get(0));
            //adding(appending) all the mails corresponding to that name
            temp.addAll(listOfMails);
            sortedMailAccounts.add(temp);
        }
        return sortedMailAccounts;
    }

    public static void main(String[] args) {
        List<String> mail1 = Arrays.asList("John", "johnsmith@mail.com", "john_newyork@mail.com");
        List<String> mail2 = Arrays.asList("John", "johnsmith@mail.com", "john00@mail.com");
        List<String> mail3 = Arrays.asList("Mary", "mary@mail.com");
        List<String> mail4 = Arrays.asList("John", "johnnybravo@mail.com");
        List<List<String>> accounts = new ArrayList<>();
        accounts.add(mail1);
        accounts.add(mail2);
        accounts.add(mail3);
        accounts.add(mail4);
        AccountsMerge am = new AccountsMerge();
        System.out.println(am.accountsMerge(accounts));
    }
}