package com.jenny.yelp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WaitList {
	
	// return the party whose size <= freeTableSize but with longest waiting time
	public Party getTableForFirstAvailableParty(List<Party> waitingList, int freeTableSize) {
		Party result = null;
		for (Party p: waitingList) {
			if (result == null || (p.size <= freeTableSize && p.waitTime > result.waitTime)) {
				result = p;
			}
		}
		return result;
		
//		waitingList.sort((a, b) -> Long.compare(b.waitTime, a.waitTime)); // O(N) = nlogn
//		for (Party p: waitingList) {
//			if (p.size <= freeTableSize) return p;
//		}
//		return null;
	}
	
	// return the party whose size = freeTableSize but with longest waiting time
	public Party getTableForExactSizeParty(List<Party> waitingList, int freeTableSize) {
		
		Party result = null;
		for (Party p: waitingList) {
			if (result == null || (p.size == freeTableSize && p.waitTime > result.waitTime)) {
				result = p;
			}
		}
		return result;
		
//		Map<Integer, List<Party>> map = new HashMap<>();
//		for (Party p: waitingList) {
//			map.putIfAbsent(p.size, new ArrayList<>());
//			map.get(p.size).add(p);
//		}
//		if (map.containsKey(freeTableSize)) {
//			map.get(freeTableSize).sort((a, b) -> Long.compare(b.waitTime, a.waitTime)); // O(N) = klogk, k is the length of freeTableSize list
//			return map.get(freeTableSize).get(0);
//		}
//		return null;
	}
	
	public Party getTableForParty(List<Party> waitingList, int freeTableSize, Strategy strategy) {
		return strategy.getTableForParty(waitingList, freeTableSize);
	}
	
	public interface Strategy {
		public Party getTableForParty(List<Party> waitingList, int freeTableSize);
	}
	
	public class ExactSizeStrategy implements Strategy {

		@Override
		public Party getTableForParty(List<Party> waitingList, int freeTableSize) {
			waitingList.sort((a, b) -> Long.compare(b.waitTime, a.waitTime)); // O(N) = nlogn
			for (Party p: waitingList) {
				if (p.size <= freeTableSize) return p;
			}
			return null;
		}
		
	}
	
	public class LessOrEqualStrategy implements Strategy {

		@Override
		public Party getTableForParty(List<Party> waitingList, int freeTableSize) {
			Map<Integer, List<Party>> map = new HashMap<>();
			for (Party p: waitingList) {
				map.putIfAbsent(p.size, new ArrayList<>());
				map.get(p.size).add(p);
			}
			if (map.containsKey(freeTableSize)) {
				map.get(freeTableSize).sort((a, b) -> Long.compare(b.waitTime, a.waitTime)); // O(N) = klogk, k is the length of freeTableSize list
				return map.get(freeTableSize).get(0);
			}
			return null;
		}
		
	}

	public class Party {
		private String name;
		private int size;
		private long waitTime;
	
		public Party(String name, int size, long waitTime) {
			this.name = name;
			this.size = size;
			this.waitTime = waitTime;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public int getSize() {
			return size;
		}

		public void setSize(int size) {
			this.size = size;
		}

		public long getWaitTime() {
			return waitTime;
		}

		public void setWaitTime(long waitTime) {
			this.waitTime = waitTime;
		}
	}
}
