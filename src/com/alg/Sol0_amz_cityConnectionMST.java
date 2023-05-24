package com.alg;

import java.util.*;

/**
 * Created by HAU on 1/10/2018.
 */
/*题目内容是这样的，给十几个城市供电，连接不同城市的花费不同，让花费最小同时连到所有的边。给出一系列connection类，里面是edge两端的城市名和它们之间的一个cost，找出要你挑一些边，把所有城市连接起来并且总花费最小。不能有环，最后所以城市要连成一个连通块。
不能的话输出空表，最后还要按城市名字排序输出，按照node1来排序,如果一样的话再排node2。
输入:
{"Acity","Bcity",1}
("Acity","Ccity",2}
("Bcity","Ccity",3}
输出：
("Acity","Bcity",1}
("Acity","Ccity",2}*/
/*最⼩小⽣生成树的概念是在⼀一个⽆无向图中，找出⼀一些边，组成⼀一棵树，使得这些边的权值
加起来最⼩小。
最⼩小⽣生成树是⼀一类问题，有很多解决⽅方法。这⾥里里我们介绍Kruskal算法。
Kruskal的逻辑⾮非常简单。⾸首先按照每条边的边权对边进⾏行行从⼩小到⼤大排序。然后依次判
断每条边是否属于最⼩小⽣生成树。下⾯面是具体步骤：
判断边的两个端点是否都已经在最⼩小⽣生成树中了了，如果在，说明连这条边就会形成⼀一
个环，所以就不不能连这条边。如果⾄至少有⼀一⽅方不不在最⼩小⽣生成树中，就把这条边加⼊入最
⼩小⽣生成树中。
判断两个端点是否在⼀一个集合的⽅方法叫做并查集。
并查集的具体做法⻅见下⾯面博客：
http://blog.csdn.net/dm_vincent/article/details/7655764*/
public class Sol0_amz_cityConnectionMST {
    static class Connection {
        String node1;
        String node2;
        int cost;

        public Connection(String a, String b, int c) {
            node1 = a;
            node2 = b;
            cost = c;
        }
    }
    private static int unionNum;
    // time complexity O(ElogE) , due to the sorting of all edges
    public static ArrayList<Connection> getLowCost(ArrayList<Connection> connections) {
        if (connections == null || connections.size() == 0)
            return new ArrayList<>();
        ArrayList<Connection> res = new ArrayList<>();
        Map<String, Integer> map = new HashMap<>();
        // put smallest cost at front
        Collections.sort(connections, new Comparator<Connection>() {
            @Override
            public int compare(Connection o1, Connection o2) {
                return o1.cost - o2.cost;
            }
        });

        unionNum = 0;
        for(Connection c: connections){
            String a = c.node1;
            String b = c.node2;
            if (union(map,a,b)){
                res.add(c);
            }
        }
        String find = connections.get(0).node1;
        int union = map.get(find);
        for(String str: map.keySet()){
            if(map.get(str) != union){
                return new ArrayList<>();
            }
        }
        // have the city in the order of name
        Collections.sort(res, new Comparator<Connection>() {
            @Override
            public int compare(Connection o1, Connection o2) {
                if(o1.node1.equals(o2.node1)){
                    return o1.node2.compareTo(o2.node2);
                };
                return o1.node1.compareTo(o2.node1);
            }
        });
        return res;


    }
    public static void main(String[] args) {
        ArrayList<Connection> connections = new ArrayList<>();
        //下面的图是个苯环，中间加上了几根，如果想验证空表，去掉几根线就行。
        connections.add(new Connection("A", "B", 6));
        connections.add(new Connection("B", "C", 4));
        connections.add(new Connection("C", "D", 5));
        connections.add(new Connection("D", "E", 8));
        connections.add(new Connection("E", "F", 2));
        connections.add(new Connection("B", "F", 10));
        connections.add(new Connection("E", "C", 9));
        connections.add(new Connection("F", "C", 7));
        connections.add(new Connection("B", "E", 3));
        connections.add(new Connection("A", "F", 16));
        //这里就输出一下看看结果。
        ArrayList<Connection> res = getLowCost(connections);
        for (Connection c : res) {
            System.out.println(c.node1 + " -> " + c.node2 + " " + c.cost);
        }
    }

    private static boolean union(Map<String, Integer> map, String a, String b) {
        //两个城市都没有城市代号（都存在于单独的集合，都没有被合并过）
        if(!map.containsKey(a) && !map.containsKey(b)){
            map.put(a,unionNum);
            map.put(b,unionNum);
            unionNum++;
            return true;
        }
        // 有一个城市有代号（说明其中有一个是之前合并过的）
        if(map.containsKey(a) && !map.containsKey(b)){
            int aU = map.get(a);
            map.put(b,aU);
            return true;
        }
        if(map.containsKey(b) && !map.containsKey(a)){
            int bU = map.get(b);
            map.put(a,bU);
            return true;
        }
        // if two already in check unionNun
        //两个都有代号（那么合并它们分别所在的集合中的所有城市）
        // i.e. map.containsKey(a) and map.containsKey(b)
        int aU = map.get(a);
        int bU = map.get(b);
        if( aU == bU) return false; // loop false
        for(String s: map.keySet()){
            if(map.get(s) == bU){
                map.put(s,aU);
            }
        }
        return true;
    }
    /**
     * Time: O(ElogE + E) 后面的 "+E"是在union函数中，当两个城市都有代号的时候
     * Space: O(E)
     */


}
