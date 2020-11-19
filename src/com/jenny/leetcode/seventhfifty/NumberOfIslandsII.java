package com.jenny.leetcode.seventhfifty;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NumberOfIslandsII {
	// Union Find
	public List<Integer> numIslands2(int n, int m, int[][] positions) {
		List<Integer> res = new ArrayList<>();
		int[] roots = new int[m * n];
		Arrays.fill(roots, -1);
		int[][] dirs = new int[][] {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
		int count = 0;
		for (int[] pos: positions) {
			int id = pos[0] * n + pos[1];
			roots[id] = id;
			count++;
			for (int[] d: dirs) {
				int x = pos[0] + d[0], y = pos[1] + d[1], curId = x * n + y;
				if (x < 0 || x >= m || y < 0 || y >= n || roots[curId] == -1) continue;
				int p = findRoot(roots, curId), q = findRoot(roots, id);
				if (p != q) {
					roots[p] = q; // merge to the same island and count--
					count--;
				}
			}
			res.add(count);
		}
		return res;
	}
	
	private int findRoot(int[] roots, int id) {
		while (roots[id] != id) id = roots[id];
		return id;
	}
}
