package com.jenny.algo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Random;

public class KnuthShuffle {
	
	// Players in same team can't be paired/adjacent 
	// O(N) = N + NlogK, N is the length of players, K is the number of teams
	public void randomPair(Player[] players) throws InvalidShuffleException {
		int l = players.length;
		Map<String, List<Player>> teamMap = new HashMap<>();
		for (Player p: players) {
			teamMap.putIfAbsent(p.team, new ArrayList<>());
			teamMap.get(p.team).add(p);
		}
		
		PriorityQueue<List<Player>> pq = new PriorityQueue<>((a, b) -> b.size() - a.size());
		for (String key: teamMap.keySet()) {
			if (teamMap.get(key).size() > (l + 1) / 2) {
				throw new InvalidShuffleException("There will always be adjacent players from same team!");
			}
			Player[] teamPlayersArr = teamMap.get(key).toArray(new Player[teamMap.get(key).size()]); // ArrayList to Array
			shuffle(teamPlayersArr);
			pq.offer(new ArrayList<Player>(Arrays.asList(teamPlayersArr))); // Array to ArrayList
		}
		
		int i = 0;
		while (pq.size() >= 2) {
			List<Player> l1 = pq.poll();
			List<Player> l2 = pq.poll();
            players[i++] = l1.get(0);
            players[i++] = l2.get(0);
            l1.remove(0);
            l2.remove(0);
            if(l1.size() > 0) pq.offer(l1);
            if(l2.size() > 0) pq.offer(l2);
        }
		if (pq.size() > 0) players[i] = pq.poll().get(0);
	}
	
	// Knuth Shuffle 
	// O(N) = N
	public void shuffle(Player[] players) {
		int l = players.length;
		for(int i = 0; i < l; i++){
			Random r = new Random();
			int idx = r.nextInt(l - i) + i;
			Player temp = players[i];
			players[i] = players[idx];
			players[idx] = temp;
		}
	}
	
	public class Player {
		private String name;
		private String team;
		public Player(String name, String team) {
			this.name = name;
			this.team = team;
		}
		public String getName() {
			return this.name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getTeam() {
			return this.team;
		}
		public void setTeam(String team) {
			this.team = team;
		}
	}
	
	public class InvalidShuffleException extends Exception {
		private static final long serialVersionUID = 1L;
		public InvalidShuffleException(String errMsg) {
			super(errMsg);
		}
	}
}
