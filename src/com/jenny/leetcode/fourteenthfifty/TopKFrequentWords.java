package com.jenny.leetcode.fourteenthfifty;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class TopKFrequentWords {
	public List<String> topKFrequent(String[] words, int k) {
        Map<String, Integer> map = new HashMap<>();
        for (String w: words) {
        	map.put(w, map.getOrDefault(w, 0) + 1);
        }
        // minHeap O(N) = NlogK
        PriorityQueue<String> pq = new PriorityQueue<>((a, b) -> map.get(a) != map.get(b) ? map.get(a) - map.get(b) : b.compareTo(a));
        for (String key: map.keySet()) {
        	if (pq.size() < k || map.get(key) >= map.get(pq.peek())) {
        		pq.offer(key);
        	}
        	if (pq.size() > k) {
        		pq.poll();
        	}
        }
        
        List<String> res = new ArrayList<>();
        while (!pq.isEmpty()) {
        	res.add(0, pq.poll());
        }
        return res;
    }
}
