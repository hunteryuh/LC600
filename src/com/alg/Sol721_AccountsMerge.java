package com.alg;

import java.util.*;

/*Given a list accounts, each element accounts[i] is a list of strings, where the first element accounts[i][0] is a name, and the rest of the elements are emails representing emails of the account.

Now, we would like to merge these accounts. Two accounts definitely belong to the same person if there is some email that is common to both accounts. Note that even if two accounts have the same name, they may belong to different people as people could have the same name. A person can have any number of accounts initially, but all of their accounts definitely have the same name.

After merging the accounts, return the accounts in the following format: the first element of each account is the name, and the rest of the elements are emails in sorted order. The accounts themselves can be returned in any order.

Example 1:
Input:
accounts = [["John", "johnsmith@mail.com", "john00@mail.com"], ["John", "johnnybravo@mail.com"], ["John", "johnsmith@mail.com", "john_newyork@mail.com"], ["Mary", "mary@mail.com"]]
Output: [["John", 'john00@mail.com', 'john_newyork@mail.com', 'johnsmith@mail.com'],  ["John", "johnnybravo@mail.com"], ["Mary", "mary@mail.com"]]
Explanation:
The first and third John's are the same person as they have the common email "johnsmith@mail.com".
The second John and Mary are different people as none of their email addresses are used by other accounts.
We could return these lists in any order, for example the answer [['Mary', 'mary@mail.com'], ['John', 'johnnybravo@mail.com'],
['John', 'john00@mail.com', 'john_newyork@mail.com', 'johnsmith@mail.com']] would still be accepted.*/
public class Sol721_AccountsMerge {
    /*Draw an edge between two emails if they occur in the same account.
    The problem comes down to finding connected components of this graph.*/
    public static List<List<String>> accountsMerge(List<List<String>> accounts) {
        Map<String,String> name = new HashMap<>(); // <email, username>
        Map<String,Set<String>> graph = new HashMap<>(); //<email node, neighbor nodes>

        for(List<String> account: accounts){
            String username = account.get(0); // first item is the username
            for(int i = 1; i < account.size(); i++){
                if(!graph.containsKey(account.get(i))){
                    graph.put(account.get(i),new HashSet<>());
                }
                //graph.putIfAbsent(account.get(i),new HashSet<>());
                // exist this email
                name.put(account.get(i), username); // for the name Map
                if ( i == 1) continue;
                //build the "edge" between two adjacent email-nodes
                graph.get(account.get(i)).add(account.get(i-1));
                graph.get(account.get(i-1)).add(account.get(i));
            }
        }
        Set<String> visited = new HashSet<>();
        List<List<String>> res = new LinkedList<>();
        // dfs search the graph
        for(String email: name.keySet()){
            List<String> list = new LinkedList<>();
            if(visited.add(email)){
                dfs(graph,email,visited,list);
                Collections.sort(list);
                list.add(0,name.get(email));
                res.add(list);
            }
        }
        return res;
    }

    private static void dfs(Map<String, Set<String>> graph, String email, Set<String> visited, List<String> list) {
        list.add(email);
        for(String next: graph.get(email)){
            if(visited.add(next)){
                /* if (!visited.contains(next))
                *   visited.add(next);*/
                dfs(graph,next,visited,list);
            }
        }
    }
}
