package com.jenny.leetcode.fifthfifty;

import java.util.LinkedList;
import java.util.Queue;

public class CourseSchedule {
	public boolean canFinish(int numCourses, int[][] prerequisites) {
        int[] pCount = new int[numCourses]; // number of prerequisites for each course
        for (int[] p: prerequisites) {
            pCount[p[0]]++;
        }
        
        //store courses that have no prerequisites
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (pCount[i] == 0) {
                queue.offer(i);
            }
        }
        
        // BFS:
        int numOfNoPre = queue.size(); // number of courses that have no prerequisites
        while (!queue.isEmpty()) {
            int course = queue.poll();
            for (int[] p: prerequisites) {
                if (p[1] == course) {
                    pCount[p[0]]--;
                    if (pCount[p[0]] == 0) {
                        numOfNoPre++;
                        queue.offer(p[0]);
                    }
                }
            }
        }
        return numOfNoPre == numCourses;
	}
}
