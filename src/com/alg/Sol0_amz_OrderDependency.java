package com.alg;

import java.util.*;

/**
 * Created by HAU on 1/6/2018.
 */
/*题目大概内容是这样的，比如赵兄托你帮我办点事，那么这个顺序就是赵兄先托你，然后你再帮我办事，事情讲究个先来后到。输入的是一群OrderDependency的object，输出是Order的List。类似于这样[(A, B), (A, C), (B, C)]，意思每一对里，A的生产依赖于B，B就必须得先造出来，最后让你返回顺序[C, B ,A]。Order就是顾名思义，命令，里面就一个String类型的内容，而且这题据说test case一共就4个。
地里有人说，其实就是Leetcode 210题，只不过lc上的数据是数字，oa上的是string而已，思路做法是一样的，不过要多做一点预处理。

解决思路
首先用脚后跟想想，这题必须是topological sorting。我个人非常偏向使用BFS，剥洋葱法。那么就按照这个思路来想，每个order都用map记录入度，还有个map记录这个order指向多少个order。
另外再用一个set来记录出现过的order，用来判断最后是否有环。
至于map之外的数据结构，没想出来，因为order这个object不像数字可以用个数组啥的，这种也只能用map了吧。
https://www.jianshu.com/p/deceb6173865*/
public class Sol0_amz_OrderDependency {
    static class Order{
        String order = "";
        public Order(String string){
            this.order = string;
        }
    }
    static class Dependency{
        Order cur;
        Order pre;
        public Dependency(Order cur, Order pre){
            this.cur = cur;
            this.pre = pre;
        }
    }

    public static List<Order> getOrderList(List<Dependency> list) {
        List<Order> result = new ArrayList<>();
        if (list == null || list.size() == 0) return result;
        int totalOrder = 0;
        //建两个map,第一个是当前的order指向多少个order,就是先决条件
        Map<Order, ArrayList<Order>> map = new HashMap<>();
        //第二个是当前order被多少个order指,即为入度
        Map<Order,Integer> inMap = new HashMap<>();

        for(Dependency dep: list){
            Order cur = dep.cur;
            Order pre = dep.pre;
            //add to adjList
            if(map.containsKey(pre)){
                map.get(pre).add(cur);
            }else{
                map.put(pre, new ArrayList<>());
                map.get(pre).add(cur);
            }
            // add to in-degree map
            if(inMap.containsKey(cur)){
                inMap.put(cur,inMap.get(cur) + 1);
            }else{
                inMap.put(cur,1);
            }
            //这里入度也要把pre加上,因为最后要找线头,就是入度为0的点。
            if (!inMap.containsKey(pre)){
                inMap.put(pre, 0);
            }

        }

        Queue<Order> q = new LinkedList<>();


        for( Order od : inMap.keySet()){
            if(inMap.get(od) == 0){
                q.add(od);
            }
        }
        //BFS
        while(!q.isEmpty()){
            Order order = q.poll();
            inMap.remove(order);
            result.add(order);
            if(map.containsKey(order)){
                for(Order od: map.get(order)){
                    if(inMap.containsKey(od)){
                        inMap.put(od,inMap.get(od) - 1);
                    }
                    if(inMap.get(od) == 0){
                        q.add(od);
                    }
                }
            }
        }
        if(inMap.size() != 0){
            return null; //有环 ，没有入度为0的点
        }
        return result;

    }
    public static void main(String[] args) {
        Order o1 = new Order("A");
        Order o2 = new Order("B");
        Order o3 = new Order("C");
        Order o4 = new Order("D");
        Dependency od1 = new Dependency(o1, o2);
        Dependency od2 = new Dependency(o2, o3);
        //成环的情况就是把o4，改成o2，看看输出。
        Dependency od3 = new Dependency(o3, o4);
        List<Dependency> list = new ArrayList<>();
        list.add(od1);
        list.add(od2);
        list.add(od3);

        for (Order o : getOrderList(list)){
            System.out.println(o.order);
        }
    }
}
