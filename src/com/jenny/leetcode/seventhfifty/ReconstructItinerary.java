package com.jenny.leetcode.seventhfifty;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Stack;

public class ReconstructItinerary {
	// Time complexity: O(N)=V+E
    public List<String> findItinerary(List<List<String>> tickets) {
        Map<String, PriorityQueue<String>> map = new HashMap<>();
        for (List<String> t: tickets) { // E is the length of tickets(edges)
            map.putIfAbsent(t.get(0), new PriorityQueue<>((a, b) -> a.compareTo(b)));
            map.get(t.get(0)).offer(t.get(1));
        }
        
        Stack<String> stack = new Stack<>();
        stack.push("JFK");
        List<String> res = new ArrayList<>();
        while (!stack.isEmpty()) { // V is the number of cities(vertices)
            String orig = stack.peek();
            if (!map.containsKey(orig) || map.get(orig).isEmpty()) {
                res.add(0, stack.pop());
            } else {
                stack.push(map.get(orig).poll());
            }
        }
        return res;
    }
}
