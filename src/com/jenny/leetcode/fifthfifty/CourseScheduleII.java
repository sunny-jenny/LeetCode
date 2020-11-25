package com.jenny.leetcode.fifthfifty;

import java.util.LinkedList;
import java.util.Queue;

public class CourseScheduleII {
	public int[] findOrder(int numCourses, int[][] prerequisites) {
        int[] res = new int[numCourses];
        int[] pCount = new int[numCourses]; // number of prerequisites for each course
        for (int[] p: prerequisites) {
            pCount[p[0]]++;
        }
        
        //store courses that have no prerequisites
        Queue<Integer> queue = new LinkedList<>();
        int index = 0;
        for (int i = 0; i < numCourses; i++) {
            if (pCount[i] == 0) {
                queue.offer(i);
                res[index++] = i; 
            }
        }
        
        // BFS:
        while (!queue.isEmpty()) {
            int course = queue.poll();
            for (int[] p: prerequisites) {
                if (p[1] == course) {
                    pCount[p[0]]--;
                    if (pCount[p[0]] == 0) {
                        queue.offer(p[0]);
                        if (index < numCourses) res[index++] = p[0]; 
                    }
                }
            }
        }
        return index == numCourses ? res : new int[0];
    }
}
