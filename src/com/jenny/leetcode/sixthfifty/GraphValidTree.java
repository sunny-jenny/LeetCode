package com.jenny.leetcode.sixthfifty;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/* 
Given n nodes labeled from 0 to n-1 and a list of undirected edges (each edge is a pair of nodes), write a function to check whether these edges make up a valid tree.

Example 1:
Input: n = 5, and edges = [[0,1], [0,2], [0,3], [1,4]]
Output: true

Example 2:
Input: n = 5, and edges = [[0,1], [1,2], [2,3], [1,3], [1,4]]
Output: false

Note: you can assume that no duplicate edges will appear in edges. Since all edges are undirected, [0,1] is the same as [1,0] and thus will not appear together in edges.
*/
public class GraphValidTree {
	// BFS
	public boolean validTree(int n, int[][] edges) {
		Map<Integer, Set<Integer>> graph = new HashMap<>();
		for (int i = 0; i < edges.length; i++) {
			graph.putIfAbsent(edges[i][0], new HashSet<>());
			graph.putIfAbsent(edges[i][1], new HashSet<>());
			graph.get(edges[i][0]).add(edges[i][1]);
			graph.get(edges[i][1]).add(edges[i][0]);
		}
		
		Queue<Integer> queue = new LinkedList<>();
		Set<Integer> visited = new HashSet<>();
		queue.offer(0);
		visited.add(0);
		while (!queue.isEmpty()) {
			int node = queue.poll();
			if (!graph.get(node).isEmpty()) {
				for (int neighbor: graph.get(node)) {
					if (visited.contains(neighbor)) return false;
					visited.add(neighbor);
					graph.get(neighbor).remove(node);
					queue.add(neighbor);
				}
			}
		}
		return visited.size() == n;
	}
	
	// Union Find
//	public boolean validTree(int n, int[][] edges) {
//		int[] roots = new int[n];
//		Arrays.fill(roots, -1);
//		for (int[] edge: edges) {
//			int x = findRoot(roots, edge[0]);
//			int y = findRoot(roots, edge[1]);
//			if (x == y) return false;
//			roots[x] = y;
//		}
//		return edges.length == n - 1;
//	}
//	
//	private int findRoot(int[] roots, int i) {
//		while (roots[i] != -1) i = roots[i];
//		return i;
//	}
}
